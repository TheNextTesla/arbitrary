package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JOptionPane;

import control.Configuration;

//Host Multiplayer Server
public class NetworkingServer extends Thread
{
	public enum ServerState {FAILED, WAITING, CONNECTED, GAMING, CLOSING};
	
	private ServerState serverState;
	private ServerSocket serverSocket;
	private Socket server;
	
	public NetworkingServer()
	{
		try 
		{
			serverSocket = new ServerSocket(Configuration.NETWORK_PORT);
			serverSocket.setSoTimeout(Configuration.NETWORK_TIMEOUT);
			serverState = ServerState.WAITING;
		}
		catch (IOException e) 
		{
			serverState = ServerState.FAILED;
			JOptionPane.showMessageDialog(null, "Error", "Connection Not Found", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public NetworkingServer(int port)
	{
		try 
		{
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(Configuration.NETWORK_TIMEOUT);
			serverState = ServerState.WAITING;
		}
		catch (IOException e) 
		{
			serverState = ServerState.FAILED;
			JOptionPane.showMessageDialog(null, "Error", "Connection Not Found", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//Threading Connection Method
	public void run()
	{
		/*
		 * @see https://docs.oracle.com/javase/tutorial/networking/overview/networking.html
		 */
        try 
        {
        	switch(serverState)
        	{
        	case WAITING:
        		//System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                server = serverSocket.accept();
                
                //System.out.println("Just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                
                //System.out.println(in.readUTF());
                String request = in.readUTF();
                
                if(request.equals(Configuration.GAME_REQUEST))
                {
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    out.writeUTF(Configuration.GAME_RECIEVED);
                    serverState = ServerState.CONNECTED;
                }   
                break;
                
        	case CONNECTED:
        		
        		break;
        	case GAMING:
        		break;
        	case CLOSING:
        		if(serverClose())
        		{
        			serverState = ServerState.FAILED;
        		}
        		break;
        	case FAILED:
        		break;
        	}
        }
        catch(SocketTimeoutException s) 
        {
        	JOptionPane.showMessageDialog(null, "Error", "Connection Timed Out", JOptionPane.WARNING_MESSAGE);
        }
        catch(IOException e) 
        {
        	JOptionPane.showMessageDialog(null, "Error", "Connection Exception", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	//Self-explanatory
	public boolean serverClose()
	{
		try 
		{
			serverState = ServerState.CLOSING;
			server.close();
		} 
		catch (IOException e) 
		{
			return false;
		}
		return true;
	}
}
