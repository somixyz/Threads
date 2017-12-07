/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package synchronizedd;

/**
 *
 * @author Milos Dragovic
 */
public class Broj {

    static int brojac = 0;
    
    //synchronized    
    public static void povecaj() {
        brojac++;
    }
}

class Nit extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Broj.povecaj();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Nit().start();
        }
        Thread.sleep(10000);
        System.out.println(Broj.brojac);
    }
}
