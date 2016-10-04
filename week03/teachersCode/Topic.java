import org.w3c.dom.Element;

public class Topic implements Annotation {
	String _name;

	public Topic(Element e)  {
		_name = e.getAttribute("name");
	}

	public String getDisplayText() {
		return _name;
	}
}