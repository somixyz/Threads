/**
 * Wait i notify i
 * Mehanizmi za kontrolu toka izvrsavanja razlicitih niti, preko boolean flag-ova
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
//            if (isDobarDan == false)
//                wait();
            while (isCao == false) {
                wait();
            }
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
            while (isZdravo == false) {
                wait();
            }
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
            while (isDobarDan == false) {
                wait();
            }
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

    /**
     * Pored dve niti koje ispisuju Cao! i Zdravo!, ovde dodajemo i tredu nit
     * koja ispisuje DobarDan!. Ideja je ista, redosled ispisivanja da bude Cao!
     * Zdravo! Dobar dan! Cao! Zdravo!
     *
     * Sada nije dovoljna boolean vrednost da proveri sva tri slučaja, pa su
     * uvedene 3 vrednosti. Kada je neka od njih true, to je slededa nit koja
     * treba da se izvrši. Kada se ispiše Cao!, boolean za nit Zdravo se postavi
     * na true, ostale na false i ista logika je za druge niti.
     *
     * Sada nije dovoljna boolean vrednost da proveri sva tri slučaja, pa su
     * uvedene 3 vrednosti. Kada je neka od njih true, to je slededa nit koja
     * treba da se izvrši. Kada se ispiše Cao!, boolean za nit Zdravo se postavi
     * na true, ostale na false i ista logika je za druge niti.
     *
     * Rezultat nije očekivan, ispiše se Cao! , Zdravo!, pa opet Cao! itd. To je
     * zbog toga što se provera za čekanje vrši if uslovom. Ono što se desi je
     * da se ispiše Cao!, postave booleani da se sledede ispiše Zdravo!. Nakon
     * Zdravo! poziva se notifyAll(). Za to vreme i Cao i DobarDan su ušle u if
     * uslov i čekaju da se prekine metoda wait(). Kada to uradi Zdravo, opet se
     * ispiše Cao.
     * <b>Ono što je dobra praksa i što bi uvek trebalo raditi, je da se if
     * uslov zameni while uslovom. 2 Sve tri metode bi trebao da liče na
     * slededu.</b>
     *
     * Sada, kada se ispiše Cao!, nameste booleani, ispiše Zdravo!, ponovo
     * nameste booleani i pozove metoda notifyAll(), obavesti se i nit Cao da
     * izađe iz wait() stanja. Međutim, kako se sad taj uslov nalazi u petji,
     * opet se proveri da li je zaista nit Cao ta koja treba da nastavi sa
     * izvršavanjem. Ako nije, ona opet ulazi u fazu wait()
     */
    public static void main(String[] args) {
        Konzola konzola = new Konzola();
        new Cao(konzola).start();
        new Zdravo(konzola).start();
        new DobarDan(konzola).start();
    }
}
