
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Milos Dragovic
 */
public class OsluskujListener implements ActionListener {

    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
//        Thread trd = new Thread() {
//            @Override
//            public void run() {
                ServerSocket serverSoket;
                try {
                    serverSoket = new ServerSocket(8765);
                    System.out.println("SERVER: Osluskujem....."); 
                    Socket soket = serverSoket.accept();
                } catch (Exception ex) {
                    Logger.getLogger(OsluskujListener.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
//        };
//        trd.start();
//    }
}
