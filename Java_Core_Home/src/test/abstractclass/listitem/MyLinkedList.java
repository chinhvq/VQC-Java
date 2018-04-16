package test.abstractclass.listitem;

public class MyLinkedList implements NodeList {
	private ListItem root = null;

	protected MyLinkedList(ListItem root) {
		this.root = root;
	}

	@Override
	public ListItem getRoot() {
		return this.root;
	}

	@Override
	public boolean addItem(ListItem newItem) {
		if (this.root == null) {
			// this List is empty so new Item become root
			this.root = newItem;
			return true;
		}

		ListItem currentItem = this.root;
		while (currentItem != null) {
			int comparision = currentItem.compareTo(newItem);
			if (comparision < 0) {
				// newItem is greater than current item
				if (currentItem.next() != null) {
					// we move to right if possible
					currentItem = currentItem.next(); // newItems
				} else {
					// because we want to sort My LinkedList in order AND there is no another items
					// behind current items
					// so we just add new items to next position AND make linkage between current
					// and newItem
					currentItem.setNext(newItem).setPrevious(currentItem);
				}
				return true;
			} else if (comparision > 0) {
				// newItem is smaller than currentItem, so we insert before currentItem
				if (currentItem.previous() != null) {
					// currentItem.previous o day la item dung truoc currentItem
					// ta phai chen new item vao giua currentItem va currentItem.previous()
					currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
					newItem.setNext(currentItem).setPrevious(newItem);
				} else {
					// there is no item before current items -> current Items is root
					// we need to set new items as root
					newItem.setNext(this.root).setPrevious(newItem);
					this.root = newItem;
				}
				return true;
			} else {
				// newItem is equal to current Item AND we want our MyLinkedList do not have
				// duplicated items (same as set)
				System.out.println(newItem.getValue() + " already exist in the list, not add");
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem item) {
		if (item != null) {
			System.out.println("Removing Item " + item);
		}

		ListItem currentItem = this.root;
		while (currentItem != null) {
			int comparision = currentItem.compareTo(item);
			if (comparision == 0) {
				// we found the item is equal to item in the list
				// we need to check found item whether be root or not
				if (currentItem == this.root) {
					this.root = currentItem.next();
				} else {
					// found item is not a root -> found items is in between two other items
					// we remove current Item by connecting two other items which connect to
					// currentItem before removal , make them connect to each other
					currentItem.previous().setNext(currentItem.next());
					if (currentItem.next() != null) {
						// check if list has only 01 item or not --> currentItem is only item in the
						// list
						currentItem.next().setPrevious(currentItem.previous());
					}
				}
				return true;
			} else if (comparision < 0) {
				// searching item is greater than the root BUT not found -> we need to move to
				// next items
				currentItem = currentItem.next();
			} else {
				// searchinh item is smaller than the root BUT NOT MATCH -> suppose no item will
				// the list will match with searching item because MyLinked List is sorted by
				// order
				System.out.println("Item need to be removed was NOT FOUND");
				return false;
			}
		}

		return false;
	}

	@Override
	public void traverse(ListItem root) {
		if (root == null) {
			System.out.println("The List is empty");
		} else {
			while (root != null) {
				System.out.print(root.getValue() + "\t");
				root = root.next();
			}
		}
	}

}
