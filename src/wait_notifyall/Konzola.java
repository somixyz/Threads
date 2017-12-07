/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wait_notifyall;

/**
 *
 * @author Milos Dragovic
 */
public class Konzola {

    boolean reciZdravo = false;

    public synchronized void cao() {
        try {
            if (reciZdravo) {
                wait();
            }
            System.out.println("CAO!");
            reciZdravo = true;
            notifyAll();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void zdravo() {
        try {
            if (!reciZdravo) {
                wait();
            }
            System.out.println("\t\t ZDRAVO!");
            reciZdravo = false;
            notifyAll();
        } catch (InterruptedException e) {
        }
    }
}

class Cao extends Thread {

    Konzola konzola;

    public Cao(Konzola konzola) {
        this.konzola = konzola;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            konzola.cao();
        }
    }
}

class Zdravo extends Thread {

    Konzola konzola;

    public Zdravo(Konzola konzola) {
        this.konzola = konzola;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            konzola.zdravo();
        }
    }
}

class Test {

    public static void main(String[] args) {
        Konzola konzola = new Konzola();
        new Cao(konzola).start();
        new Zdravo(konzola).start();
    }
}
