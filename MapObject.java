import java.awt.Rectangle;
import java.awt.Color;

public abstract class MapObject {

	protected Point pos; // position on map
	protected Point dir; // x and y values can be -1, 0, or 1. used for movement only
	protected int size; // size in pixels
	protected Color color;

	// Collisions
	protected Rectangle hitBox;
	// Constructor
	MapObject(Point pos, Point dir, int size, Color color) {

		this.pos = pos;
		this.dir = dir;
		this.size = size;
		this.color = color;
	}
	
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public int getSize() {
		return size;
	}
	
	public Point getDir() {
		return dir;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
}