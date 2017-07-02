/*
* Name: <Shabab Ali>
* Program Name: <Maze>
* Program Description: <A Java program (verified compiler - JDK8u131) that
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
import java.util.Arrays;



public class Maze {
	
	
	// Scanner object console stores user inputs.
	public static Scanner console = new Scanner(System.in);


	public static int rows;
	public static int cols;
	public static int[][] map;

/*  
  // you can use this method to clear the screen of the console LINUX
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
*/
   
   
  // method clearScreen() will clear the Console screen for WINDOWS. 
  public static void clearScreen() throws IOException, InterruptedException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
  }
   
  
  
  // 
  public static void printRoute(int[][] a, Point p) {

    int rows = a.length; // 1st dimension of 2D Array - analogous to global variable r
    int cols = a[0].length; // 2nd dimension of 2D Array - analogous to global variable cols

    for (int i=rows-1; i>=0; i--) {

      for (int j=0; j<cols; j++) {

        if (p.x == j && p.y == i) {
          System.out.print("X");
          a[i][j] = 1;
        } else if (a[i][j] == 0) {
          System.out.print("#");
        } else {
          System.out.print(".");
        }

      }
      System.out.println();
    }

  } // end method printRoute(int[][] a, Point p)
  
  public static void printDirections() {
    // you need to setup your parameters
    
    // your code here
  }
  
  
  public static boolean updatePosition() {
    // you need to setup your parameters
    
    // your code here, and change the return statement accordingly
    return false;
  }
  
  
  
  // Method generateMap() creates a File object and Scanner object to read 
  // map grid informtation from 'maze.txt' file. The method stores the information into
  // gloabl variables: rows (row information), cols (column information), and 
  // 2D Array map ([rows][cols]).
  public static void generateMap() throws FileNotFoundException {
	  
	File inF = new File ("maze.txt");
	Scanner inputF = new Scanner(inF);
	
	// Obtain a grid of rows and columns from inputF File object - "maze.txt"
	// and establish 2D Array map dimensions
	rows = inputF.nextInt();
	cols = inputF.nextInt();
	
	map = new int [rows][cols];
	
	// fill maze info into 2D int Array map. Rows are read in reverse order to create 
	// refference semantics that follow a Cartesian Plane. Therefore, grid positions 
	// in the 2D Array map can be refferenced with a Point object analogous to a Cartesian (x,y) co-oridnate.
	// ie Point (0,0) will be map [row:0][cols:0]
	for (int i=rows-1; i>=0; i--) {
		
		for (int j=0; j<cols; j++) {
				map[i][j] = inputF.nextInt();
		}
		
	}
	  
  } // end method generateMap()


  
  // Method printMap() is a test method that uses map informtaion global variables, 
  // rows, cols and 2D array map to print the maze grid to the console.  
  public static void printMap() {
	  
	System.out.println (rows);
	System.out.println (cols);

	System.out.println();
	for (int i=rows-1; i>=0; i--) {
		
		for (int j=0; j<cols; j++) {
				System.out.print(map[i][j]);
		}
		System.out.println();
	
	}
  
  } // end method printMap()  
  
  
  
  // method introOutput() prints an introduction script to the console and
  // prompts the user to start the game.
  public static void introOutput() {

	System.out.println("\n------------------");
    System.out.println("Welcome to the maze!");
    System.out.println("Your current position is marked with an X.");
    System.out.println("To leave, find your way to the top-right corner.");
    System.out.print("Press Enter to start!");
    console.nextLine();

  } // end method introOutput()
  
  
  
  // method finishOutput() prints a game finished script to the console.
  public static void finishOutput() {
	  
	System.out.println("\n------------------");
    System.out.println("Congratulations!");
    System.out.println("You finished the maze!");  
	  
  } // end method finishOutput()
  
  
  
  public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
    
	// read map information from 'maze.txt' and store map in 2D array: map [rows][cols].  
	generateMap();
	
	// start game.
	introOutput();
	
	// test method to printMap from global map variables: rows, cols and 2D array map.
	printMap();
	

	
	
	Point p = new Point (0,0);
	printRoute(map,p);
	
	
	// finish game.
	finishOutput();
	
  } // end method main(String[] args)
  
}