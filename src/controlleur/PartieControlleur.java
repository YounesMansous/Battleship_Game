package src.controlleur;

import src.modele.Joueur;
import src.modele.Partie;

public class PartieControlleur {

    protected Partie partie;
    protected JoueurControlleur joueurControlleur1;
    protected JoueurControlleur joueurControlleur2;

    public PartieControlleur(Partie partie, JoueurControlleur joueurControlleur1,
            JoueurControlleur joueurControlleur2) {
        this.partie = partie;
        this.joueurControlleur1 = joueurControlleur1;
        this.joueurControlleur2 = joueurControlleur2;
    }

    public Partie getPartie() {
        return this.partie;
    }

    public JoueurControlleur getJoueur1() {
        return this.joueurControlleur1;
    }

    public JoueurControlleur getJoueur2() {
        return this.joueurControlleur2;
    }

    public Joueur getWinner() { // retourne le joueur gagnant de la partie
        if (this.joueurControlleur1.gagne()) {
            this.partie.finie = true;
            this.partie.fireChangement();
            return this.partie.joueur1;
        } else if (this.joueurControlleur2.gagne()) {
            this.partie.finie = true;
            this.partie.fireChangement();
            return this.partie.joueur2;
        }
        return null;
    }

    public boolean isGameOver() { // fonction qui vérifie si le jeu est fini (un des deux joueur à gagné).
        this.getWinner();
        return this.partie.finie;
    }
}
