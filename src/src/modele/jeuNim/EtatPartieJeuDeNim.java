package modele.jeuNim;

import modele.EtatJoueur;
import modele.EtatPartie;
import modele.Joueur;

import java.util.Map;

/**
 * La classe EtatPartieJeuDeNim est une sous-classe de la classe EtatPartie qui représente l'état d'une partie du jeu de Nim.
 */
public class EtatPartieJeuDeNim extends EtatPartie {
    /**
     * Constructeur de la classe EtatPartieJeuDeNim.
     *
     * @param lesJoueurs Une carte des joueurs et de leurs états respectifs.
     */
    public EtatPartieJeuDeNim(Map<Joueur, EtatJoueur> lesJoueurs) {
        super(lesJoueurs);
    }
}