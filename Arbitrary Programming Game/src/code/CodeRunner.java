package code;

import java.io.ByteArrayOutputStream;

import game.Criterion;
import game.Test;

public interface CodeRunner 
{
	void run();
	boolean runGiven(Test test, int index);
	Criterion runGivenAndGet(Test test, int index);
	boolean hasRun();
	boolean hasFailed();
	ByteArrayOutputStream getByteArray();
	String getOriginCode();
	String getResultMessage();
	void update(String userResponse);
	CodeRunner clone();
}
