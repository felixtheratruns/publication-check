import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LTextPair implements ActionListener {
	public JLabel label = null;
	public JTextField text_field = null;
	
	public LTextPair(String label_title, String text_title){
		label = new JLabel(label_title);
		text_field = new JTextField(text_title);
	}
	
    public void addToPane(JPanel panel){
    	panel.add(label);
    	panel.add(text_field);
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
