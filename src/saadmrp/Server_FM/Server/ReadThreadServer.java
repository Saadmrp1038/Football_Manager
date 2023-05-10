package saadmrp.Server_FM.Server;

import saadmrp.Functions.*;
import saadmrp.Server_FM.util.NetworkUtil;
import saadmrp.Server_FM.util.LoginDTO;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;


public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    private HashMap<String, String> Users;
    private AllPlayers Players;
    private MarketPlayers marketPlayers;
    private String Sell_list_Filename ="./src/saadmrp/Server_FM/Data/sellList.txt";
    private String Player_Filename ="./src/saadmrp/Server_FM/Data/players.txt";
    private FM_Server server_main;
    private boolean run = true;
    private String user;

    public ReadThreadServer(NetworkUtil networkUtil,AllPlayers p,MarketPlayers m,HashMap<String, String> u,FM_Server server_main) {
        this.networkUtil = networkUtil;
        this.Players = p;
        this.Users = u;
        this.marketPlayers=m;
        this.server_main=server_main;
        this.thr = new Thread(this);
        thr.start();
    }
    public void setPlayers(AllPlayers players) {
        Players = players;
    }

    public void setMarketPlayers(MarketPlayers marketPlayers) {
        this.marketPlayers = marketPlayers;
    }
    public void run() {
        try {
            while (run) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = Users.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);

                        if(loginDTO.isStatus()){
                           ArrayList<Player> p =new ArrayList<>();
                            for(int i=0;i<Players.getPlayers().size();i++){
                                if(loginDTO.getUserName().equals(Players.getPlayers().get(i).getClub())){
                                    p.add(Players.getPlayers().get(i));
                                }
                            }
                            AllPlayers userClub = new AllPlayers(p);
                            networkUtil.write(userClub);
                            networkUtil.write(marketPlayers);
                            user = loginDTO.getUserName();
                        }
                    }

                    if(o instanceof SoldPlayer){
                        SoldPlayer sp = (SoldPlayer) o;
                        Player p = sp.getPlayer();
                            FileWriter fw=  new FileWriter(Sell_list_Filename,true);
                            if(marketPlayers.getPlayers().size()!=0) fw.write("\n");
                                fw.write(p.getName()+",");
                                fw.write(p.getCountry()+",");
                                fw.write(p.getAge()+",");
                                fw.write(p.getHeight()+",");
                                fw.write(p.getPosition()+",");
                                fw.write(p.getNumber()+",");
                                fw.write(p.getSalary()+",");
                                fw.write(Double.toString(p.getPrice()));
                                fw.close();

                            Players.removePlayer(p.getName());
                            marketPlayers.addPlayer(p);

                            FileHandling file = new FileHandling();
                            file.WriteToPlayerFile(Player_Filename,Players.getPlayers());

                            server_main.update();
                    }

                    if(o instanceof BoughtPlayer){
                        BoughtPlayer bp = (BoughtPlayer) o;
                        Player p = bp.getPlayer();
                        p.setPrice(0);

                        Players.addPlayer(p);
                        marketPlayers.removePlayer(p.getName());

                        FileHandling file = new FileHandling();
                        file.WriteToPlayerFile(Player_Filename,Players.getPlayers());
                        file.WriteToMarketFile(Sell_list_Filename,marketPlayers.getPlayers());

                        server_main.update();
                    }

                    if(o instanceof String){
                        String s = (String) o;
                        System.out.println(s);
                        if(s.equals("update")){
                            this.setMarketPlayers(server_main.getMarketPlayers());
                            this.setPlayers(server_main.getPlayers());
                            networkUtil.write(marketPlayers);
                            ArrayList<Player> p =new ArrayList<>();
                            for(int i=0;i<Players.getPlayers().size();i++){
                                if(user.equals(Players.getPlayers().get(i).getClub())){
                                    p.add(Players.getPlayers().get(i));
                                }
                            }
                            AllPlayers userClub = new AllPlayers(p);
                            networkUtil.write(userClub);

                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



