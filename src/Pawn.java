import java.util.*;

public class Pawn extends Piece {

	private int numSteps;

	public Pawn(Color c, Board b) {
		super(c, b);
		numSteps = 0;
	}

	public List<Vector> getPossibleMoves() {
		Board b = getBoard();
		List<Vector> ret = new ArrayList<>();

        Vector front = (this.getColor() == Color.WHITE) ? getLoc().getAdjacentLocation(Vector.NORTH) : getLoc().getAdjacentLocation(Vector.SOUTH);
        Vector attLeft = (this.getColor() == Color.WHITE) ? getLoc().getAdjacentLocation(Vector.NORTHWEST) : getLoc().getAdjacentLocation(Vector.SOUTHWEST);
        Vector attRight = (this.getColor() == Color.WHITE) ? getLoc().getAdjacentLocation(Vector.NORTHEAST) : getLoc().getAdjacentLocation(Vector.SOUTHEAST);
        Vector twiceFront = (this.getColor() == Color.WHITE) ? front.getAdjacentLocation(Vector.NORTH) : front.getAdjacentLocation(Vector.SOUTH);

		if(b.isValid(front) && b.isVacant(front)) {
			ret.add(front);
		}
		if(numSteps == 0 && b.isVacant(front) && b.isVacant(twiceFront)) {
			ret.add(twiceFront);
		}
		if(b.isValid(attLeft) && !b.isVacant(attLeft) && b.get(attLeft).getColor() != getColor()) {
			ret.add(attLeft);
		}
		if(b.isValid(attRight) && !b.isVacant(attRight) && b.get(attRight).getColor() != getColor()) {
			ret.add(attRight);
		}
		return ret;
	}

	public void moveTo(Vector loc) {
		getBoard().put(getLoc(),null);

		Piece capture = getBoard().put(loc,this);
		if(capture != null) capture.getsCaptured();
		numSteps++;
	}

	public String toString() {
		if(getColor() == Color.WHITE)
			return "P";
		else
			return "p";
	}

	public Piece clone(Board board){
		return new Pawn(getColor(),board).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
	}

}
