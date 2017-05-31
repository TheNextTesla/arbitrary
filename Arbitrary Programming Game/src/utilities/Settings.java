package utilities;

import control.Configuration;

public class Settings 
{
	private static Settings settings;
	private static byte difficulty = 1;
	
	public enum Setting {Difficulty}
	
	private Settings() {}
	
	public static Settings getInstance()
	{
		if(settings == null)
		{
			settings = new Settings();
		}
		return settings;
	}
	
	public boolean setSetting(Setting set, Object value, SettingsDependent sd)
	{
		switch(set)
		{
		case Difficulty:
			if(value instanceof Integer && (((Integer) value).intValue() >= 1) && (((Integer) value).intValue() <= Configuration.MAX_DIFFICULTY))
			{
				difficulty = (byte) ((Integer) value).intValue();
				sd.updateOnSettingsChange();
				return true;
			}
			break;
		}
		return false;
	}
	
	public Object getSetting(Setting set)
	{
		switch(set)
		{
		case Difficulty:
			return new Integer(difficulty);
		default:
			return null;
		}
	}
}
