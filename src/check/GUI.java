package check;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI implements ActionListener {
    private int clicks = 0;
    JFrame frame = new JFrame();
    
    
    public JFrame getFrame(){
    	return frame;
    }
    
    JButton getTitlesButton = new JButton("Get titles");
    JButton getMissingButton = new JButton("Get Missing");
    JButton saveToOutputFile = new JButton("Append to output file");
    JButton clearOutputFile = new JButton("Clear output file");
    TextArea textArea = new TextArea();
    

    
    public void addActionListeners(){
        getTitlesButton.addActionListener(this);
        getMissingButton.addActionListener(this);
    }
    
    void setPropTextArea(){
    	textArea.setProperties();
    }
   
    public void reDraw(){
    	frame.getContentPane().removeAll();
    	frame.repaint();
    }

    
    public GUI() {
    	NormalMode.makeNormalGUI(this);
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

    
    public String oldGetTitles(){
		String areaText = null;
	  	Stores stores = null;
		ArrayList<Store> found = null;
		ArrayList<Store> unfound = null;
	  	
	  	
		try {
			Singleton.lines = Singleton.text.readSmallTextFile(Singleton.path_in.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  	/*areaText = DataProc.getTitles(Singleton.lines, Singleton.remove.getText(), Singleton.matches.getText(),
	    		Singleton.path_in.getText());
	  	*/
	  	stores = DataProc.getTitlesNew(Singleton.lines);
	  	found = stores.getFound();
	  	unfound = stores.getUnfound();
	  	///areaText = DataProc.getBlockFromStores(unfound);
	  	areaText = DataProc.getTitlesNewTest();
	  	return areaText;
    }
   /* 
    public ArrayList<String> getTitles(){
    	ReadXMLFile rxmlf = new ReadXMLFile(Singleton.xml_path.getText());
    	return rxmlf.getFileList();
    } */
    
    public void setTextArea(ArrayList<String> lines){
    	String s = NormalMode.getNonRepeatTitles(lines);
    	textArea.setText(s);
    }
    

    // process the button clicks
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals(getTitlesButton.getActionCommand())){   	
    		/*String areaText = null;
    		areaText = oldGetTitles();
    		
    		setTextArea(areaText); */

    	} else if (e.getActionCommand().equals(getMissingButton.getActionCommand())){
    		if(null != textArea.getText()){
	    	//	String areaText = DataProc.getMissingTitles(Singleton.titles, Singleton.uploaded_pub_list.getText());
	    		ArrayList<String> areaTextList = DataProc.getMissingTitles(Singleton.titles, Singleton.uploaded_pub_list.getText());
	    		
	    		textArea.setTextWrap(areaTextList);
	    		try {
					Singleton.text.writeSmallTextFile(Singleton.titles, new File(Singleton.path_out.getText()));
	    		} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(null, "You must have titles in the box to find which ones are missing");
    		}
    	}
    };
    
}

