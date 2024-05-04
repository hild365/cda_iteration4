package vue.jeuNim;

import vue.Coup;

/**
 * La classe CoupJeuDeNim est une sous-classe de la classe Coup qui représente un coup spécifique au Jeu de Nim.
 * Elle contient le numéro du tas d'allumettes que le joueur a choisi et le nombre d'allumettes qu'il a retirées.
 */
public class CoupJeuDeNim extends Coup {
    // Le numéro du tas d'allumettes choisi par le joueur
    private int numTas;
    // Le nombre d'allumettes retirées par le joueur
    private int nbAllu;

    /**
     * Constructeur par défaut de la classe CoupJeuDeNim.
     */
    public CoupJeuDeNim() {
    }

    /**
     * Définit le numéro du tas d'allumettes choisi par le joueur.
     *
     * @param numTas Le numéro du tas.
     */
    public void setNumTas(int numTas) {
        this.numTas = numTas;
    }

    /**
     * Définit le nombre d'allumettes retirées par le joueur.
     *
     * @param nbAllu Le nombre d'allumettes.
     */
    public void setNbAllu(int nbAllu) {
        this.nbAllu = nbAllu;
    }

    /**
     * Récupère le numéro du tas d'allumettes choisi par le joueur.
     *
     * @return Le numéro du tas.
     */
    public int getNumTas() {
        return numTas;
    }

    /**
     * Récupère le nombre d'allumettes retirées par le joueur.
     *
     * @return Le nombre d'allumettes.
     */
    public int getNbAllu() {
        return nbAllu;
    }
    public String toString() {
        return "(Tas " + numTas + " : " + nbAllu + " allumettes)";
    }
}