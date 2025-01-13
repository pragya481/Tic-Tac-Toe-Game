package src.main.java.Domain;

public class PlayingPiece {
    PieceType pieceType;

    PlayingPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
    public PieceType getPieceType() {
        return this.pieceType;
    }
}