import java.util.Random;
import java.io.FileNotFoundException;

public class Picture {
	
	
  public static void main(String[] args) throws FileNotFoundException {

	Random r = new Random();
    
    Screen window = new Screen();
    // you don't need to call clear() to set up 
    // anymore with new version of Screen

/*    
    // set up your sprite filenames here
    String[] filenames = { 
      "alien1.txt",
      "alien1.txt",
      "alien1.txt" 
    };
*/
    
    // make a Sprite array
	
	Sprite a1 = new Sprite("alien2.txt");
	a1.setCorner(0, 0);
	a1.put(window);
	a1.draw();
    
    // use loops to draw each Sprite at three different Points
    
    window.draw();

	}

  
}