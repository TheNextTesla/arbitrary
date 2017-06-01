package display;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import control.Configuration;

//Central JFrame
public class CoreFrame extends JFrame
{

	/**
	 * Requested by JFrame
	 */
	private static final long serialVersionUID = -5053888180408127771L;
	
	private static CoreFrame coreFrame;
	private CardLayout cardLayout;
	private JPanel internalPanel;
	public enum PanelSwap {MainMenu, SinglePlayer, MultiPlayer, Settings, Sandbox}
	
	//GUI Setup
	private CoreFrame()
	{
		//TODO: How to actually choose relative location?
		setSize(Configuration.CORE_FRAME_WIDTH, Configuration.CORE_FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Configuration.GAME_TITLE);
		
		//Creates the Layout and 'Panel' for Handling all the other Panels
		cardLayout = new CardLayout();
		internalPanel = new JPanel(cardLayout);
		
		
		internalPanel.add("MainMenu", new MainMenuPanel());
		internalPanel.add("SinglePlayer", new SinglePlayerPanel());
		internalPanel.add("MultiPlayer", new MultiPlayerPanel());
		internalPanel.add("Sandbox", new SandboxPanel());
		internalPanel.add("Settings", new SettingsPanel());
		
		cardLayout.show(internalPanel, "MainMenu");
		
		//Creates and Adds All Menu Bar Options
		JMenuBar menu = new JMenuBar();
		
		JMenuItem homePass = new JMenuItem("Home");
		JMenuItem sandboxPass = new JMenuItem("Sandbox");
		JMenuItem settingsPass = new JMenuItem("Settings");
		
		menu.add(homePass);
		menu.add(sandboxPass);
		menu.add(settingsPass);
		
		homePass.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						swapPanel(PanelSwap.MainMenu);		
					}
				});
		
		sandboxPass.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						swapPanel(PanelSwap.Sandbox);	
					}
				});
		
		settingsPass.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						swapPanel(PanelSwap.Settings);	
					}
				});
		
		//Sets the MenuBar to the Frame
		setJMenuBar(menu);
		
		setVisible(true);
		add(internalPanel);
	}
	
	//Singleton Instantiation
	public static CoreFrame getInstance()
	{
		if(coreFrame == null)
		{
			coreFrame = new CoreFrame();
		}
		return coreFrame;
	}
	
	//Flips between panels
	public void swapPanel(PanelSwap panelSwap)
	{
		switch(panelSwap)
		{
		case MainMenu:
			cardLayout.show(internalPanel, "MainMenu");
			break;
		case SinglePlayer:
			cardLayout.show(internalPanel, "SinglePlayer");
			break;
		case MultiPlayer:
			cardLayout.show(internalPanel, "MultiPlayer");
			break;
		case Settings:
			cardLayout.show(internalPanel, "Settings");
			break;
		case Sandbox:
			cardLayout.show(internalPanel, "Sandbox");
			break;
		}
	}
}
