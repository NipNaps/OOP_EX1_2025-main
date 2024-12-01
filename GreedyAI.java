import java.util.List;
public class GreedyAI extends AIPlayer{
    public GreedyAI(boolean isPlayerOne) {
        super(isPlayerOne);
    }

    public Move makeMove(PlayableLogic gameStatus) {
        List<Position> validMoves = gameStatus.ValidMoves();
        if (validMoves.isEmpty()) {
            return null;
        }

        // Find the move that flips the maximum number of discs
        Position maxMove = null;
        int maxFlips = 0;

        for (Position move : validMoves) {
            int flips = gameStatus.countFlips(move);
            if (flips > maxFlips) {
                maxMove = move;
                maxFlips = flips;
            }
        }
        if (maxMove != null) {
            Disc disc = new SimpleDisc(this);
            return new Move(maxMove, disc, null);

        }
        return null;
    }
}
