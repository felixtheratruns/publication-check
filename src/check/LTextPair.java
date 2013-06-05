package check;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LTextPair implements ActionListener {
	public JLabel label = new JLabel("");
	public JTextField text_field = null;
	public String id;
	
	public LTextPair(String ident, String text_title){
		id = ident;
		text_field = new JTextField(text_title);
	}
	
	public void setLabel(String text){
		label.setText(text);
	}
	public void setText(String text){
		text_field.setText(text);
	}
	
	public String getText(){
		return text_field.getText();
	}
	
    public void addToPane(JPanel panel){
    	panel.add(label);
    	panel.add(text_field);
    }

	
	public String getId(){
		return id;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
