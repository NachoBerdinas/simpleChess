import java.util.*;

public class Knight extends Piece {

	public Knight(Color c, Board b) {
		super(c, b);
	}

	public List<Vector> getPossibleMoves() {
		List<Vector> ret = new ArrayList<>();
		int[] dx = {-2,-1,1,2};
		int[] dy = {-2,-1,1,2};
		for(int x : dx) {
			for(int y : dy) {
				if(Math.abs(x) + Math.abs(y) == 3) {
					Vector testLoc = new Vector(getLoc().getX()+y, getLoc().getY()+x);
					if(getBoard().isValid(testLoc)) {
						Piece testPiece = getBoard().get(testLoc);
						if(testPiece == null || testPiece.getColor() != getColor())
							ret.add(testLoc);
					}
				}
			}
		}
		ret.stream().forEach(System.out::println);
		return ret;
	}

	public String toString() {
		if(getColor() == Color.WHITE)
			return "N";
		else
			return "n";
	}

    public Piece clone(Board board){
        return new Knight(getColor(),board).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
    }

}
