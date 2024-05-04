package vue;

/**
 * La classe Coup est une classe abstraite qui représente un coup joué par un joueur.
 * Elle contient le numéro du joueur qui a joué le coup.
 */
public abstract class Coup {
    // Le numéro du joueur qui a joué le coup
    private int numJoueur;

    /**
     * Constructeur par défaut de la classe Coup.
     */
    public Coup() {
    }

    /**
     * Définit le numéro du joueur qui a joué le coup.
     *
     * @param numJoueur Le numéro du joueur.
     */
    public void setNumJoueur(int numJoueur) {
        this.numJoueur = numJoueur;
    }

    /**
     * Récupère le numéro du joueur qui a joué le coup.
     *
     * @return Le numéro du joueur.
     */
    public int getNumJoueur() {
        return numJoueur;
    }
    public abstract String toString();
}