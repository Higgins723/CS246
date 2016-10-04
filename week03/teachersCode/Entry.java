import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Entry {
	String _date;
	List<Annotation> _annotations;
	String _content;

	// We could have just had public getters and setters and let
	// the Journal class do all of the XML parsing, but splitting it
	// up like this can make it easier to maintain.
	//
	// One downside to this approach is that it increases the number of places in our code that
	// are dependent upon the DOM classes.
	//
	// One upside is that all of the Scripture logic is contained in the Scripture class, 
	// which makes it easier to find where to extend and/or modify it.
	public Entry(Element e) {

		_annotations = new ArrayList<Annotation>();

		loadScriptures(e);
		loadTopics(e);
		loadContent(e);

		_date = e.getAttribute("date");
	}

	void loadContent(Element e) {
		NodeList content = e.getElementsByTagName("content");
		// Note here that we have to get the first child node of the content node in
		// order to get the text. That's because every node that contains text
		// has a hidden text node inside of it that actually holds the text.
		// This is silly, but many things in the DOM object parser are silly.
		//
		// Lots of people use 3rd party libraries to deal with XML parsing
		// because of things like this.
		String text = content.item(0).getFirstChild().getTextContent();
		// Just cleaning the string up a bit, because the DOM parser
		// preserves all of the whitespace.
		_content = text.replaceAll("\\t+","\t");
	}

	// Notice how similar loadScriptures and loadTopics are. 
	// When we have repeated code like this, it usually indicates we have
	// an architecture issue. 
	// Later in the course, we'll be learning about different design
	// patterns we can use to deal with issues like this. 
	void loadScriptures(Element e) {
		NodeList scriptures = e.getElementsByTagName("scripture");
		for (int i = 0; i < scriptures.getLength(); i++) {
			Element theNode = (Element)scriptures.item(i);
			Scripture s = new Scripture(theNode);
			_annotations.add(s);
		}
	}

	void loadTopics(Element e) {
		NodeList topics = e.getElementsByTagName("topic");
		for (int i = 0; i < topics.getLength(); i++) {
			Element theNode = (Element)topics.item(i);
			Topic s = new Topic(theNode);
			_annotations.add(s);
		}
	}

	// Note the use of StringBuilder here to build the string up
	// It's slightly more efficient when dealing with lots and lots of strings.
	public String getDisplayText() {

		StringBuilder output = new StringBuilder();

		output.append("----\n");
		output.append("Entry\n");
		output.append("Date: " + _date + "\n");
		output.append("Content:\n");
		output.append(_content + "\n");

		output.append("Annotations:\n");
		for(Annotation a : _annotations){
			String display = a.getDisplayText();
			output.append("-" + display + "\n");
		}

		return output.toString();
	}
}