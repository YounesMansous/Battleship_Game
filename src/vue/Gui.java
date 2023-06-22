package src.vue;

import javax.swing.*;
import src.controlleur.PartieControlleur;
import src.modele.Joueur.EnumTypeJoueur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {

	protected PartieControlleur jeu;

	public Gui(PartieControlleur jeu) { // lancement de la partie
		this.jeu = jeu;
		JPanel J1 = new Vue(this.jeu.getPartie().joueur1); // panel contenant la grille du premier joueur
		JPanel J2 = new Vue(this.jeu.getPartie().joueur2); // panel contenant la grille du deuxième joueur

		J1.setPreferredSize(new Dimension(Vue.DIM, Vue.DIM));
		J2.setPreferredSize(new Dimension(Vue.DIM, Vue.DIM));

		JPanel content = new JPanel(new FlowLayout(FlowLayout.CENTER));
		content.add(J1, BorderLayout.CENTER);
		content.add(J2, BorderLayout.CENTER);

		add(content, BorderLayout.CENTER);

		setSize(Vue.DIM * 2 + 40, Vue.DIM * 2 + 40);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		J1.addMouseListener(new MouseAdapter() { // gère les tires des deux joueurs avec la souris.
			public void mousePressed(MouseEvent e) {
				if (!jeu.isGameOver()) {

					int x = (int) ((int) e.getPoint().getX() - e.getPoint().getX() % Vue.TCELLULE);
					int y = (int) ((int) e.getPoint().getY() - e.getPoint().getY() % Vue.TCELLULE);

					if (!jeu.getPartie().joueur1.getGrille().chercherCellule(x, y).getEstTouche()) {

						jeu.getJoueur1().tire(x, y);
						System.out.println(jeu.getPartie().joueur1.getGrille());
						jeu.getJoueur2().miseAjour();

						if (jeu.getPartie().joueur2.getType() == EnumTypeJoueur.RobotAleatoire) {
							jeu.getJoueur2().tireAleatoire();
							jeu.getJoueur1().miseAjour();
						} else if (jeu.getPartie().joueur2.getType() == EnumTypeJoueur.RobotIntelligent) {
							jeu.getJoueur2().tireIntelligent();
							jeu.getJoueur1().miseAjour();
						}
					}
				} else {
					System.out.println(jeu.getWinner().getNom());
					content.remove(J1);
					content.remove(J2);

					// Création d'un nouveau panel qui s'affiche après la fin du jeu et indiquant la
					// fin de la partie avec le joueur gagnant.

					JPanel partieVue = new VuePartie(jeu);
					partieVue.setPreferredSize(new Dimension(Vue.DIM * 2 - 10, Vue.DIM - 10)); // gestion des dimensions
																								// du panel.
					content.add(partieVue);
					content.repaint();
					content.revalidate();// permet de raffraichire le frame avec son contenu.
				}
			}
		});
	}
}
