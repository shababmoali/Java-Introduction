import java.lang.Thread;
import java.io.IOException;

/* Russell Campbell
   30 June 2017
   CSC 110 Fundamentals of Programming I
   
   This program gives a demonstration of some animations you can do with math.
   Results may vary depending on the system you run it on.
*/

public class Animation {
  // adjust values for desired effects
  
  // setup the size of the screen you want to animate, units are number of characters
  final static int ROWS = 55;
  final static int COLS = 175;
  final static double DIAGONAL = Math.sqrt(ROWS*ROWS + COLS*COLS);
  
  // characters are discrete values, so there will be a lot of blockiness to everything
  final static double SCALE = 8.0;
  final static double SPEED = 0.1; // higher is faster
  // lower jitter creates more regular pattern in worley noise
  final static double JITTER = 1.0;
  // FRAME_RATE will depend on the computations you are doing in your animation
  // and so it may not be possible to control the exact amount of time per frame
  final static int FRAME_RATE = 30;
  
  // I suspect this only works for Windows less than version 10?
  // Make sure to import java.io.IOException, and remove the other clearScreen functions
  // 
  public static void clearScreen() throws IOException, InterruptedException {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
  }
  
  // I don't have a Windows 10 machine to try this on, but something like this
  // might work, but then you have to run your program from powershell instead of cmd
  // please let me know if you can get this working!
  // 
  //public static void clearScreen() throws IOException, InterruptedException {
  //  new ProcessBuilder("powershell.exe", "-Command", "cls").inheritIO().start().waitFor();
  //}
  
  //public static void clearScreen() {  
  //  System.out.print("\033[H\033[2J");  
  //  System.out.flush();  
  //}
  
  public static void resetCursor() {
    System.out.print("\033[0;0H");
  }
  
  // note that the 2D plane y axis is not flipped in any of these animations!
  public static void print2D(char[][] a) {
	String s = "";
    for (int i=0; i<ROWS; i++) {
      for (int j=0; j<COLS; j++) {
        s+=a[i][j];
      }
      s+="\n";
    }
	System.out.print(s);
  }
  
  // generates random value between 0 inclusive and 1 exclusive
  public static double rand(int[] vec, int p1, int p2) {
    return Math.abs((9437 * Math.sin(p1*vec[0] + p2*vec[1] + vec[2])) % 1.0);
  }
  
  public static void randomPoint3D(int[] vec, double[] point) {
    // numbers below are prime, and help control the *different* randomness for each dimension we need
    point[0] = rand(vec, 43, 1009);
    point[1] = rand(vec, 73, 2069);
    point[2] = rand(vec, 97, 3023);
  }
  
  public static void scale(double[] vec) {
    vec[0] /= SCALE;
    vec[1] /= SCALE;
    vec[2] *= SPEED;
  }
  
  public static void floor(double[] vec, int[] Pi) {
    for (int i=0; i<vec.length; i++) {
      Pi[i] = (int)vec[i];
    }
  }
  
  public static void fract(double[] vec, double[] Pf) {
    for (int i=0; i<vec.length; i++) {
      Pf[i] = vec[i] - (int)vec[i];
    }
  }
  
  public static void add(int[] a, int[] b) {
    for (int i=0; i<a.length; i++) {
      b[i] += a[i];
    }
  }
  
  public static void distanceVector(double[] Pf, double[] p, int[] cube, double[] v) {
    for (int i=0; i<p.length; i++) {
      v[i] = Pf[i] - (JITTER*p[i] + cube[i]);
    }
  }
  
  public static double dotProduct(double[] x, double[] y) {
    double d = 0;
    for (int i=0; i<x.length; i++) {
      d += x[i]*y[i];
    }
    return d;
  }
  
  // If you want to read about Worley Noise then check out the following journal article:
  // http://www.rhythmiccanvas.com/research/papers/worley.pdf
  
  // This is improved slightly from what I showed in class
  // more variability in the waves
  // faster computation by dealing with memory for variables better
  public static void worley(double[] P,
                                int[] Pi,
                                double[] Pf,
                                int[] corner,
                                int[] cube,
                                double[] p,
                                double[] v,
                                double[] dist) {
    scale(P);
    floor(P, Pi);    // integer part
    fract(P, Pf); // fractional part
    
    // vairables for minimum distances
    double d1 = 99;
    double d2 = 99;
    double d;
    // iterate through the 26 cubes surrounding and the cube at this corner
    for (int x=-1; x<2; x++) {
      for (int y=-1; y<2; y++) {
        for (int z=-1; z<2; z++) {
          corner[0] = cube[0] = y;
          corner[1] = cube[1] = x;
          corner[2] = cube[2] = z;
          // update corner to actual position in 3D space
          add(Pi, corner);
          // generates same random point in 3D space based off of the corner of the cube we are in
          // stores result in p, and each element of p is a value between 0 and 1
          randomPoint3D( corner, p );
          // distance vector of current location in 3D space to random point
          // stores result in v
          distanceVector( Pf, p, cube, v );
          // the square root of d is the actual distance, but we can
          // avoid expensive Math.sqrt operation for now and still find minimum
          d = dotProduct(v, v);
          if ( d < d1 ) {
            // closest distance
		    d2 = d1;
		    d1 = d;
		  } else if ( d < d2 ) {
            // second closest distance
		    d2 = d;
		  } // more distances could be calculated if desired
        }
      }
    }
    
    // now take square root AFTER triple nested for loop
    dist[0] = Math.sqrt(d1);
    dist[1] = Math.sqrt(d2);
  }
  
