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

    //synchronized    -   metodu treba sinhronizovati
    public static void povecaj() {
        brojac++;
    }
//     public synchronized static void povecaj() {
//        brojac++;
//    }
}

class Nit extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Broj.povecaj();
        }
    }

    /**
     * Ako pažljivije pogledamo kod, vidimo da se u main metodi kreira 10000
     * niti i da se svaka startuje. Svaka od tih niti, tačno 100 puta pozove
     * statičku metodu povecaj(), a ta metoda povecava za jedan brojac, koji je
     * statički intiger. Nakon startovanja niti, uspavamo glavnu nit na 5
     * sekundi, kako bismo bili sigurni da ce svih 10000 niti završiti posao,
     * tj. povecati po 100 puta brojač. Zatim, taj brojac ispišemo u konzoli.
     * Očekivano bi bilo da je vrednost brojaca 100*10000 = 100 000, međutim
     * broj koji se ispiše je 999764 Problem je u tome što se povecavanje broja
     * (iako jednostavna operacija), sastoji iz više koraka. 
     * Prvo se vrednost koju trenutno ima brojac prepiše u registar, zatim se u registru poveca,
     * pa se onda ta povecana vrednost vrati u brojac. Kako smo mi pokrenuli
     * 10000 niti, a nemamo 10000 jezgara na procesoru da se one izvršavaju
     * potpuno paralelno, izvršava se multitasking. <b>Procesor svakoj niti daje po
     * mali deo vremena, tako da se nama čini da se sve niti izvršavaju
     * paralelno.</b> Zbog toga, svaka nit može biti prekinuta u bilo kom trenutku,
     * čak iako nije završila svoju metodu, metodu povecaj().
     * 
     * Posmatrajmo dve niti, npr. nit A i nit B. Neka je vrednost brojac = 121. Trenutno se
     * izvtšava A, a B čeka. A uzme vrednost upisanu u brojac i upiše je u
     * registar, zatim poveca broj u registru za 1, znači na 122. Tu nastupa
     * prekid niti A, i počinje izvršavanje niti B. Nit B, takođe uzima vrednost
     * iz brojac-a koji je i dalje 121, upisuje ga u svoj registar, povedava na
     * 122 i vraca ga u brojac. brojac sada ima vrednost 122. Prekida se nit B i
     * počinje opet sa izvršavanjem nit A. A nastavlja tamo gde je stala. Da bi
     * završila metodu, potrebno je da upiše vrednost iz registra u brojac, a to
     * je 122. I opet je vrednost u brojac-u je 122. Dakle, dva puta se izvršila
     * metoda povecaj(), a brojac se sa 121 povedao na 122. Kako bismo se
     * zaštitili od ovakve greške, potrebno je da nekako zaključamo promenljivu
     * brojac za sve niti, osim za onu koja je započela rad sa njom. To
     * postižemo ključnom reči syncronized, prilikom deklarisanja metode koje
     * rade na deljenom resursu. Kod nas je to metoda povecaj(), a deljeni
     * resurs je brojac.
     * 
     * <b> Postoji još jedan mehanizam za zakljčavanje, a to je
     *   klasa ReentrantLock. </b>
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Nit().start();
        }
        Thread.sleep(10000);
        System.out.println(Broj.brojac);
    }
}
