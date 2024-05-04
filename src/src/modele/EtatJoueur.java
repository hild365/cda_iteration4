package modele;

/**
 * La classe EtatJoueur est une classe abstraite qui représente l'état d'un joueur.
 */
public abstract class EtatJoueur {
    /**
     * Le joueur associé à cet état.
     */
    protected Joueur joueur;

    /**
     * Constructeur de la classe EtatJoueur.
     *
     * @param joueur Le joueur associé à cet état.
     */
    public EtatJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}