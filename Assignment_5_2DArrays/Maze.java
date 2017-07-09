/*
* Name: <Shabab Ali>
* Program Name: <Maze>
* Program Description: <A Java program (verified compiler - JDK8u131 run on Windows 7) that
						lets a user play a game navigating a maze. The program utilizes:
						- procedural decomposition to break down a problem into methods.
						- File class to read from a file.
						- Scanner to process file input.
						- 2D arrays to store data.
						- print a grid map using 2D arrays.>
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;
import java.io.IOException;



public class Maze {
	
	
	
	// Scanner object console stores user inputs.
  public static Scanner console = new Scanner(System.in);

  public static int rows;
  public static int cols;
	
	
/*  
  // you can use this method to clear the screen of the console in LINUX
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
*/
   
   
  // method clearScreen() will clear the Console screen in WINDOWS 7. 
  public static void clearScreen() throws IOException, InterruptedException {
  
	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
  
  } // end method clearScreen()
  
  
  
  // method generateMap() creates a File object and Scanner object to read maze grid informtation from the 'maze.txt' file. 
  // The method - 1)stores dimension information into global variables: rows (row numbers) and cols (column numbers) - 
  // 2)returns a 2D Array map ([rows][cols]) of the 'maze.txt' blueprint.
  public static int[][] generateMap() throws FileNotFoundException {
	  
	File inF = new File ("maze.txt");
	Scanner inputF = new Scanner(inF);
	
	// Obtain a grid of rows and columns from inputF File object - "maze.txt" and establish 2D Array map dimensions.
	rows = inputF.nextInt();
	cols = inputF.nextInt();
	
	int[][] map = new int [rows][cols];
	
	// fill maze information into 2D Array map. Rows are read in reverse order to create 
	// reference semantics that follow a Cartesian Plane. Therefore, grid positions 
	// in the 2D Array map can be referenced with a Point object analogous to a Cartesian (x,y) co-ordinate.
	// ie Point (0,0) will be map [row:0][cols:0]
	for (int i=rows-1; i>=0; i--) {
		
		for (int j=0; j<cols; j++) {
				map[i][j] = inputF.nextInt();
		}
		
	}
	
	return map;
	  
  } // end method generateMap()


  
  // method printMap(int[][] m) is a test method that uses a 2D array input parameter and the global variables rows and cols,
  // to print the 2D Array map's values to the console.  
  public static void printMap(int[][] m) {
	
	int rows = m.length;
    int cols = m[0].length;
	
	System.out.println ("rows: " + rows);
	System.out.println ("columns: " + cols);

	System.out.println();
	for (int i=rows-1; i>=0; i--) {
		
		for (int j=0; j<cols; j++) {
				System.out.print(m[i][j]);
		}
		System.out.println();
	
	}
  
  } // end method printMap(int[][] m)
  
   
     
  // method setRoute() returns a 2D Array route with the maze grid dimensions ie rows and columns and default 0 map values: 
  // the variable assignment pointing to the 2D Array route is updated in the GAMEPLAY track by method updateRoute(int[][] a, Point p) called in method main().
  public static int[][] setRoute() {
	
	// Set dimensions for route tracking 2D Array global variable route.
	int[][] route = new int [rows][cols];
	
	// Establish marker for route start.
	route[0][0] = 1;
	  
	return route;
	
  }  // end method setRoute()
  
  
  
  // method printRoute(int[][] a, Point p) prints the gameplay route 2D Array.
  // The method uses the Point object p parameter to reference the 2D Array's grid and prints the current user position 'X', 
  // unchartered default array positions are printed with '#' and the route track with '.'  
  public static void printRoute(int[][] a, Point p) {

	System.out.println();
    for (int i=rows-1; i>=0; i--) {

      for (int j=0; j<cols; j++) {
        if (p.x == j && p.y == i) {
          System.out.print("X");
        } else if (a[i][j] == 0) {
          System.out.print("#");
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
    }

  } // end method printRoute(int[][] a, Point p)
  
  
  
  // method updateRoute(int[][] a, Point p) updates the gameplay route 2D Array by using the Point p object
  // as a reference to convert the default array value from 0 to 1, leaving a user route track history.  
  public static void updateRoute(int[][] a, Point p) {

	for (int i=rows-1; i>=0; i--) {

      for (int j=0; j<cols; j++) {
        if (p.x == j && p.y == i) {
          a[i][j] = 1;
        }
      }

    }

  } // end method updateRoute(int[][] a, Point p)
  
  
  
  // method printDirections(boolean left, boolean right, boolean up, boolean down) uses boolean parameters to print 'in-game'
  // acceptable directions to the user and prompt for the next move.
  public static void printDirections(boolean left, boolean right, boolean up, boolean down) {

    if (left == true)
      System.out.println("You can move left.\n(Type L for left)");
    if (right == true)
      System.out.println("You can move right.\n(Type R for right)");
    if (up == true)
        System.out.println("You can move up.\n(Type U for up)");
    if (down == true)
      System.out.println("You can move down.\n(Type D for down)");
  	
	System.out.println("------------------");
    System.out.print("Which way will you go? ");
	
  } // end method printDirections(boolean left, boolean right, boolean up, boolean down)
  
  
  
  // method updatePosition(String response, boolean left, boolean right, boolean up, boolean down, Point p) updates the Point p object
  // for the user's position 'in-game' utilizing the user input response parameter and correlating the user's input with the boolean state of acceptable
  // 'in-game' direction options. 
  public static boolean updatePosition(String response, boolean left, boolean right, boolean up, boolean down, Point p) {
	
	if (response.equalsIgnoreCase("L") && left) {
        p.x--;
    } else if (response.equalsIgnoreCase("R") && right) {
        p.x++;
    } else if (response.equalsIgnoreCase("U") && up) {
        p.y++;
    } else if (response.equalsIgnoreCase("D") && down) {
        p.y--;
    } else {
        return false;
    }
    return true;
    
  } // end method updatePosition(String response, boolean left, boolean right, boolean up, boolean down, Point p)
  

  
  // method introOutput() prints an introduction script to the console and prompts the user to start the game.
  public static void introOutput() {

	System.out.println("\n------------------");
    System.out.println("Welcome to the maze!");
    System.out.println("Your current position is marked with an X.");
    System.out.println("To leave, find your way to the top-right corner.");
    System.out.print("Press Enter to start!");
    console.nextLine();

  } // end method introOutput()
  
  
  
  // method finishOutput() prints a game-over script to the console.
  public static void finishOutput() {
	  
	System.out.println("\n------------------");
    System.out.println("Congratulations!");
    System.out.println("You finished the maze!");  
	  
  } // end method finishOutput()
  
  
  
  public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
    
	// Read map information from 'maze.txt' and store map in 2D Array: map [rows][cols]. 
	// Also, assign values for global map dimension variables rows and cols.   
	int[][] gameMap = generateMap();
	
	// Test method to print grid values mapped inside a 2D array.
	//printMap(gameMap);
	
	// START game - output introduction to console/user.
	introOutput();
	clearScreen();
		
	// Establish route tracking with 2D Array gameRoute and co-ordinate referencing via Point object
	int[][] gameRoute = setRoute();
	Point p = new Point (0,0);
	
	// initialize GAMEPLAY variables
	boolean blocked = false; //default setting is not blocking to allow for a userResponse to updatePosition
	boolean leftOption, rightOption, upOption, downOption; 
	String userResponse;
	
	// GAMEPLAY
	while (!(p.x == cols-1 && p.y == rows-1)) {
		
		clearScreen();
		
		if (blocked) {
        System.out.println("You cannot go that way!");
        blocked = false;
		}
	
		// Print 2D Array gameRoute to the console. 
		printRoute(gameRoute, p);
		System.out.println();
		
		// if boolean conditions are true, user can move in the direction, therefore method updatePosition() moves the Point object and returns true,
		// else method updatePosition() does not move Point object and returns false. Furthermore, boolean blocked is assigned true and user/console will receive warning.
		leftOption  = p.x > 0        && gameMap[p.y][p.x-1] == 1;
		rightOption = p.x < (cols-1) && gameMap[p.y][p.x+1] == 1;
		upOption    = p.y < (rows-1) && gameMap[p.y+1][p.x] == 1;
		downOption  = p.y > 0        && gameMap[p.y-1][p.x] == 1;
	
		// Print acceptable directions based on boolean values.
		printDirections(leftOption, rightOption, upOption, downOption);
        userResponse = console.next();
		
		// blocked is true if userResponse is an unnaceptable direction. 
		blocked = !updatePosition(userResponse, leftOption, rightOption, upOption, downOption, p);
		
		// Assigns a mark to 2D Array gameRoute
		updateRoute(gameRoute, p);
		
	}
	
	clearScreen();
    printRoute(gameRoute, p);
	
	// print finish game script to user.
	finishOutput();
	
  } // end method main(String[] args)
  
  
  
} // end Program class Maze