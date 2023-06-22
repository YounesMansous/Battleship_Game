package src.modele;

import src.util.AbstractModeleEcoutable;

public class Partie extends AbstractModeleEcoutable {

    public Joueur joueur1; 
    public Joueur joueur2;
    protected Joueur joueurCourant;
    public Boolean finie = false; // teste si la partie est finie 

    public Partie(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = this.joueur1;
    }

}