package code;

import java.nio.charset.StandardCharsets;

import control.Configuration;
import display.ResponseFrame;

public class MonitorThread extends Thread
{
	private CodeRunner codeRunner;
	private ResponseFrame responseFrame;
	private String totalICRData;
	private String lastICRData;
	
	public MonitorThread(CodeRunner codeRunner, ResponseFrame responseFrame)
	{
		this.codeRunner = codeRunner;
		this.responseFrame = responseFrame;
		lastICRData = null;
		totalICRData = "";
	}
	
	public void run()
	{
		codeRunner.run();
		lastICRData = "";
		
		do
		{
			String userResponse = responseFrame.getUserPanel().getUserResponse();
			
			if(!userResponse.equals(""))
			{
				totalICRData += userResponse;
				codeRunner.update(userResponse);
				responseFrame.getUserPanel().updateUserInterface(totalICRData);
			}
			
			else if(!lastICRData.equals(new String(codeRunner.getByteArray().toByteArray(), StandardCharsets.UTF_8)))
			{
				lastICRData = new String(codeRunner.getByteArray().toByteArray(), StandardCharsets.UTF_8);
				totalICRData += lastICRData;
				responseFrame.getUserPanel().updateUserInterface(totalICRData);
			}
			
			else
			{
				try
				{
					Thread.sleep(Configuration.TICK_TIME);
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
			
		}
		while(!codeRunner.hasRun() && !responseFrame.isClosed());
		
		responseFrame.getUserPanel().finalize();
	}
}
