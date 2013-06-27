package check;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CVMode {
	
	   public static void makeCVGUI(){
	    	Singleton.gui.addActionListeners();
	        JPanel panel = makeCVPanel();
	        Singleton.gui.setPropTextArea();
	        
	    	MainMenuBar sm = new MainMenuBar();
	    	Singleton.gui.frame.setJMenuBar(sm);
	        setCVFrame(panel,"Find Articles Not Uploaded (CV Mode)");       
	    }
	   
	    private static void setCVFrame(JPanel panel, String title){
		    JScrollPane jScrollPane1 = new JScrollPane(Singleton.gui.textArea);
		    // set up the frame and display it
		    Singleton.gui.frame.add(panel, BorderLayout.NORTH);
		    Singleton.gui.frame.add(jScrollPane1,BorderLayout.CENTER);
		    Singleton.gui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Singleton.gui.frame.setTitle(title);
		    Singleton.gui.frame.pack();
		    Singleton.gui.frame.setPreferredSize(new Dimension(400,600));       
		    Singleton.gui.frame.setMinimumSize(new Dimension(400,600));	    
		    Singleton.gui.frame.setVisible(true);
	    }
	   
	   
	    public static JPanel makeCVPanel(){
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
		    
		    panel.add(Singleton.gui.getTitlesButton);
		    panel.add(Singleton.gui.getMissingButton);
		    panel.add(Singleton.gui.saveToOutputFile);
		    panel.add(Singleton.gui.clearOutputFile);
		    return panel;
	    }
}
