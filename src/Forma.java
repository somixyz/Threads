
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Pimer problema jednonitnih programa
 *
 *
 * Forma koja sadrži dva dugmeta. Drugo dugme prekida rad programa. Kada se
 * pritisne dugme Osluskuj, izvšava se actionPerformed() koja čeka da se ostvari
 * TCP konekcija na portu 8765.
 *
 * Kada se kreira GUI aplikacija, tačnije prilikom pozivanja metode
 * setVisible(true), kreira se jedna posebna nit koja se zove Event Dispatch
 * Thread. Ova nit je zadužena za osluškivanje događaja kao što su pritiskanje
 * dugmeta, klik mišem itd. U našem slučaju desio se klik mišem na dugme
 * Osluskuj i actionPerformed() je počela sa izvršavanjem, i to u ovoj Event
 * Dispatch niti. Metoda .accept() je tzv. “bloker” i kod nece nastaviti da se
 * izvršava sve dok ne dođe do TCP zahteva. Samim tim, ni metoda
 * actionPerformed() se nede završiti, pa ni Event Dispatch nit nije u
 * mogudnosti da osluškuje na nove događaje (klik na dugme Zatvori). Problem bi
 * bio rešen ukoliko bismo Osluškivanje ostvarili u posebnoj niti, tako da nit
 * Event Dispatch može nesmetano da nastavi sa osluškivanjem.
 */
/**
 *
 * @author Milos Dragovic
 */
public class Forma extends JFrame {

    JButton dugme_1;
    JButton dugme_2;

    public Forma() throws InterruptedException {

        setSize(150, 150);
        setLayout(new FlowLayout());
        dugme_1 = new JButton("Osluskuj");
        dugme_1.setSize(60, 20);
        dugme_1.addActionListener(new OsluskujListener());
        dugme_2 = new JButton("Zatvori");
        dugme_2.setSize(60, 20);
        dugme_2.addActionListener(new ZatvoriListener());
        add(dugme_1);
        add(dugme_2);
        setVisible(true);

    }

    public static void main(String[] args) throws InterruptedException {
        //Ovde se pokrece ("glavna") EventDespecher nit, medjutim postavljanjem posebne niti 
        //za OsluskujListener omogucavamo da se glavna nit rastereti tj da ne zavisi od 
        //izvrsavanja metode u konkretnom listeneru. 
        
        // Da bi bilo moguce prekinuti izvrsavanje osluskivanja servera
        //potrebno je u predefinisanoj metodi klase OsluskujListener odkomentarisati 
        //delove koda 
        new Forma();
    }
}
