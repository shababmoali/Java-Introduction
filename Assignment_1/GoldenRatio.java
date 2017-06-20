/*
* Name: <Shabab Ali>
* Student Number: <V00651717>
* Program Name: <GoldenRatio>
* Program Description: <A Java program (verified compiler - JDK8u131) that approximates Phi and tests the Fibonnaci Sequence, 
						concomitantly generating Fibonacci sequence themed ASCII art.>
*/


public class GoldenRatio {
	
	
	public static void main( String args[] ){
		
		System.out.println("\n[START ASSIGNMENT]");
		
		//Call phiApproximationsOutput() method - Assignment TASK 1.
		phiApproximationsOutput();
		
		//Call compareFibonacciGoldenRatio() method - Assignment TASK 2.		
		compareFibonacciGoldenRatio();
					
		//Call printRabbit() method - Assignment TASK 3.
		printRabbit();
		
		System.out.println("\n[END ASSIGNMENT]");
		
	} //end method main()

	
	// method phiApproximationsOutput() prints approximations of Phi. 	
	// Output Format:	The Golden Ratio is approximately <value of approxPhi here>.
	//		 			The Golden Ratio is <value of phi here>.
	
	public static void phiApproximationsOutput(){

		//Create a variable of type double called phi, set phi to (1 + Math.sqrt(5))/2.
		double phi = (1 + Math.sqrt(5))/2;
		
		//Get approximations for phi using method getApproxPhi() which iterates through the Golden Ratio infinite series.
		double lessAccuApproxPhi = getApproxPhi( 5 );
		double moreAccuApproxPhi = getApproxPhi( 100 );
		
		//Print: Less accurate approximation of the Golden Ratio.
		System.out.println("\nThe Golden Ratio is approximately " + lessAccuApproxPhi);
		System.out.println("The Golden Ratio is " + phi);
		
		//Print: More accurate approximation of the Golden Ratio.		
		System.out.println("\nNow for a more accurate approximation.");
		System.out.println("The Golden Ratio is approximately " + moreAccuApproxPhi);
		System.out.println("The Golden Ratio is " + phi +"\n");
		
	} //end method phiApproximationsOutput()

	
/* 	
	Assignment TASK 1:
	Approximate Phi:
	(a) create a variable of type double called approxPhi, (b) set approxPhi to 1.0,
	(c) set approxPhi to its reciprocal plus 1.0,
	(d) repeat Step (c) four more times, and then 99 more times, respectively.
*/


	// method getApproxPhi() returns a double value that approximates the GoldenRatio.
	// It uses the counter parameter as an iterator to obtain the phi value at a given position in the Golden Ratio infinite series.
	
	public static double getApproxPhi( int counter ){
		
		//Create a variable of type double called approxPhi, set approxPhi to 1.0, and use for loop to iterate [ approxPhi = (1/approxPhi) + 1 ].
		double approxPhi = 1.0;
	
		for (int i=0; i<counter; i++){
			approxPhi = (1/approxPhi) + 1;
		}
		
		return approxPhi;
		
	} //end method getApproxPhi()

	
/* 	
	Assignment TASK 2:
	Write a for-loop with a loop-control variable i that iterates from 3 to 9, inclusive, with each iteration
	performing the following steps:
	(a) set fn equal to the sum of f1 and f2
	(b) set f1 equal to f2
	(c) set f2 equal to fn
	(d) System.out.println("f " + i + " = " + fn);
	(e) call checkFibonacci( approxPhi, i ) as described below,
	(f) call starArt( f1, f2 ) as described below.
*/
	
	
	// method compareFibonacciGoldenRatio() iterates through the Fibonnacci sequence and calls methods: checkFibonacci() and starArt(). 
	// a) It compares GoldenRatio sequence numbers to Fibonacci Sequence Numbers 
	//		by passing approximate Phi and position numbers ( approxPhi, i ) argument into the method checkFibonacci().
	// b) It illustrates the arithmetic series in the form of '*' filled rectangle 
	//		by passing postional value ( f1, f2 ) arguments to method starArt().
	
	public static void compareFibonacciGoldenRatio() {
		
		double approxPhi = getApproxPhi( 100 );
		
		//Establish initial values for Fibonacci sequence.
		int f1 = 1;
		int f2 = 1;
		int fn = 0;
		
		//Iterate through the arithmetic series by expressing a number (fn) as the sum of the preceeding two number in the sequence.
		for (int i=3; i<10; i++){
			fn = f1 + f2;
			f1 = f2;
			f2 = fn;
			System.out.println("f_" + i + " = " + fn);//print iterated sequence
			
			//Check iterated output against Fibonacci calculation and GoldenRatio.
			checkFibonacci( approxPhi, i ); 
			
			//Illustrate arithmetic series.
			starArt( f1, f2 ); 
		}
		
	} //end method compareFibonacciGoldenRatio()

	
	// method checkFibonacci() compares Fibonacci Sequence Numbers to GoldenRatio sequence numbers,
	// using the approxPhi and 'arithmetic series position number' parameters.
	
	public static void checkFibonacci( double approxPhi, int n ) {
		
		double A = Math.pow(approxPhi, n);
		double B = Math.pow(1 - approxPhi, n);
		double Fibonacci = (A - B) / Math.sqrt(5);
		long check_fn = Math.round( Fibonacci );
		System.out.println("check f_ " + n + " = " + check_fn);
		
	} //end method checkFibonacci()


	// method starArt() illustrates a "*" filled rectangle using height and width parameters.
	// Outer loop establishes the line number for illustration within the range specified by height parameter.
	// Inner loop prints a sequece of "*" as a function of the provided width argument ie (2.5*width).		
	
	public static void starArt( int height, int width ){
		
		//Outerloop iterates the width domain fills, and establishes new height line
		for (int i = 0; i<height; i++){
			
			//Inner loop illustrates width in "*" units
			for (int w = 0; w<(2.5*width); w++){
				System.out.print("*"); 
			}
			System.out.println(); //new height line
			
		}
		System.out.println(); //spacer at end of outer loop illustrated object
		
	} //end method starArt()
	
	
/*
	Assignment TASK 3:
	Create a method called printRabbit() that outputs the following ASCII art rabbit:
	(\(\
	( -.-)
	o_(")(")
*/


	// method printRabbit() prints Rabbit model ASCII art.
	
	public static void printRabbit(){
		
		System.out.println("\n(\\(\\");
		System.out.println("( -.-)");
		System.out.println("o_(\")(\")\n");
		
	} //end method printRabbit()
	
		
} //end class GoldenRatio 