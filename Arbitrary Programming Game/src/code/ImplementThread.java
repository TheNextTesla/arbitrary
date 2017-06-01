package code;

import display.ResponseFrame;
import game.Criterion;
import game.Test;

public class ImplementThread extends Thread
{
	private CodeRunner[] codeRunners;
	private ResponseFrame responseFrame;
	private Test[] testsToRun;
	private Criterion[] outputsToSee;
	private boolean[] resultsOfCriterionTests;
	private boolean completed;
	private int localIndex;
	
	
	public ImplementThread(CodeRunner codeRunner, ResponseFrame responseFrame, Test[] inputs, Criterion[] outputs)
	{
		int localIndex = Math.min(inputs.length, outputs.length);
		codeRunners = new CodeRunner[localIndex];
		for(int i = 0; i < localIndex; i++)
		{
			codeRunners[i] = codeRunner.clone();
		}
		
		this.responseFrame = responseFrame;
		testsToRun = inputs;
		outputsToSee = outputs;
		completed = false;
	}
	
	public void run()
	{
		if(localIndex == 0 && !completed)
		{
			complete();
			super.stop();
		}
		else
		{
			Criterion tempOutput = codeRunners[localIndex].runGivenAndGet(testsToRun[localIndex], localIndex);
			resultsOfCriterionTests[localIndex] = outputsToSee[localIndex].equals(tempOutput);
			responseFrame.getUserPanel().updateUserInterface("Test " + localIndex + ": " + (resultsOfCriterionTests[localIndex] ? "Pass" : "Fail"));
		}
	}
	
	public void complete()
	{
		completed = true;
		responseFrame.getUserPanel().updateUserInterface("Finished: " + (getResult() ? "Succesfully" : "Failing"));
	}
	
	public boolean[] getResults()
	{
		return resultsOfCriterionTests;
	}
	
	public boolean getResult()
	{
		for(boolean bool : resultsOfCriterionTests)
		{
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
