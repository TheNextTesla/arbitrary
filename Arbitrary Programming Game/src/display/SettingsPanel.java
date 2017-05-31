package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Configuration;
import utilities.Settings;
import utilities.SettingsDependent;

class SettingsPanel extends JPanel implements SettingsDependent
{

	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = -4091223992593426349L;
	
	private String[] difficultyOptions;
	private byte difficultyCurrent;
	
	private JLabel jTitleLabel;
	private JLabel jDifficultyLabel;
	private JComboBox<String> jDifficultyOptions;
	
	SettingsPanel()
	{
		//Creates and Sets Layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		difficultyOptions = populateOptions(Configuration.MAX_DIFFICULTY);
		difficultyCurrent = (byte) ((Integer) Settings.getInstance().getSetting(Settings.Setting.Difficulty)).intValue();
		
		jTitleLabel = new JLabel("Settings");
		jDifficultyLabel = new JLabel("Difficulty (" + difficultyCurrent + ") : ");
		jDifficultyOptions = new JComboBox<>(difficultyOptions);
		
		constraint.gridx = 0;
		constraint.gridy = 0;
		add(jTitleLabel, constraint);
		
		constraint.gridy++;
		add(jDifficultyLabel, constraint);
		
		constraint.gridx++;
		add(jDifficultyOptions, constraint);
		
		jDifficultyOptions.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int index = jDifficultyOptions.getSelectedIndex();
				
				if(index + 1 != difficultyCurrent)
				{
					setDifficulty((byte)(index + 1));
				}
			}	
		});
	}
	
	private void setDifficulty(byte index)
	{
		Settings.getInstance().setSetting(Settings.Setting.Difficulty, new Integer(index), this);
	}
	
	private String[] populateOptions(byte max)
	{
		String[] temp = new String[max];
		
		for(int i = 1; i <= max; i++)
		{
			temp[i - 1] = Integer.toString(i);
		}
		
		return temp;
	}

	@Override
	public void updateOnSettingsChange() 
	{
		difficultyCurrent = (byte) ((Integer) Settings.getInstance().getSetting(Settings.Setting.Difficulty)).intValue();
		jDifficultyLabel.setText("Difficulty (" + difficultyCurrent + ") : ");
	}
}
