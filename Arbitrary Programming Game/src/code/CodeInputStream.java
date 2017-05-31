package code;

import java.io.IOException;
import java.io.InputStream;

public class CodeInputStream extends InputStream
{
	int index;
	byte[] localByteArray;
	String localCharArray;
	
	public CodeInputStream()
	{
		super();
		index = 0;
		localByteArray = new byte[0];
	}
	
	@Override
 	public int available()
 	{
		return localByteArray.length - index;
 	}
	
	@Override
	public int read() throws IOException 
	{
		byte response = localByteArray[index];
		index++;
		
		return (int) response;
	}
	
	public void patch(String patchString)
	{
		localCharArray += patchString;
		char[] tempCharArray = localCharArray.toCharArray();
		byte[] tempByteArray = new byte[tempCharArray.length];
		
		for(int i = tempCharArray.length; i > 0; i--)
		{
			tempByteArray[i - 1] = (byte) tempCharArray[i - 1];
		}
		localByteArray = tempByteArray;
	}
}
