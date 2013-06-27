package check;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class NormalMode {
    static JButton getTitles =  new JButton("Find Uploaded Papers");

	
    
    public static void setNormalFrame(GUI gui, JPanel panel, String title){
	   
    	JScrollPane jScrollPane1 = new JScrollPane(gui.textArea.getJTextArea());
	    jScrollPane1.setMinimumSize(new Dimension(400,500));
	    jScrollPane1.setPreferredSize(new Dimension(400,500));

	    JPanel jpan = new JPanel();
	    JLabel jl = new JLabel("No data displayed (below)");
	    jpan.setLayout(new BorderLayout());
	    jpan.add(jl);
	    jpan.add(getTitles,BorderLayout.NORTH);

	    gui.frame.add(panel, BorderLayout.NORTH);
	    gui.frame.add(jpan, BorderLayout.CENTER);
	    gui.frame.add(jScrollPane1,BorderLayout.SOUTH);
	    gui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gui.frame.setTitle(title);
	    gui.frame.pack();
	    gui.frame.setPreferredSize(new Dimension(400,600));       
	    gui.frame.setMinimumSize(new Dimension(400,600));	    
	    gui.frame.setVisible(true);
    }
    
    
    public static JPanel makeNormalPanel(){
    	JPanel panel = new JPanel();
	    panel.setMinimumSize(new Dimension(100,100));
	    panel.setPreferredSize(new Dimension(100,100));
	    panel.setBorder(BorderFactory.createEmptyBorder(5, 30, 10, 30));
	    panel.setLayout(new GridLayout(3, 2));
	    
	    JLabel jl = new JLabel("Paths:");
	    JLabel jb = new JLabel();

	    Singleton.path_pub.setBText("Publications Path: "); BTextPair row4 = Singleton.path_pub;
	    Singleton.xml_path.setBText("XML Path: "); BTextPair row6 = Singleton.xml_path;
	    panel.add(jl);
	    panel.add(jb);
	    row4.addToPane(panel);
	    row6.addToPane(panel);
	    
	    
    	/*   JPanel jp = new JPanel();
	    JLabel jl = new JLabel("This area shows titles: ");
	    jp.add(jl);
	    frame.add(jp);
	    frame.setVisible(true);*/
	    
	    return panel;
    }
    
    
    public static void makeNormalGUI(final GUI gui){
    	getTitles.addActionListener(new ActionListener(){

    		@Override
    		public void actionPerformed(ActionEvent e) {
    			gui.setTextArea(getNonRepeatTitles());
    		}
    		
    	});
        gui.addActionListeners();
        JPanel panel = makeNormalPanel();
        gui.setPropTextArea();
        
    	MainMenuBar sm = new MainMenuBar();
    	gui.frame.setJMenuBar(sm);
    	setNormalFrame(gui, panel, "Find Articles Not Uploaded (Normal Mode)");  
    }
    
    public static ArrayList<String> getTitlesNew(){
    	ReadXMLFile rxmlf = new ReadXMLFile(Singleton.xml_path.getText());
    	return rxmlf.getFileList();
    }

    
    public static String getNonRepeatTitles(){
    	return DataProc.makeBlockNoRep(getTitlesNew());
    }


}
