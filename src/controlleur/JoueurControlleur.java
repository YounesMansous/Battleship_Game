package src.controlleur;

import java.util.Random;

import src.modele.Bateau;
import src.modele.Cellule;
import src.modele.Joueur;
import src.vue.Vue;

public class JoueurControlleur {

    protected Joueur joueur; // Un joueur
    protected Cellule celluleDebut; // la cellule par laquelle commence (avec ses coordonnées)
    protected Cellule dernierCoup; // le dernieur coup joué (dernière case tiréé)
    protected Cellule premierRouge; // la première case case touché est contenant un bateau

    public Cellule getCelluleDebut() {
        return celluleDebut;
    }

    public JoueurControlleur(Joueur joueur) {
        this.joueur = joueur;
        this.celluleDebut = null;
        this.dernierCoup = null;
    }

    public void tire(int x, int y) { // fonction qui permet de tirer sur une case choisi avec les coordonnées en
                                     // paramètre
        Cellule celluleTire = this.joueur.getGrille().chercherCellule(x, y);
        celluleTire.tire();
        if (celluleTire.getEstDansBateau()) {
            if (this.dernierCoup == null) {
                this.premierRouge = celluleTire;
            }
            this.dernierCoup = celluleTire;
        }
        if (this.recupBateau(celluleTire) != null) {
            if (this.recupBateau(celluleTire).getComposition().size() == 1) {
                dernierCoup = null;
                premierRouge = null;
            }
        }
        for (Bateau b : this.joueur.getBateaux()) {
            b.touche(celluleTire);
        }
        this.miseAjour();
        this.joueur.fireChangement();
    }

    public void tireAleatoire() { // fonction qui permet de faire des tires aléatoires (utile pour le robot
                                  // aléatoire)
        Random rand = new Random();
        int x = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
        int y = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
        while (this.joueur.getGrille().chercherCellule(x, y).getEstTouche()) {
            x = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
            y = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
        }
        this.tire(x, y);
    }

    public void tireIntelligent() { // fonction qui permet de faire des tires plus intéligent après la détection du
                                    // premier morceau de bateau (utile pour le robot aléatoire)
        if (dernierCoup == null) {
            this.tireAleatoire();
        } else {
            int initX = this.dernierCoup.getX();
            int initY = this.dernierCoup.getY();
            int x = 0;
            int y = 0;

            // définition des voisins de la première case rouge
            Cellule gauche = null;
            Cellule droite = null;
            Cellule bas = null;
            Cellule haut = null;

            if (initX - Vue.TCELLULE >= 0 && initX - Vue.TCELLULE <= Vue.BORD) {
                gauche = this.joueur.getGrille().chercherCellule(initX - Vue.TCELLULE, initY);
            }
            if (initX + Vue.TCELLULE >= 0 && initX + Vue.TCELLULE <= Vue.BORD) {
                droite = this.joueur.getGrille().chercherCellule(initX + Vue.TCELLULE, initY);
            }
            if (initY + Vue.TCELLULE >= 0 && initY + Vue.TCELLULE <= Vue.BORD) {
                bas = this.joueur.getGrille().chercherCellule(initX, initY + Vue.TCELLULE);
            }
            if (initY - Vue.TCELLULE >= 0 && initY - Vue.TCELLULE <= Vue.BORD) {
                haut = this.joueur.getGrille().chercherCellule(initX, initY - Vue.TCELLULE);
            }

            System.out.println(gauche);

            if (droite != null) {
                if (droite.getEstDansBateau() && droite.getEstTouche()) {
                    x -= Vue.TCELLULE;
                }
            }
            if (gauche != null) {
                if (gauche.getEstDansBateau() && gauche.getEstTouche()) {
                    x += Vue.TCELLULE;
                }
            }
            if (haut != null) {
                if (haut.getEstDansBateau() && haut.getEstTouche()) {
                    y += Vue.TCELLULE;
                }
            }
            if (bas != null) {
                if (bas.getEstDansBateau() && bas.getEstTouche()) {
                    y -= Vue.TCELLULE;
                }
            }

            if (x == y) {
                Random rand = new Random();
                x = rand.nextInt(2) * Vue.TCELLULE;
                y = rand.nextInt(2) * Vue.TCELLULE;
                int pos = rand.nextInt(2);
                if (pos == 1) {
                    x = -x;
                    y = -y;
                }

                boolean check = true;
                while (x == y || initX + x < 0
                        || initX + x > Vue.BORD
                        || initY + y < 0 || initY + y > Vue.BORD
                        || check) {

                    if (x != y && initX + x >= 0
                            && initX + x <= Vue.BORD
                            && initY + y >= 0 && initY + y <= Vue.BORD) {
                        check = this.joueur.getGrille().chercherCellule(initX + x, initY + y).getEstTouche();
                        if (!check) {
                            break;
                        }
                    }
                    x = rand.nextInt(2) * Vue.TCELLULE;
                    y = rand.nextInt(2) * Vue.TCELLULE;
                    pos = rand.nextInt(2);
                    if (pos == 1) {
                        x *= -1;
                        y *= -1;
                    }
                }
            }

            if (this.joueur.getGrille().chercherCellule(initX + x, initY + y).getEstTouche()) {
                this.dernierCoup = this.premierRouge;
                tire(initX + x, initY + y);
            }
            tire(initX + x, initY + y);
        }
    }

