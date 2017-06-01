package game;

public class Challenge 
{
	private byte lowDifficulty;
	private byte highDifficulty;
	private boolean standardRun;
	
	private final String challengeTitle;
	private final String challengeMessage;
	private final Test inputs;
	private final Criterion[] outputs;
	
	public Challenge(String title, String message, byte low, byte high, boolean standard, Test tests, Criterion[] expectations)
	{
		challengeTitle = title;
		challengeMessage = message;
		inputs = tests;
		outputs = expectations;
		
		standardRun = standard;
		lowDifficulty = low;
		highDifficulty = high;
	}
	
	public String getTitle()
	{
		return challengeTitle;
	}
	
	public String getMessage()
	{
		return challengeMessage;
	}
	
	public boolean isStandardRun()
	{
		return standardRun;
	}
	
	public byte[] getRangeArray()
	{
		byte[] range = new byte[2];
		range[0] = lowDifficulty;
		range[1] = highDifficulty;
		return range;
	}
	
	public Test getInputs()
	{
		return inputs;
	}
	
	public Criterion[] getOutputs()
	{
		return outputs;
	}
	
	public boolean equals(Challenge challenge)
	{
		if(challengeTitle.equals(challenge.challengeTitle))
		{
			return true;
		}
		return false;
	}
}
