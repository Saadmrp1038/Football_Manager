package saadmrp.Functions;

import java.io.Serializable;

public class Player implements Serializable {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private int Number;
    private double Salary;
    private double Price;

    public Player(String name, String country, int age, double height, String club, String position, int number, double salary) {
        Name = name;
        Country = country;
        Age = age;
        Height = height;
        Club = club;
        Position = position;
        Number = number;
        Salary = salary;
    }

    public Player(String name, String country, int age, double height, String position, int number, double salary,double price) {
        Name = name;
        Country = country;
        Age = age;
        Height = height;
        Position = position;
        Number = number;
        Salary = salary;
        Price = price;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }

    public int getAge() {
        return Age;
    }

    public double getHeight() {
        return Height;
    }

    public String getClub() {
        return Club;
    }

    public String getPosition() {
        return Position;
    }

    public int getNumber() {
        return Number;
    }

    public double getSalary() {
        return Salary;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public void setClub(String club) {
        Club = club;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public void print(int ind){
        System.out.println("________"+"Player No.: "+(ind+1)+"________");
        System.out.println("Name     : "+Name);
        System.out.println("Country  : "+Country);
        System.out.println("Age      : "+Age);
        System.out.println("Height   : "+Height);
        System.out.println("Club     : "+Club);
        System.out.println("Position : "+Position);
        System.out.println("Number   : "+Number);
        System.out.println("Salary   : "+Salary);
    }

}
