package modele.jeuNim;

import modele.EtatJoueur;
import modele.EtatPartie;
import modele.Joueur;

import java.util.List;
import java.util.Map;

/**
 * La classe EtatPartieJeuDeNim est une sous-classe de la classe EtatPartie qui représente l'état d'une partie du jeu de Nim.
 */
public class EtatPartieJeuDeNim extends EtatPartie {
    private final int nbAlluMax;
    private List<Tas> tas;
    /**
     * Constructeur de la classe EtatPartieJeuDeNim.
     *
     * @param lesJoueurs Une carte des joueurs et de leurs états respectifs.
     */
    public EtatPartieJeuDeNim(Map<Joueur, EtatJoueur> lesJoueurs,List<Tas> tas) {
        super(lesJoueurs);
        this.tas=tas;
        nbAlluMax = 0;
    }
    public EtatPartieJeuDeNim(Map<Joueur, EtatJoueur> lesJoueurs,List<Tas> tas,int nbAlluMax) {
        super(lesJoueurs);
        this.tas=tas;
        this.nbAlluMax=nbAlluMax;
    }
    public int getNbTas() {
        return tas.size();
    }
    public int getNbAllumettes(int numTas) {
        return tas.get(numTas-1).getAllumette();
    }

    public int getNbAlluMax() {
        return nbAlluMax;
    }
}