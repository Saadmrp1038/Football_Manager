package saadmrp.Functions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AllPlayers implements Serializable {
    private ArrayList<Player> players;
    HashMap<String, Integer> countries=new HashMap<String,Integer>();

    public AllPlayers(ArrayList<Player> players) {
        this.players = players;
        Integer i,count;
        for(i=0;i<players.size();i++){
            count=countries.get(players.get(i).getCountry());
            if(count==null){
                countries.put(players.get(i).getCountry(),1);
            }
            else{
                countries.put(players.get(i).getCountry(),count+1);
            }
        }
    }
    public int getPlayerCount(){
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    //Add a Player to players arraylist
    public void addPlayer(Player p) {
        players.add(p);

        Integer count;
        count=countries.get(p.getCountry());
        if(count==null){
            countries.put(p.getCountry(),1);
        }
        else{
            countries.put(p.getCountry(),count+1);
        }
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


    //print data of all players
    public void print(){
        for(int i=0;i<players.size();i++){
            players.get(i).print(i);
        }
    }

    //print player with given name
    public ArrayList<Player> searchName(String name){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        for(i=0;i<players.size();i++){
            if(players.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                out.add(players.get(i));
                break;
            };
        }

        return out;
    }

    //print player with given name
    public ArrayList<Player> searchCountry(String c){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        for(i=0;i<players.size();i++){
            if(players.get(i).getCountry().toLowerCase().contains(c.toLowerCase())){
                out.add(players.get(i));
            };
        }

        return out;
    }

    //print players with given Club and Country
    public ArrayList<Player> searchClubnCountry(String countryName, String clubName){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        for(i=0;i<players.size();i++){
            if(players.get(i).getCountry().equalsIgnoreCase(countryName) &&
                    (players.get(i).getClub().equalsIgnoreCase(clubName) || "ANY".equalsIgnoreCase(clubName))){
                out.add(players.get(i));
            };
        }
        return out;

    }

    //print players with given Position
    public ArrayList<Player> searchPosition(String pos){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        for(i=0;i<players.size();i++){
            if(players.get(i).getPosition().equalsIgnoreCase(pos)){
                out.add(players.get(i));
            };
        }
        return out;

    }

    //print players in Salary Range
    public ArrayList<Player> searchSalary(double low, double high){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        for(i=0;i<players.size();i++){
            if(players.get(i).getSalary()>=low && players.get(i).getSalary()<=high){
                out.add(players.get(i));
            };
        }

        return out;

    }

    //get max age
    public ArrayList<Player> searchMaxAge(){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        int age=-1;
        for(i=0;i<players.size();i++){
            if(players.get(i).getAge()>age)age=players.get(i).getAge();
        }
        for(i=0;i<players.size();i++){
            if(players.get(i).getAge()==age) out.add(players.get(i));
        }
        return out;

    }

    //get max height
    public ArrayList<Player> searchMaxHeight(){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        double height=-1;
        for(i=0;i<players.size();i++){
            if(players.get(i).getHeight()>height)height=players.get(i).getHeight();
        }
        for(i=0;i<players.size();i++){
            if(players.get(i).getHeight()==height) out.add(players.get(i));
        }
        return out;

    }

    //get max salary
    public ArrayList<Player> searchMaxSalary(){
        ArrayList<Player> out = new ArrayList<Player>();
        int i;
        double salary=-1;
        for(i=0;i<players.size();i++){
            if(players.get(i).getSalary()>salary)salary=players.get(i).getSalary();
        }
        for(i=0;i<players.size();i++){
            if(players.get(i).getSalary()==salary) out.add(players.get(i));
        }
        return out;

    }

    //get total Salay
    public double getTotalSalary(){
        int i;
        double sum=0;
        for(i=0;i<players.size();i++){
            sum+=players.get(i).getSalary();
        }
        return sum;
    }

    // Return Hashmap with country and player count
    public HashMap<String, Integer> getCountries() {
        return countries;
    }

    //check is name exits
    public boolean isNameHere(String name){
        int i=0;
        boolean exists=false;
        for(i=0;i<players.size();i++){
            if(players.get(i).getName().equalsIgnoreCase(name)){
                exists=true;
                break;
            }
        }

        return exists;
    }
}