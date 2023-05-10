package saadmrp.Functions;

import java.io.Serializable;

public class BoughtPlayer implements Serializable {
    private Player player;

    public BoughtPlayer(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }
}
