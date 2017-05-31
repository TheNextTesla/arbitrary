package code;

import java.io.ByteArrayOutputStream;

public interface CodeRunner 
{
	void run();
	boolean hasRun();
	boolean hasFailed();
	ByteArrayOutputStream getByteArray();
	String getResultMessage();
	void update(String userResponse);
}
