import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Sprite {
	
  Screen panel;
  private char[][] art;
  Point p;
  int width;
  int height;
  
  
  // this is the constructor we want to use when initializing objects
  public Sprite(String filename) throws FileNotFoundException {
    
	File inF = new File(filename);
	Scanner f = new Scanner(inF);
	height = f.nextInt();
	width = f.nextInt();
	// remember to call an extra nextLine() after nextInt() when reading the file
	// java Scanner has a bug calling nextLine() after nextInt()  
	f.nextLine();
    
    // setup the characters in array art	
	art = new char[height][width];
    for (int i=height-1; i>=0; i--) {
	    // you need to use nextLine() and not next(), in order to
		// allow you to use space chars in the sprite file - read through String s;
    	String s = f.nextLine();
		for (int j=0; j<s.length(); j++) {
			art[i][j] = s.charAt(j);
		}
		// remember to fill extra spaces at end of each row where needed
		for (int k=s.length(); k<width; k++) {
			art[i][k] = ' ';
		}
      
	}
  
  } // end Sprite(String filename) constructor
  
  
  public void setCorner(double x, double y) {
    // set up Point p
	p = new Point(x,y);
  }
  
  
  public void put(Screen panel) {
    // set up Screen panels
	this.panel = panel;
  }
  
  
  public char get(int x, int y) {
	// return the entry in array art
	return art[y][x];
  }
  
  
  public void draw() {
    
	// Establish the dimensions in the Screen object that will receive characters from the Sprite object.
	// Bottom left corner of the Sprite object is art(0,0) 
	//   ie astract the 2D Sprite array art[rows][columns] to art(x,y) for drawing to a Screen object - panel. 
	// Instance method setCorner(), called in main(), assigns the screen origin and inherent Sprite corner position Point p.
	// Screen instance method set(), draws Sprite characters to the correpsonding Screen grid.
	int startY = (int)p.y;
	int stopY = (int)p.y + (height-1);
	int startX = (int)p.x;
	int stopX = (int)p.x + (width-1);
	
	// send art[][] chars to panel
	int yChar = 0;
	for (int i=startY; i<=stopY; i++) {
		
		int xChar = 0;
		for (int j=startX; j<=stopX; j++) {
			// don't draw space chars to panel
			if (get(xChar,yChar) != ' ') {
				panel.set( j, i, get(xChar,yChar) );
			}
			xChar++;
		}
		yChar++;

	} 
    
  } // end draw()
  
  
} // end class Sprite