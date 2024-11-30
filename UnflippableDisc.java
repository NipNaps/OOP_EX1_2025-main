public class UnflippableDisc implements Disc{

    private Player player;

    public UnflippableDisc(Player player){
        this.player = player;
    }

    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    public String getType() {
        return "⭕";
    }
}
