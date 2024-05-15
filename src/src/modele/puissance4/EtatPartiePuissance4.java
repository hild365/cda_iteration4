package modele.puissance4;

import modele.EtatJoueur;
import modele.EtatPartie;
import modele.Joueur;

import java.util.Map;

/**
 * La classe EtatPartieP4 est une sous-classe de la classe EtatPartie qui représente l'état d'une partie du jeu de Puissance 4.
 */
public class EtatPartiePuissance4 extends EtatPartie {
    /**
     * Indique si une rotation est possible dans la partie.
     */
    private final boolean rotationPossible;
    private int[] dernierCoup;

    /**
     * Constructeur de la classe EtatPartieP4.
     *
     * @param lesJoueurs       Une carte des joueurs et de leurs états respectifs.
     * @param rotationPossible Indique si une rotation est possible dans la partie.
     */
    public EtatPartiePuissance4(Map<Joueur, EtatJoueur> lesJoueurs, int[] dernierCoup, boolean rotationPossible) {
        super(lesJoueurs);
        this.rotationPossible = rotationPossible;
        this.dernierCoup = dernierCoup;
    }

    /**
     * Récupère l'information si une rotation est possible dans la partie.
     *
     * @return Vrai si une rotation est possible, faux sinon.
     */
    public boolean getRotationPossible() {
        return rotationPossible;
    }

    /**
     * Récupère le nombre de rotations disponibles pour un joueur spécifique.
     *
     * @param joueur Le nom du joueur.
     * @return Le nombre de rotations disponibles pour le joueur.
     */
    public int getNbRotation(String joueur) {
        return ((EtatJoueurPuissance4) lesJoueurs.get(joueur)).getNbRotation();
    }
    public int[] getDernierCoup() {
        return dernierCoup;
    }
}