package game;

public class Challenge 
{
	private byte lowDifficulty;
	private byte highDifficulty;
	
	private final String challengeTitle;
	private final String challengeMessage;
	private final String[] inputs;
	private final Criterion[] outputs;
	
	public Challenge(String title, String message, byte low, byte high, String[] tests, Criterion[] expectations)
	{
		challengeTitle = title;
		challengeMessage = message;
		inputs = tests;
		outputs = expectations;
		
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
	
	public byte[] getRangeArray()
	{
		byte[] range = new byte[2];
		range[0] = lowDifficulty;
		range[1] = highDifficulty;
		return range;
	}
	
	public String[] getInputs()
	{
		return inputs;
	}
	
	public Criterion[] getOutputs()
	{
		return outputs;
	}
}
