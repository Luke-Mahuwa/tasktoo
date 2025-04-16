package tasktoo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the field (tag) they want to extract
        System.out.print("Enter the XML tag you want to extract (e.g., title): ");
        String tag = scanner.nextLine();

        try {
            File file = new File("data.xml"); // assuming it's in the root directory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(tag);

            if (nodeList.getLength() == 0) {
                System.out.println("No elements found with tag: " + tag);
            } else {
                System.out.println("Values for tag <" + tag + ">:");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        System.out.println("- " + element.getTextContent().trim());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading XML: " + e.getMessage());
        }
    }
}
