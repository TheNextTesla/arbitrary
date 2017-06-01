package display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

class UserResponsePanel extends JPanel implements UserPanel
{

	/**
	 * Requested by JPanel
	 */
	private static final long serialVersionUID = 5125376648593435538L;
	
	private String responseLocal;
	private JTextArea jTextArea;
	
	UserResponsePanel(String response)
	{
		responseLocal = response;
		
		//Creates and Sets Layout
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{449, 0};
		layout.rowHeights = new int[]{0, 14, 0};
		layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		GridBagConstraints constraint = new GridBagConstraints();
		setLayout(layout);
		
		JLabel jLabel = new JLabel("Program Reponse");
		jTextArea = new JTextArea(responseLocal);
		jTextArea.setEditable(false);
	    jTextArea.setLineWrap(false);
	    jTextArea.setVisible(true);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		jScrollPane.setSize(25, 50);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.insets = new Insets(0, 0, 5, 0);
		add(jLabel, constraint);
		
		constraint.gridy++;
		constraint.fill = GridBagConstraints.BOTH;
		constraint.insets = new Insets(0, 0, 0, 0);
		constraint.gridheight = 100;
		add(jScrollPane, constraint);
		
		setVisible(true);
	}
	
	public void finalize()
	{
		//Already not accepting user input
	}
	
	//Puts in New String Data
	public void updateUserInterface(String newData)
	{
		responseLocal = newData;
		jTextArea.setText(responseLocal);
	}
	
	public String getUserResponse()
	{
		return ""; 
	}
}
