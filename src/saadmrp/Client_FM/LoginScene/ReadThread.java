package saadmrp.Client_FM.LoginScene;

import javafx.application.Platform;
import saadmrp.FM_Client;
import saadmrp.Functions.AllPlayers;
import saadmrp.Functions.MarketPlayers;
import saadmrp.Functions.Player;
import saadmrp.Server_FM.util.LoginDTO;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final FM_Client main;
    public ReadThread(FM_Client main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showMainPage(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    else if(o instanceof AllPlayers){
                        AllPlayers p = (AllPlayers) o;
                        main.setUserClub(p);
                    }
                    else if(o instanceof MarketPlayers){
                        MarketPlayers m = (MarketPlayers) o;
                        main.setMarketPlayers(m);
                    }
                    else if(o instanceof String){
                        String s = (String) o;
                        if(s.equals("update")){
                            this.main.getNetworkUtil().write("update");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



