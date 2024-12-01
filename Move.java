import java.util.List;

public class Move {

    private final Position position;
    private final Disc disc;
    private final List<Position> flippedPositions; //Tracks flipped discs.

    public Move(Position position, Disc disc, List<Position> flippedPositions) {
        this.position = position;
        this.disc = disc;
        this.flippedPositions = flippedPositions;
    }

    public Position position() {
        return position;
    }

    public Disc disc() {
        return disc;
    }
    public List<Position> flippedPositions() {
        return flippedPositions;
    }

}
