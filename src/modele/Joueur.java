package src.modele;

import java.util.ArrayList;
import src.util.AbstractModeleEcoutable;

public class Joueur extends AbstractModeleEcoutable {

    public enum EnumTypeJoueur { // énumération des types de joueurs dans le jeu.
        Humain,
        RobotAleatoire,
        RobotIntelligent,
    }

    protected String nom; // nom du joueur
    protected Grille grille; // grille de chaque joueur
    protected ArrayList<Bateau> bateaux; // liste des bateaux qui possèdent chaque joueur
    protected EnumTypeJoueur type; // type de joueur

    public Joueur(String nom, EnumTypeJoueur type) {
        super();
        this.nom = nom;
        this.grille = new Grille();
        this.bateaux = new ArrayList<Bateau>();
        this.type = type;
    }

    // accesseurs
    public String getNom() {
        return this.nom;
    }

    public Grille getGrille() {
        return this.grille;
    }

    public ArrayList<Bateau> getBateaux() {
        return this.bateaux;
    }

    public EnumTypeJoueur getType() {
        return this.type;
    }

}