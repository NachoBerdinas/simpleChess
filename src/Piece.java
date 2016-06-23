import java.io.Serializable;
import java.util.*;

public abstract class Piece implements Serializable {

	private static final long serialVersionUID = 1L;
	private Color color;
	private Vector position;
	private Board board;

	public Piece(Color c, Board b) {
		color = c;
		position = null;
		board = b;
	}

	public Color getColor() { return color; }
	public Vector getLoc() { return position; }
	public Piece setLoc(Vector newLoc) {
		position = newLoc;
		return this;
	}
	public Board getBoard() { return board; }

	public void getsCaptured() {
		position = null;
		board.addCaptured(this);
		board = null;
	}


	public abstract List<Vector> getPossibleMoves();
	public abstract String toString();

	public boolean validMove(Vector loc) {
		List<Vector> moves = getPossibleMoves();

		Vector initLoc = position;
		Board testBoard = new Board(board);
		Piece testPiece = testBoard.get(position);
		testBoard.put(initLoc,null);
		testBoard.put(loc, testPiece);

		if(testBoard.isInCheck(color)) {
			System.out.println("This move puts " + color.toString().toLowerCase() + " in check, so");
			return false;
		}
		System.out.println("Contains?? "+loc);
		return moves.contains(loc);
	}

	public void moveTo(Vector loc) {
		board.put(position,null);
		Piece capture = board.put(loc,null);
		if(capture != null)
			capture.getsCaptured();
		board.put(loc, this);
	}

	protected void getInline(List<Vector> ret, Vector currLoc, List<Vector> inWay, double direction) {
		while (!inWay.contains(currLoc) && getBoard().isValid(currLoc)) {
			ret.add(currLoc);
			if (getBoard().get(currLoc) != null && getBoard().get(currLoc).getColor() != getColor())
				break;
			currLoc = currLoc.getAdjacentLocation(direction);
		}
	}

	public abstract Piece clone(Board board);

}