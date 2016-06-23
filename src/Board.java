import java.io.*;
import java.util.*;

public class Board{

  private Piece[][] board;
  private List<Piece> capturedWhitePieces;
  private List<Piece> capturedBlackPieces;
  private List<Piece> whitePieces;
  private List<Piece> blackPieces;
  private Piece whiteKing;
  private Piece blackKing;
  private Color whosTurn;

  public Board(Board b) {
      board = new Piece[8][8];
      whosTurn = b.whosTurn;
      capturedWhitePieces = new ArrayList<Piece>();
      capturedBlackPieces = new ArrayList<Piece>();
      whitePieces = new ArrayList<Piece>();
      blackPieces = new ArrayList<Piece>();
      for(int x = 0; x < 8; x++) {
          for(int y = 0; y < 8; y++) {
              if(b.board[x][y] !=null) {
                  add(b.board[x][y].clone(this));
              }
          }
      }
      printBoard();
  }
  
  public Board() { 
      board = new Piece[8][8];
      whosTurn = Color.WHITE;
      capturedWhitePieces = new ArrayList<Piece>();
      capturedBlackPieces = new ArrayList<Piece>();
      whitePieces = new ArrayList<Piece>();
      blackPieces = new ArrayList<Piece>();
      generatePieces();
  }

  public Color whosTurn() { return whosTurn; }

  public void endTurn() {
    whosTurn = whosTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
  }
  
  private void add(Piece p) {
      if (p.getColor() == Color.BLACK) {
          blackPieces.add(p);
      }
      else whitePieces.add(p);
      board[p.getLoc().getY()][p.getLoc().getX()] = p;
  }

    private void generatePieces() {
        //Generate Pawns
        for(int c = 0; c < 8; c++) {
            add(new Pawn(Color.BLACK, this).setLoc(new Vector(1,c)));
            add(new Pawn(Color.WHITE, this).setLoc(new Vector(6,c)));
        }
        //Add Kings
        whiteKing = new King(Color.WHITE, this).setLoc(new Vector(7,4));
        blackKing = new King(Color.BLACK, this).setLoc(new Vector(0,4));
        add(whiteKing);
        add(blackKing);
        //Add rest of superior pieces
        add(new Rook(Color.BLACK, this).setLoc(new Vector(0,0)));
        add(new Rook(Color.BLACK, this).setLoc(new Vector(0,7)));
        add(new Knight(Color.BLACK, this).setLoc(new Vector(0,1)));
        add(new Knight(Color.BLACK, this).setLoc(new Vector(0,6)));
        add(new Bishop(Color.BLACK, this).setLoc(new Vector(0,2)));
        add(new Bishop(Color.BLACK, this).setLoc(new Vector(0,5)));
        add(new Queen(Color.BLACK, this).setLoc(new Vector(0,3)));

        add(new Rook(Color.WHITE, this).setLoc(new Vector(7,0)));
        add(new Rook(Color.WHITE, this).setLoc(new Vector(7,7)));
        add(new Knight(Color.WHITE, this).setLoc(new Vector(7,1)));
        add(new Knight(Color.WHITE, this).setLoc(new Vector(7,6)));
        add(new Bishop(Color.WHITE, this).setLoc(new Vector(7,2)));
        add(new Bishop(Color.WHITE, this).setLoc(new Vector(7,5)));
        add(new Queen(Color.WHITE, this).setLoc(new Vector(7,3)));
    }

  
  public void addCaptured(Piece p) {
    if(p.getColor().equals(Color.WHITE)) {
        capturedWhitePieces.add(p);
        whitePieces.remove(p);
    }else {
        capturedBlackPieces.add(p);
        blackPieces.remove(p);
    }
  }
  
  public Piece get(Vector loc) {
    if(!isValid(loc))
      throw new IllegalArgumentException("Invalid location");
    return board[loc.getY()][loc.getX()];
  }

  public Piece get(int r, int c) {
    Vector pos = new Vector(r,c);
    if(!isValid(pos))
      throw new IllegalArgumentException("Invalid location");
    return board[pos.getY()][pos.getX()];
  }
  
  public Piece put(Vector loc, Piece p) {
    if(!isValid(loc))
      throw new IllegalArgumentException("Invalid location");
    Piece old = this.get(loc);
    this.board[loc.getY()][loc.getX()] = p;
    if(p != null) {
        p.setLoc(loc);
    }
    return old;
  }

