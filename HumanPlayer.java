public class HumanPlayer extends Player {

    public HumanPlayer(boolean isPlayerOne) {
        super(isPlayerOne);
    }

    boolean isHuman() {
        return true;
    }

}
