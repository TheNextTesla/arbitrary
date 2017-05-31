package game;

import java.util.ArrayList;

public class ChallengeIO 
{
	private static ChallengeIO challengeIO;
	
	private ArrayList<Challenge> challengeStore;
	
	private static final String[] titleStore = 
		{
				
		};
	
	private static final String[] messageStore = 
		{
				
		};
	
	private static final byte[] lowStore = 
		{
				
		};
	
	private static final byte[] highStore = 
		{
				
		};
	
	private ChallengeIO()
	{
		challengeStore = new ArrayList<>();
		populateStore();
	}
	
	private static void populateStore()
	{
		
	}
	
	public static ChallengeIO getInstance()
	{
		if(challengeIO == null)
		{
			challengeIO = new ChallengeIO();
		}
		return challengeIO;
	}
}
