import java.util.*;

public class Rook extends Piece {

  public Rook(Color c, Board b) {
    super(c, b);
  }
  
  public List<Vector> getPossibleMoves() {

    List<Vector> ret = new ArrayList<>();
    Vector currLoc;
    List<Vector> inWay = getBoard().getVectorPiecesOfColor(getColor());

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

  public void moveTo(Vector loc) {
    super.moveTo(loc);
  }
  
  public String toString() {
    if(getColor() == Color.WHITE)
      return "R";
    else
      return "r";
  }

  public Piece clone(Board board){
    return new Rook(getColor(),board).setLoc(new Vector(getLoc().getX(),getLoc().getY()));
  }
  
}
