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
  
  }
  
  public void setCorner(double x, double y) {
    // set up Point p
	p = new Point(x,y);
  }
  
  public void put(Screen panel) {
    // set up Screen panels
	this.panel = panel;
  }
  
  public char get(int x, int y) {
	  return art[y][x];
    // return the entry in array art
  }
  
  public void draw() {
    
	// Establish dimensions of Screen object that will receive characters from the Sprite object.
	// Bottom left corner ie art(x,y) = art(0,0) of the Sprite object. 
	// The screen origin or corner position Point p is instantiated by
	// instance method setCorner() call in method main of Program class Picture.
	int startY = (int)p.y;
	int stopY = (int)p.y + (height-1);
	int startX = (int)p.x;
	int stopX = (int)p.x + (width-1);
	
	// send art[][] chars to panel
	int yChar = 0;
	for (int i=startY; i<=stopY; i++) {
		
		int xChar = 0;
		for (int j=startX; j<=stopX; j++) {
			//System.out.print((art[i][j]));
			// don't draw space chars to panel
			if (get(xChar,yChar) != ' ') {
				panel.set( j, i, get(xChar,yChar) );
			}
			xChar++;
		}
		yChar++;
		//System.out.print(" 			" + y);
		//System.out.print(" 			" + x);
		//System.out.println();

	}
	
    
    
  }
}