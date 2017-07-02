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
   
  
  public static void printRoute(int[][] a, Point p) {
    // you need to setup your parameters
    
    // your code here
  }
  
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
	
	rows = inputF.nextInt();
	cols = inputF.nextInt();
	
	map = new int [rows][cols];
	
	for (int i=0;i<rows;i++) {
		
		for (int j=0;j<cols;j++) {
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
	for (int i=0;i<rows;i++) {
		
		for (int j=0;j<cols;j++) {
				System.out.print(map[i][j]);
		}
		System.out.println();
	
	}
  
  } // end method printMap()  
  
  
  
  public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
    
    
	// read map information from 'maze.txt' and store map in 2D array: map [rows][cols]  
	generateMap();
	
	//test method to printMap from global map variables: rows, cols and 2D array map
	printMap();
	

	
	
	
	
  } // end method main(String[] args)
  
}