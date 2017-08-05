import java.util.Arrays;


public class Search {


	// method boolean linearSearch(int[] A, int k) iterates through the array
	// to find int k and returns true if found. Time complexity O(n).
	public static boolean linearSearch(int[] A, int k) {
	
		for (int i=0; i<A.length; i++) {
			if (A[i] == k) {
				System.out.println("True");
				return true;
			}
		}
		System.out.println("False");
		return false;
	
	} // end method boolean linearSearch(int[] A, int k)

	
	// method boolean binarySearch(int[] A, int k) partitions a sorted array
	// to find int k and returns true if found. Time complexity O(n).
	public static boolean binarySearch(int[] A, int k) {
		
		int min = 0;
		int max = A.length-1;
		
		while (min <= max) {
			int mid = (min + max)/2;
			System.out.printf("min: %d, med: %d, max: %d \n", min, mid, max);
			// A[mid] == k ? => k is found, return true;
			if (A[mid] == k) {
				System.out.println("True");
				return true;
			// A[mid] > k ? => eliminate right
			} else if (A[mid] > k) { 
				max = mid - 1;
				System.out.printf("L - min: %d, med: %d, max: %d \n", min, mid, max);
			// A[mid] < k ? => eliminate left
			} else { 				
				min = mid + 1;
				System.out.printf("R - min: %d, med: %d, max: %d \n", min, mid, max);
			}
		}
		
		System.out.println("False");
		return false;
				
	} // end method boolean binarySearch(int[] A, int k)
	
	
	
	public static void main(String[] args) {
		
		int[] A = {2,3,4,5,6,7,8,9,10,10,12,13,14,15,16};
		
		int numTrue = 10;
		int numFalse = 11;
		
		//Linear Search True Test
		System.out.println();
		linearSearch(A, numTrue);
		
		//Binary Search True Test
		System.out.println();
		binarySearch(A, numTrue);
		
		//Linear Search False Test
		System.out.println();
		linearSearch(A, numFalse);
		
		//Binary Search True Test
		System.out.println();
		binarySearch(A, numFalse);
	
	}


} // end Program class Search