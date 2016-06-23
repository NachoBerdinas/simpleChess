import java.util.*;

public class Bishop extends Piece {

	public Bishop(Color c, Board b) {
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
		return ret;
	}



	public String toString() {
		if(getColor() == Color.WHITE)
			return "B";
		else
			return "b";
	}

	public Piece clone(Board board){
		return new Bishop(getColor(),board).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
	}


}
