/*
* Name: <Shabab Ali>
* Student Number: <V00651717>
* Program Name: <Picture>
* Program Description: <A Java program (verified compiler - JDK8u131 run on Windows 7) that
						reads ASCII art from a file and prints the file and animations to the screen. 
						The program overviews:
						- procedural decomposition to break down a problem into methods.
						- Classes to break down the programs parts.
						- File class to read from a file.
						- Scanner to process file input.
						- 2D arrays and text (.txt) files to store data.
						- printing text art image using 2D arrays and Objects.
						- basic procedural distribution in graphics.>
*/


import java.util.Random;
import java.io.FileNotFoundException;


public class Picture {
	
  
  // method fillBarnacles(Sprite a, Screen panel) generates random points inside
  // a Sprite object's boundary (2 chars from image edge) and draws '*' characters at the
  // respective points. These random '*' points are illustrated to the console by drawing on to a Screen object.    
  public static void fillBarnacles(Sprite a, Screen panel) {
	
	Point[] pts = new Point[25];
    Random rand = new Random();
    
    for (int i=0; i<pts.length; i++) {
      double rx = rand.nextInt(a.width + 4) + (a.p.x - 2); //Screen domain [-2x from Sprite corner to +2x from Sprite end]
      double ry = rand.nextInt(a.height + 4) + (a.p.y - 2); //Screen range [-2y from Sprite corner to +2y from Sprite end]
      pts[i] = new Point( rx, ry );
      // prep for drawing by connectin to a Screen object
      pts[i].put(panel);
    }
	// draw random generated points to the Screen object
	for (int i=0; i<pts.length; i++) {
      pts[i].draw('*');
    }
	
  } // end method fillBarnacles(Sprite a, Screen panel)
	
  
  // method main(String[] args) instantiates 3 Sprite objects stored in a 2D array. 
  // The alien Sprite objects contain ASCII graphic art .
  // Sprite instance methods put()/connect the Sprite and draw() to the Screen object - window, 
  // Screen instance method draw()/illustrate the filled Screen object with Cartesian plane(x,y) to the console.
  public static void main(String[] args) throws FileNotFoundException {

	Random r = new Random();
    
	// clear() is called in the constructor
	// you don't need to call clear() to set up anymore with new version of Screen. 
    Screen window = new Screen();
       
    // set up your sprite filenames here
    String[] filenames = { 
      "alien1.txt",
      "alien2.txt",
      "alien3.txt" 
    };
	
    // make a Sprite array alien containing 3 instantiated prite objects
	Sprite[] alien = new Sprite[3]; 
	for (int i=0; i<3; i++) {
		alien[i] = new Sprite(filenames[i]); 
	}
	
	// print 9 graphics from 3 Sprite objects to the Screen object - window
	for (int c=0; c<3; c++) {
		
	  for (int i=0; i<3; i++) {
		  // set random origins or corners for placing the Sprite on a Screen object
		  // Screen size (2D array) is rows(y) = 48; cols(x) = 150;
		  alien[i].setCorner(r.nextInt(125)+5, r.nextInt(25)+5);	  
		  // draw 25 random '*' characters inside a Sprite object 2 char boundary
		  fillBarnacles(alien[i], window);
		  // draw Sprite alien instances (non-blank space characters only) 
		  // over the Barnacles onto the Screen object window
		  alien[i].put(window);
		  alien[i].draw();
	  }  
		
	}   
    window.draw();

  } // end method main(String[] args)

  
} // end Program class Picture