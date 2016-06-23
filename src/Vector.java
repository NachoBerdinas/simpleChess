import java.io.Serializable;

public class Vector implements Serializable {


	private int x;
	private int y;

	public Vector(int x, int y) {
		this.y = y;
		this.x = x;
	}

	public int getY() { return y; }
	public int getX() { return x; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vector vector = (Vector) o;

		return x == vector.x && y == vector.y;

	}

	public String toString() {
		return "(" + (char)(y+65) + "," + x + ")";
	}

	public static final double NORTH = Math.PI/2;
	public static final double NORTHEAST = Math.PI/4;
	public static final double EAST = 0;
	public static final double SOUTHEAST = 7*Math.PI/4;
	public static final double SOUTH = 3*Math.PI/2;
	public static final double SOUTHWEST = 5*Math.PI/4;
	public static final double WEST = Math.PI;
	public static final double NORTHWEST = 3*Math.PI/4;

	public Vector getAdjacentLocation(double dir) {
		int dx = (int) Math.round(Math.cos(dir));
		int dy = (int) (-1*Math.round(Math.sin(dir)));
		return new Vector(x +dy,y +dx);
	}

}
