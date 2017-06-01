package code;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import bsh.EvalError;
import bsh.Interpreter;
import control.Configuration;
import game.Criterion;
import game.Test;

//Asynchronous Code Executor - Necessary and Irritating Twin of Arbitrary
public class InteractionCodeRunner implements CodeRunner
{
	private Interpreter interpreter;
	private ByteArrayOutputStream baos;
	private CodeInputStream codeInputStream;
	private InputStreamReader codeStreamReader;
	private PrintStream printStreamStandard;
	private PrintStream printStreamError;
	private String codeLocal;
	private String codeError;
	private String codeResult;
	private boolean hasRun;
	private boolean hasFailed;
	
	public InteractionCodeRunner(String code)
	{
		baos = new ByteArrayOutputStream();
		printStreamStandard = new PrintStream(baos);
		printStreamError = new PrintStream(baos);
		codeInputStream = new CodeInputStream();
		codeStreamReader = new InputStreamReader(codeInputStream);
		
		interpreter = new Interpreter(codeStreamReader, printStreamStandard, printStreamError, true);
		
		codeResult = "";
		codeError = "";
		
		codeLocal = code;
		codeError = null;
		codeResult = null;
		hasRun = false;
		hasFailed = false;
	}
	
	//Runs Regardless of Options
	public void run()
	{
		try
		{
			System.setOut(printStreamStandard);
			System.setIn(codeInputStream);
			//interpreter.set("context", this);
			//interpreter.set("bsh.interactive", true);
			interpreter.eval(codeLocal);
		}
		catch (Exception e)
		{
			hasFailed = true;
			//hasRun = true;
			codeError += e.getLocalizedMessage();
			//TODO: Find how to grab outputs
		}
		finally
		{
			codeResult = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			//System.setIn(Configuration.DEFAULT_INPUT_STREAM);
			//System.setOut(Configuration.DEFAULT_PRINT_STREAM);
			//System.out.println(codeResult + "-" + (codeResult.equals("")));
		}
	}
	
	//Runs Given Some Variable Option
	public boolean runGiven(Test test, int index)
	{
		try 
		{
			System.setOut(printStreamStandard);
			interpreter.set("context", this);
			
			switch(test.testTypeLocal)
			{
			case NONE:
				interpreter.set(test.getVariable(), null);
				break;
				
			case INT:
				interpreter.set(test.getVariable(), test.getValuesInt()[index]);
				break;
				
			case BOOL:
				interpreter.set(test.getVariable(), test.getValuesBoolean()[index]);
				break;
				
			case STR:
				interpreter.set(test.getVariable(), test.getValuesString()[index]);
				break;
			}
			
			interpreter.eval(codeLocal);
		}
		catch (Exception e)
		{
			hasFailed = true;
			hasRun = true;
			codeError = e.getLocalizedMessage();
		}
		finally
		{
			codeResult = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			System.setOut(Configuration.DEFAULT_PRINT_STREAM);
			hasRun = true;
		}
		
		return hasRun && !hasFailed;
	}
	
	//Runs Given an Option, Packaging a Criterion
	public Criterion runGivenAndGet(Test test, int index)
	{
		System.out.println("Hi");
		try 
		{
			System.setOut(printStreamStandard);
			interpreter.set("context", this);
			
			switch(test.testTypeLocal)
			{
			case NONE:
				interpreter.set(test.getVariable(), null);
				break;
				
			case INT:
				interpreter.set(test.getVariable(), test.getValuesInt()[index]);
				break;
				
			case BOOL:
				interpreter.set(test.getVariable(), test.getValuesBoolean()[index]);
				break;
				
			case STR:
				interpreter.set(test.getVariable(), test.getValuesString()[index]);
				break;
			}
			
			interpreter.eval(codeLocal);
		}
		catch (Exception e)
		{
			hasFailed = true;
			hasRun = true;
			codeError = e.getLocalizedMessage();
		}
		finally
		{
			codeResult = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			System.setOut(Configuration.DEFAULT_PRINT_STREAM);
			hasRun = true;
		}
		
		try
		{
			switch(test.testTypeLocal)
			{
			case NONE:
				return new Criterion(codeResult == null ? codeError : codeResult);
			
			case INT:
				return new Criterion(((Integer) interpreter.get(test.getVariable())).intValue(), test.getVariable());
			
			case BOOL:
				return new Criterion(((Boolean) interpreter.get(test.getVariable())).booleanValue(), test.getVariable());
			
			case STR:
				return new Criterion((String) interpreter.get(test.getVariable()), test.getVariable());
			
			default:
				return new Criterion("");
			}
		}
		catch(EvalError ee)
		{
			return new Criterion(codeResult == null ? codeError : codeResult);
		}
	}
	
	//Passes along new Data
	public void update(String newData)
	{
		codeInputStream.patch(newData);
	}
	
	//Closes Asynchronous Streaming
	public void setRun()
	{
		hasRun = true;
		System.setIn(Configuration.DEFAULT_INPUT_STREAM);
		System.setOut(Configuration.DEFAULT_PRINT_STREAM);
	}
	
	public boolean hasRun()
	{
		return hasRun;
	}
	
	public boolean hasFailed()
	{
		return hasFailed;
	}
	
	public ByteArrayOutputStream getByteArray()
	{
		return baos;
	}
	
	public String getResultMessage()
	{
		return codeResult;
	}
	
	public String getFailureMessage()
	{
		return codeError;
	}
	
	public String getOriginCode()
	{
		return codeLocal;
	}
	
	//Clones Based on codeLocal
	public CodeRunner clone()
	{
		return new InteractionCodeRunner(getOriginCode());
	}
}
