import java.util.*;

public class King extends Piece {

	public King(Color c, Board b) {
		super(c, b);
	}
	public List<Vector> getPossibleMoves() {
		List<Vector> ret = new ArrayList<>();
		for(int r = -1; r <= 1; r++) {
			for(int c = -1; c <= 1; c++) {
				Vector testLoc = new Vector(getLoc().getX()+r,getLoc().getY()+c);
				if(getBoard().isValid(testLoc)) {
					Piece testPiece = getBoard().get(testLoc);
					if(!testLoc.equals(getLoc()) && (testPiece == null || testPiece.getColor() != getColor()))
						ret.add(testLoc);
				}
			}
		}
		return ret;
	}

	public void moveTo(Vector loc) {
		super.moveTo(loc);
	}

	@Override
	public Piece clone(Board b) {
		King aux = this;
		if (getColor() == Color.WHITE) {
			b.setWhiteKing(aux);
		} else {
			b.setBlackKing(aux);
		}
		return new King(aux.getColor(),b).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
	}

	public String toString() { 
		if(getColor() == Color.WHITE)
			return "K";
		else
			return "k";
	}

}
