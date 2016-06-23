import java.util.*;

public class Queen extends Piece {

	public Queen(Color c, Board b) {
		super(c, b);
	}

	public List<Vector> getPossibleMoves() {
		List<Vector> ret = new ArrayList<>();
		Vector currLoc;
		List<Vector> inWay = getBoard().getVectorPiecesOfColor(getColor());

		currLoc = getLoc().getAdjacentLocation(Vector.NORTHEAST);
		getInline(ret, currLoc, inWay, Vector.NORTHEAST);

		currLoc = getLoc().getAdjacentLocation(Vector.NORTHWEST);
		getInline(ret, currLoc, inWay, Vector.NORTHWEST);

		currLoc = getLoc().getAdjacentLocation(Vector.SOUTHEAST);
		getInline(ret, currLoc, inWay, Vector.SOUTHEAST);

		currLoc = getLoc().getAdjacentLocation(Vector.SOUTHWEST);
		getInline(ret, currLoc, inWay, Vector.SOUTHWEST);

		currLoc = getLoc().getAdjacentLocation(Vector.NORTH);
		getInline(ret, currLoc, inWay, Vector.NORTH);

		currLoc = getLoc().getAdjacentLocation(Vector.WEST);
		getInline(ret, currLoc, inWay, Vector.WEST);

		currLoc = getLoc().getAdjacentLocation(Vector.SOUTH);
		getInline(ret, currLoc, inWay, Vector.SOUTH);

		currLoc = getLoc().getAdjacentLocation(Vector.EAST);
		getInline(ret, currLoc, inWay, Vector.EAST);
		return ret;
	}

	public String toString() {
		if(getColor() == Color.WHITE)
			return "Q";
		else
			return "q";

	}


	public Piece clone(Board board){
		return new Queen(getColor(),board).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
	}

}
