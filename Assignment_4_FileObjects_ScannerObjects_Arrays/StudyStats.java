/*
* Name: <Shabab Ali>
* Program Name: <StudyStats>
* Program Description: <A Java program (verified compiler - JDK8u131) that processes a study log file and generates student information.
						The program utilizes: 
						 - procedural decomposition to break down a problem into methods.
						 - while loops to process variable length input.
						 - File class to read from a file.
						 - Scanner to process user input.
						 - Arrays to process data.
*/


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class StudyStats {

	
	
  public static final int MAX = 5000;
  
  static String[] months = new String[MAX];
  static int[] days = new int[MAX];
  static int[] start = new int[MAX];
  static int[] stop = new int[MAX];
  static String[] activities = new String[MAX];
  static String[] locations = new String[MAX];
  // n is an index for 'file-information populated' elements in the arrays.
  // n is established in correlation with the implementation of Scanner reading 
  // a new line, containing String and integer input tokens in the Program's File object.  
  static int n = 0;  
  
  
  
	// method int timeInMins(int time):
	// INPUT: time in military format
	// OUTPUT: returns number of minutes from midnight to time
	
	public static int timeInMins(int time) {
	
		int timeHours = (time/100);
		int timeMin = time % 100;
		int minAfterMidNight = (timeHours*60) + timeMin;
		return minAfterMidNight;

	} // end method int timeInMins(int time)
  
  
  
	// method int entryMins(int start, int stop):
	// INPUT: start and stop in military format
	// OUTPUT: returns number of total minutes from start to stop
	
	public static int entryMins(int start, int stop) {

		int timespan = timeInMins(stop)-timeInMins(start);
		return timespan;

	} // end method int entryMins(int start, int stop)
  
  
  
	// method double aveStudyMins():
	// INPUT: uses arrays start and stop previously filled by file
	// OUTPUT: returns the average study time in minutes among entries
	
	public static double aveStudyMins() {
    
		int timespanSum = 0;
		double timespanAverage;
	
		for (int i =0;i<n;i++) {
			timespanSum += entryMins(start[i], stop [i]);
		}
		timespanAverage = (double)timespanSum/n;
	
		return timespanAverage;
  
	} // end method double aveStudyMins()
  
  
  
	// method int maxStudyTimeEntry():
	// INPUT: uses arrays start and stop previously filled by file
	// OUTPUT: returns the array index of the entry with the maximum minutes studying
	
	public static int maxStudyTimeEntry() {
	
		int max = 0;
		int indexMaxStudyTime = 0;
	
		for (int i=0;i<n;i++) {
	
			int timespan = entryMins(start[i], stop[i]);
			if (timespan > max) {
				//store max timespan value of the iterated set of study records
				max = timespan; 
				//store index position of max timespan value
				indexMaxStudyTime = i;
			}	

		}
		return indexMaxStudyTime;

	} // end method int maxStudyTimeEntry()
  
  
  
	// method printEntry(int k) outputs study journal lists to the console. 
	// Formal paramater int k passes array index value arguments established by variable n
	//  in method main(String[] args)
	
	public static void printEntry(int k) {
		
		System.out.print( months[k] + " " );
		System.out.print( days[k] + " " );
		System.out.printf("%04d ", start[k]);
	    System.out.printf("%04d ", stop[k]);
	    System.out.print( activities[k] + " " );
		System.out.println( locations[k] + " " );
	
	} // end method printEntry(int k)
	
	
	
	// method checkLineInput() tests n indexing for elements in String[] months, int[] days, int[] start, int[] stop, String[] activities, and String[] locations.
	// Additionally, method checkLineInput() tests return values for method int timeInMins(int time) and method int entryMins(int start, int stop). 
	
	public static void checkLineInput() {
		
		System.out.println();
		Scanner console = new Scanner(System.in);
		int userEntrySelection;
		do {
			System.out.print("Please select study log entry #1 - " + n + ": ");
			userEntrySelection = console.nextInt();
		} while (userEntrySelection < 1 || userEntrySelection > n);
		
		int index = userEntrySelection-1; 
		
		System.out.println("\nEntry # " + userEntrySelection);
		printEntry(index);
		System.out.println ("\nStart time in minutes after midnight: " + timeInMins(start[index]));
		System.out.println ("Stop time in minutes after midnight: " + timeInMins(stop[index]));
		System.out.println ("Study timespan in minutes: " + entryMins(start[index], stop[index]));
		
	} // end method checkLineInput()
	
  
  
  
	public static void main(String[] args) throws FileNotFoundException {
		
		File inF = new File("study_log_check.txt");
		Scanner inputF = new Scanner(inF);
    
		while (inputF.hasNextLine()) {
			months[n] = inputF.next();
			days[n] = inputF.nextInt();
			start[n] = inputF.nextInt();
			stop[n] = inputF.nextInt();
			activities[n] = inputF.next();
			locations[n] = inputF.next();
      
			printEntry(n);
      
			n++;
		}
		System.out.println();
    
		double ave = aveStudyMins();
		System.out.println("Your average study session in minutes is: " + ave);
		int k = maxStudyTimeEntry();
		System.out.println("Your maximum time study session was:");
		printEntry(k);
		
		//checkLineInput();
  
	} // end method main(String[] args)
  
  
  
} //end program class StudyStats