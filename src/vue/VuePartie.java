package src.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

import src.controlleur.PartieControlleur;
import src.util.EcouteurModele;

public class VuePartie extends JPanel implements EcouteurModele {

    public PartieControlleur partieControlleur;
    public JLabel text;

    public VuePartie(PartieControlleur partie) {
        this.partieControlleur = partie;
        this.partieControlleur.getPartie().ajoutEcouteur(this);
        setPreferredSize(new Dimension(Vue.DIM * 3, Vue.DIM * 2));
        this.setVisible(false);
        JLabel text = new JLabel("Partie terminée, le gagnant est : " + this.partieControlleur.getWinner().getNom(),
                SwingConstants.CENTER);
        text.setFont(new Font("Serif", Font.BOLD, 35));
        setLayout(new BorderLayout(2, 3));
        this.add(text, BorderLayout.CENTER);

    }

    public void modeleMiseAJour(Object source) {
        this.setVisible(true);
        System.out.println("on est dans visible");
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
