package saadmrp.Functions;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketPlayers implements Serializable {
    private ArrayList<Player> players;

    public MarketPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    //Add a Player to players arraylist
    public void addPlayer(Player p) {
        players.add(p);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    //remove player with given name
    public void removePlayer(String s){
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(s)){
                players.remove(i);
                break;
            }
        }
    }
}
