
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Milos Dragovic
 */
public class Forma extends JFrame {

    JButton dugme_1;
    JButton dugme_2;

    public Forma() {
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

    public static void main(String[] args) {
        new Forma();
    }
}
