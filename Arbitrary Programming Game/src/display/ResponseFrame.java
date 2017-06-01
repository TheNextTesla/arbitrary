package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import control.Configuration;

//Handles Complex Code User IO - Frame
public class ResponseFrame extends JFrame
{

	/**
	 * Requested by JFrame
	 */
	private static final long serialVersionUID = 6040867982665621286L;
	
	private boolean hasClosed;
	private UserPanel userPanel;
	
	public ResponseFrame()
	{
		setSize(Configuration.RESPONSE_FRAME_WIDTH_2, Configuration.RESPONSE_FRAME_HEIGHT_2);
		setLocationRelativeTo(null); //TODO: How to actually choose relative location?
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		JMenuItem stopPass = new JMenuItem("STOP");
		
		stopPass.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						stopUserPanel();
					}
				});
		
		menu.add(stopPass);
		setJMenuBar(menu);
		
		userPanel = new UserInteractionPanel();
		add((JPanel) userPanel);
		
		setResizable(false);
		setVisible(true);
	}
	
	public ResponseFrame(String response)
	{
		setSize(Configuration.RESPONSE_FRAME_WIDTH, Configuration.RESPONSE_FRAME_HEIGHT);
		setLocationRelativeTo(null); //TODO: How to actually choose relative location?
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JMenuBar menu = new JMenuBar();
		JMenuItem stopPass = new JMenuItem("STOP");
		
		stopPass.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						stopUserPanel();
					}
				});
		
		menu.add(stopPass);
		setJMenuBar(menu);
		
		userPanel = new UserResponsePanel(response);
		add((JPanel) userPanel);
		
		setResizable(false);
		setVisible(true);
	}
	
	public UserPanel getUserPanel()
	{
		return userPanel;
	}
	
	//Kills Panel
	private void stopUserPanel()
	{
		userPanel.finalize();
	}
	
	//Suicide Frame
	public void dispose()
	{
		hasClosed = true;
		super.dispose();
	}
	
	public boolean isClosed()
	{
		return hasClosed;
	}
}
