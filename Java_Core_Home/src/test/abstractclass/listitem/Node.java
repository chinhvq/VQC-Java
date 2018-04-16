package test.abstractclass.listitem;

public class Node extends ListItem{

	protected Node(Object value) {
		super(value);
	}

	@Override
	ListItem next() {
		return this.rightLink;
	}

	@Override
	ListItem previous() {
		return leftLink;
	}

	@Override
	ListItem setNext(ListItem item) {
		this.rightLink = item;
		return this.rightLink;
	}

	@Override
	ListItem setPrevious(ListItem item) {
		this.leftLink = item;
		return this.leftLink;
	}

	@Override
	int compareTo(ListItem item) {
		if (item != null) {
			return ((String) super.getValue()).compareTo((String)item.getValue());			
		} else {
			return -1;
		}
	}
	

}
