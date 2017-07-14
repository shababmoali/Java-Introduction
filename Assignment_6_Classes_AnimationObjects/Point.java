public class Point {
  // field variables: panel, x, y
  private Screen panel;
  double x;
  double y;
  
  // constructor
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  // constructor
  public Point(Point p) {
    x = p.x;
    y = p.y;
  }
  
  public void translate(double dx, double dy) {
    x += dx;
    y += dy;
  }
  
  // methods
  // put: connect panel to a Screen
  public void put(Screen panel) {
    this.panel = panel;  // field = parameter
  }
  
  // panel must be set
  // method draw
  public void draw(char a) {
    panel.set( (int)x, (int)y, a);
  }
}
