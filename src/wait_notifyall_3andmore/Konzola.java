/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wait_notifyall_3andmore;

/**
 *
 * @author Milos Dragovic
 */
public class Konzola {

    boolean isCao, isZdravo, isDobarDan;

    public Konzola() {
        isCao = true;
        isZdravo = false;
        isDobarDan = false;
    }

    public synchronized void cao() {
        try {
            //if
            while (isCao == false) 
                wait();
            
            System.out.println("CAO!");
            isCao = false;
            isZdravo = true;
            isDobarDan = false;
            notifyAll();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void zdravo() {
        try {
            //if
            while (isZdravo == false) 
                wait();
            

            System.out.println("\t\t ZDRAVO!");
            isCao = false;
            isZdravo = false;
            isDobarDan = true;
            notifyAll();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void dobarDan() {
        try {
            //if
            while (isDobarDan == false) 
                wait();
            
            System.out.println("\t\t\t\t DOBAR DAN!");
            isCao = true;
            isZdravo = false;
            isDobarDan = false;
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

class DobarDan extends Thread {

    Konzola konzola;

    public DobarDan(Konzola konzola) {
        this.konzola = konzola;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            konzola.dobarDan();
        }
    }
}

class Test {

    public static void main(String[] args) {
        Konzola konzola = new Konzola();
        new Cao(konzola).start();
        new Zdravo(konzola).start();
        new DobarDan(konzola).start();
    }
}
