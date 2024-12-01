import java.util.ArrayList;
import java.util.List;

public class GameLogic implements PlayableLogic {

    private final int BOARD_SIZE = 8;
    private Player firstPlayer;
    private Player secondPlayer;
    private boolean isFirstPlayerTurn = true;
    private boolean gameFinished = false;
    private final Disc[][] board = new Disc[BOARD_SIZE][BOARD_SIZE];
    private final List<Move> moveHistory = new ArrayList<>();

    //asd

    public boolean locate_disc(Position a, Disc disc) {
        if (gameFinished) {
            System.out.println("Game is finished.");
            return false;
        }
        if (a.col() < 0 || a.col() >= BOARD_SIZE || a.row() < 0 || a.row() >= BOARD_SIZE || board[a.row()][a.col()] != null) {
            return false; // Out of bounds
        }
        // Calculate flipped discs
        List<Position> flippedPositions = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            flippedPositions.addAll(flipDiscs(a, direction));
        }
        // if no discs are flipped, the move is invalid
        if (flippedPositions.isEmpty()) {
            System.out.println("Invalid move.");
            return false;
        }

        board[a.row()][a.col()] = disc; // Place the disc
        moveHistory.add(new Move(a, disc, flippedPositions)); // Save the move to history
        isFirstPlayerTurn = !isFirstPlayerTurn; // Switch turn
        int number = (isFirstPlayerTurn) ? 2 : 1;
        System.out.println("Player " + number + " placed a " + disc.getType() + " in (" + a.row() + "," + a.col() + ").");
        System.out.println();

        return true;

    }

    public Disc getDiscAtPosition(Position position) {
        return board[position.row()][position.col()];
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public List<Position> ValidMoves() {
        List<Position> validMoves = new ArrayList<>();

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

    private List<Position> flipDiscs(Position start, int[] direction) {
        List<Position> flipped = new ArrayList<>();
        int row = start.row() + direction[0];
        int col = start.col() + direction[1];

        //Traverse in the given direction
        while (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE) {
            Disc disc = board[row][col];

            // Stops if there's no disc
            if (disc == null) {
                break;
            }
            // Check if the disc belongs to the current player
            if (disc.getOwner() == (isFirstPlayerTurn ? firstPlayer : secondPlayer)) {
                // Flip all collected discs
                for (Position pos : flipped) {
                    board[pos.row()][pos.col()].setOwner(isFirstPlayerTurn ? firstPlayer : secondPlayer);
                    int number = (isFirstPlayerTurn) ? 1 : 2;
                    System.out.println("Player " + number + " flipped the " + disc.getType() + " in (" + pos.row() + "," + pos.col() + ").");
                }
                return flipped;
            }
            // Add the position to the flipped list and continue
            flipped.add(new Position(row, col));
            row += direction[0];
            col += direction[1];

        }
        // No valid discs to flip in this direction
        return new ArrayList<>();
    }

    // All posible directions
    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

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
            else {
                totalFlips++;
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

    private int calculateScore(Player player) {
        int score = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Disc disc = board[i][j];
                if (disc != null && disc.getOwner().equals(player)) {
                    score++;
                }
            }
        }
        return score;
    }

    public boolean isFirstPlayerTurn() {
        return isFirstPlayerTurn;
    }

    public boolean isGameFinished() {
        return ValidMoves().isEmpty();
    }

    public void reset() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null; // Clear the board
            }
        }

        board[3][3] = new SimpleDisc(firstPlayer);
        board[4][4] = new SimpleDisc(firstPlayer);
        board[3][4] = new SimpleDisc(secondPlayer);
        board[4][3] = new SimpleDisc(secondPlayer);

        isFirstPlayerTurn = true;
        moveHistory.clear();
        gameFinished = false;
    }

    public void undoLastMove() {
        if (moveHistory.isEmpty()) {
            System.out.println("No moves to undo.");
            return; // No moves to undo

        }
        Move lastMove = moveHistory.remove(moveHistory.size() - 1);

        //Retrieve and remove the last move
        Position pos = lastMove.position();
        board[pos.row()][pos.col()] = null;

        // Revert flipped discs
        for (Position flipPos : lastMove.flippedPositions()) {
            Disc flippedDisc = board[flipPos.row()][flipPos.col()];
            flippedDisc.setOwner(isFirstPlayerTurn ? secondPlayer : firstPlayer);

        }

        // Switch the turn back
        isFirstPlayerTurn = !isFirstPlayerTurn;
    }
}
