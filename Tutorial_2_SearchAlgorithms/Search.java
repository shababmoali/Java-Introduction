import java.util.Arrays;


public class Search {


	public static boolean linearSearch(int[] A, int k) {
	
		for (int i=0; i<A.length; i++) {
			if (A[i] == k) {
				return true;
			}
		}
		
		return false;
	
	} // end method boolean linearSearch(int[] A, int k)

	
	public static boolean binarySearch(int[] A, int k) {
		
		int min = 0;
		int max = A.length-1;
		
		while (min <= max) {
			int mid = (min + max)/2;
			System.out.printf("min: %d, med: %d, max: %d \n", min, mid, max);
			if (A[mid] < k) { // eliminate left
				min = mid + 1;
				System.out.printf("R - min: %d, med: %d, max: %d \n", min, mid, max);
			} else if (A[mid] > k) { //eliminate right
				max = mid - 1;
				System.out.printf("L - min: %d, med: %d, max: %d \n", min, mid, max);
			// A[mid] == k
			} else { 
				System.out.println("True");
				return true;
			}
		}
		
		System.out.println("False");
		return false;
				
	} // end method boolean binarySearch(int[] A, int k)
	
	
	
	public static void main(String[] args) {
		
		int[] A = {2,3,4,5,6,7,8,9,10};
		int num = 2;
		
		binarySearch(A, num);
	
	}


}