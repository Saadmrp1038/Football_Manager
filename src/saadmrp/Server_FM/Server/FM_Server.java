package saadmrp.Server_FM.Server;

import saadmrp.Functions.*;
import saadmrp.Server_FM.util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class FM_Server {
    private ServerSocket serverSocket;
    private static String Player_FileName = "./src/saadmrp/Server_FM/Data/players.txt";
    private static String User_FileName = "./src/saadmrp/Server_FM/Data/users.txt";
    private static String Sell_list_Filename ="./src/saadmrp/Server_FM/Data/sellList.txt";
    private static AllPlayers Players;
    private static MarketPlayers marketPlayers;
    private static HashMap<String,String> Users;
    private ArrayList<NetworkUtil> nu = new ArrayList<>();

    FM_Server() {
        System.out.println("Server Open");
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        System.out.println("Client Connected");
        nu.add(networkUtil);
        new ReadThreadServer(networkUtil,Players,marketPlayers,Users,this);
    }

    public static void main(String args[]) throws FileNotFoundException {
        FileHandling File = new FileHandling(Player_FileName,User_FileName);
        Players = new AllPlayers(File.getPlayers());
        Users = File.getUsers();
        FileHandling File2 = new FileHandling(Sell_list_Filename);
        marketPlayers =new MarketPlayers(File2.getPlayers());
        FM_Server server = new FM_Server();

    }

    public void update() throws IOException {
        FileHandling File = new FileHandling(Sell_list_Filename);
        marketPlayers =new MarketPlayers(File.getPlayers());
        FileHandling File2 = new FileHandling(Player_FileName,User_FileName);
        Players = new AllPlayers(File2.getPlayers());

        for(int i=0;i<nu.size();i++){
            if(!nu.get(i).getSocket().isClosed()) {
                nu.get(i).write("update");
            }
        }
    }

    public  AllPlayers getPlayers() {
        return Players;
    }

    public MarketPlayers getMarketPlayers() {
        return marketPlayers;
    }

}
