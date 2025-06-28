
// Updated 2023-02-25
//
// Note: You are allowed to add additional methods if you need.
// Name: Yaro Lebedynskyi
// Student ID: 201858746
//
// Time Complexity and explanation: 
// f denotes initial cabinet size
// n denotes the total number of requests 
// d denotes number of distinct requests
// You can use any of the above notations or define additional notation as you wish.
// 
// appendIfMiss():
// Time complexities:
// - a for loop - O(n)
// - a nasted while loop - O(f)
// - insertTail - O(1)
// - headToTail - O(1)
// - tailToHead - O(1)
// We get = 1 + 1 + 1 + n * f = 3 + nf
//
// Total time complexity: O(nf)
// 	
// freqCount():
// Time complexities:
// - a for loop - O(n)
// - a nasted while loop - O(d)
// - insertTail - O(1)
// - headToTail - O(1)
// - tailToHead - O(1)
// - headToTailFreq - O(1)
// - checkAndMoveNodes - O(d) (worst scenario)
// We get = 1 + 1 + 1 + 1 + n * d * d = 4 + nd^2
//
// Total time complexity: O(nd^2)

class COMP108Cab {

	public COMP108Node head, tail;
	
	public COMP108Cab() {
		head = null;
		tail = null;
	}

	// Time complexity:
	// O(nf) where n - the total number of requests and f - initial cabinet size
	public COMP108CabOutput appendIfMiss(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);
	
		// Going through all the request
		for (int i = 0; i < rSize; i++) {
			int request = rArray[i], comparisons = 0;
			COMP108Node curr = head;
			boolean found = false;

			// Checking if a request is in the cabinet
			while (curr != null) {
				comparisons++;
				if (curr.data == request) {
					found = true;
					break;
				}
				curr = curr.next;
			}

			// If a request is not in the cabinet, adding it
			if (!found) {
				output.missCount++;
				COMP108Node newNode = new COMP108Node(request);
				insertTail(newNode);
			} 			
			else {
				output.hitCount++;
			}

			output.compare[i] = comparisons;
		}
	
		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		return output;
	}


	// Time complexity:
	// O(n*d^2), where n - he total number of requests and d - number of distinct requests
	public COMP108CabOutput freqCount(int rArray[], int rSize) {
		COMP108CabOutput output = new COMP108CabOutput(rSize);
		
		// Going through the requests
		for (int i = 0; i < rSize; i++) {
			int request = rArray[i], comparisons = 0;
			COMP108Node curr = head;
			boolean found = false;

			// Checking if the request is in the cabinet
			while (curr != null) {
				comparisons++;
				if (curr.data == request) {
					curr.freq++;
					found = true;
					break;
				}
				curr = curr.next;
			}

			// If the request is not in the cabinet, adding it
			if (!found) {
				output.missCount++;
				COMP108Node newNode = new COMP108Node(request);
				insertTail(newNode);
			} 			
			else {
				output.hitCount++;
			}

			output.compare[i] = comparisons;

			// Swapping the nodes if required
			checkAndMoveNodes(curr);
		
		}

		output.cabFromHead = headToTail();
		output.cabFromTail = tailToHead();
		output.cabFromHeadFreq = headToTailFreq();
		return output;		
	}


	// DO NOT change this method
	// insert newNode to head of list
	// Time complexity: O(1)
	public void insertHead(COMP108Node newNode) {		

		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

 
	// DO NOT change this method
	// insert newNode to tail of list
	// Time complexity: O(1)
	public void insertTail(COMP108Node newNode) {

		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}


	// DO NOT change this method
	// delete the node at the head of the linked list
	// Time complexity: O(1)
	public COMP108Node deleteHead() {
		COMP108Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
			else
				head.prev = null;
		}
		return curr;
	}
	

	// DO NOT change this method
	// empty the cabinet by repeatedly removing head from the list
	// Time complexity: O(f)
	public void emptyCab() {
		while (head != null)
			deleteHead();
	}


	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	// Time complexity: O(f)
	public String headToTail() {
		COMP108Node curr;
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
	// Only to be used for output, do not use it to manipulate the list
	// Time complexty: O(f)
	public String tailToHead() {
		COMP108Node curr;
		String outString="";
		
		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the frequency of the list nodes into a String from head to tail
	// Only to be used for output, do not use it to manipulate the list
	// Time complexity: O(f)
	public String headToTailFreq() {
		COMP108Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.freq;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}


	// This method gets a node and then 
	// Compares the frequency of this node with all the previous ones
	// If the frequency of the previous one(ones) is less than the 
	// Frequency of the current node, they swap
	// This resembles the Insertion Sort algorithm for the linked list
	// The time complexity is O(d) in the worst case
	public void checkAndMoveNodes(COMP108Node curr) {
		if (curr == null || curr.prev == null) {
			return;
		}

		while (curr.prev != null && curr.prev.freq < curr.freq) {
            COMP108Node prevCurrNode = curr.prev;

            prevCurrNode.next = curr.next;

            if (curr.next != null) {curr.next.prev = prevCurrNode;}
            
			curr.next = prevCurrNode;
            curr.prev = prevCurrNode.prev;
            prevCurrNode.prev = curr;
            
			if (curr.prev != null) {curr.prev.next = curr;} 
			else {head = curr;}

            if (prevCurrNode.next == null) {tail = prevCurrNode;}
        }
	}
}
