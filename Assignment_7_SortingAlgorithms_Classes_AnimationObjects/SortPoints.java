/*
* Name: <Shabab Ali>
* Program Name: <SortPoints>
* Program Description: <A Java program (verified compiler - JDK8u131 run on Windows 7) that
						reads Catesian Points (x,y) from a file and processes the Points - 
						(i) sorting by distance from a center Point
						(ii) sorting by position nearest to top-left Point 
						The file Points are rendered to the console via a Screen object. 
						The program overviews:
						- procedural decomposition to break down a problem into methods.
						- using custom Classes in a client program.
						- reading a file in to a program from the command line.
						- write Selection Sort code.
						- write Bubble Sort code.
						- sorting of elements based on your own comparisons.
						- create a voronoi diagram.>
*/



import java.util.Scanner;



public class SortPoints {

  
  // method swap(Point[] pts, int i, int j): swaps elements between index i and index j
  // in a Point Array object. Point is a custom Class with attributes and behaviour that emulate Cartesian points.
  public static void swap(Point[] pts, int i, int j) {
	  
	Point temp = pts[i];
	pts[i] = pts[j];
	pts[j] = temp;
	    
  } // end swap(Point[] pts, int i, int j)
  
  
  
  // method int readCompare(Point p1, Point p2) evaluates 2 Points on a cartesian plane (x,y),
  // with respect to proximity to the top-left corner:
  // return  1: Points are in correct order
  // return  0: No need to swap equal Points
  // return -1: Points are out of order
  public static int readCompare(Point p1, Point p2) {
	  
    // only care about the position in Screen as an integer
    int x1 = (int)Math.floor(p1.x);
    int y1 = (int)Math.floor(p1.y);
    int x2 = (int)Math.floor(p2.x);
    int y2 = (int)Math.floor(p2.y);
    
    // check for top to bottom order
	if (y1 > y2) {
		return 1;
	} else if (y1 < y2) {
		return -1;
	}
	// at this spot in code then Points p1 and p2 should be in same row
    // so check for left to right order
	if (x1 < x2) {
		return 1;
	} else if (x1 > x2) {
		return -1;
	// at this spot in code position of p1 should equal position of p2
	} else {
		return 0;
	}
    
  } // end int readCompare(Point p1, Point p2)
  

  
  // method readSort(Screen window, Point[] pts) implements Selection Sort 
  // using readCompare to sort Point array []pts.
  public static void readSort(Screen window, Point[] pts) {
    
	int n = pts.length;
    
    // Selection Sort:
	for (int i=0; i<n-1; i++) {
		
		int iMin = i;
		for (int j=i+1; j<n; j++) {
			if (readCompare(pts[iMin], pts[j]) == -1) {	
                iMin = j;
			}
		}
		swap(pts, i, iMin);
	
	}
	
  } // end readSort(Screen window, Point[] pts)
  
  
  
  // method int distCompare(Point p1, Point p2, Point p3) evaluates 2 Points on a cartesian plane (x,y),
  // with respect to proximity to a common/center Point p3:
  // return  1: Point p1 is closer to p3
  // return  0: Points p1 and p2 are roughly equidistant from p3
  // return -1: Point p2 is closer to p3
  public static int distCompare(Point p1, Point p2, Point p3) {
	  
    double d1 = dist(p1, p3);
    double d2 = dist(p2, p3);
    
    // this is a very small threshold to consider two Points of equal distance
    if (d1 < d2 - 0.000001)
      return 1;
    else if (d1 > d2 + 0.000001)
      return -1;
    
    return 0;
  
  } // end int distCompare(Point p1, Point p2, Point p3)


  
  // method distSort(Screen window, Point[] pts) implements Bubble Sort
  // using distCompare from centre of Screen as comparison.
  public static void distSort(Screen window, Point[] pts) {
    int n = pts.length;
    int h = window.rows;
    int w = window.cols;
    Point c = new Point(w/2.0, h/2.0); // centre Point
    
    // Bubble Sort:
	// set flag to true to begin first Sort pass
	boolean flag = true; 
	
	while (flag) {
		//set flag to false awaiting a possible swap
		flag = false; 
        for(int j=1; j<n; j++ ) {
			// if Point pts[j] is closer to Point c (center): swap Point[] elements  
			if (distCompare(pts[j-1], pts[j], c) == -1) {	
                swap(pts, j-1, j);
				// record the occurence of a swap
				flag = true;  
			}
		} 
    } 
		
  } // end distSort(Screen window, Point[] pts)
  
  
  
