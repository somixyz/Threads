/**
 *  Wait i notify i
 * Mehanizmi za kontrolu toka izvrsavanja razlicitih niti, preko boolean flag-ova
 * 
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

    /**
     * Ključna reč synchronized ne garantuje koliko dugo jedna nit radi sa
     * jednim resursom. Slededi primer to pokazuje. Treba napraviti dve niti,
     * koje ce po 50 puta naizmenično da ispisuju Cao! pa Zdravo! u konzoli. Iz
     * main metode se pokredu dve niti. Obe niti koriste metode objekta klase
     * Konzola. Te metode su sinhronizovane, što znači da su resursi u njoj
     * zaključani za korišcenje drugih niti, dok tekuca ne završi posao. Kako
     * procesor daje određeno vreme jednoj niti, pa drugoj, može se desiti da se
     * izvrši više od jedne iteracije metode run() i na taj način ispiše više
     * puta Cao! pre jednog Zdravo! i obrnuto. Mi želelimo da se ispiše jedno
     * Cao!, zatim jedno Zdravo!, pa opet Cao! itd. Zbog toga, jednu nit treba
     * staviti na čekanje, dok je druga ne obavesti da može da počne sa radom.
     *
     *
     * Metoda za obaveštanje drugih niti je <b>notify() ili notifyAll()</b>. U ovom
     * primeru smo koristili notifyAll(). Ukoliko postoji više niti, preporuka
     * je koristiti notifyAll(), jer se na taj način obaveštavaju sve niti, dok
     * se metodom notify() nasumično bira nit koja ce prekinuti čekanje.
     * Čekanje niti na poziv notify() se ostvaruje metodom wait(). Ova metoda baca
     * InterruptedException ukoliko se nad njom pozove metode interrupt(), a nit
     * se nalazi u fazi wait(). 
     * 
     * Sve metode se pozivaju samo iz syncronized
     * metoda. Tako su resursi zaključani, ukoliko su zajednički. U našem
     * slučaju, zajednički resurs je konzola po kojoj pišemo. <b>Redosled kojim ce
     * se niti izvršavati moramo mi da odredimo. To se radi uz pomoc
     * booleana/flagova ili sličnih mehanizama.</b> U ovom primeru, koristimo
     * boolean reciZdravo, koji je na početku postavljen na false, znači prvo ce
     * se ispisati Cao!. Iz main metode smo startovali obe niti. I jedna, i
     * druga, pokušavaju da ispišu svoj tekst u konzoli. Zbog booleana
     * reciZdravo, uslov u niti Zdravo je ispunjen i ta nit je pozvala metodu
     * wait(). Za to vreme, ispisalo se Cao, zatim je promenjena vrednost
     * reciZdravo i pozvana je metoda notifyAll(). To je obavestenje za metodu
     * Zdravo da nastavi sa radom, dok je nit Cao otišla na čekanje (zbog
     * vrednosti booleana). Sada nit Cao čeka da neko pozove notifyAll(). I sve
     * se dešava tako u krug dok se ne završi 50-ta iteracija.
     */
    public static void main(String[] args) {
        Konzola konzola = new Konzola();
        new Cao(konzola).start();
        new Zdravo(konzola).start();
    }
}
