package display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

class UserInteractionPanel extends JPanel  implements UserPanel
{
	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = -8257662627452265527L;
	
	private JButton jButton;
	private JTextArea jTextArea;
	private JTextField jTextEntry;
	private ActionListener actionListener;
	
	private boolean hasEntryOccurred;
	private String responseLocal;
	
	UserInteractionPanel()
	{
		responseLocal = "";
		hasEntryOccurred = false;
		
		//Creates and Sets Layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		JLabel jLabel = new JLabel("Program Reponse");
		jTextArea = new JTextArea();
		jTextArea.setEditable(false);
	    jTextArea.setLineWrap(false);
	    jTextArea.setVisible(true);
		jTextArea.setSize(50, 50);
	    JScrollPane jScrollPane = new JScrollPane(jTextArea);
		jScrollPane.setSize(100, 50);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        jTextEntry = new JTextField(15);
        jTextEntry.setText("");
        jTextEntry.setVisible(true);
        
        jButton = new JButton("Enter");
        
        constraint.weightx = 0;
        constraint.weighty = 0.1;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.ipadx = 20;
		constraint.ipady = 20;
		constraint.insets = new Insets(1, 1, 1, 1);
		add(jLabel, constraint);
		
		constraint.gridy++;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		constraint.ipady = 40;
		add(jScrollPane, constraint);
		
		constraint.gridy++;
		constraint.ipady = 20;
		add(jTextEntry, constraint);
		
		constraint.gridy++;
		add(jButton, constraint);
		
		actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					if(!jTextEntry.getText().equals(""))
					{
						hasEntryOccurred = true;
					}
			}
		};
		
        jButton.addActionListener(actionListener);
		
		setVisible(true);
	}
	
	public void updateUserInterface(String newData)
	{
		responseLocal = newData;
		jTextArea.setText(responseLocal);
	}
	
	public void finalize()
	{
		jButton.setBackground(Color.GRAY);
		jButton.removeActionListener(actionListener);
	}
	
	public String getUserResponse()
	{
		if(hasEntryOccurred)
		{
			String tempText = jTextEntry.getText();
			jTextEntry.setText("");
			hasEntryOccurred = false;
			return tempText + "\n";
		}
		else
		{
			return "";
		}
	}
}
