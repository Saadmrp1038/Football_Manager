package saadmrp.Functions;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;

public class AllClubs {
    private ArrayList<Club> clubs = new ArrayList<Club>();

    public AllClubs(ArrayList<Player> players){
        for(int i=0;i<players.size();i++){
           addPlayer(players.get(i));
        }
    }

    //Add a Player to appropriate Club or create a new Club in case of no match
    public void addPlayer(Player p) {
        int i, flag = 0;
        for (i = 0; i < clubs.size(); i++) {
            if (clubs.get(i).getName().equalsIgnoreCase(p.getClub())==true) {
                clubs.get(i).addMember(p);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            clubs.add(new Club(p.getClub()));
            clubs.get(i).addMember(p);
        }
    }

    //Print all Players Data from all Clubs
    public void print(){
        for(int i=0;i<clubs.size();i++){
            clubs.get(i).print(i);
        }
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    //print player with maximum salary of given club
    public ArrayList<Player> checkMaxSalary(String name){
        ArrayList<Player> out = new ArrayList<Player>();
        int i=0,j=0,ind=0;
        double mx=-1;

        for(i=0;i< clubs.size();i++) {
            if (clubs.get(i).getName().equalsIgnoreCase(name)) {
                for (j = 0; j < clubs.get(i).members.size(); j++) {
                    if (clubs.get(i).members.get(j).getSalary() > mx) {
                        mx = clubs.get(i).members.get(j).getSalary();
                    }
                }
                break;
            }
        }
            for (j = 0; j < clubs.get(i).members.size(); j++) {
                if (mx == clubs.get(i).members.get(j).getSalary()) {
                    out.add(clubs.get(i).members.get(j));
                }
            }

            return out;
    }

    //print player with maximum age of given club
    public ArrayList<Player> checkMaxAge(String name){
        ArrayList<Player> out = new ArrayList<Player>();
        int i=0,j=0,ind=0;
        int mx=-1;

        for(i=0;i< clubs.size();i++) {
            if (clubs.get(i).getName().equalsIgnoreCase(name)) {
                for (j = 0; j < clubs.get(i).members.size(); j++) {
                    if (clubs.get(i).members.get(j).getAge() > mx) {
                        mx = clubs.get(i).members.get(j).getAge();
                    }
                }
                break;
            }
        }
            for (j = 0; j < clubs.get(i).members.size(); j++) {
                if(mx == clubs.get(i).members.get(j).getAge()) {
                    clubs.get(i).members.get(j).print(ind);
                    out.add(clubs.get(i).members.get(j));
                }
            }

            return out;
    }

    //print player with maximum height of given club
    public ArrayList<Player> checkMaxHeight(String name){
        ArrayList<Player> out = new ArrayList<Player>();
        int i=0,j=0,ind=0;
        double mx=-1;

        for(i=0;i< clubs.size();i++) {
            if (clubs.get(i).getName().equalsIgnoreCase(name)) {
                for (j = 0; j < clubs.get(i).members.size(); j++) {
                    if (clubs.get(i).members.get(j).getHeight() > mx) {
                        mx = clubs.get(i).members.get(j).getHeight();
                    }
                }
                break;
            }
        }

        for (j = 0; j < clubs.get(i).members.size(); j++) {
            if(mx == clubs.get(i).members.get(j).getAge()) {
                clubs.get(i).members.get(j).print(ind);
                out.add(clubs.get(i).members.get(j));
            }
        }

        return out;
    }

    //print total yearly salary of a given club
    public double checkYearlySalary(String name){
        int i=0,j=0;
        double sum=-1;

        for(i=0;i< clubs.size();i++) {
            if (clubs.get(i).getName().equalsIgnoreCase(name)) {
                sum=0;
                for (j = 0; j < clubs.get(i).members.size(); j++) {
                    sum+=clubs.get(i).members.get(j).getSalary();
                }
            }
        }

        return sum;
    }

    //check club player count
    public int clubPlayerCount(String cname){
        int count=0;
        for(int i=0;i<clubs.size();i++){
            if(clubs.get(i).getName().equalsIgnoreCase(cname)){
                count=clubs.get(i).members.size();
            }
        }
        return count;
    }

    //check is a Player Number already exists in the given club
    public boolean isNumberHere(int num,String cname){
        int i,j;

        for(i=0;i<clubs.size();i++){
            if(clubs.get(i).getName().equalsIgnoreCase(cname)){
                for(j=0;j<clubs.get(i).members.size();j++){
                    if(num==clubs.get(i).members.get(j).getNumber()){
                        return true;
                    }
                }
                break;
            }
        }
        return false;

    }


}