    public void ajoutBateau(Bateau b) { // ajout d'un bateau dans la grille
        this.joueur.getBateaux().add(b);
    }

    public Bateau generationBateau() { //
        Bateau res = null;
        Random rand = new Random();

        int taille = rand.nextInt(4) + 2;
        int orientation = rand.nextInt(2);
        int debutX = 0;
        int debutY = 0;
        boolean intersection = true;

        if (orientation == 0) {

            debutX = rand.nextInt(Vue.GTAILLE - taille) * Vue.TCELLULE;
            debutY = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;

            while (intersection == true) {
                debutX = rand.nextInt(Vue.GTAILLE - taille) * Vue.TCELLULE;
                debutY = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
                intersection = false;
                for (int i = 0; i < taille; i++) {
                    if (this.joueur.getGrille().chercherCellule(debutX + i * Vue.TCELLULE,
                            debutY).getEstDansBateau()) {
                        intersection = true;
                    }
                }
            }

            res = new Bateau("test", taille, debutX, debutY, orientation);
            for (int i = 0; i < taille; i++) {
                Cellule cellule = this.joueur.getGrille().chercherCellule(debutX + i * Vue.TCELLULE, debutY);
                res.getComposition().add(cellule);
                cellule.composeBateau();
            }
        }

        if (orientation == 1) {

            debutX = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
            debutY = rand.nextInt(Vue.GTAILLE - taille) * Vue.TCELLULE;

            while (intersection == true) {
                debutX = rand.nextInt(Vue.GTAILLE) * Vue.TCELLULE;
                debutY = rand.nextInt(Vue.GTAILLE - taille) * Vue.TCELLULE;
                intersection = false;
                for (int i = 0; i < taille; i++) {
                    if (this.joueur.getGrille().chercherCellule(debutX,
                            debutY + i * Vue.TCELLULE).getEstDansBateau()) {
                        intersection = true;
                    }
                }
            }

            res = new Bateau("test", taille, debutX, debutY, orientation);
            for (int i = 0; i < taille; i++) {
                Cellule cellule = this.joueur.getGrille().chercherCellule(debutX, debutY + i * Vue.TCELLULE);
                res.getComposition().add(cellule);
                cellule.composeBateau();
            }
        }
        return res;

    }

    public Bateau recupBateau(Cellule cellule) { // une fonction qui récupère la bateau dans lequel la cellule donnée en
                                                 // paramètre se trouve.
        for (Bateau b : this.joueur.getBateaux()) {
            for (Cellule c : b.getComposition()) {
                if (c == cellule) {
                    return b;
                }
            }
        }
        return null;
    }

    public void miseAjour() { // fonction qui permet de mettre à jour le programme
        if (this.joueur.getBateaux().size() != 0) {
            int index = -1;
            for (Bateau b : this.joueur.getBateaux()) {
                if (b.estCoule()) {
                    index = this.joueur.getBateaux().indexOf(b);
                }
            }
            if (index != -1) {
                this.joueur.getBateaux().remove(index);
            }
        }
    }

    public boolean gagne() { // fonction qui prècise si un joueur a fini ses taches (a gagné)
        return this.joueur.getBateaux().size() == 0;
    }

    @Override
    public String toString() {
        return this.joueur.getGrille().toString();
    }
}