package src.modele;

public class Cellule {
    protected int x; // la position x de la cellule.
    protected int y; // la position y de la cellule.
    protected boolean estTouche; // teste si une cellule est tirée.
    protected boolean estDansBateau; // teste si une cellule est contenue dans un bateau.

    public Cellule(int x, int y) {
        this.x = x;
        this.y = y;
        this.estTouche = false;
        this.estDansBateau = false;
    }

    // acdesseurs et setteurs

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getEstDansBateau() {
        return this.estDansBateau;
    }

    public boolean getEstTouche() {
        return this.estTouche;
    }

    public void composeBateau() {
        this.estDansBateau = true;
    }

    public void tire() {
        this.estTouche = true;
    }

    public boolean equals(Object o) {
        return (this == o);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}