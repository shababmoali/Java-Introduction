public class Screen {
  // field variables: rows, cols, surface
  int rows = 48;
  int cols = 150;
  private char[][] surface = new char[rows][cols];
  
  // this constructor means client doesn't have
  // to worry about calling clear right away
  public Screen() {
    clear();
  }
  
  // mutator method
  public void set(int x, int y, char a) {
    // The modulo operations wrap content around the edges 
    // if you go out of bounds with the indices.
    // Technically, this now makes the surface a torus!
    x = (x + cols) % cols;
    y = (y + rows) % rows;
    surface[y][x] = a;
  }
  
  // accessor method
  public char get(int x, int y) {
    return surface[y][x];
  }
  
  public void setSize(int rows, int cols) {
    this.rows = rows; // field = parameter
    this.cols = cols;
    surface = new char[rows][cols];
    // should call clear() after using setSize
    // since the surface is auto-initialized to all zeros
    clear();
  }
  
  public void draw() {
    for (int i=rows-1; i>=0; i--) {
      for (int j=0; j<cols; j++) {
        System.out.print( surface[i][j] );
      }
      System.out.println();
    }
  }
  
  // need to call this before ever calling draw
  public void clear() {
    for (int i=0; i<rows; i++) {
      for (int j=0; j<cols; j++) {
        surface[i][j] = ' ';
      }
    }
  }
}