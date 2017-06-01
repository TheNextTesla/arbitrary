package code;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import bsh.EvalError;
import bsh.Interpreter;
import control.Configuration;
import game.Criterion;
import game.Test;

public class ArbitraryCodeRunner implements CodeRunner
{
	private Interpreter interpreter;
	private ByteArrayOutputStream baos;
	private PrintStream printStream;
	private String codeLocal;
	private String codeError;
	private String codeResult;
	private boolean hasRun;
	private boolean hasFailed;
	
	public ArbitraryCodeRunner(String code)
	{
		interpreter = new Interpreter();
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		
		codeLocal = code;
		codeError = null;
		codeResult = null;
		hasRun = false;
		hasFailed = false;
	}
	
	public void run()
	{
		try 
		{
			System.setOut(printStream);
			interpreter.set("context", this);
			interpreter.eval(codeLocal);
		}
		catch (Exception e)
		{
			hasFailed = true;
			hasRun = true;
			codeError = e.getLocalizedMessage();
			//TODO: Find how to grab outputs
		}
		finally
		{
			codeResult = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			System.setOut(Configuration.DEFAULT_PRINT_STREAM);
			hasRun = true;
		}
	}
	
	public boolean runGiven(Test test, int index)
	{
		try 
		{
			System.setOut(printStream);
			interpreter.set("context", this);
			
			switch(test.testTypeLocal)
			{
			case NONE:
				//interpreter.set(test.getVariable(), null);
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
	
	public Criterion runGivenAndGet(Test test, int index)
	{
		try 
		{
			System.setOut(printStream);
			interpreter.set("context", this);
			
			switch(test.testTypeLocal)
			{
			case NONE:
				//interpreter.set(test.getVariable(), null);
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
				return new Criterion(new String(getByteArray().toByteArray(), StandardCharsets.UTF_8));
			
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
			ee.printStackTrace();
			return new Criterion(codeResult == null ? codeError : codeResult);
		}
	}
	
	public boolean hasRun()
	{
		return hasRun;
	}
	
	public boolean hasFailed()
	{
		return hasFailed;
	}
	
	public void update(String userResponse)
	{
		return;
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
	
	public CodeRunner clone()
	{
		return new ArbitraryCodeRunner(getOriginCode());
	}
}
