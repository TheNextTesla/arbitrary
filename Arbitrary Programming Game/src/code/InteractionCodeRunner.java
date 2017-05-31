package code;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import bsh.Interpreter;
import control.Configuration;

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
	
	public void update(String newData)
	{
		codeInputStream.patch(newData);
	}
	
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
}
