import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ButtonLPair implements ActionListener {
	public JButton button = new JButton();
	public JLabel text_field = null;
	public String id = null;
	

	public ButtonLPair(String ident, String text_title){
		id = ident;
		text_field = new JLabel(text_title);
	}
	
	public String getText(){
		return text_field.getText();
	}
	
	public void setBText(String s){
		button.setText(s);
	}
	
	public void ButtonPress(){
		runSelect(text_field);
	}
	
	public void setText(String s){
		text_field.setText(s);
	}
	
    public void runSelect(final JLabel text_field){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                String str = FileChooserDemo.selectFile();
                if (str == null){
                	
                }else{
                	text_field.setText(str);
                }
            }
        });
    }
    
    public void addToPane(JPanel panel){
    	panel.add(button);
    	panel.add(text_field);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ButtonPress();
		
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
