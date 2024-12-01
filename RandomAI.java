import java.util.List;
import java.util.Random;
public class RandomAI extends AIPlayer{
    private Random random = new Random();

    public RandomAI(boolean isPlayerOne) {
        super(isPlayerOne);
    }

    public Move makeMove(PlayableLogic gameStatus) {
        List<Position> validMoves = gameStatus.ValidMoves();
        if (validMoves.isEmpty()) {
            return null;
        }
        // Pick a random valid move
        Position randomMove = validMoves.get(random.nextInt(validMoves.size()));
        Disc disc = new SimpleDisc(this); // Place  a Simple for this Ai
        return new Move(randomMove, disc, null);
    }
}
