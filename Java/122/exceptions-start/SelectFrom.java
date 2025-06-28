
public class SelectFrom {

	private int[] arrayOfNumbers;
	

    /** constructor
     * This copies the given string list into some attribute
     */
	public SelectFrom(String[] args) throws NullPointerException {
        arrayOfNumbers = new int[args.length];
		for (int i = 0; i < arrayOfNumbers.length; i++) {
			arrayOfNumbers[i] = Integer.parseInt(args[i]);
		}
	}

    /**
     * access method that gives access to the data by index
     */
	public int selectFromArray(int i) {
		if (arrayOfNumbers.length <= i || i < 0)
			return -1;
		return arrayOfNumbers[i];
	}
	
	
    /**
     * This code demos a very common bug that results from bad encapsulation,
     * where outside code messes around with the internals of some class.
     *
     * We instantiate an object of some class SelectFrom that is supposed to
     * store and give access to a list of string arguments.
     * The issue is that the internal data structure (array) is not dynamically
     * created in the constructor before it's accessed but instead
     * only in the outside code *after* the constructor tried to access it.
     */
	public static void main(String[] args) {
		SelectFrom n = new SelectFrom(args);
		System.out.println(n.selectFromArray(args.length-1));

	}

}
