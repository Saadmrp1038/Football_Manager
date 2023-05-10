package saadmrp.Functions;

import java.util.ArrayList;

public class Club {
    private String Name;

    ArrayList<Player> members = new ArrayList<Player>();

    public Club(String name) {
        Name = name;
    }

    public void setName(String name) { Name = name; }

    public String getName() { return Name; }

    public ArrayList<Player> getMembers() {
        return members;
    }

    //Add a member ot Club
    public void addMember(Player p){
        members.add(p);
    }

    //Print all player from this club
    public void print(int ind){
        System.out.println("_______"+(ind+1)+". Club Name: "+Name+"_______");
        System.out.println("Player Count: "+members.size());
        for(int i=0;i< members.size();i++){
            System.out.println(i+1+". "+members.get(i).getName());
        }
    }

}
