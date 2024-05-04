package modele.puissance4;

import modele.EtatJoueur;
import modele.Joueur;

/**
 * La classe EtatJoueurP4 est une sous-classe de la classe EtatJoueur qui représente l'état d'un joueur dans le jeu de Puissance 4.
 */
public class EtatJoueurP4 extends EtatJoueur {
    /**
     * Le nombre de rotations disponibles pour le joueur.
     */
    private int nbRotation;

    /**
     * Constructeur de la classe EtatJoueurP4.
     *
     * @param joueur Le joueur associé à cet état.
     */
    public EtatJoueurP4(Joueur joueur) {
        super(joueur);
        this.nbRotation = 4;
    }

    /**
     * Récupère le nombre de rotations disponibles pour le joueur.
     *
     * @return Le nombre de rotations disponibles.
     */
    public int getNbRotation() {
        return nbRotation;
    }

    /**
     * Décrémente le nombre de rotations disponibles pour le joueur.
     */
    public void decrementerRotation() {
        nbRotation--;
    }
}