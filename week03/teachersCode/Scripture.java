import org.w3c.dom.Element;

public class Scripture implements Annotation {

	String _book;
	int _chapter;
	int _startVerse;
	int _endVerse;

	public Scripture(Element e) {
		_book = e.getAttribute("book");
		_chapter = Integer.parseInt(e.getAttribute("chapter"));

		loadVerses(e);
	}

	// Notice how we load the verses in a separate method because of all of the logic involved.
	// When trying to figure out how much stuff should go into one function, the convention
	// is that each function should have one main job. 
	//
	// Another rule of thumb that a few people use is "the size of your head". If you 
	// put your head next to the code on the screen, and the distance between the opening and
	// closing braces is bigger than your header, you should probably break it up into
	// separate functions.
	void loadVerses(Element e) {
		if(e.hasAttribute("startVerse")) {
			_startVerse = Integer.parseInt(e.getAttribute("startVerse"));
		}
		else {
			_startVerse = 0;
		}

		if(e.hasAttribute("endVerse")) {
			_endVerse = Integer.parseInt(e.getAttribute("endVerse"));
		}
		else {
			_endVerse = 0;
		}
	}

	public String getDisplayText() {

		if(_startVerse == 0) {
			return String.format("%s %d", _book, _chapter);
		}
		else if(_endVerse == 0) {
			return String.format("%s %d:%d", _book, _chapter, _startVerse);
		}
		else {
			return String.format("%s %d:%d-%d", _book, _chapter, _startVerse, _endVerse);
		}
	}
}