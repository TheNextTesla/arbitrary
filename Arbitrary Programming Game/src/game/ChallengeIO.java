package game;

import java.util.ArrayList;

//Hosts the ChallengeStore - Test Entry is the Only One At The Moment
public class ChallengeIO 
{
	private static ChallengeIO challengeIO;
	
	//Has the 'Hello World' Example Entry
	private static ArrayList<Challenge> challengeStore;
	
	private static final String[] titleStore = 
		{
				"Print-a-World",
				//"Safe Square Root"
		};
	
	private static final String[] messageStore = 
		{
				"Simply Test System.out.println() With 'Hello World!'",
				//"Find a way to have Math.sqrt accept negative numbers"
		};
	
	private static final byte[] lowStore = 
		{
				1//, 1
		};
	
	private static final byte[] highStore = 
		{
				10//, 10
		};
	
	private static final int[] inputA = {1, -1, 0};
	
	private static final Test[] testStore =
		{
				new Test()//, new Test("x", inputA)
		};
	
	private static final Criterion[][] reqStore = 
		{
				{
					new Criterion("Hello World!")
				}
                 /*,
				{
					new Criterion(1, "x"),
					new Criterion(1, "x"),
					new Criterion(0, "x")
				}
				*/
		};
	
	private ChallengeIO()
	{
		challengeStore = new ArrayList<>();
		populateStore();
	}
	
	//Fills the ArrayList Instantiation
	private static void populateStore()
	{
		for(int i = reqStore.length - 1; i >= 0; i--)
		{
			challengeStore.add(new Challenge(titleStore[i], messageStore[i], lowStore[i], highStore[i], true, testStore[i], reqStore[i]));
		}
	}
	
	//Singleton
	public static ChallengeIO getInstance()
	{
		if(challengeIO == null)
		{
			challengeIO = new ChallengeIO();
		}
		return challengeIO;
	}
	
	//Removes a Completed Challenge from Temp and Global Stores
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
	
	//Returns a List of Challenges Based on an Array
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
