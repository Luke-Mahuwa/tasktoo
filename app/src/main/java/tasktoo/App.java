package tasktoo;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            File file = new File("data.xml");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the XML tag name to extract: ");
            String tagName = scanner.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName(tagName);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < nodeList.getLength(); i++) {
                JSONObject jsonObject = new JSONObject();
                Node node = nodeList.item(i);
                jsonObject.put("value", node.getTextContent().trim());
                jsonArray.put(jsonObject);
            }

            System.out.println("JSON Output:\n" + jsonArray.toString(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
