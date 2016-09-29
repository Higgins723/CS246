import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class xmlParser {

   public static void main(String argv[]) {

      try {
         File inputFile = new File(argv[0]);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("session");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               System.out.println("----------------------------");
               Element eElement = (Element) nNode;
               System.out.println(eElement.getAttribute("name"));
               NodeList speakerName =
                  eElement.getElementsByTagName("speaker");
               System.out.println("----------------------------");
               for (int count = 0;
                  count < speakerName.getLength(); count++) {
                  Node node1 = speakerName.item(count);
                  if (node1.getNodeType() ==
                     node1.ELEMENT_NODE) {
                     Element speaker = (Element) node1;
                     System.out.println(speaker.getTextContent());
                     System.out.println(speaker.getAttribute("name"));
                  }
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
