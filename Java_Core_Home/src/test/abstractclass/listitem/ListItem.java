package test.abstractclass.listitem;

public abstract class ListItem {
	protected Object getValue() {
		return value;
	}

	protected void setValue(Object value) {
		this.value = value;
	}

	protected ListItem rightLink = null;
	protected ListItem leftLink = null;
	protected Object value;
	
	protected ListItem(Object value) {
		this.value = value;
	}
	
	abstract ListItem next();
	abstract ListItem previous();
	abstract ListItem setNext(ListItem item);
	abstract ListItem setPrevious(ListItem item);
	
	abstract int compareTo(ListItem item);
	
	

}
