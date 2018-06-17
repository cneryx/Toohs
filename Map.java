import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	private static boolean[][] walls = new boolean[10][22];
	private static boolean[][] vis = new boolean[10][22];
	private int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private ArrayList<Rectangle> wallHitBoxes;
	private ArrayList<Rectangle> pickupHitBoxes = new ArrayList<Rectangle>();
	private static Random random = new Random();

	public Map() {
		// keeps generating maps until the map is valid (no rooms, no walls on players)
		do {
			wallHitBoxes = new ArrayList<Rectangle>();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 22; j++) {
					if (Math.random() < 0.94) {
						walls[i][j] = false;
					} else {
						walls[i][j] = true;
						wallHitBoxes.add(new Rectangle(50 + (j * 50), 50 + (i * 50), 50, 50)); // for
																								// collisions
																								// with
																								// wall
																								// obstacles
					}
				}
			}
		} while (!valid(walls) || walls[4][0] || walls[4][1] || walls[5][0] || walls[5][1] || walls[4][20]
				|| walls[4][21] || walls[5][20] || walls[5][21]); // These areas are where the players will spawn
	}

	public boolean[][] getMap() {
		return walls;
	}

	public ArrayList<Rectangle> getWallHitBoxes() {
		return wallHitBoxes;
	}
	// Used to gerenate pickups. 0.000454 is just an arbitrary small number that worked.
	public void generatePickups() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				boolean validPos = true;
				if (walls[i][j]) {
					validPos = false;
				} else {
					for (int k = 0; k < Pickup.getInstances().size(); k++) {
						if ((new Rectangle(50 + 50 * i, 50 + 50 * j, 50, 50))
								.intersects(Pickup.getInstances().get(k).getHitBox())) {
							validPos = false;
						}
					}
				}

				if (validPos) {
					if (Math.random() < 0.000454) {
						int pickupType = random.nextInt(3);
						if (pickupType == 0) {
							Pickup.getInstances().add(new Speed(new Point(65 + (j * 50), 65 + (i * 50))));
						} else if (pickupType == 1) {
							Pickup.getInstances().add(new Health(new Point(65 + (j * 50), 65 + (i * 50))));
						} else {
							Pickup.getInstances().add(new Damage(new Point(65 + (j * 50), 65 + (i * 50))));
						}
					}
				}
			}
		}
	}
	// Method used to determine if a wall configuration is valid (You can visit every square)
	public boolean valid(boolean[][] map) {
		boolean a = true;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				if (!walls[i][j]) {
					vis = new boolean[10][22];
					verify(i, j);
					check: for (int k = 0; k < 10; k++) {
						for (int l = 0; l < 22; l++) {
							if (!vis[k][l] && !walls[k][l]) {
								a = false;
								break check;
							}
						}
					}
					return a;
				}
			}
		}
		return false;
	}
	//  Recursive method used to determine if all empty squares on the map are reachable (no rooms)
	public void verify(int x, int y) {
		vis[x][y] = true;
		for (int i = 0; i < 4; i++) {
			if (move(x + moves[i][0], y + moves[i][1])) {
				verify(x + moves[i][0], y + moves[i][1]);
			}
		}
		return;
	}
	//Used to determine if it is possible to move to that square
	public static boolean move(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 22 && !vis[x][y] && !walls[x][y];
    }
}