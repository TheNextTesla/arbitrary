package code;

import display.ResponseFrame;
import game.Criterion;
import game.Test;

public class ImplementThread extends Thread
{
	private CodeRunner[] codeRunners;
	private ResponseFrame responseFrame;

	private Test testsToRun;
	private Criterion[] outputsToSee;
	private boolean[] resultsOfCriterionTests;
	private boolean completed;
	private int localIndex;
	private String sendStore;
	
	public ImplementThread(CodeRunner codeRunner, ResponseFrame responseFrame, Test inputs, Criterion[] outputs)
	{
		int localIndex = outputs.length;
		codeRunners = new CodeRunner[localIndex];
		resultsOfCriterionTests = new boolean[localIndex];
		for(int i = 0; i < localIndex; i++)
		{
			codeRunners[i] = codeRunner.clone();
		}
		
		this.responseFrame = responseFrame;
		testsToRun = inputs;
		outputsToSee = outputs;
		completed = false;
		sendStore = "";
	}
	
	public void run()
	{
		
		while(localIndex == 0 && !completed)
		{
			Criterion tempOutput = codeRunners[localIndex].runGivenAndGet(testsToRun, localIndex);
			resultsOfCriterionTests[localIndex] = outputsToSee[localIndex].equals(tempOutput);
			//responseFrame.getUserPanel().updateUserInterface("Hi");
			sendStore += "Test " + localIndex + ": " + (resultsOfCriterionTests[localIndex] ? "Pass\n" : "Fail\n");
			sendStore += tempOutput.getBaseString();
			responseFrame.getUserPanel().updateUserInterface(sendStore);
			localIndex--;
		}
		//responseFrame.getUserPanel().updateUserInterface(sendStore);
		complete();
		//super.stop();
		
	}
	
	public void complete()
	{
		completed = true;
		//responseFrame.getUserPanel().updateUserInterface("Finished!");
		sendStore += "Finished: " + (getResult() ? "Succesfully" : "Failing");
		responseFrame.getUserPanel().updateUserInterface(sendStore);
	}
	 
	public boolean[] getResults()
	{
		return resultsOfCriterionTests;
	}
	
	public boolean getResult()
	{
		for(boolean bool : resultsOfCriterionTests)
		{
			System.out.println(resultsOfCriterionTests.length);
			if(!bool)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isDone()
	{
		return completed;
	}
}
