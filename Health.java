import java.awt.Color;
//Health pickup
public class Health extends Pickup {
	Health(Point pos) {
		super(pos);
		color = Color.green;
		fadedColor = new Color(0, 255, 0, 100);
	}
}