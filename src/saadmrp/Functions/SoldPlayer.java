package saadmrp.Functions;

import java.io.Serializable;

public class SoldPlayer implements Serializable {
    private Player player;

    public SoldPlayer(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }
}
