package check;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class TextArea {
	JTextArea jta = new JTextArea();
	
	
	public void setProperties(){
        jta.setColumns(20);
        jta.setLineWrap(true);
        jta.setRows(5);
        jta.setEditable(false);
        jta.setMinimumSize(new Dimension(100,500));
        jta.setPreferredSize(new Dimension(100,500));
	}
	
	public void setText(String areaText){
    	Singleton.area_text.setText(areaText);
    	jta.setWrapStyleWord(true);
    	jta.setRows(600);
    	jta.setColumns(1);
    	jta.setText(areaText);
	}
	
	public void setTextWrap(ArrayList<String> areaText){
		String s = NormalMode.makeBlockNoRep(areaText);
		setText(s);
	}
	
	public String getText(){
		return jta.getText();
	}
	  
	public JTextArea getJTextArea(){
		return jta;
	}
	
}
