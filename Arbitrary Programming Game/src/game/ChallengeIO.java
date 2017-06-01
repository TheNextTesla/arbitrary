package game;

import java.util.ArrayList;

public class ChallengeIO 
{
	private static ChallengeIO challengeIO;
	
	private static ArrayList<Challenge> challengeStore;
	
	private static final String[] titleStore = 
		{
				"Print-a-World"
		};
	
	private static final String[] messageStore = 
		{
				"Simply Test System.out.println() With 'Hello World!'"
		};
	
	private static final byte[] lowStore = 
		{
				1
		};
	
	private static final byte[] highStore = 
		{
				10
		};
	
	private static final Test[] testStore =
		{
				new Test()
		};
	
	private static final Criterion[][] reqStore = 
		{
				{
					new Criterion("Hello World!")
				}
		};
	
	private ChallengeIO()
	{
		challengeStore = new ArrayList<>();
		populateStore();
	}
	
	private static void populateStore()
	{
		for(int i = reqStore.length - 1; i >= 0; i--)
		{
			challengeStore.add(new Challenge(titleStore[i], messageStore[i], lowStore[i], highStore[i], true, testStore[i], reqStore[i]));
		}
	}
	
	public static ChallengeIO getInstance()
	{
		if(challengeIO == null)
		{
			challengeIO = new ChallengeIO();
		}
		return challengeIO;
	}
	
	public void removeChallenge(Challenge remove)
	{
		for(Challenge tempChallenge : challengeStore)
		{
			if(tempChallenge.equals(remove))
			{
				challengeStore.remove(tempChallenge);
				return;
			}
		}
	}
	
	public ArrayList<Challenge> getApplicableChallenges(byte range)
	{
		ArrayList<Challenge> tempChallenges = new ArrayList<>();
		for(Challenge tempChallenge : challengeStore)
		{
			byte[] tempRange = tempChallenge.getRangeArray();
			if(tempRange[0] <= range && tempRange[1] >= range)
			{
				tempChallenges.add(tempChallenge);
			}
		}
		return tempChallenges;
	}
}
