/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interrupt;

import java.util.Random;

/**
 *
 * @author Milos Dragovic
 */
public class Nit implements Runnable {

    String ime;
    Random random;

    public Nit(String ime) {
        this.ime = ime;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            //u while uslovu se ispituje da li je trenutna nit prekinuta ako nije
            //izvrsavaju se dalje koraci u while loop-u, sve dok se ne pozove metoda inerrupt()
            //koja boolean flag postavlja na true, a uslov postaje false i prekida se while loop
            //pa se zavrsava i metoda run()
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread ID: " + Thread.currentThread().getId() + " - " +ime );
                int pauza = random.nextInt(5) * 1000;
                Thread.sleep(pauza);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) throws Exception {

        
        //Ovde koristimo metodu inerrupt() kako bismo prekinuli izvrsavanje niti 
        Thread A = new Thread(new Nit("A"));
        A.start();
        Thread B = new Thread(new Nit("B"));
        B.start();
        Thread C = new Thread(new Nit("C"));
        C.start();
        Thread D = new Thread(new Nit("D"));
        D.start();
        Thread E = new Thread(new Nit("E"));
        E.start();
        Thread.sleep(3000); // uspavaj glavnu nit 3s
        C.interrupt();
        System.out.print("|C|"); // prekini nit "C"
        Thread.sleep(3000); // uspavaj glavnu nit 3s
        D.interrupt();
        System.out.print("|D|"); // prekini nit "D"
        Thread.sleep(3000); // uspavaj glavnu nit 3s
        E.interrupt();
        System.out.print("|E|"); // prekini nit "E"

    }
}
