package src.vue;

import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import src.modele.Cellule;
import src.modele.Grille;
import src.modele.Joueur;
import src.util.EcouteurModele;

public class Vue extends JPanel implements EcouteurModele {

    public Joueur joueur;
    public static final int TCELLULE = 50; // taile de la cellule fixe
    public static final int GTAILLE = 10; // taille de la grille fixe
    public static final int BORD = TCELLULE * GTAILLE - 50; // taiile maximum de la grille
    public static final int DIM = BORD + 60; // dimension de la grille

    public Vue(Joueur joueur) {
        this.joueur = joueur;
        this.joueur.ajoutEcouteur(this);
        setPreferredSize(new Dimension(DIM, DIM));

    }

    public void modeleMiseAJour(Object source) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) { // fonction qui permet d'afficher les deux grille dans le jframe dans
                                             // jpanel différents
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Line2D.Float(30, 20, 80, 90));
        super.paintComponent(g);

        Grille grid = this.joueur.getGrille();
        Cellule[][] cells = grid.getCellules();

        int initX = cells[0][0].getX() + (this.getWidth() - Vue.GTAILLE * TCELLULE) / 2;
        int initY = cells[0][0].getY() + (this.getHeight() - Vue.GTAILLE * TCELLULE) / 2;

        g.setColor(Color.CYAN);
        g.fillRect(initX, initY, DIM - 10, DIM - 10);
        g.setColor(Color.BLUE);
        g.drawRect(initX, initY, DIM - 10, DIM - 10);

        for (int i = 0; i < Vue.GTAILLE; i++) {
            for (int j = 0; j < Vue.GTAILLE; j++) {

                int x = cells[i][j].getX() + (this.getWidth() - Vue.GTAILLE * TCELLULE) / 2;
                int y = cells[i][j].getY() + (this.getHeight() - Vue.GTAILLE * TCELLULE) / 2;

                g.setColor(Color.BLUE);
                g.drawRect(x, y, TCELLULE, TCELLULE);

                // g.setColor(Color.GREEN);
                // g.fillOval(x + 8, y + 8, TCELLULE - 16, TCELLULE - 16);

                if (cells[i][j].getEstDansBateau() && cells[i][j].getEstTouche()) {
                    g.setColor(Color.RED);
                    g.fillOval(x + 8, y + 8, TCELLULE - 16, TCELLULE - 16);
                }

                else if (cells[i][j].getEstTouche()) {
                    g.setColor(Color.GREEN);
                    g.fillOval(x + 8, y + 8, TCELLULE - 16, TCELLULE - 16);
                }
            }
        }
    }
}
