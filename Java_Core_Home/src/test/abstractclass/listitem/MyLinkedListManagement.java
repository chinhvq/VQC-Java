package test.abstractclass.listitem;

public class MyLinkedListManagement {

	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList(null);
		myLinkedList.traverse(myLinkedList.getRoot());
		
//		String myString = "2 4 0 5 6 3"; 
//		String[] strArray = myString.split(" ");
//		for (String str : strArray) {
//			myLinkedList.addItem(new Node(str));
//		}
		
		for (int i = 1; i < 5; i++) {
			myLinkedList.addItem(new Node(String.valueOf(i)));
		}
		
		myLinkedList.traverse(myLinkedList.getRoot());

	}

}