  // generate random value for corner of square in 2D plane
  public static double noise(double[] P, int[] Pi) {
    scale(P);
    floor(P, Pi); // integer part
    return rand(Pi, 43, 1009);
  }
  
  public static double circle(double[] P) {
    scale(P);
    P[0] -= (COLS/SCALE)/2.0;
    P[1] -= (ROWS/SCALE)/2.0;
    return Math.sqrt(P[0]*P[0] + P[1]*P[1]);
  }
  
  public static void fillWorley(char[][] a, long z) {
    // avoid setup of memory (keyword new) inside nested for loops
    // asking for memory to use with variables is computationally expensive
    // p, v, corner, cube, Pi, Pv variables are used for worley method
    double[] p = new double[3];
    double[] v = new double[3];
    
    // variable to iterate through one corner of each of 27 cubes, i.e. 3*3*3 set of cubes
    // ultimately, we want to find the closest random point to current location in surrounding cubes
    int[] corner = new int[3];
    int[] cube = new int[3];
    
    int[] Pi = new int[3]; // for integer part of point P
    double[] Pf = new double[3]; // for fractional part of point P
    
    // these variables are for inside the following nested loops
    double[] point = new double[3];
    double[] d = new double[2];
    double f;
    
    for (int i=0; i<ROWS; i++) {
      for (int j=0; j<COLS; j++) {
        point[0] = j;
        point[1] = i;
        point[2] = z;
        // variable d will contain values for first closest and second closest distance
        worley( point, Pi, Pf, corner, cube, p, v, d );
        // different linear combinations of the two distances gives different patterns
        f = (2*d[0] + d[1]) / 3;
        if (f > .8) {
          a[i][j] = '#';
        } else if (f > .75) {
          a[i][j] = '*';
        } else if (f > .65) {
          a[i][j] = '+';
        } else if (f > .5) {
          a[i][j] = '.';
        } else {
          a[i][j] = ' ';
        }
      }
    }
  }
  
  public static void fillNoise(char[][] a, long z) {
    int[] Pi = new int[3];
    for (int i=0; i<ROWS; i++) {
      for (int j=0; j<COLS; j++) {
        double[] point = { j, i, z };
        double f = noise( point, Pi );
        if (f > .9) {
          a[i][j] = '@';
        } else if (f > .8) {
          a[i][j] = '"';
        } else if (f > .7) {
          a[i][j] = '&';
        } else if (f > .6) {
          a[i][j] = 'X';
        } else if (f > .5) {
          a[i][j] = 'v';
        } else if (f > .4) {
          a[i][j] = '*';
        } else if (f > .3) {
          a[i][j] = '+';
        } else if (f > .2) {
          a[i][j] = 'O';
        } else if (f > .1) {
          a[i][j] = '.';
        } else {
          a[i][j] = ' ';
        }
      }
    }
  }
  
  public static void fillCircle(char[][] a, long z) {
    long radius = (long)(z*SPEED) % (long)(DIAGONAL/SCALE);
    double[] point = new double[3];
    double f;
    for (int i=0; i<ROWS; i++) {
      for (int j=0; j<COLS; j++) {
        point[0] = j;
        point[1] = i;
        f = circle( point );
        if (radius - 0.5 < f && f < radius + 0.5) {
          a[i][j] = '#';
        } else if (radius - 1.5 < f && f < radius - 0.5) {
          a[i][j] = '*';
        } else if (radius - 2.5 < f && f < radius - 1.5) {
          a[i][j] = '.';
        } else {
          a[i][j] = ' ';
        }
      }
    }
  }
  
  public static void main(String[] args) throws IOException, InterruptedException {
    
    long timeInMillis = 30000; // default 30 seconds
    if (args.length > 0) {
      timeInMillis = Integer.parseInt(args[0]);
    }
    
    // surface represented by characters printed in 2D array
    // indices row by col
    char[][] surface = new char[ROWS][COLS];
    
    // setup loop to run the animation
    long start = System.currentTimeMillis();
    long moment;
    clearScreen();
    long z = 0; // z is third dimension in cubes
    do {
      /* SPEED: 0.1  SCALE: 8  looks pretty good with Worley Noise.
       * Comment out Thread.sleep statement because Worley is computationally expensive
       * for large ROWS and COLS. Your processor will work on a full load with no sleep
       * overworking processor makes a lot of heat
       * so be careful with length of time you run this for!
       */
      fillWorley(surface, z++);
      
      // SPEED: 1.0  SCALE: 3  looks nice
      // remember to uncomment Thread.sleep
      //fillCircle(surface, z++);
      
      // SPEED: 1.0  SCALE: 1  looks like White Noise
      // SPEED: 0.1  SCALE: 4  random squares
      // remember to uncomment Thread.sleep
      // fillNoise(surface, z++);
      
      clearScreen();  // for Windows
      print2D(surface);
      //resetCursor();     // remove this statement for Windows
      
      // thread avoids use of CPU to keep track of time
      //Thread.sleep(1000/FRAME_RATE);
      
      moment = System.currentTimeMillis();
    } while ((moment - start) < timeInMillis);
  }
  
}