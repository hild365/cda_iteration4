package vue.puissance4;

import vue.Coup;

/**
 * La classe CoupP4 est une sous-classe de la classe Coup qui représente un coup spécifique au jeu Puissance 4.
 * Elle contient le numéro de la colonne où le joueur a joué son coup, la rotation effectuée et le type de coup.
 */
public class CoupP4 extends Coup {
    // Le numéro de la colonne où le joueur a joué son coup
    private int colonne;
    // La rotation effectuée par le joueur
    private String rotation;
    // Le type de coup joué par le joueur
    private int typeCoup;

    /**
     * Constructeur par défaut de la classe CoupP4.
     */
    public CoupP4() {
    }

    /**
     * Récupère le type de coup joué par le joueur.
     *
     * @return Le type de coup.
     */
    public int getTypeCoup() {
        return typeCoup;
    }

    /**
     * Définit le type de coup joué par le joueur.
     *
     * @param typeCoup Le type de coup.
     */
    public void setTypeCoup(int typeCoup) {
        this.typeCoup = typeCoup;
    }

    /**
     * Définit le numéro de la colonne où le joueur a joué son coup.
     *
     * @param colonne Le numéro de la colonne.
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Définit la rotation effectuée par le joueur.
     *
     * @param rotation La rotation.
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * Récupère le numéro de la colonne où le joueur a joué son coup.
     *
     * @return Le numéro de la colonne.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Récupère la rotation effectuée par le joueur.
     *
     * @return La rotation.
     */
    public String getRotation() {
        return rotation;
    }
    public String toString() {
        return "(Colonne " + colonne + " : " + rotation + ")";
    }
}