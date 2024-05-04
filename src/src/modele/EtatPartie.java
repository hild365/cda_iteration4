package modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe EtatPartie est une classe abstraite qui représente l'état d'une partie.
 */
public abstract class EtatPartie {
    /**
     * Une carte des joueurs et de leurs états respectifs.
     */
    protected Map<String, EtatJoueur> lesJoueurs;

    /**
     * Constructeur de la classe EtatPartie.
     *
     * @param lesJoueurs Une carte des joueurs et de leurs états respectifs.
     */
    public EtatPartie(Map<Joueur, EtatJoueur> lesJoueurs) {
        this.lesJoueurs = new HashMap<String, EtatJoueur>();
        for (Map.Entry<Joueur, EtatJoueur> entry : lesJoueurs.entrySet()) {
            this.lesJoueurs.put(entry.getKey().getNom(), entry.getValue());
        }
    }
}