package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Configuration;

public class MainMenuPanel extends JPanel
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 3250610783098668368L;
	
	private JLabel jLabelTitle;
	private JButton jButtonSinglePlayer;
	private JButton jButtonMultiPlayer;
	private JButton jButtonSandbox;
	private JButton jButtonSettings;
	
	//Creates Main Menu Panel
	MainMenuPanel()
	{
		//Creates and Sets Layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		jLabelTitle = new JLabel(Configuration.GAME_TITLE);
		jLabelTitle.setPreferredSize(new Dimension(200, 100));
		jLabelTitle.setFont(new Font("Serif", Font.PLAIN, 50));
		jButtonSinglePlayer = new JButton("Single Player");
		jButtonSinglePlayer.setPreferredSize(new Dimension(150, 40));
		jButtonMultiPlayer = new JButton("MultiPlayer");
		jButtonMultiPlayer.setPreferredSize(new Dimension(150, 40));
		jButtonMultiPlayer.setBackground(Color.GRAY);
		
		try
		{
			ImageIcon imageIconSandbox = new ImageIcon(new ImageIcon("include/sandbox.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			ImageIcon imageIconSettings = new ImageIcon(new ImageIcon("include/gear.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			
			jButtonSandbox = new JButton(imageIconSandbox);
			jButtonSettings = new JButton(imageIconSettings);
		}
		catch(Exception ex)
		{
			jButtonSandbox = new JButton("Sandbox");
			jButtonSettings = new JButton("Settings");
		}
		 
		jButtonSandbox.setSize(50, 50);
		jButtonSettings.setSize(50, 50);
		
		constraint.insets = new Insets(0, 15, 0, 15);
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.anchor = GridBagConstraints.CENTER;
		add(jLabelTitle, constraint);
		
		constraint.gridy++;
		add(jButtonSinglePlayer, constraint);
		
		constraint.gridy++;
		add(jButtonMultiPlayer, constraint);
		
		constraint.gridy++;
		constraint.gridx = 2;
		add(jButtonSettings, constraint);
		
		constraint.gridx = 0;
		add(jButtonSandbox, constraint);
		
		jButtonSinglePlayer.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						CoreFrame.getInstance().swapPanel(CoreFrame.PanelSwap.SinglePlayer);
					}
				});
		
		jButtonMultiPlayer.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						CoreFrame.getInstance().swapPanel(CoreFrame.PanelSwap.MultiPlayer);
					}
				});
		
		jButtonSandbox.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						CoreFrame.getInstance().swapPanel(CoreFrame.PanelSwap.Sandbox);
					}
				});
		
		jButtonSettings.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						CoreFrame.getInstance().swapPanel(CoreFrame.PanelSwap.Settings);
					}
				});
		
	}
}
