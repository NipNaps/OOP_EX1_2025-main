public class SimpleDisc implements Disc{

    private Player player;

    public SimpleDisc(Player player){
        this.player = player;
    }
    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    public String getType() {
        return "⬤";
    }
}
