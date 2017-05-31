package game;

public final class Criterion 
{
	private String base;
	
	public Criterion(String givenString)
	{
		base = givenString;
	}
	
	private String getBaseString()
	{
		return base;
	}
	
	public boolean equals(Criterion other)
	{
		if(other.getBaseString().equals(base))
		{
			return true;
		}
		else
		{
			String[] splitArrayThis = base.trim().split("\n");
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
