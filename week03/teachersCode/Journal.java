// Look at all of these imports! You can see why some people opt for
// import java.io.* instead.
// Don't give in to the darkside! Most editors have a feature called
// code-folding, which will let you hide all of the important statements
// until you need them.

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

class Journal {
	
	List<Entry> _entries;

	// Once again, we're just creating an anonymous instance of the main object (Journal in this case)
	// so that we can call a method on it.
	public static void main(String[] args) {
		String filename = args[0];
		new Journal().parseFile(filename);
	}

	public Journal() {
		// We have to instantiate the ArrayList before using it.
		// It's typically best to do this in the constructor.
		_entries = new ArrayList<Entry>();
	}

	public void parseFile(String filename) {

		// Note how we use the getMessage() function on the exception to get
		// extended error information. The custom message we prepend to that 
		// allows us to identify exactly where the problem happened.
		try
		{

			// Factories and instances and parsers, oh-my!
			// Java loves factories, something we'll learn more about later in the course.
			// Some people really despise this about Java: https://i.imgur.com/y41pi4n.jpg
			File theFile = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(theFile);

			// When we're dealing with nodes that contain text, it's always best to call normalize()
			// on the root element to clean things up a bit.
			// See http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			// for more information
			doc.getDocumentElement().normalize();
			
			loadEntries(doc);
			displayEntries();
		}
		catch(SAXException ex) {
			System.out.println("Exception while parsing file. " + ex.getMessage());
		}
		catch(ParserConfigurationException ex) {
			System.out.println("Exception while settup up parser. " + ex.getMessage());
		}
		catch(IOException ex)
		{
			System.out.println("Exception while opening file. " + ex.getMessage());
		}
	}

	void loadEntries(Document doc) {

		// Sadly, NodeList doesn't support the alternative for-loop
		// syntax we learned about last week, because it's old and nobody
		// has bothered to update it. Many java folks lament this
		NodeList entries = doc.getElementsByTagName("entry");
		for (int i = 0; i < entries.getLength(); i++) {

			// Once we get the node, we have to cast it to one of its
			// subclasses, (Element in this case) in order to get what we
			// want out of it.
			Element theNode = (Element)entries.item(i);
			Entry e = new Entry(theNode);
			_entries.add(e);
		}
	}

	void displayEntries() {
		for(Entry e : _entries) {
			System.out.println(e.getDisplayText());
		}
	}
}