import java.util.Random;
import java.io.FileNotFoundException;

public class Picture {
	
	
  public static void main(String[] args) throws FileNotFoundException {

	Random r = new Random();
    
    Screen window = new Screen();
    // you don't need to call clear() to set up 
    // anymore with new version of Screen


    
    // set up your sprite filenames here
    String[] filenames = { 
      "alien1.txt",
      "alien2.txt",
      "alien3.txt" 
    };

	
    // make a Sprite array
	Sprite[] alien = new Sprite[3]; 
	for (int i=0; i<3; i++) {
		alien[i] = new Sprite(filenames[i]); 
	}
	
	// print 9 Sprite objects to the Screen objects
	for (int c=0; c<3; c++) {
		
	  for (int i=0; i<3; i++) {	
		  alien[i].setCorner(r.nextInt(130), r.nextInt(30));
		  alien[i].put(window);
		  alien[i].draw();
	  }  
		
	}

	

/*
	Sprite a1 = new Sprite("alien1.txt");
	a1.setCorner(10, 25);
	a1.put(window);
	a1.draw();

*/	
    
    // use loops to draw each Sprite at three different Points
    
    window.draw();

	}

  
}