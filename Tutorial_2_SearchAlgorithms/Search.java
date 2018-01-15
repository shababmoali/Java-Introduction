import java.util.Arrays;


public class Search {


	// method boolean linearSearch(int[] A, int k) iterates through the array
	// to find int k and returns true if found. Time complexity O(n).
	public static boolean linearSearch(int[] A, int k) {
	
		for (int i=0; i<A.length; i++) {
			if (A[i] == k) {
				System.out.printf("Found %d - True%n", A[i]);
				return true;
			}
		}
		System.out.println("Not found, False");
		return false;
	
	} // end method boolean linearSearch(int[] A, int k)
	
	
	
	// method boolean binarySearch(int[] A, int k) partitions a sorted array
	// to find int k and returns true if found. Time complexity O(log n).
	public static boolean binarySearch(int[] A, int k) {
		
		int min = 0;
		int max = A.length-1;
		
		while (min <= max) {
			int mid = (min + max)/2;
			System.out.printf("min: %d, med: %d, max: %d %n", min, mid, max);
			// A[mid] == k ? => k is found, return true;
			if (A[mid] == k) {
				System.out.printf("Found %d - True%n", A[mid]);
				return true;
			// A[mid] > k ? => eliminate right
			} else if (A[mid] > k) { 
				max = mid - 1;
				System.out.printf("Set max Left%n");
			// A[mid] < k ? => eliminate left
			} else { 				
				min = mid + 1;
				System.out.printf("Set min Right%n");
			}
		}
		
		System.out.println("Not found, False");
		return false;
				
	} // end method boolean binarySearch(int[] A, int k)
	
	
	
	// method void secondSmallest(int[] A) iterates through an int[] Array A
	// and returns the smallest and second smallest number. Time complexity O(n).
	public static void secondSmallest(int[] A) {
		
		// Initialize the smallest and second-smallest variables with the
		// final array value to fairly evaluate the entire array. 
		int min = A[A.length-1];
		int min2 = A[A.length-1];
		
		for (int i=0; i<A.length; i++) {
			if (A[i] < min) {
				min2 = min;
				min = A[i];				
			} else if (A[i] < min2) {
				min2 = A[i];
			}
		}
		
		System.out.printf("The smallest number is %d and the second smallest number is %d", min, min2);
		
	} // end method secondSmallest(int[] A)
	
	
	// method main(String[] args) implements tester methods for Linear Search and Binary Search methods.`
	public static void main(String[] args) {
		
		int[] A = {0,1,2,3,4,5,6,7,8,9,10};
		
		int numTrue = 8;
		int numFalse = 11;
		
		System.out.println("\nQuery Array: " + Arrays.toString(A));
		
		//Linear Search True Test
		System.out.println("\nLinear Search True Example: ");
		System.out.printf("Search: %d in ", numTrue); 
		System.out.println(Arrays.toString(A));
		linearSearch(A, numTrue);
		
		//Binary Search True Test
		System.out.println("\nBinary Search True Example: ");
		System.out.printf("Search: %d in ", numTrue);
		System.out.println(Arrays.toString(A));
		binarySearch(A, numTrue);
		
		//Linear Search False Test
		System.out.println("\nLinear Search False Example: ");
		System.out.printf("Search: %d in ", numFalse); 
		System.out.println(Arrays.toString(A));
		linearSearch(A, numFalse);
		
		//Binary Search True Test
		System.out.println("\nBinary Search False Example: ");
		System.out.printf("Search: %d in ", numFalse);
		System.out.println(Arrays.toString(A));
		binarySearch(A, numFalse);
		
		//Smallest and Second Smallest Number Search Test
		System.out.println("\nSecond Smallest Number Search Example: ");
		secondSmallest(A);
	
	} //end method main(String[] args)


} // end Program class Search