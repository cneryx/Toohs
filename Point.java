
public class Point extends Object {
	public double x;
	public double y;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	// Returns a new point object with the same values
	public Point clone() {
		Point point = new Point(this.x, this.y);
		return point;
	}
}
