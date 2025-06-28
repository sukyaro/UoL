/**
 * COMP122 Lab1
 *
 * Yaro Lebedysnkyi
 * Student ID: 201858746
 */


class COMP108W04 {

	static int sumFromOne(int number) {
		int i;
		int sum = 0;
		
		i = 1;
		sum = 0;
		while (i <= number) {
			sum = sum + i;
			i = i + 1;
		}
 		//System.out.println("Sum from 1 to " + number + " equals to " + sum); 
		return sum;

	}
	
	// fill in this method for Task 1
	// output the sum from numberX to numberY
	// Hint: refer to sumFromOne()
	static void sumFromTo(int numberX, int numberY) {
		System.out.println(sumFromOne(numberY) - sumFromOne(numberX - 1));
	}
	
	static void isFactor(int numberX, int numberY) {
		if (numberX % numberY == 0)
			System.out.println(numberY + " is a factor of " + numberX);
		else
			System.out.println(numberY + " is not a factor of " + numberX);
	}

	// fill in this method for Task 2
	// finding all multiples of numberX that are factors of numberY
	static void multipleFactor(int numberX, int numberY) {
		for (int i = numberX; i <= numberY; i++) {
			if ((i % numberX == 0) && (numberY % i == 0)) {
				System.out.print(i + " ");
			}	
		}
	}

	// Aim: to output all common multiples of numberX and numberY up to 100
	// Find the bug and fix it by altering ONE line of code
	static void bugOne(int numberX, int numberY) {
		int i, bound;
		
		i = numberY;
		bound = 100;
		System.out.print("Common Multiples up to " + bound + ": ");
		while (i <= bound) {
			if (i % numberX == 0) 
				System.out.print(i + " ");
			i += numberY;
		}
		System.out.println();
	}
}
