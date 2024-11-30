import java.util.ArrayList;
import java.util.List;

public class GameLogic implements PlayableLogic {

    private final int BOARD_SIZE = 8;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean isFirstPlayerTurn = true;
    private final Disc[][] board = new Disc[BOARD_SIZE][BOARD_SIZE];


    public boolean locate_disc(Position a, Disc disc) {
        if (a.col() < 0 || a.col() >= BOARD_SIZE || a.row() < 0 || a.row() >= BOARD_SIZE || board[a.row()][a.col()] != null) {
            return false; // Out of bounds
        }
        Disc currentDisc = board[a.row()][a.col()];
        return currentDisc !=null && currentDisc.getOwner().equals(disc.getOwner()) && currentDisc.getType().equals(disc.getType());
    }

    public Disc getDiscAtPosition(Position position) {
        return board[position.row()][position.col()];
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public List<Position> ValidMoves() {
        List<Position> validMoves = new ArrayList<>();
        Player currentPlayer = isFirstPlayerTurn ? firstPlayer : secondPlayer;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Position position = new Position(row, col);
                if (board[row][col] == null && countFlips(position) > 0) {
                    validMoves.add(position);
                }
            }
        }

        return validMoves;
    }

    // All posible directions
    private static final int[][] DIRECTIONS = { {-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };

    public int countFlips(Position a) {
        Player currentPlayer = isFirstPlayerTurn ? firstPlayer : secondPlayer;
        int totalFlips = 0;

        for (int[] direction : DIRECTIONS) {
            int flipsInDirection = countFlipsDirection(a, direction, currentPlayer);
            totalFlips += flipsInDirection;
        }

        return totalFlips;
    }

    private int countFlipsDirection(Position a, int[] direction, Player currentPlayer) {
        int row = a.row() + direction[0];
        int col = a.col() + direction[1];
        int totalFlips = 0;
        
        // Stop if there's no disc
        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            Disc disc = board[row][col];
            if (disc == null) {
                return 0;
            }
            
            // Return flips if a disc  of the current player is found
            else if (disc.getOwner() == currentPlayer) {
                return totalFlips;
            }
            // Count the opponent's disc and continue 
            else { totalFlips++; 
            }
            row += direction[0];
            col += direction[1];
        }
        
        // No valid flip path in this direction
        return 0;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setPlayers(Player player1, Player player2) {
        this.firstPlayer = player1;
        this.secondPlayer = player2;
    }

    public boolean isFirstPlayerTurn() {
        return isFirstPlayerTurn;
    }

    public boolean isGameFinished() {
        // Check if either play has valid moves
        if (!ValidMoves().isEmpty()) {
            return false;
        }
        // Switch turn to the other player and check for valid moves
        isFirstPlayerTurn = !isFirstPlayerTurn;
        boolean otherPlayHasMoves = !ValidMoves().isEmpty();
        isFirstPlayerTurn = !isFirstPlayerTurn; // Switch back to the original player
        return !otherPlayHasMoves; //Game is finished if neither play has valid moves

        
    }

    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null;
            }
            board[3][3] = new SimpleDisc(firstPlayer);
            board[4][4] = new SimpleDisc(firstPlayer);
            board[3][4] = new SimpleDisc(secondPlayer);
            board[4][3] = new SimpleDisc(secondPlayer);
            isFirstPlayerTurn = true;
        }
    }

    public void undoLastMove() {


    }
}
