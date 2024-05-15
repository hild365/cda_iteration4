package vue.puissance4;

import modele.EtatPartie;
import modele.puissance4.EtatPartiePuissance4;
import vue.Coup;
import vue.Ihm;

import java.util.Scanner;

/**
 * La classe IhmPuissance4 est une sous-classe de la classe Ihm qui gère l'interaction entre l'utilisateur et le jeu de Puissance 4.
 */
public class IhmPuissance4 extends Ihm {
    /**
     * Constructeur de la classe IhmPuissance4.
     * Initialise le scanner pour lire les entrées de l'utilisateur.
     */
    public IhmPuissance4() {
        super();
    }

    /**
     * Demande à l'utilisateur de jouer un coup dans le jeu de Puissance 4.
     *
     * @param joueurCourant Le nom du joueur courant.
     * @param etatPartieP4  L'état actuel de la partie de Puissance 4.
     * @return Le coup joué par l'utilisateur.
     */
    public Coup demanderCoups(String joueurCourant, EtatPartie etatPartieP4) {
        String affichage = "\nÀ " + texteVert(joueurCourant) + " de jouer. Saisissez un numéro correspondant à une colonne de la grille (de 1 à 7)";
        boolean rotationPossible = ((EtatPartiePuissance4) etatPartieP4).getRotationPossible();
        if (rotationPossible) {
            int nbRotation = ((EtatPartiePuissance4) etatPartieP4).getNbRotation(joueurCourant);
            if (nbRotation == 0) {
                affichage += ", \n" + texteGris("(attention cependant vous ne pouvez plus effectuer de rotation de la grille)");
            } else {
                affichage += texteGris(", \nvous pouvez aussi saisir '") + texteVert("g") + texteGris("' ou '") + texteVert("d") + texteGris("' pour effectuer une rotation de la grille vers la gauche ou la droite. ") + "\nAttention il vous reste " + texteJaune(Integer.toString(nbRotation)) + " rotation possible";
            }
        }
        affichage(affichage + " :\n");

        CoupP4 coup = new CoupP4();
        if (sc.hasNextInt()) {
            int col = sc.nextInt();
            ignorer(sc.nextLine());
            if (col >= 1 && col <= 7) {
                coup.setTypeCoup(0);
                coup.setColonne(col);
            } else {
                affichage(texteRouge(texteRouge("\nErreur, la colonne n'existe pas.\n")));
                return demanderCoups(joueurCourant, etatPartieP4);
            }
        } else if (((EtatPartiePuissance4) etatPartieP4).getRotationPossible()){
            String input = sc.next();
            sc.nextLine();
            if (input.equalsIgnoreCase("g") || input.equalsIgnoreCase("d")) {
                coup.setTypeCoup(1);
                coup.setRotation(input);
            } else {
                affichage(texteRouge("\nVeuillez saisir une entrée valide svp.\n"));
                return demanderCoups(joueurCourant, etatPartieP4);
            }
        } else {
            sc.nextLine();
            affichage(texteRouge("Veuillez entrer un entier positif."));
            return demanderCoups(joueurCourant,etatPartieP4);
        }
        return coup;
    }

    /**
     * Affiche le résultat d'une partie de Puissance 4.
     *
     * @param vainqueurPartie Le nom du vainqueur de la partie.
     * @param infosVictoire   Les informations sur la victoire.
     */
    public void afficherResultatPartie(String vainqueurPartie, String[] infosVictoire) {
        int tailleP4 = Integer.parseInt(infosVictoire[0]);
        if (vainqueurPartie != null) {
            affichage("\n" + texteVert("Puissance ") + texteJaune("" + tailleP4) + " ! Bravo " + texteVert(vainqueurPartie) + " vous avez gagné !\n");
        } else {
            affichage(texteVert("\nEx-Aequo !") + " Personne ne gagne.\n");
        }
    }

    /**
     * Demande des informations initiales à l'utilisateur pour le jeu de Puissance 4.
     *
     * @return Un tableau d'entiers contenant les informations initiales.
     */
    public int[] demanderInfoInitiales() {
        return demanderInfoSupplementaires();
    }

    /**
     * Demande des informations supplémentaires à l'utilisateur pour le jeu de Puissance 4.
     *
     * @return Un tableau d'entiers contenant les informations supplémentaires.
     */
    public int[] demanderInfoSupplementaires() {
        affichage("\nVoulez-vous jouer avec des rotations de la grille ? (o/n)\n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("o")) {
            return new int[]{1};
        } else if (input.equalsIgnoreCase("n")) {
            return new int[]{0};
        } else {
            affichage(texteRouge("\nVeuillez saisir une entrée valide svp.\n"));
            return demanderInfoSupplementaires();
        }
    }


}
