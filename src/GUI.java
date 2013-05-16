
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
    
    JLabel containsL = new JLabel("counts button");

    JButton getTitlesButton = new JButton("Get titles");
    JButton getMissingButton = new JButton("Get Missing");

    JLabel testL = new JLabel("");
    JLabel button2L = new JLabel("");
    JLabel conNameL = new JLabel("Contains name l");

    JButton containsNameBtt = new JButton("Contains name");
    JButton containsBtt = new JButton("Contains: ");
    JButton matchesBtt = new JButton("Matches: ");
    JButton pubInBtt = new JButton("Publications: ");
    JButton titleOutBtt = new JButton("Path out: ");
    JButton pubComBtt = new JButton("Uploaded Publications: ");
    
    JTextArea textArea = new JTextArea();
    
    private JPanel makePanel(){
	    // the panel with the button and text

	    JPanel panel = new JPanel();
	    panel.setMinimumSize(new Dimension(100,200));
	    panel.setPreferredSize(new Dimension(100,200));
	    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    panel.setLayout(new GridLayout(10, 2));

	    File f = new File(".","paper_finder_settings");
	    
	    BTextPair settingsLocation = new BTextPair("Settings location: ",f.getAbsolutePath());
	    LTextPair row1 = new LTextPair("Contains name: ","Rai");
	    LTextPair row2 = new LTextPair("Contains: ","ln:");
	    LTextPair row3 = new LTextPair("Matches: ",".* [0-9][0-9][0-9][0-9]$");
	    BTextPair row4 = new BTextPair("Publications: ", "/home/joel/proj/rai_pub");
	    BTextPair row5 = new BTextPair("Path out: ", "/home/joel/proj/out");
	    BTextPair row6 = new BTextPair("Unloaded Public: ", "/home/joel/proj/pub_compare");
	    
	    ComboBoxDemo cbd = new ComboBoxDemo();
	    
	    settingsLocation.addToPane(panel);

	    cbd.addToPanel(panel);
	    
	    row1.addToPane(panel);
	    row2.addToPane(panel);
	    row3.addToPane(panel);
	    row4.addToPane(panel);
	    row5.addToPane(panel);
	    row6.addToPane(panel);
	    
	    panel.add(getTitlesButton);
	    panel.add(testL);
	    panel.add(getMissingButton);
	    panel.add(button2L);
	    
	    return panel;
    }
    private void addActionListeners(){
        getTitlesButton.addActionListener(this);
        getMissingButton.addActionListener(this);
        pubInBtt.addActionListener(this);
        titleOutBtt.addActionListener(this);
        pubComBtt.addActionListener(this);
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
    	if(e.getActionCommand().equals(pubInBtt.getActionCommand()) ){
    	//	runSelect(Singleton.path_in);
    	}else if (e.getActionCommand().equals(titleOutBtt.getActionCommand())){
    	//	runSelect(Singleton.path_out);
    	}else if (e.getActionCommand().equals(pubComBtt.getActionCommand())){
	   //     runSelect(Singleton.path_pub);
    	}else if(e.getActionCommand().equals(getTitlesButton.getActionCommand())){   	
    		String areaText;
    	  	try {
				Singleton.lines = Singleton.text.readSmallTextFile(Singleton.path_in.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		areaText = Singleton.text.getTitles(Singleton.lines, Singleton.contains.getText(), Singleton.matches.getText(),
		    		Singleton.path_in.getText());

		    areaText.trim();
		    Singleton.area_text.setText(areaText);
		    textArea.setWrapStyleWord(true);
		    textArea.setRows(600);
		    textArea.setColumns(1);
		    textArea.setText(areaText);
    	} else if (e.getActionCommand().equals(getMissingButton.getActionCommand())){
    		if(null != textArea.getText()){
	    		String areaText = Singleton.text.getMissingTitles(Singleton.titles, Singleton.comparator.getText());
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

