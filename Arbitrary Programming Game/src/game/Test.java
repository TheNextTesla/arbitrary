package game;

//Some Variable to Inject into Code
public class Test 
{
	//public enum TestType {NONE, NO_INPUT, NO_OUTPUT, IN_OUT};
	public enum TestType {NONE, INT, BOOL, STR};
	public TestType testTypeLocal;
	
	private String variable;
	private int[] valuesInt;
	private boolean[] valuesBool;
	private String[] valuesString;
	
	public Test()
	{
		testTypeLocal = TestType.NONE;
	}
	
	public Test(String varName, int[] values)
	{
		testTypeLocal = TestType.INT;
		variable = varName;
		valuesInt = values;
	}
	
	public Test(String varName, boolean[] values)
	{
		testTypeLocal = TestType.BOOL;
		variable = varName;
		valuesBool = values;
	}
	
	public Test(String varName, String[] values)
	{
		testTypeLocal = TestType.STR;
		variable = varName;
		valuesString = values;
	}
	
	public String getVariable()
	{
		return variable;
	}
	
	public int[] getValuesInt()
	{
		return valuesInt;
	}
	
	public boolean[] getValuesBoolean()
	{
		return valuesBool;
	}
	
	public String [] getValuesString()
	{
		return valuesString;
	}
}
