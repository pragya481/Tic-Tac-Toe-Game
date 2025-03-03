package src.main.java.Domain;

public class Player {
    String name;
    PlayingPiece playingPiece;
    public int[] rowScore;
    public int[] colScore;
    public int diagScore;
    public int antiDiagScore;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.playingPiece = playingPiece;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }

    public void setPlayingPiece(PlayingPiece playingPiece) {
        this.playingPiece = playingPiece;
    }

}