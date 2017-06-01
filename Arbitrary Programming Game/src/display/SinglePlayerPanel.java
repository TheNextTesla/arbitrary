package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.ArbitraryCodeRunner;
import code.ImplementThread;
import code.InteractionCodeRunner;
import code.MonitorThread;
import control.Configuration;
import game.Challenge;
import game.ChallengeIO;
import utilities.Settings;
import utilities.Settings.Setting;
import utilities.SettingsDependent;

class SinglePlayerPanel extends JPanel implements SettingsDependent
{

	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 241204251034961474L;
	
	private ArrayList<Challenge> challengeStoreLocal;
	private Challenge currentChallenge;
	
	private JLabel jLabelTitle;
	private JLabel jLabelMessage;
	private JTextArea jTextArea;
	private JCheckBox jCheckBoxInput;
	private JButton jButton;
	
	SinglePlayerPanel()
	{
		updateOnSettingsChange();
		updateCurrentChallenge();
		
		//Creates and Sets Layout
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		Insets insets = new Insets(1, 1, 1, 1);
		constraint.insets = insets;
		
		jLabelTitle = new JLabel(currentChallenge.getTitle());
		jLabelMessage = new JLabel(currentChallenge.getMessage());
		jTextArea = new JTextArea(10, 20);
		//jCheckBoxInput = new JCheckBox("User Input?");
		jButton = new JButton("Enter Code");
		
		constraint.gridx = 0;
		constraint.gridy = 0;
		add(jLabelTitle, constraint);
		
		constraint.gridy++;
		add(jLabelMessage, constraint);
		
		constraint.gridy++;
		add(jTextArea, constraint);
		
		//constraint.gridy++;
		//add(jCheckBoxInput, constraint);
		
		constraint.gridy++;
		add(jButton, constraint);
		
		jButton.addActionListener(new ActionListener()
		{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					if(jTextArea.getText() != null && !jTextArea.getText().trim().equals("") && currentChallenge.isStandardRun())
					{
						ArbitraryCodeRunner acr = new ArbitraryCodeRunner(jTextArea.getText());
						ResponseFrame responseFrame = new ResponseFrame("");
							
						ImplementThread it = new ImplementThread(acr, responseFrame, currentChallenge.getInputs(), currentChallenge.getOutputs());
						it.start();
						
						while(!it.isDone())
						{
							try
							{
								Thread.sleep(Configuration.TICK_TIME);
							}
							catch(InterruptedException ie)
							{
								
							}
						}
						
						if(it.getResult())
						{
							completeCurrent();
							updateCurrentChallenge();
						}
						
					}
					else if(jTextArea.getText() != null && !jTextArea.getText().trim().equals("") && !currentChallenge.isStandardRun())
					{
						InteractionCodeRunner icr = new InteractionCodeRunner(jTextArea.getText());
						ResponseFrame responseFrame = new ResponseFrame("");
							
						ImplementThread it = new ImplementThread(icr, responseFrame, currentChallenge.getInputs(), currentChallenge.getOutputs());
						it.start();
						
						while(!it.isDone())
						{
							try
							{
								Thread.sleep(Configuration.TICK_TIME);
							}
							catch(InterruptedException ie)
							{
								
							}
						}
						
						if(it.getResult())
						{
							completeCurrent();
							updateCurrentChallenge();
						}
					}
				}
		});
	}

	private void updateCurrentChallenge()
	{
		currentChallenge = challengeStoreLocal.get((int) (Math.random() * challengeStoreLocal.size()));
	}
	
	private void completeCurrent()
	{
		challengeStoreLocal.remove(currentChallenge);
		ChallengeIO.getInstance().removeChallenge(currentChallenge);
	}
	
	@Override
	public void updateOnSettingsChange()
	{
		challengeStoreLocal = ChallengeIO.getInstance().getApplicableChallenges( (byte) (int) ((Integer) Settings.getInstance().getSetting(Setting.Difficulty)));
	}
}
