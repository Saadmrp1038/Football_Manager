package saadmrp.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcessRequest {
    private Scanner scan = new Scanner(System.in);
    private String s1,s2;
    private ArrayList<Player> res;
    private double low,high,sum;
    private HashMap<String, Integer> countries;

    //Player Options
    public void playerProcess(AllPlayers p,int state){
        if(state==11){
            System.out.println("Enter Player Name: ");
            s1=scan.nextLine();
            res = p.searchName(s1);
            if(res.size()==0){
                System.out.println("No such player with this name.");
            }
            else printPlayers(res);
            scan.nextLine();
        }
        else if(state==12){
            System.out.println("Enter Country Name: ");
            s1=scan.nextLine();
            System.out.println("Enter Club Name: ");
            s2=scan.nextLine();
            res = p.searchClubnCountry(s1,s2);
            if(res.size()==0){
                System.out.println("No such player with this country and club");
            }
            else printPlayers(res);
            scan.nextLine();
        }
        else if(state==13){
            System.out.println("Enter Position Name: ");
            s1=scan.nextLine();
            res = p.searchPosition(s1);
            if(res.size()==0){
                System.out.println("No such player with this position");
            }
            else printPlayers(res);
            scan.nextLine();
        }
        else if(state==14){
            System.out.println("Enter Lowest Salary: ");
            s1=scan.nextLine();
            System.out.println("Enter Highest Name: ");
            s2=scan.nextLine();
            if(canItBeDouble(s1) && canItBeDouble(s2)) {
                low=Double.parseDouble(s1);
                high=Double.parseDouble(s2);
                res = p.searchSalary(low, high);
                if(res.size()==0){
                    System.out.println("No such player with this weekly salary range.");
                }
                else printPlayers(res);

            }
            else System.out.println("Input is not valid.");

            scan.nextLine();
        }
        else if(state==15){
            countries = p.getCountries();
            int i=1;
            System.out.println("Country-wise player count:");
            for (Map.Entry mapElement : countries.entrySet()) {
                System.out.println(i+". "+mapElement.getKey()+" - "+mapElement.getValue());
                i++;
            }
            scan.nextLine();
        }

    }

    //Club Options
    public void clubProcess(AllClubs c,int state){
        if(state==21){
            System.out.println("Enter Club Name: ");
            s1=scan.nextLine();
            res = c.checkMaxSalary(s1);
            if(res.size()==0) System.out.println("No such club with this name.");
            else{
                System.out.println("Max Salary of " + res.get(0).getClub()+" :");
                printPlayers(res);
            }
            scan.nextLine();
        }
        else if(state==22){
            System.out.println("Enter Club Name: ");
            s1=scan.nextLine();
            res = c.checkMaxAge(s1);
            if(res.size()==0) System.out.println("No such club with this name.");
            else{
                System.out.println("Max Age of " + res.get(0).getClub()+" :");
                printPlayers(res);
            }
            c.checkMaxAge(s1);
            scan.nextLine();
        }
        else if(state==23){
            System.out.println("Enter Club Name: ");
            s1=scan.nextLine();
            res = c.checkMaxHeight(s1);
            if(res.size()==0) System.out.println("No such club with this name.");
            else{
                System.out.println("Max Height of " + res.get(0).getClub()+" :");
                printPlayers(res);
            }
            c.checkMaxHeight(s1);
            scan.nextLine();
        }
        else if(state==24){
            System.out.println("Enter Club Name: ");
            s1=scan.nextLine();
            sum = c.checkYearlySalary(s1);

            if(sum==-1) System.out.println("No such club with this name.");
            else {
                sum=sum*52;
                System.out.print("Yearly Salary of " + s1 +" is "); // Do something to get proper club name
                System.out.printf("%f \n",sum);
            }

            scan.nextLine();
        }

    }

    //Add Player Option
    public void addPlayerProcess(AllPlayers p,AllClubs c){
        String name="",country="",club="",position="",s;
        Double height=-1.0,salary=-1.0;
        int age=-1,number=-1;
        boolean repeat;

        //Player name
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player Name: ");
            name = scan.nextLine();

            if(!isItValidString(name)) System.out.println("Input is not valid.");
            else if(p.isNameHere(name)){
                System.out.println("Player already exists.");
            }
            else repeat=false;
        }

        //Player country
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player's Country: ");
            country = scan.nextLine();

            if(!isItValidString(country)) System.out.println("Input is not valid.");
            else repeat = false;

        }

        //Player age
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player's Age : ");
            s= scan.nextLine();
            if(canItBeInt(s)){
                age=Integer.parseInt(s);
                repeat=false;
            }
            else System.out.println("Input type is not valid");
        }
        //Player height
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player's Height : ");
            s= scan.nextLine();
            if(canItBeDouble(s)){
                height=Double.parseDouble(s);
                repeat=false;
            }
            else System.out.println("Input type is not valid");
        }

        //Player club
        repeat=true;
        while(repeat) {
            System.out.println("Enter Club Name: ");
            club = scan.nextLine();

            if(!isItValidString(club)) System.out.println("Input is not valid.");
            else if(c.clubPlayerCount(club)==7){
                System.out.println("This Club is Already Full.");
            }
            else repeat=false;
        }

        //Player position
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player's Position: ");
            position = scan.nextLine();

            if(position.equalsIgnoreCase("goalkeeper")||
                    position.equalsIgnoreCase("defender")||
                    position.equalsIgnoreCase("midfielder")||
                    position.equalsIgnoreCase("forward")) repeat=false;
            else System.out.println("Input is not valid.");
        }

        //Player number
        repeat=true;
        while(repeat){
            System.out.println("Enter Player's  Number: ");
            s=scan.nextLine();


            if(canItBeInt(s)) {
                number=Integer.parseInt(s);
                if(c.isNumberHere(number,club)){
                    System.out.println("Player with this same number already exists in this club.");
                }
                else repeat=false;
            }
            else System.out.println("Input is not valid");

        }

        //Player salary
        repeat=true;
        while(repeat) {
            System.out.println("Enter Player's Salary : ");
            s= scan.nextLine();
            if(canItBeDouble(s)){
                salary=Double.parseDouble(s);
                repeat=false;
            }
            else System.out.println("Input is not valid");
        }




        Player tmp = new Player(name,country,age,height,club,position,number,salary);

            p.addPlayer(tmp);
            c.addPlayer(tmp);
            System.out.println("Player is successfully added.");
            scan.nextLine();


    }

    //check is string is parsable to double
    public boolean canItBeDouble(String s){
        double d;

        try{
            d=Double.parseDouble(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    //check is string is parsable to int
    public boolean canItBeInt(String s){
        int i;

        try{
            i=Integer.parseInt(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public boolean isItValidString(String s){
        int i;
        for(i=0;i<s.length();i++){
            if(Character.isLetter(s.charAt(i)) || s.charAt(i)==' ');
            else return false;
        }
        return true;
    }

    public void printPlayers(ArrayList<Player> in){
        for(int i=0;i<in.size();i++){
            in.get(i).print(i);
        }
    }


}
