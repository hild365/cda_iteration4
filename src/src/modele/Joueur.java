package modele;
import vue.Coup;
/**
 * La classe Joueur représente un joueur dans le jeu.
 * Chaque joueur a un numéro, un nom et un score.
 */
public abstract class  Joueur {
    private int numJoueur;
    private String nomJoueur;
    private int score;
    private static int num = 0;

    /**
     * Constructeur de la classe Joueur.
     * Initialise un nouveau joueur avec un nom spécifié et un score de 0.
     *
     * @param nomJoueur Le nom du joueur.
     */
    public Joueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
        this.score = 0;
        numJoueur = num++;
    }

    /**
     * Incrémente le score du joueur de 1.
     */
    public void incrementerScore() {
        score += 1;
    }

    /**
     * Récupère le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNom() {
        return nomJoueur;
    }

    /**
     * Récupère le score du joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Récupère le numéro du joueur.
     *
     * @return Le numéro du joueur.
     */
    public int getNumJoueur() {
        return numJoueur;
    }
    public abstract Coup demanderCoup(Partie etatPartie);
}