import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class BTextPair implements ActionListener {
	public JButton button = null;
	public JTextField text_field = null;


	public BTextPair(String button_title, String text_title){
		button = new JButton(button_title);
		button.addActionListener(this);
		text_field = new JTextField(text_title);
	}
	
	public void ButtonPress(){
		runSelect(text_field);
	}
	
	
    public void runSelect(final JTextField text_field){
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

}