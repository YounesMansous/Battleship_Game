package src.modele;

import java.util.ArrayList;

public class Bateau {

    protected String nom; // nom du bateau.
    protected int taille; // taille du bateau (nombre de cellules).
    protected ArrayList<Cellule> composition; // tableau de cellule qui compose le bateau.
    protected int orientation; // direction du bateau (vertical = 1 ou horizontal = 0).
    protected int debutX; // coordonné x de la première cellule composante du bateau (son début).
    protected int debutY; // coordonné y de la première cellule composante du bateau (son début).
    protected boolean estCoule; // précise si un bateau est coulé

    public Bateau(String nom, int taille, int debutX, int debutY, int orientation) {
        this.nom = nom;
        this.taille = taille;
        this.debutX = debutX;
        this.debutY = debutY;
        this.orientation = orientation;
        this.composition = new ArrayList<Cellule>();
        this.estCoule = false;
    }

    // accesseurs

    public int getTaille() {
        return this.taille;
    }

    public int getDebutX() {
        return this.debutX;
    }

    public int getDebutY() {
        return this.debutY;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean getCoule() {
        return this.estCoule;
    }

    public ArrayList<Cellule> getComposition() {
        return this.composition;
    }

    // setteurs
    public void setComposition(ArrayList<Cellule> compoBateau) {
        this.composition = compoBateau;
    }
    
    public void touche(Cellule c) { // fonction qui décrémente la liste des cellules d'un bateau lorsqu'il est touché.
        if (this.composition.contains(c)) {
            this.composition.remove(this.composition.indexOf(c));

        }
    }

    public boolean estCoule() { // teste si le navire est coulé
        this.estCoule = true;
        return this.composition.size() == 0;
    }

    

}