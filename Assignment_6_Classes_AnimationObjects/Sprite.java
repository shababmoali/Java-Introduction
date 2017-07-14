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
	f.nextLine();
    // remember to call an extra nextLine() after nextInt() when reading the file
    
    // you need to use nextLine() and not next(), in order to
    // allow you to use space chars in the sprite file
    
    // setup the characters in array art
	//String s;
	art = new char[height][width];
    for (int i=height-1; i>=0; i--) {
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
    // send chars to panel
	for (double i=p.y; i<height; i++) {
		for (double j=p.x; j<width; j++) {
			//System.out.print((art[i][j]));
			panel.set( (int)j, (int)i, get((int)j,(int)i) );
		}      
		//System.out.println();
	}
	
    
    // don't draw space chars to panel
  }
}