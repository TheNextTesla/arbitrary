package game;

import game.Test.TestType;

public final class Criterion 
{
	private String variableName;
	private TestType comparisonType;
	private int numSet;
	private boolean bitSet;
	private String strSet;
	
	private String flatOutput; 
	
	public Criterion(String givenString)
	{
		comparisonType = Test.TestType.NONE;
		flatOutput = givenString;
	}
	
	public Criterion(int numSet, String variableName)
	{
		comparisonType = Test.TestType.INT;
		this.numSet = numSet;
		this.variableName = variableName;
	}
	
	public Criterion(boolean bitSet, String variableName)
	{
		comparisonType = Test.TestType.BOOL;
		this.bitSet = bitSet;
		this.variableName = variableName;
	}
	
	public Criterion(String strSet, String variableName)
	{
		comparisonType = Test.TestType.STR;
		this.strSet = strSet;
		this.variableName = variableName;
	}
	
	private boolean getBitSet()
	{
		return bitSet;
	}
	
	private int getNumSet()
	{
		return numSet;
	}
	
	private String getStrSet()
	{
		return strSet;
	}
	
	private String getBaseString()
	{
		return flatOutput;
	}
	
	public String getVariableName()
	{
		return variableName;
	}
	
	public boolean equals(Criterion other)
	{
		switch(comparisonType)
		{
		case NONE:
			return stringEquals(other);
			
		case INT:
			return numSet == other.numSet;
			
		case BOOL:
			return bitSet == other.bitSet;
			
		case STR:
			return strSet.trim().equals(other.strSet.trim());
		
		default:
			return stringEquals(other);
		}
	}
	
	private boolean stringEquals(Criterion other)
	{
		if(other.getBaseString().equals(flatOutput))
		{
			return true;
		}
		else
		{
			String[] splitArrayThis = flatOutput.trim().split("\n");
			String[] splitArrayOther = other.getBaseString().trim().split("\n");
			
			for(int i = Math.min(splitArrayThis.length, splitArrayOther.length) - 1; i >= 0; i--)
			{
				if(!splitArrayThis[i].equals(splitArrayOther[i]))
				{
					return false;
				}
			}
			
			if(splitArrayThis.length != splitArrayOther.length)
			{
				if(splitArrayThis.length > splitArrayOther.length)
				{
					return splitArrayThis[splitArrayThis.length - 1].equals("") || splitArrayThis[splitArrayThis.length - 1].equals(" ");
				}
				else if(splitArrayThis.length < splitArrayOther.length)
				{
					return splitArrayOther[splitArrayOther.length - 1].equals("") || splitArrayOther[splitArrayOther.length - 1].equals(" ");
				}
				else
				{
					return false;
				}
			}
			else
			{
				return true;
			}
		}
	}
}
