/*
* Name: <Shabab Ali>
* Program Name: <GuessingGame>
* Program Description: <A Java program (verified compiler - JDK8u131) that plays a simple game to generate a RANDOM NUMBER based on user input, 
						and subsequently allows the user 10 chances to correctly guess the program generated RANDOM NUMBER. 
						The program utilizes Scanner Objects, nested for loops, nested if-else statements and exemplifies procedural decomposition.>
*/


import java.util.Scanner; // use Scanner


public class GuessingGame {
	
	
	static final Scanner CONSOLE = new Scanner(System.in);
	static int version = 1;

	
	
	public static void main( String[] args ) {
	
		// Print game details to the screen for user.
		welcome();
		// Use Scanner object to get user input integer and generate the game's RANDOM NUMBER.
		int num = getNum();
		// Provide the user with 10 guesses interpreted through Scanner objects, 
		// evaluate the respective user inputs to the parameter num (game RANDOM NUMBER), and determine win/lose result.
		boolean win = play( num );
		// Print game result to the user by determining win/loss boolean state using the parameter win.
		goodbye( win );
	
	} //end method main()
	
	
	
	// method int rand( int max, int x ) returns a random int value:
	//  a) The first parameter max controls the range of integer values that can be generated, from zero to max. 
	//  b) The second parameter i controls which RANDOM NUMBER is returned 
	//     from the sequence of possible random numbers the formula generates.

	public static int rand( int max, int i ) {

		double frac = Math.abs(9437 * Math.sin(1009*i)) % 1.0;
		return (int)Math.round(max * frac);
	
	} //end method rand()
	
	
	
	// method void welcome() presents user with game concept and establishes interface introduction.
	// The welcome() prefaces method getnum() in the program method main().

	public static void welcome() {
		
		System.out.println("\nWelcome to Guessing Game!");
		System.out.println("-------------------------");
		System.out.println("Enter a number to play:");
		System.out.print("(1 to 2147483647) -> ");
	
	} //end method welcome()	
	
	
	
	// method int getNum() is called directly after method welcome() in program method main(), and 
	// uses Scanner Object CONSOLE to get user input from console, returning the game's RANDOM NUMBER.
	
	public static int getNum() {
		
		// uses class scope variable version which is also used by method confetti().  
		version = CONSOLE.nextInt();
		System.out.println("Generating random number.");
		// Generate game RANDOM NUMBER by calling method int rand( int max, int i ). 
		int randomNumber = rand(100, version);
		System.out.println("----RANDOM NUMBER SET----");
		return randomNumber;

	} //end method getNum()
	
	
	
	// method boolean play( int num ) runs the gameplay, passing parameter num argument.
	// In method main() num arguments are equal to the method int getNum() generated RANDOM NUMBER. 
	// Thus, method boolean play( int num ) uses num as the comparison operand for the gameplay logic.
	// Note - for loop iterates through logic of handling 10 user inputs ie Scanner objects,
	// 			outputs comparison reults to the user, and determines boolean true/false return value to evaluate user guess sequence.
	
	public static boolean play( int num ) {
		
		System.out.println("Guess my number (0--100): ");
		// iterate through user guesses 10 times.
		for (int i=10; i>0; i--) {
			
			System.out.println("You have " + i + " guesses left. ");
			System.out.print("What is your next guess? ");
			int userGuess = CONSOLE.nextInt();
			
			// nested if/else conditional statements test whether user guesses the correct RANDOM NUMBER (num), 
			// if so - boolean method play() returns true.
			if (userGuess < num) {
				System.out.println("Your guess was too low.");
			} else if (userGuess > num) {
				System.out.println("Your guess was too high.");
			} else {
				System.out.println("You guessed it!");
				return true;
			}
		
		}
		// if user does not guess correct RANDOM NUMBER (num), boolean method play() returns false. 
		System.out.println("The number was " + num);
		return false;
		
	} //end method play()
	
	
	
	// method void goodbye( boolean win ) uses parameter win to ouput the game result to the user.
	// Parameter win is equal to the boolean return value of method play(), which implements the game logic.
	// ie if win = method play() returns true? => goodbye( win ) - outputs WIN, else false? => goodbye( win ) - outputs LOSS.	 
	
	public static void goodbye( boolean win ) {
		
		System.out.println("-------------------------");
		if (win) {
			// method void confetti() prints ASCII art.
			confetti();
			System.out.println("CONGRATULATIONS, YOU WIN!");
		} else {
			System.out.println("Sorry, you lost. Try again!");
		}
	
	} //end method goodbye()

	

	// method void confetti() prints ASCII character art to construct a variable "*" or "." filled triangle. It is called in method goodbye() if the user wins.
	// Outer loop establishes the line number for illustration within the range 1-14 height lines.
	// First Inner loop prints a sequece of " " to establish the blank-space boundary of the triangle.
	// Second Inner loop uses a binary logic to print either "*" or "." characters to construct the filled triangle.  
	
	public static void confetti() {
		
		int count = 0;
		// Outer loop iterates the width domain fills, and establishes new height line
		for (int h=0; h<14; h++) {
			
			// First Inner loop prints width blank-space characters per each height line of the filled triangle.
			for (int s=0; s<(25*0.5)-h; s++) {
				System.out.print(" ");
			}
			
			// Second inner loop uses random binary number generator binaryNum to print either "*" or "." 
			// width characters per each height line iteration of the filled triangle.
			for (int f=1; f<(2*h); f++) {
				// formula uses 239*version because there are exactly 239 characters in the triangle, 
				// produces different pattern of "*" and "." depending on the first user input value ie version assignment in method getNum().
				int binaryNum = rand( 1, (count++) + 239*version );
				if (binaryNum == 1){
					System.out.print("*");
				} else {
					System.out.print(".");
				}
			}
			
			System.out.println(); //establish new height line in print output
		
		}
		
	} //end method confetti()

/*		confetti() OUTPUT EXAMPLE: for version = 1:

		("            *            ");
		("           ..*           ");
		("          *....          ");
		("         ....*..         ");
		("        *..**.**.        ");
		("       **.**.*.**.       ");
		("      ..**..**.**..      ");
		("     ...**....**..*.     ");
		("    *.***.**.*..*.**.    ");
		("   .**.***.**.***.**.*   ");
		("  ***.*...*.**..*.*.*.*  ");
		(" *..*.****..*...**.*.... ");
		("*****.....*..*.....*.*.*.");
		("CONGRATULATIONS, YOU WIN!");
*/	
		
	
} //end class GuessingGame