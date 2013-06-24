package check;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
	
/**
 * 
 * 
 * Note to self: remember to remove  punctuation at the end of the titles
 * when comparing.
 * @author joel
 *
 */

public class ReadXMLFile {
	static File out_file = new File("/media/datal/Documents/test");
	static BufferedWriter bw = null;
	static ArrayList<String> list = null;
	
	
	
	  private static void openFile(){
			try {
				//String content = "This is the content to write into file";
				// if file doesnt exists, then create it
				if (!out_file.exists()) {
					out_file.createNewFile();
				}
	 
				FileWriter fw = new FileWriter(out_file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
	  
	  
	  private static void writeFile(String s) throws IOException{
		  bw.write(s);
	  }
	  
	  private static void closeFile() throws IOException{
		  bw.close();
	  }
	
  public static void main(String[] args) {
    try {
 
	File file = new File("/media/datal/Documents/publications.xml");
 
	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();
 
	Document doc = dBuilder.parse(file);
	
	
	
	
	Nodel<String> root = new Nodel<String>("Root element :" + doc.getDocumentElement().getNodeName());

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	//list.insert("Root element :" + doc.getDocumentElement().getNodeName());
	openFile();
	if (doc.hasChildNodes()) {
		printNode(doc.getChildNodes(),root);
	}
	closeFile();
    } catch (Exception e) {
    	System.out.println(e.getMessage());
    }
  }
  
  
  private static String Sanitize(String str){
	  if ((str.endsWith(",")) || str.endsWith(".")) {
		  str = str.substring(0,str.length()-1);  
	  }
	  
	  if ((str.endsWith(",")) || str.endsWith(".")) {
		  str = Sanitize(str);
	  }
	  
	  return str;
  }
 
  private static void printNode(NodeList nodeList, Nodel<String> root) throws DOMException, IOException {
    for (int count = 0; count < nodeList.getLength(); count++) {
		Node tempNode = nodeList.item(count);
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
			
	//		list.add("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			// get node name and value
		/*	System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
			*/
			
		//	Nodel<String> bran = nodeTree.branch(new Nodel<String>(tempNode.getNodeName()));
			
		//	System.out.println("Node Name =" + tempNode.getNodeName());

		//	System.out.println("Node Value =" + tempNode.getNodeValue());
		//	System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
			
			if (pathCorrect(tempNode)){
				
				System.out.println("Node value =" + tempNode.getTextContent() + " [CLOSE]");
				System.out.println("Node value =" + Sanitize(tempNode.getTextContent()) + " [CLOSE]");
			}
			
			
			if (tempNode.hasAttributes()) {
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
				tempNode.getNodeName();
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
				//	System.out.println("attr name : " + node.getNodeName());
				//	System.out.println("attr value : " + node.getNodeValue());
		//			lines.add("attr name : " + node.getNodeName() );
		//			lines.add("attr value : " + node.getNodeValue());
				}
			}
	 
			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNode(tempNode.getChildNodes(), root);
			}
	//		lines.add("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		}
    }
  }
  
  private static boolean pathCorrect(Node test) throws IOException{

	  Node itemNode = null;
	  Node orig = test;
	 // Node keyNode = null;
	  
	  if(!test.getNodeName().equals("unicode"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("value"))
		  return false;
	  test = test.getParentNode();
	  itemNode = test;
	  if(!test.getNodeName().equals("item"))
		  return false;
	  
	  NodeList nl = itemNode.getChildNodes();	   
//	  System.out.println("t "+itemNode.getNodeName());

	  itemNode = nl.item(1); //key node
//	   System.out.println("test "+itemNode.getNodeName());
	  if(!itemNode.getNodeName().equals("key"))
		  return false;	  
	  
	  nl = itemNode.getChildNodes();
	  itemNode = nl.item(0); //string node	  
	//  System.out.println("String "+itemNode.getNodeName());

	  if(!itemNode.getNodeName().equals("string"))
		  return false;	 	  
	  if(!itemNode.getTextContent().equals("title"))
		  return false;
	  
	  test = test.getParentNode();
      if(!test.getNodeName().equals("dictionary"))
    	  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("pickle"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("record"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("ZopeData"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("#document"))
		  return false;
	  if(orig.getTextContent().equals("Publications"))
		  return false;
	  /*
	  if(orig.getTextContent().contains("diffusion") ){
		  while(orig != null){
			  System.out.println("tree: " +orig.getNodeName());
			  NodeList node_list = orig.getChildNodes();
			//  writeFile(orig.getNodeName() + ":"+ orig.getTextContent() + "\n");
			  System.out.println(orig.getNodeName() + ":"+ orig.getTextContent() + "\n");
			  for(int i = 0; i < node_list.getLength(); i++){
			//	  System.out.println("---" + node_list.item(i).getNodeName() + ":" + node_list.item(i).getTextContent()  +"\n");
			//	  writeFile("---" + node_list.item(i).getNodeName() + ":" + node_list.item(i).getTextContent()  +"\n");
			  }
			//  writeFile("\n");
			  orig = orig.getParentNode();
		  }
	  }*/
	  
	  return true;
  }
  

  
  private static void closeFile(BufferedWriter bw) throws IOException{
	  
		bw.close();
		System.out.println("Done");
  }
  
 
}