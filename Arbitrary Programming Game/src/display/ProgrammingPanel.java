package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.ArbitraryCodeRunner;
import control.Configuration;

public class ProgrammingPanel extends JPanel
{

	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 6261522059878071855L;
	
	private JTextArea jTextArea;
	private JButton jButton;
	
	public ProgrammingPanel()
	{
		//Creates and Sets Layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		jTextArea = new JTextArea(20, 20);
		jButton = new JButton("Enter Code");
		
		constraint.gridx = 0;
		constraint.gridy = 0;
		add(jTextArea, constraint);
		
		constraint.gridx++;
		add(jButton, constraint);
		
		jButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(jTextArea.getText() != null && !jTextArea.getText().trim().equals(""))
				{
					ArbitraryCodeRunner acr = new ArbitraryCodeRunner(jTextArea.getText());
					acr.run();
					
					while(!acr.hasRun())
					{
						try
						{
							Thread.sleep(Configuration.TICK_TIME);
						}
						catch(InterruptedException ie)
						{
							ie.printStackTrace();
						}
					}
					
					if(!acr.hasFailed())
					{
						new ResponseFrame(acr.getResultMessage());
					}
					else
					{
						new ResponseFrame(acr.getFailureMessage());
					}
				}
			}
		});
	}
}