  public boolean isValid(Vector loc) {
    return 0 <= loc.getY() && loc.getY() < 8 && 0 <= loc.getX() && loc.getX() < 8;
  }
  
  public boolean isVacant(Vector loc) {
    if(!isValid(loc))
      return false;
    return get(loc) == null;
  }
  
  public List<Piece> getPiecesOfColor(Color col) {
    return col.equals(Color.WHITE)?whitePieces:blackPieces;
  }

  public List<Vector> getVectorPiecesOfColor(Color col) {
      List<Vector> ret = new ArrayList<>();
      for (Piece p: col.equals(Color.WHITE)?whitePieces:blackPieces){
          ret.add(p.getLoc());
      }
      return ret;
  }


  public Vector[] parseMove(String rawStr, Color whosTurn) {
    String move[] = rawStr.toLowerCase().split(" ");
    Vector[] ret = new Vector[2];
    checkFormat(move);

    ret[0] = new Vector(move[0].charAt(1)-'1', move[0].charAt(0)-'a');
    ret[1] = new Vector(move[2].charAt(1)-'1', move[2].charAt(0)-'a');

    if(get(ret[0]) == null || get(ret[0]).getColor() != whosTurn)
      throw new IllegalArgumentException(whosTurn + " has no piece at that position");

    Piece piece = get(ret[0]);
    if(!piece.validMove(ret[1])) {
      throw new IllegalArgumentException(rawStr + " is an illegal move");
    }
    return ret;
  }

  private void checkFormat(String[] move) {
    String first = move[0];
    String second = move[2];
    if(move.length != 3)
      throw new NumberFormatException("Improper Move Format");

    if(first.length() != 2 || first.charAt(0) < 'a' || first.charAt(0) > 'h' || first.charAt(1) < '1' || first.charAt(1) > '8')
      throw new NumberFormatException("Improper Move Format");

    if(second.length() != 2 || second.charAt(0) < 'a' || second.charAt(0) > 'h' || second.charAt(1) < '1' || second.charAt(1) > '8')
      throw new NumberFormatException("Improper Move Format");
  }

  public void printBoard() {
    System.out.print("   ");
    for(int c = 0; c < 8; c++) {
        System.out.print((char)(c + 'a') + " ");
    }
    System.out.println("");
    System.out.println("  _________________  ");
    for(int r = 0; r < 8; r++) {
        System.out.print("" + (r+1) + " |");
        for(int c = 0; c < 8; c++) {
            Piece p = get(r,c);
            System.out.print((p==null)?"_|":p+"|");
        }
        System.out.println();
    }

    System.out.println();
    System.out.println("Captured White Pieces: " + capturedWhitePieces);
    System.out.println("Captured Black Pieces: " + capturedBlackPieces);
  }
  
  public boolean isInCheck(Color color) {
      Color oppColor = (color == Color.WHITE) ? Color.BLACK : Color.WHITE;;
      Piece king = (color == Color.WHITE) ? blackKing: whiteKing;
      List<Piece> oppPieces = getPiecesOfColor(oppColor);

      for(Piece p : oppPieces) {
          for(Vector v: p.getPossibleMoves()){
              if(v.equals(king.getLoc())){
                  return true;
              }
          }
      }
      return false;
  }

  
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Scanner kb = new Scanner(System.in);
    Board board = new Board();

    Vector[] move;

    while(true) {
        if(board.isInCheck(board.whosTurn())) {
            System.out.println(board.whosTurn() + " is in check");
        }

        move = null;
        do {
            board.printBoard();
            System.out.println(board.whosTurn() + " to move:");
            String rawMove = kb.nextLine();
            try {
                move = board.parseMove(rawMove, board.whosTurn());
            }catch(NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again using notation \"e2 to e4\"");
            }catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again with a legal move");
            }
        } while(move == null);

        Piece piece = board.get(move[0]);
        piece.moveTo(move[1]);
        board.endTurn();
    }
  }

  public void setWhitePieces(List<Piece> whitePieces) {
    this.whitePieces = whitePieces;
  }

  public void setBlackPieces(List<Piece> blackPieces) {
    this.blackPieces = blackPieces;
  }

  public void setWhiteKing(Piece whiteKing) {
    this.whiteKing = whiteKing;
  }

  public void setBlackKing(Piece blackKing) {
    this.blackKing = blackKing;
  }
}