  // method double dist(Point p1, Point p2) returns the distance of Point p1 from Point p2.
  // Point objects are arranged in an elliptical spiral to match the aspect ratio of the Screen.
  public static double dist(Point p1, Point p2) {
	  
	// vertical distance has been scaled to contribute 3 times because
	// the Screen object is defined to have roughly three times as much width as there is height
    double dx = p1.x - p2.x;
	double dy = 3.0*(p1.y - p2.y);
    return Math.sqrt(dx*dx + dy*dy);
  
  } // end double dist(Point p1, Point p2)



  // method boolean equidistant(Point[] pts, Point p) determines whether
  // two closest Points in pts array are equidistant to a Point p parameter?
  public static boolean equidistant(Point[] pts, Point p) {

	int n = pts.length;

	// vairables for minimum distances
	double d;
	double d1 = 999;
	double d2 = 999;

	// find two closest Points to p
	for (int i=0; i<n; i++) {

		d = dist(pts[i], p);
		if (d < d1) {
			// closest distance
			d2 = d1;
			d1 = d;
		} else if (d < d2) {
			// second closest distance
			d2 = d;
		}

	}

	// if absolute value of difference of 2 minimum distances is below 3.0 then return true
	if (Math.abs(d2-d1) < 3.0 ) {
		return true;
	} else {
		return false;
	}

  } // end method equidistant(Point[] pts, Point p)



  // method voronoi(Screen window, Point[] pts) draws '.' char boundaries around a set of points,
  // so that any position on the boundary is equidistant from its two closest points.
  public static void voronoi(Screen window, Point[] pts) {

    // create a Point for each position in the Screen to check
    // i.e. Point p = new Point(i + 0.5, j + 0.5);  // centre of character at col j, row i
    for (int i=0; i<window.rows; i++) {
		
		for (int j=0; j<window.cols; j++) {			
			Point p = new Point((double)j,(double)i);
			if (equidistant(pts, p)) {
				window.set(j, i, '.');
			}
		}
		
	}

  } // end voronoi(Screen window, Point[] pts)
  
  
  
  // method drawDiagram(Screen window, Point[] pts) draws the points (from pts[] array) on a Screen 
  // and labels each Point in the order they are listed in the array from A to S.
  public static void drawDiagram(Screen window, Point[] pts) {
    window.clear();
    
    // draw voronoi diagram first once you finish code for it
	voronoi( window, pts);
    
    // need to use a centre point
    Point c = new Point(window.cols / 2.0, window.rows / 2.0);
    c.put(window);
    
    // draw points and labels
	char labelChar = 'A';
	for (int i=0; i<pts.length; i++) {
		// if the Point is above the centre of the Screen, 
		// then the label draws directly below the Point
		if (pts[i].y > c.y) {
			
			Point labelPt = new Point(pts[i]);
			labelPt.translate(0,-1);
			
			pts[i].put(window);
			pts[i].draw('*');
			
			labelPt.put(window);
			labelPt.draw(labelChar);
		
		//otherwise the label should draw directly above the Point
		} else {
			Point labelPt = new Point(pts[i]);
			labelPt.translate(0,1);
			
			pts[i].put(window);
			pts[i].draw('*');
			
			labelPt.put(window);
			labelPt.draw(labelChar);
			
		}
		labelChar++;
	}
    
    // draw centre Point
	c.draw('@');
    
    // draw the Screen
	window.draw();
  }
  
  
  // printPoints(Point[] pts) prints pts[] array contents, useful method for program building. 
  public static void printPoints(Point[] pts) {
	  
  	for (int i=0; i<pts.length; i++) {
		
		System.out.printf("%,03.9f  ",pts[i].x);
		System.out.printf("%,02.9f",pts[i].y);
		System.out.println();
	
	}
	
  } // end printPoints(Point[] pts)



  public static void main(String[] args) {

    // setup Scanner
	Scanner console = new Scanner (System.in);
	int numOfPts = console.nextInt();
	
	// create a Screen
	Screen window = new Screen();
	
	// create Points from input Scanner
	Point[] pts = new Point[numOfPts];
	//System.out.println(numOfPts);

	for (int i=0; i<pts.length; i++) {
		double x = console.nextDouble();
		double y = console.nextDouble();
		pts[i] = new Point(x,y);
	}

	//Point[] testing method prints file import result:
	//printPoints(pts);

    // draw pts and labels
    drawDiagram(window, pts);
	System.out.println("\n\n\n");

    // sort pts in order from centre of Screen
    distSort(window, pts);

	//check distSort() resulting sort on pts[] in order of distance from center
	//printPoints(pts);
	
	drawDiagram(window, pts);
	System.out.println("\n\n\n");
	
	// sort pts in order of reading top left to bottom right
	readSort(window, pts);

	////check readSort() resulting sort on pts[] in order of reading top left to bottom right
	//printPoints(pts);

    drawDiagram(window, pts);
	System.out.println("\n\n\n");


  } // end method main()


} // end Program class SortPoints