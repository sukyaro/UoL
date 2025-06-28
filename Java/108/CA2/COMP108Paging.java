
// Updated 2023-02-26
// Updated 2023-03-03
//
// NOTE: You are allowed to add additional methods if you need.
//
// Name: Yaro Lebedynskyi
// Student ID: 201858746
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// evictFIFO():
// Time complexities:
// - indexArray - O(p)
// - isInArray - O(p) (worst scenario)
// - dequeueAndEnqueue - O(p)
// - arrayToString - O(p)
// -- The methods above are called in a loop with a time complexity of O(n)
// So we get: p + p + p + p * n = 4p * n
//
// Total time complexity: O(pn)
//
//
// evictLRU():
// Time complexities:
// - indexArray - O(p)
// - isInArray - O(p) (worst scenario)
// - updateLRU - O(p)
// - arrayToString - O(p)
// -- The methods above are called in a loop with a time complexity of O(n)
// We get: p + p + p + p * n = 4p * n
//
// Total time complexity: O(pn)

class COMP108Paging {

	// evictFIFO
	// This method  goes thriugh the requests array
	// And it calls other methods
	// The time complexity:
	// O(pn), where p - the size of the cache and n - the number of requests
	static COMP108PagingOutput evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();
		int[] indexArray = indexArray(cSize);

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			if (isInArray(cArray, cSize, request)) {
				output.hitCount++;
				output.hitPattern += 'h';
			}
			else {
				cArray[indexArray[0]] = request;
				dequeueAndEnqueue(indexArray, cSize, indexArray[0]);
				output.missCount++;
				output.hitPattern += 'm';
			}
		}	

		output.cache = arrayToString(cArray, cSize);
		return output;
	}


	// evict LRU
	// This method  goes thriugh the requests array
	// And it calls other methods
	// The time complexity:
	// O(pn), where p - the size of the cache and n - the number of requests
	static COMP108PagingOutput evictLRU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();
		int[] indexArray = indexArray(cSize, cArray);

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			if (isInArray(cArray, cSize, request)) {
				output.hitCount++;
				output.hitPattern += 'h';
				
				updateLRU(cArray, cSize, indexArray, request, true);

			}
			else {
				output.missCount++;
				output.hitPattern += 'm';

				updateLRU(cArray, cSize, indexArray, request, false);
			}
		}	

		output.cache = arrayToString(cArray, cSize);
		return output;
	}


	// DO NOT change this method
	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	// The time complexity is O(p)
	static String arrayToString(int[] array, int size) {
		String outString="";
		
		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}


	// This method creates a looping queue
	// Every time it is called the element with an index i+1 shifts to an index i
	// And the last element gets a new value called "newValie"
	// The algorithm goes through the entire cache array
	// Therefore, the time complexity is O(p)
	static void dequeueAndEnqueue(int[] cArray, int cSize, int newValue) {
		for (int i = 0; i < cSize - 1; i++) {
			cArray[i] = cArray[i + 1];
		}
		cArray[cSize - 1] = newValue;
	}


	// This method creates a queue in an array
	// Every element in this array is its index
	// It is used for shifting elements
	// Since this method is the same size as the cache array
	// The time complexity for this method is O(p)
	static int[] indexArray(int cSize) {
		int[] newArray = new int[cSize];
		for (int i = 0; i < cSize; i++) {
			newArray[i] = i;
		}

		return newArray;
	}


	// Overloading the previous method
	// The change in this method is that 
	// It takes an additional parameter (the cache array)
	// The method copies the original cache array
	// Since this method is the same size as the cache array
	// The time complexity for this method is O(p)
	static int[] indexArray(int cSize, int[] cArray) {
		int[] newArray = new int[cSize];
		for (int i = 0; i < cSize; i++) {
			newArray[i] = cArray[i];
		}

		return newArray;
	}


	// This method goes through the cache array
	// And the algorithm looks for a given element
	// If the element is in the array True is returned
	// The algorithm goes through the entire cache array
	// Therefore, the time complexity is O(p)
	static boolean isInArray (int[] cArray, int cSize, int element) {
		boolean inArray = false;

		for (int i = 0; i < cSize; i++) {
			if (element == cArray[i]) {
				inArray = true;
				break;
			}
		}

		return inArray; 
	}


	// This method looks for a specific value in the cache array
	// If the value is found, its index is returned
	// If not, -1 is returned
	// The algorithm goes through the entire cache array
	// Therefore, the time complexity is O(p)
	static int getPosition(int[] cArray, int cSize, int request) {
		int position = -1;
		for (int i = 0; i < cSize; i++) {
			if (cArray[i] == request) {
				position = i;
				break;
			}
		}
		return position;
	}


	// This method implements the shift for a "queue"
	// it has a loop that goes throught the chache array
	// It give a time complexity of O(p)
	// Additionally, it calls the getPosition method which has a time complexity of O(p) as well
	// However, the time complexity is not nasted
	// The total time complexity of the method:
	// O(p) = O(p) + O(p)
	static void updateLRU(int[] cArray, int cSize, int[] indexArray, int request, boolean isHit) {
		if (isHit) {
			int pos = getPosition(indexArray, cSize, request);

			for (int i = pos+1; i < cSize; i++) {
				indexArray[i - 1] = indexArray[i];
			}

			indexArray[cSize - 1] = request;
		}
		else {
			int evictedData = indexArray[0];
			int pos = getPosition(cArray, cSize, evictedData);
			cArray[pos] = request; 

			for (int i = 1; i < cSize; i++) {
				indexArray[i - 1] = indexArray[i];
			}
			indexArray[cSize-1] = request;	
		}
	}
}

