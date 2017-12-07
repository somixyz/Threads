package runnable;


import java.util.Random;

/**
* Za kreiranje niti, prvo instanciramo objekat klase Thread.
* Potrebno je kao ulazni parametar u konstruktor ubaciti interfejs Runnable
* (u ovom slučaju klasu koja je implementirala taj
* interfjes), a zatim nad tim objektom pozvati metodu start()
* 
*/
/**
 *
 * @author Milos Dragovic
 */
public class NitRunnable implements Runnable {

    String ime;
    Random random;

    public NitRunnable(String imeNiti) {
        this.ime = imeNiti;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print(ime + " - ");
                int pauza = random.nextInt(5) * 1000; 
                System.out.println(getClass().getName() + "JA SAM NIT SA ID: " +Thread.currentThread().getId()+" IME SUPERKLASE : " +Thread.currentThread().getClass());
               // staticka metoda klase Thread
                Thread.sleep(pauza);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
//        new Thread(new NitRunnable("A")).start();
//        new Thread(new NitRunnable("B")).start();
//        new Thread(new NitRunnable("C")).start();
//        new Thread(new NitRunnable("D")).start();
//        new Thread(new NitRunnable("E")).start();
//        new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(" JA SAM RUNNABLE NIT, ");
//            }
//        }.run();
//        System.out.println("JA SAM MAIN ");
    }

}
