package modele.jeuNim;

import modele.InputException;

/**
 * La classe Tas représente un tas dans le jeu de Nim.
 * Elle contient le numéro du tas et le nombre d'allumettes dans le tas.
 */
public class Tas {
    private final int numTas; // Le numéro du tas.
    private int allumette; // Le nombre d'allumettes dans le tas.

    /**
     * Constructeur de la classe Tas.
     * Initialise un nouveau tas avec un numéro spécifique et un nombre d'allumettes égal à deux fois le numéro du tas moins un.
     *
     * @param numTas Le numéro du tas.
     */
    public Tas(int numTas) {
        this.numTas = numTas;
        allumette = numTas * 2 - 1;
    }

    /**
     * Vérifie si le tas est vide.
     *
     * @return Vrai si le tas est vide (c'est-à-dire s'il n'y a pas d'allumettes), faux sinon.
     */
    public boolean estVide() {
        return allumette == 0;
    }

    /**
     * Supprime un certain nombre d'allumettes du tas.
     *
     * @param nbAllumettes Le nombre d'allumettes à supprimer.
     * @throws InputException Si le nombre d'allumettes à supprimer est supérieur au nombre d'allumettes dans le tas ou si le nombre d'allumettes à supprimer est inférieur ou égal à zéro.
     */
    public void supprimerAllumettes(int nbAllumettes) throws InputException {
        if (nbAllumettes > allumette) {
            throw new InputException("Oups ! Pas assez d'allumettes dans le tas " + numTas + ".");
        } else if (nbAllumettes <= 0) {
            throw new InputException("Zut ! le nombre d'allumettes doit être supérieur à 0 ");
        } else {
            allumette = allumette - nbAllumettes;
        }
    }

    /**
     * Obtient le numéro du tas.
     *
     * @return Le numéro du tas.
     */
    public int getNumTas() {
        return numTas;
    }

    /**
     * Obtient le nombre d'allumettes dans le tas.
     *
     * @return Le nombre d'allumettes dans le tas.
     */
    public int getAllumette() {
        return allumette;
    }
}