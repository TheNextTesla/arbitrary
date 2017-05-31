package code;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import bsh.Interpreter;
import control.Configuration;

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
}