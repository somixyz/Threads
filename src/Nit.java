
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Milos Dragovic
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
        new Nit("A").start();
        new Nit("B").start();
        new Nit("C").start();
        new Nit("D").start();
        new Nit("E").start();
        //za prekid ukucati "kraj"
                if(new Scanner(System.in).hasNext("kraj")){System.exit(0);}

    }
}
