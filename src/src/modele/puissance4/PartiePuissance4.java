package modele.puissance4;

import modele.*;
import vue.Coup;
import vue.puissance4.CoupP4;

import java.util.List;

/**
 * La classe PartiePuissance4 étend la classe Partie pour représenter une partie du jeu de Puissance 4.
 * Elle contient une grille de jeu, une taille de Puissance 4, un tableau de jetons, et un indicateur si la rotation est possible.
 */
public class PartiePuissance4 extends Partie {
    // La taille de la séquence de jetons alignés (horizontalement, verticalement ou en diagonale) nécessaire pour gagner la partie.
    private int tailleP4;

    // Les différents types de jetons utilisés dans le jeu.
    private final static String[] lesJetons = {"⚫", "🔴", "🟡"};

    // La grille de jeu sur laquelle les joueurs placent leurs jetons.
    private Grille grille;

    // Indique si la rotation de la grille est possible dans cette partie.
    private boolean rotationPossible;

    /**
     * Constructeur de la classe PartiePuissance4.
     * Initialise une nouvelle partie de Puissance 4 avec une liste de joueurs et une option pour permettre ou non la rotation de la grille.
     *
     * @param lesJoueurs       La liste des joueurs de la partie.
     * @param rotationPossible Indique si la rotation de la grille est possible dans cette partie.
     */
    public PartiePuissance4(List<Joueur> lesJoueurs, boolean rotationPossible) {
        super();
        grille = new Grille();
        this.rotationPossible = rotationPossible;
        for (Joueur joueur : lesJoueurs) {
            this.lesJoueurs.put(joueur, (new EtatJoueurP4(joueur)));
        }
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return Vrai si une séquence de 4 jetons ou plus est alignée ou si la grille est remplie, faux sinon.
     */
    public boolean partieFinie() {
        tailleP4 = grille.puissance4();
        return (tailleP4 >= 4 || grille.estRemplie());
    }

    /**
     * Joue un tour en insérant un jeton dans une colonne spécifique ou en effectuant une rotation de la grille.
     *
     * @param coups Le coup à jouer, qui contient le type de coup (insertion de jeton ou rotation), le numéro du joueur, la colonne et la direction de rotation.
     * @throws InputException Si le joueur a épuisé ses rotations possibles.
     */
    public void jouerTour(Coup coups) throws InputException {
        CoupP4 coupsP4 = (CoupP4) coups;
        if (coupsP4.getTypeCoup() == 0) {
            grille.insererJeton(coupsP4.getColonne(), coupsP4.getNumJoueur() + 1);
        } else if (coupsP4.getTypeCoup() == 1) {
            EtatJoueurP4 etatJoueur = ((EtatJoueurP4) lesJoueurs.get(getJoueur(coupsP4.getNumJoueur())));
            if (etatJoueur.getNbRotation() == 0) {
               throw new InputException("Vous n'avez plus de rotations possible");
            }
            etatJoueur.decrementerRotation();
            grille = grille.rotation(coupsP4.getRotation());
        }
    }

    /**
     * Vérifie si la partie est un match nul.
     *
     * @return Vrai si la taille de Puissance 4 est inférieure à 4 et que la grille est remplie, faux sinon.
     */
    public boolean testExAequo() {
        return tailleP4 < 4 && grille.estRemplie();
    }

    /**
     * Obtient une représentation du plateau de jeu.
     *
     * @return Une chaîne de caractères représentant le plateau de jeu, avec les jetons dans chaque cellule.
     */
    public String getPlateau() {
        int[][] grille = this.grille.getGrille();
        String plateau = "\n";
        for (int i = 6; i >= 0; i--) {
            plateau += "|";
            for (int j = 0; j <= 6; j++) {
                plateau += lesJetons[grille[j][i]];
            }
            plateau += "|\n";
        }
        plateau += " ";
        for (int i = 1; i < 8; i++) {
            plateau += i + "\uFE0F⃣";
        }
        plateau += " ";
        return plateau;
    }

    /**
     * Obtient les informations sur la victoire.
     *
     * @return Un tableau de chaînes de caractères contenant la taille de Puissance 4.
     */
    public String[] getInformationsVictoire() {
        return new String[]{Integer.toString(tailleP4)};
    }

    /**
     * Crée une nouvelle partie pour rejouer.
     *
     * @param infosSup Un tableau d'entiers contenant des informations supplémentaires pour rejouer la partie.
     * @return Une nouvelle instance de la partie pour rejouer.
     */
    public PartiePuissance4 rejouer(int[] infosSup) {
        return new PartiePuissance4(getListeLesJoueurs(), infosSup[0] == 1);
    }

    /**
     * Obtient l'état actuel de la partie.
     *
     * @return L'état actuel de la partie, qui contient les informations sur les joueurs et si la rotation est possible.
     */
    public EtatPartie getEtatPartie() {
        return new EtatPartieP4(lesJoueurs, rotationPossible);
    }

}
