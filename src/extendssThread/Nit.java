package extendssThread;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Milos Dragovic
 *
 * Niti (Threads) u Javi su objekti klase Thread. Klasa Thread nasleđuje
 * interfejs Runnable, koji ima jednu jedinu metodu - run(). Kod koji se nalazi
 * u metodi run() je kod koji se izvršava pokretanjem nove niti. Pošto niti nisu
 * ništa drugo nego objekti, potrebno je kreirati klasu koja ce ili da nasledi
 * Thread, ili da implementira Runnable.
 *
 * Eksplicitno redefinisemo metodu run() klase Thread. Pozivom metode start()
 * pocinje se sa izvrsavanje nove niti, dok metoda start() poziva izvrsavanje
 * metode run(). 
 */
public class Nit extends Thread {

    String ime;
    Random random;

    public Nit(String imeNiti) {
        this.ime = imeNiti;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print(ime + " - ");
                int pauza = random.nextInt(5) * 1000;
                sleep(pauza);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new Nit("A").start();
//        new Nit("B").start();
//        new Nit("C").start();
//        new Nit("D").start();
//        new Nit("E").start();
        //za prekid ukucati "kraj"
//        if (new Scanner(System.in).hasNext("kraj")) {
//            System.exit(0);
//        }

    }
}
