package tasktoo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            File file = new File("data.xml");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the XML tag name to extract: ");
            String tagName = scanner.nextLine();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // SAX Handler to process the XML events
            DefaultHandler handler = new DefaultHandler() {
                JSONArray jsonArray = new JSONArray();
                String currentTag = "";
                JSONObject jsonObject;

                // Method for handling the start of an element
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentTag = qName;  // Track the current tag
                    if (currentTag.equals(tagName)) {
                        jsonObject = new JSONObject();
                    }
                }

                // Method for handling the end of an element
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (currentTag.equals(tagName)) {
                        jsonArray.put(jsonObject);
                    }
                    currentTag = ""; // Reset tag after processing
                }

                // Method for handling the character data inside the element
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (currentTag.equals(tagName)) {
                        jsonObject.put("value", new String(ch, start, length).trim());
                    }
                }

                // Get the JSON array once parsing is complete
                public JSONArray getJsonArray() {
                    return jsonArray;
                }
            };

            // Parse the file using the SAX parser
            saxParser.parse(file, handler);

            // Output the JSON result
            System.out.println("JSON Output:\n" + handler.getJsonArray().toString(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
