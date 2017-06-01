package control;

import java.io.InputStream;
import java.io.PrintStream;

public final class Configuration 
{
	public static final String GAME_TITLE = "Arbitrary";
	public static final String RESPONSE_TITLE = "RESPONSE";
	public static final String GAME_REQUEST = "GameRequest";
	public static final String GAME_RECIEVED = "GameRecieved";
	
	public static final short CORE_FRAME_WIDTH = 580;
	public static final short CORE_FRAME_HEIGHT = 420;
	public static final short RESPONSE_FRAME_WIDTH = 250;
	public static final short RESPONSE_FRAME_HEIGHT = 250;
	public static final short RESPONSE_FRAME_WIDTH_2 = 250;
	public static final short RESPONSE_FRAME_HEIGHT_2 = 350;
	public static final byte TICK_TIME = 100;
	public static final byte INTERACTIVE_TIMEOUT = 30;
	
	public static final byte MAX_DIFFICULTY = 10;
	public static final int NETWORK_TIMEOUT = 10000;
	public static final int NETWORK_PORT = 6786;
	
	public static final InputStream DEFAULT_INPUT_STREAM = System.in;
	public static final PrintStream DEFAULT_PRINT_STREAM = System.out;
	
	private Configuration() {}
}
