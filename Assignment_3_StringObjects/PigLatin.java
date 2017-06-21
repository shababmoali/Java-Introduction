/*
* Name: <Shabab Ali>
* Program Name: <PigLatin>
* Program Description: <A Java program (verified compiler - JDK8u131) that translates a user input message either from English to Pig Latin,
						or vice versa. The program utilizes: 
						 - procedural decomposition to break down a problem into methods.
						 - while loops to process variable length input.
						 - Scanner to get a line of String data type user input.
						 - String object methods.
						 - if/else statements.>
*/


import java.util.Scanner; // use Scanner


public class PigLatin {
	
	
	static final Scanner CONSOLE = new Scanner(System.in);
	static String OUTPUT = "";
	 
	
/* EXAMPLE method main() output:


Welcome to Pig Latin translator!
Enter 1 to translate to Pig Latin,
enter 2 to translate back to English: 1
................................
What is your message? a rose by any other name would smell as sweet
Your message translated to Pig Latin is:
aay oseray ybay nyaay theroay amenay ouldway mellsay saay weetsay
--------------------------------
*/
	
	
	
	public static void main(String[] args) {
		
		
		int Selection = getTranslationSelection();
		String userInputMessage = getMessage();
		if (Selection == 1) {
			convertToPigLatin(userInputMessage);
		} else if (Selection == 2) {
			convertToEnglish(userInputMessage);
		}	

		
	} // end method main()
	
	
	
	// method getTranslationSelection() gets console user input for translation selection
	// and return the int (1 or 2) selection value.
	
	public static int getTranslationSelection() {
		
		System.out.println("--------------------------------");
		System.out.print("Welcome to Pig Latin translator!\n" +
						   "Enter 1 to translate to Pig Latin,\n" +
						   "Enter 2 to translate back to English: ");
		int languageOption = CONSOLE.nextInt();
		while (languageOption != 1 && languageOption != 2) {
			System.out.print("Please enter 1 or 2: ");
			languageOption = CONSOLE.nextInt();
		}
		System.out.println(".................................\n");
				
		return languageOption;
		
	} //end method getTranslationSelection()

	
		
	// method getMessage() gets console user input for message selection
	// and returns the String message.
	
	public static String getMessage() {
		
		CONSOLE.nextLine();
		System.out.print("What is your message? ");
		String message = CONSOLE.nextLine();
		
		return message;
	
	}
	
	
	// String conversion methods utilize ' ' space characters to establish breakpoints in a String object.
	// The English String object which is essentially an array of char type characters is spliced at ' ' and 
	// an OUTPUT String object (char array) sorts 1st position character in the English word i.e. 1st position after space (start+1). 
	// OUTPUT moves 1st position to the end of the word i.e. position before the next ' ', this blank space is stored in the variable stop.
	// for loop iterates through the English String object, and int variables start and stop store array positions,
	// to mark the start postion and stop position of every ' ' blank space separated word.
	
	public static String forConvertToPigLatin(String input) {
		
		System.out.println("\nYour message translated to PigLatin is: ");
		
		int start = 0;
		int stop = 0;	
	
		for (int i=0;i<input.length();i++) {
				
			if (i < input.length() && input.charAt(i) == ' ') {
				
				OUTPUT += input.substring((start+1),stop) + input.charAt(start) + "ay "; //(start+1) positions at 'English_word_secondletter'
				// For the first word in the String Object, start == 0.
				// For every subsequent word encountered in String array iteration:			
				// start is repositioned at first letter char of next word when array iteration encounters a blank space ' ' word separation mark.
				start=i+1;
				
			}
			stop++;
		
		}
		
		//Output final word in string conversion.
		OUTPUT += input.substring((start+1),stop) + input.charAt(start) + "ay";
		System.out.println(OUTPUT);
		System.out.println("--------------------------------");
		
		return OUTPUT;
		
	} // end method convertToPigLatin()
	
	
	
