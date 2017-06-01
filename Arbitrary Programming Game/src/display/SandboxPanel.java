package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.ArbitraryCodeRunner;
import code.InteractionCodeRunner;
import code.MonitorThread;

class SandboxPanel extends JPanel
{

	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = -8624791924206685963L;
	
	private JTextArea jTextArea;
	private JCheckBox jCheckBoxInput;
	private JButton jButton;
	
	SandboxPanel()
	{	
		//Creates and Sets Layout
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		Insets insets = new Insets(1, 1, 1, 1);
		constraint.insets = insets;
		
		jTextArea = new JTextArea(10, 20);
		jCheckBoxInput = new JCheckBox("Responsive");
		jButton = new JButton("Enter Code");
		
		constraint.gridx = 0;
		constraint.gridy = 0;
		add(jTextArea, constraint);
		
		//constraint.gridheight = 50;
		//constraint.gridwidth = 50;
		//constraint.gridy++;
		constraint.gridy++;
		add(jCheckBoxInput, constraint);
		
		constraint.gridy++;
		add(jButton, constraint);
		
		jButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(jTextArea.getText() != null && !jTextArea.getText().trim().equals("") && !jCheckBoxInput.isSelected())
				{
					ArbitraryCodeRunner acr = new ArbitraryCodeRunner(jTextArea.getText());
					ResponseFrame responseFrame = new ResponseFrame("");
					
					new MonitorThread(acr, responseFrame).start();
				}
				else if(jTextArea.getText() != null && !jTextArea.getText().trim().equals("") && jCheckBoxInput.isSelected())
				{
					InteractionCodeRunner icr = new InteractionCodeRunner(jTextArea.getText());
					ResponseFrame responseFrame = new ResponseFrame();
					
					new MonitorThread(icr, responseFrame).start();
				}
			}
		});
	}
}
