package check;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {
	

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("/home/joel/Documents/sample.xml");


		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
	                             .newDocumentBuilder();
	 
		Document doc = dBuilder.parse(file);
		
		
		Nodel<String> root = new Nodel<String>("Root element :" + doc.getDocumentElement().getNodeName());

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		//list.insert("Root element :" + doc.getDocumentElement().getNodeName());

		if (doc.hasChildNodes()) {
			printNode(doc.getChildNodes());
		}
	}

 private static void printNode(NodeList nodeList) {
    for (int count = 0; count < nodeList.getLength(); count++) {
 
		Node tempNode = nodeList.item(count);
	 
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	 
	//		list.add("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			// get node name and value
		/*	System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
			*/
			
		//	nodeTree.branch(new Nodel<String>(tempNode.getNodeName()));
			
		//	lines.add("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		//	lines.add("Node Value =" + tempNode.getTextContent());
			System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			
			if (tempNode.hasAttributes()) {
	 
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
				/*	System.out.println("attr name : " + node.getNodeName());
					System.out.println("attr value : " + node.getNodeValue());
					*/
		//			lines.add("attr name : " + node.getNodeName() );
		//			lines.add("attr value : " + node.getNodeValue());
				}
	 
			}
	 
			if (tempNode.hasChildNodes()) {
				
				// loop again if has child nodes
				printNode(tempNode.getChildNodes());
	 
			}
	 
	//		lines.add("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		}
 
    }
  }
}
