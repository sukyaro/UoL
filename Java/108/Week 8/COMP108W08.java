
// by Prudence Wong 2021-03-20
//
// Name: Yaro Lebedysnkyi
// Student ID: 201858746
//

class COMP108W08 {

	public Node head, tail;
	
	public COMP108W08() {
		head = null;
		tail = null;
	}

	// sequential search if key is in the list
	// move the node to head if found, do nothing if not found
	public void searchMoveToHead(int key) {
		boolean found = false;
		Node curr = head;
		while (curr != null && found == false) {
			if (key == curr.data) {
				found = true;
			} else {
				curr = curr.next;
			}
		}

		if (found == true) {
			if (curr == head) {
				return;
			} else {
				(curr.prev).next = curr.next;
			}

			if (curr == tail) {
				tail = curr.prev;
				if (tail != null) {
					tail.next = null;
				}
			} else {
				(curr.next).prev = curr.prev;
			}

			curr.next = head;
			curr.prev = null;

			if (head != null) {
				head.prev = curr;
			} else {
				tail = curr;
			}
			head = curr;
		}
	}


	// sequential search if key is in the list
	// move the node to tail if found, do nothing if not found
	public void searchMoveToTail(int key) {
		boolean found = false;
		Node curr = head;
		while (curr != null && found == false) {
			if (key == curr.data) {
				found = true;
			} else {
				curr = curr.next;
			}
		}

		if (found == true) {
			
			if (curr == tail) {
				return;
			} else {
				(curr.next).prev = curr.prev;
			}

			if (curr == head) {
				head = curr.next;
				if (head != null) {
					head.prev = null;
				}
			} else {
				(curr.prev).next = curr.next;
			}

			curr.next = null;
			curr.prev = tail;

			if (tail != null) {
				tail.next = curr;
			} else {
				head = curr;
			}
			tail = curr;

		}
	}


	// DO NOT change this method
	// insert newNode to the head of the list
	public void insertHead(Node newNode) {
		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to the tail of the list
	public void insertTail(Node newNode) {
		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// this will turn the list into a String from head to tail
	public String headToTail(){
		Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	public String tailToHead(){
		Node curr;
		String outString="";
		
		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

}
