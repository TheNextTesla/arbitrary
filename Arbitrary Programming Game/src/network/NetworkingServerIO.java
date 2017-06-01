package network;

//Networking Client
//Abandoned
public class NetworkingServerIO 
{
	private static NetworkingServerIO networkingServerIO;
	
	private NetworkingServerIO()
	{
		
	};
	
	public static NetworkingServerIO getInstance()
	{
		if(networkingServerIO == null)
		{
			networkingServerIO = new NetworkingServerIO();
		}
		return networkingServerIO;
	}
	
	
}