	// String conversion methods utilize ' ' space characters to establish breakpoints in a String object.
	// The PigLatin String object which is essentially an array of char type characters is spliced at ' ' and 
	// an OUTPUT String object (char array) sorts 1st position character in the English word, which in PigLatin is the third last letter
	// and excludes 'ay'.
	// i.e. last three letters of Pig Latin word are 1) cognate English word's first letter, 2) 'a' and 3) 'y'.
	// for loop iterates through the PigLatin String object and int variables start and stop store array positions,
	// to mark the start postion and stop position of every ' ' blank space separated word.
	
	public static String forConvertToEnglish(String input) {
		
		System.out.println("\nYour message translated to English is: ");
		
		int start = 0;
		int stop = 0;	
	
		for (int i=0;i<input.length();i++) {
			
			// OUTPUT English word cognate when array iteration encounters a blank space ' ' word separation mark. 		
			// variable stop iterates with the for loop counter i, 
			// therefore stop is always positioned at a blank space ' ' word separation mark, when OUTPUT logic is implemented.		
			if (i < input.length() && input.charAt(i) == ' ') {
				
				OUTPUT += input.charAt(stop-3) + input.substring(start,(stop-3)) + " "; //(stop-3) positions at 'English_word_firstletter + ay'
				// For the first word in the String Object, start == 0.
				// For every subsequent word encountered in String array iteration:			
				// start is repositioned at first letter char of next word when array iteration encounters a blank space ' ' word separation mark.
				start=i+1;	

			}				
			stop++;
		
		}
		
		//Output final word in string conversion.
		OUTPUT += input.charAt(stop-3) + input.substring(start,(stop-3));
		System.out.println(OUTPUT);
		System.out.println("--------------------------------");
		
		return OUTPUT;
		
	} // end method convertToEnglish()
	
	
	
	// method convertToPigLatin(String input) converts English string to PigLatin. 
	// While loops work similar to for loops, utilizing  ' ' blank space characters to 
	// establish start and stop breakpoints for creating substring positions and isolate 
	// a word.
	
	public static String convertToPigLatin(String input) {
		
		System.out.println("\nYour message translated to PigLatin is: ");
		
		int start = 0;
		int stop = 0;
		OUTPUT = "";
	
		while (stop < input.length()) {
			
			start = stop;
			while (start < input.length() && input.charAt(start) == ' ') {			
				start++;				
			}
			
			stop = start;
			while (stop < input.length() && input.charAt(stop) != ' ') {				
				stop++;
			}
			
			OUTPUT += input.substring((start+1),stop) + input.charAt(start) + "ay";
			// add single blank space between words in String
			if(stop != input.length()-1) {
				OUTPUT += " "; 
			}
								
		}
		
		System.out.println(OUTPUT);
		System.out.println("--------------------------------");
		
		return OUTPUT;
		
	} // end method String convertToPigLatin(String input)
		
		
	
	// method convertToEnglish(String input) converts PigLatin string to English. 
	// While loops work similar to for loops, utilizing  ' ' blank space characters to 
	// establish start and stop breakpoints for creating substring positions and isolate 
	// a word.
	
	public static String convertToEnglish(String input) {
		
		System.out.println("\nYour message translated to English is: ");
		
		int start = 0;
		int stop = 0;
		OUTPUT = "";		
	
		while (stop < input.length()) {
			
			start = stop;
			while (start < input.length() && input.charAt(start) == ' ') {			
				start++;	
			}
			
			stop = start;
			while (stop < input.length() && input.charAt(stop) != ' ') {				
				stop++;
			}
			
			OUTPUT += input.charAt((stop-3)) + input.substring(start,(stop-3));
			//add single blank space between words in String
			if(stop != input.length()-1) {
				OUTPUT += " "; 
			}
								
		}
		
		System.out.println(OUTPUT);
		System.out.println("--------------------------------");
		
		return OUTPUT;
		
	} // end method String convertToPigLatin(String input)
	
		
	
	// Add methods for handling messages that do not fit the INPUT criteria... 
	// ie the methods can return out of bounds exceptions for certain messages.
	// The while loop methods truncate spaces before and inside messages, however extra 
	// spaces at end of string are not handled. In contrast, for loop methods do not handle 
	// any extra spaces in the message.
	
	
	
} //end Program class PigLatin	



