import javax.swing.JTextField;


public class JTextFieldE extends JTextField {
	//JTextField field = new JTextField();
	String name = null;
	public JTextFieldE(String n, String f){
		name =n;
		super.setText(f);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isName(String n){
		return (n == name);
	}

}
