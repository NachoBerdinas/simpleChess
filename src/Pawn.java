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
		Vector front = getLoc().getAdjacentLocation(Vector.NORTH);
		Vector attLeft = getLoc().getAdjacentLocation(Vector.NORTHWEST);
		Vector attRight = getLoc().getAdjacentLocation(Vector.NORTHEAST);
		Vector twiceFront = front.getAdjacentLocation(Vector.NORTH);

		if(b.isValid(front) && b.isVacant(front)) {
			System.out.println("Sarasa 1");
			ret.add(front);
		}
		if(numSteps == 0 && b.isVacant(front) && b.isVacant(twiceFront)) {
			System.out.println("Sarasa 2");
			ret.add(twiceFront);
		}
		if(b.isValid(attLeft) && !b.isVacant(attLeft) && b.get(attLeft).getColor() != getColor()) {
			System.out.println("Sarasa 3");
			ret.add(attLeft);
		}
		if(b.isValid(attRight) && !b.isVacant(attRight) && b.get(attRight).getColor() != getColor()) {
			System.out.println("Sarasa 4");
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

}
