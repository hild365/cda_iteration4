package modele.jeuNim;

import modele.EtatJoueur;
import modele.Joueur;

/**
 * La classe EtatJoueurJeuDeNim est une sous-classe de la classe EtatJoueur qui représente l'état d'un joueur dans le jeu de Nim.
 */
public class EtatJoueurJeuDeNim extends EtatJoueur {
    /**
     * Constructeur de la classe EtatJoueurJeuDeNim.
     *
     * @param joueur Le joueur associé à cet état.
     */
    public EtatJoueurJeuDeNim(Joueur joueur) {
        super(joueur);
    }
}