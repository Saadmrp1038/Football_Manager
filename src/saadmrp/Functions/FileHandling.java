package saadmrp.Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileHandling {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private int Number;
    private double Salary;
    private double Price;
    private String s,s1;
    private String Player_FileName ;
    private String User_FileName ;

    private ArrayList<Player> players = new ArrayList<Player>();
    private HashMap<String,String> users = new HashMap<>();

        //Load Player data from file to Player arraylist
        public FileHandling(){};
        public FileHandling(String Player_FileName, String User_FileName) throws FileNotFoundException {

        File player_file = new File(Player_FileName);
        Scanner sc = new Scanner(player_file);

        sc.useDelimiter(",|\\n");
        while (sc.hasNextLine()) {

            s = sc.next();
            Name = s;
            s = sc.next();
            Country = s;
            s = sc.next();
            Age = Integer.parseInt(s);
            s = sc.next();
            Height = Double.parseDouble(s);
            s = sc.next();
            Club = s;
            s = sc.next();
            Position = s;
            s = sc.next();
            Number = Integer.parseInt(s);
            s = sc.next();
            Salary = Double.parseDouble(s);

            this.players.add(new Player(Name,Country,Age,Height,Club,Position,Number,Salary));
        }
            File user_file = new File(User_FileName);
            Scanner scan = new Scanner(user_file);

            scan.useDelimiter(",|\\r\\n");
            while(scan.hasNextLine()){
                s=scan.next();
                s1=scan.next();
                users.put(s,s1);

            }
    }

    //Read SellList
    public FileHandling(String SellList_FileName) throws FileNotFoundException {

        File player_file = new File(SellList_FileName);
        Scanner sc = new Scanner(player_file);

        sc.useDelimiter(",|\\n");
        while (sc.hasNextLine()) {

            s = sc.next();
            Name = s;
            s = sc.next();
            Country = s;
            s = sc.next();
            Age = Integer.parseInt(s);
            s = sc.next();
            Height = Double.parseDouble(s);
            s = sc.next();
            Position = s;
            s = sc.next();
            Number = Integer.parseInt(s);
            s = sc.next();
            Salary = Double.parseDouble(s);
            s = sc.next();
            Price = Double.parseDouble(s);

            this.players.add(new Player(Name,Country,Age,Height,Position,Number,Salary,Price));
        }
    }


    //Return Player arraylist
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    //Return Users HashMap
    public HashMap<String,String> getUsers(){
            return this.users;
    }

    //Write player data to file
    public void WriteToMarketFile(String File_Name, ArrayList<Player> p) throws IOException {
        FileWriter fw=  new FileWriter(File_Name);
        for(int i=0;i<p.size();i++) {
            fw.write(p.get(i).getName()+",");
            fw.write(p.get(i).getCountry()+",");
            fw.write(p.get(i).getAge()+",");
            fw.write(p.get(i).getHeight()+",");
            fw.write(p.get(i).getPosition()+",");
            fw.write(p.get(i).getNumber()+",");
            fw.write(p.get(i).getSalary()+",");
            fw.write(Double.toString(p.get(i).getPrice()));
            if(i<p.size()-1) fw.write("\n");
        }

        fw.close();
    }

    public void WriteToPlayerFile(String File_Name,ArrayList<Player> p) throws IOException {
        FileWriter fw=  new FileWriter(File_Name);
        for(int i=0;i<p.size();i++) {
            fw.write(p.get(i).getName()+",");
            fw.write(p.get(i).getCountry()+",");
            fw.write(p.get(i).getAge()+",");
            fw.write(p.get(i).getHeight()+",");
            fw.write(p.get(i).getClub()+",");
            fw.write(p.get(i).getPosition()+",");
            fw.write(p.get(i).getNumber()+",");
            fw.write(p.get(i).getSalary()+"");
            if(i<p.size()-1) fw.write("\n");
        }

        fw.close();
    }

}
