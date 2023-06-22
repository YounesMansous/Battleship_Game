package src.modele;

import src.vue.Vue;

public class Grille {

    protected Cellule[][] cellule; // tableau de dimensions contenant des cellules 

    public Grille() {
        this.cellule = new Cellule[Vue.GTAILLE][Vue.GTAILLE];
        for (int i = 0; i < Vue.GTAILLE; i++) {
            for (int j = 0; j < Vue.GTAILLE; j++) {
                cellule[i][j] = new Cellule(i * Vue.TCELLULE, j * Vue.TCELLULE);
            }
        }
    }

    public Cellule[][] getCellules() {
        return this.cellule;
    }

    public Cellule chercherCellule(int posX, int posY) { // fonction permettante de chercher une cellule dans la grille à partir de ses coordonnées.
        return this.cellule[posX / Vue.TCELLULE][posY / Vue.TCELLULE];
    }

    public int nbVoisinsTouche(int posX, int posY) { // fonction qui compte le nombre des cellules voisines tirées.
        int count = 0;
        int x = 0;
        int y = 0;

        if (posX % Vue.TCELLULE == 0 && posY % Vue.TCELLULE == 0) {
            x = posX / Vue.TCELLULE;
            y = posY / Vue.TCELLULE;
        } else {
            x = posX;
            y = posY;
        }

        for (int i = -1; i < 2; i++) {
            if (i != 0) {
                if ((x + i) < cellule.length && (x + i) >= 0) {
                    if (!this.cellule[x + i][y].getEstTouche()) {
                        count++;
                    }
                }
                if ((y + i) < cellule.length && (y + i) >= 0) {
                    if (!this.cellule[x][y + i].getEstTouche()) {
                        count++;
                    }
                }
            }
        }
        return count;

    }

    @Override
    public String toString() { // affichage de la grille en console.
        String affichage = "  ";
        char x = 'A';
        for (int i = 0; i < Vue.GTAILLE; i++) {
            affichage = affichage + " " + x + " ";
            x++;
        }
        affichage = affichage + "\n";

        for (int i = 0; i < Vue.GTAILLE; i++) {
            if (i + 1 < 10) {
                affichage = affichage + (i + 1) + " ";
            } else {
                affichage = affichage + (i + 1);
            }
            for (int j = 0; j < Vue.GTAILLE; j++) {
                if (cellule[j][i].getEstDansBateau() && cellule[j][i].getEstTouche()) {
                    affichage += " T ";
                } else {
                    if (!cellule[j][i].getEstTouche()) {
                        affichage = affichage + " . ";
                    } else {
                        affichage = affichage + " X ";
                    }
                }
            }
            affichage = affichage + "\n";
        }
        return affichage;
    }

}
