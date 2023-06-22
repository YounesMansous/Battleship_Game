package src;

import src.controlleur.JoueurControlleur;
import src.controlleur.PartieControlleur;
import src.modele.Bateau;
import src.modele.Joueur;
import src.modele.Partie;
import src.modele.Joueur.EnumTypeJoueur;
import src.vue.Gui;

public class Demo {
    public static void main(String[] args) {

        Joueur joueur1 = new Joueur("Groupe", EnumTypeJoueur.Humain);
        Joueur joueur2 = new Joueur("Bot", EnumTypeJoueur.RobotIntelligent); // pour créer un robot idiot/aleatoire
                                                                             // changer cette valeur en "RobotAleatoire"
        JoueurControlleur joueurControlleur1 = new JoueurControlleur(joueur1);
        JoueurControlleur joueurControlleur2 = new JoueurControlleur(joueur2);

        Partie game = new Partie(joueur1, joueur2);
        PartieControlleur gameControlleur = new PartieControlleur(game, joueurControlleur1, joueurControlleur2);
        Bateau bateau1 = joueurControlleur1.generationBateau();
        Bateau bateau2 = joueurControlleur1.generationBateau();
        Bateau bateau3 = joueurControlleur1.generationBateau();
        Bateau bateau4 = joueurControlleur1.generationBateau();
        Bateau bateau5 = joueurControlleur2.generationBateau();
        Bateau bateau6 = joueurControlleur2.generationBateau();
        Bateau bateau7 = joueurControlleur2.generationBateau();
        Bateau bateau8 = joueurControlleur2.generationBateau();

        joueurControlleur1.ajoutBateau(bateau1);
        joueurControlleur1.ajoutBateau(bateau2);
        joueurControlleur1.ajoutBateau(bateau3);
        joueurControlleur1.ajoutBateau(bateau4);

        joueurControlleur2.ajoutBateau(bateau5);
        joueurControlleur2.ajoutBateau(bateau6);
        joueurControlleur2.ajoutBateau(bateau7);
        joueurControlleur2.ajoutBateau(bateau8);

        Gui test = new Gui(gameControlleur);

    }
}