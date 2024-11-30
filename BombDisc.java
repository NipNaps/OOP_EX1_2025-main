public class BombDisc implements Disc {

    private Player player;

    public BombDisc(Player player) {
        this.player = player;
    }
    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    public String getType() {
        return "\uD83D\uDCA3";
    }
}
