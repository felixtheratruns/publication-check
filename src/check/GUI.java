package check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI implements ActionListener {
    private int clicks = 0;
    private JFrame frame = new JFrame();

    JButton getTitlesButton = new JButton("Get titles");
    JButton getMissingButton = new JButton("Get Missing");
    JButton saveToOutputFile = new JButton("Append to output file");
    JButton clearOutputFile = new JButton("Clear output file");
    JTextArea textArea = new JTextArea();
    
    private JPanel makePanel(){
	    // the panel with the button and text

	    JPanel panel = new JPanel();
	    panel.setMinimumSize(new Dimension(100,200));
	    panel.setPreferredSize(new Dimension(100,200));
	    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    panel.setLayout(new GridLayout(12, 2));

	    Singleton.settings_location.setBText("Settings location: ");
	    Singleton.remove_name.setLabel("Remove name: "); LTextPair row1 = Singleton.remove_name;
	    Singleton.remove.setLabel("Remove: "); LTextPair row2 = Singleton.remove;
	    Singleton.matches.setLabel("Matches: "); LTextPair row3 = Singleton.matches;
	    Singleton.split_by.setLabel("Split by: "); LTextPair row3_5 = Singleton.split_by;
	    Singleton.path_pub.setBText("Publications: "); BTextPair row4 = Singleton.path_pub;
	    Singleton.path_out.setBText("Path out: "); BTextPair row5 = Singleton.path_out;
	    Singleton.path_in.setBText("Path in: "); BTextPair row6 = Singleton.path_in;
	    ComboBox c_box = Singleton.combo_box;

	    JLabel sepr = new JLabel("");
	    JLabel sepl = new JLabel("");
	    Singleton.settings_location.addToPane(panel);

	    c_box.addToPanel(panel);
	    row1.addToPane(panel);
	    row2.addToPane(panel);
	    row3.addToPane(panel);
	    row3_5.addToPane(panel);
	    row4.addToPane(panel);
	    row5.addToPane(panel);
	    row6.addToPane(panel);
	    panel.add(sepl);
	    panel.add(sepr);
	    
	    panel.add(getTitlesButton);
	    panel.add(getMissingButton);
	    panel.add(saveToOutputFile);
	    panel.add(clearOutputFile);
	    return panel;
    }
    
    
    private void addActionListeners(){
        getTitlesButton.addActionListener(this);
        getMissingButton.addActionListener(this);
    }
    
    public void setPropTextArea(){
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setEditable(false);
        textArea.setMinimumSize(new Dimension(100,500));
        textArea.setPreferredSize(new Dimension(100,500));
    }
    
    public void setFrame(JPanel panel){
	    JScrollPane jScrollPane1 = new JScrollPane(textArea);
	    // set up the frame and display it
	    frame.add(panel, BorderLayout.NORTH);
	    frame.add(jScrollPane1,BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Missing Article Finder");
	    frame.pack();
	    frame.setPreferredSize(new Dimension(400,600));       
	    frame.setMinimumSize(new Dimension(400,600));
	    frame.setVisible(true);
    }
    
    public GUI() {
        // the clickable button
        addActionListeners();
        JPanel panel = makePanel();
        setPropTextArea();
        setFrame(panel);
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

    
    // process the button clicks
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals(getTitlesButton.getActionCommand())){   	
    		String areaText = null;
    	  	ArrayList<Store> stores = new ArrayList<Store>();
    		
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
    	  	
    	  	areaText = DataProc.getBlockFromStores(stores);
        	Singleton.area_text.setText(areaText);
		    textArea.setWrapStyleWord(true);
		    textArea.setRows(600);
		    textArea.setColumns(1);
		    textArea.setText(areaText);
    	} else if (e.getActionCommand().equals(getMissingButton.getActionCommand())){
    		if(null != textArea.getText()){
	    		String areaText = DataProc.getMissingTitles(Singleton.titles, Singleton.uploaded_pub_list.getText());
	    		textArea.setText(areaText);
	    		try {
					Singleton.text.writeSmallTextFile(Singleton.titles, Singleton.path_out.getText());
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

