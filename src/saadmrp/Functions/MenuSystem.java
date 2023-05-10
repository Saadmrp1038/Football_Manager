package saadmrp.Functions;

import java.util.Scanner;

public class MenuSystem {

    String s;
    int num;
    boolean repeat;
    Scanner scan = new Scanner(System.in);

    public int LoadState(int state){
        if(state==0){
            repeat=true;
            while(repeat) {
                System.out.println("Main Menu:");
                System.out.println("(1) Search Players");
                System.out.println("(2) Search Clubs");
                System.out.println("(3) Add Player");
                System.out.println("(4) Exit System");

                s = scan.nextLine();
                if(s.matches("1|2|3|4")) repeat=false;
                else{
                    System.out.println("Input is not valid.");
                    scan.nextLine();
                }
            }
            num=Integer.parseInt(s);
        }
        else if(state==1){
            repeat=true;
            while(repeat) {
                System.out.println("Player Searching Options:");
                System.out.println("(1) By Player Name");
                System.out.println("(2) By Club and Country");
                System.out.println("(3) By Position");
                System.out.println("(4) By Salary Range");
                System.out.println("(5) Country-wise player count");
                System.out.println("(6) Back to Main Menu");

                s = scan.nextLine();
                if(s.matches("1|2|3|4|5|6")) repeat=false;
                else {
                    System.out.println("Input is not valid.");
                    scan.nextLine();
                }
            }
            num=Integer.parseInt(s);
            if(num==6) num=0;
            else num=10+num;
        }
        else if(state==2){
            repeat=true;
            while(repeat) {
                System.out.println("Club Searching Options:");
                System.out.println("(1) Player(s) with the maximum salary of a club");
                System.out.println("(2) Player(s) with the maximum age of a club");
                System.out.println("(3) Player(s) with the maximum height of a club");
                System.out.println("(4) Total yearly salary of a club");
                System.out.println("(5) Back to Main Menu");

                s = scan.nextLine();
                if(s.matches("1|2|3|4|5")) repeat=false;
                else {
                    System.out.println("Input is not valid.");
                    scan.nextLine();
                }
            }
            num=Integer.parseInt(s);
            if(num==5) num=0;
            else num=20+num;
        }
        return num;
    }
}
