package modele.puissance4;


import modele.InputException;

/**
 * La classe Grille représente la grille de jeu pour le jeu de Puissance 4.
 * Elle contient des méthodes pour insérer des jetons, vérifier l'état de la grille, et effectuer des rotations.
 */
public class Grille {
    private int[][] grille;
    private int[] dernierCoup;

    /**
     * Constructeur de la classe Grille.
     * Initialise une grille de jeu vide de 7x7 et un tableau pour stocker le dernier coup joué.
     */
    public Grille() {
        grille = new int[7][7];
        dernierCoup = new int[2];
    }

    /**
     * Insère un jeton dans la colonne spécifiée de la grille.
     *
     * @param col   La colonne dans laquelle insérer le jeton.
     * @param jeton Le jeton à insérer.
     * @throws InputException Si la colonne est pleine.
     */
    public void insererJeton(int col, int jeton) throws InputException{
        int numligne = ligneVide(col);
        if (numligne != -1) {
            grille[col - 1][numligne] = jeton;
            mettreAJourDernierCoup(col - 1, numligne);
        } else {
            throw new InputException("La colonne est pleine à cet indice. Veuillez réessayer.");
        }
    }

    /**
     * Trouve la première ligne vide dans la colonne spécifiée.
     *
     * @param colonne La colonne à vérifier.
     * @return L'indice de la première ligne vide dans la colonne, ou -1 si la colonne est pleine.
     */
    public int ligneVide(int colonne) {
        for (int ligne = 0; ligne < 7; ligne++) {
            if (grille[colonne - 1][ligne] == 0) {
                return ligne;
            }
        }
        return -1;
    }

    /**
     * Met à jour la position du dernier coup joué.
     *
     * @param colonne La colonne du dernier coup.
     * @param ligne   La ligne du dernier coup.
     */
    public void mettreAJourDernierCoup(int colonne, int ligne) {
        dernierCoup[0] = colonne;
        dernierCoup[1] = ligne;
    }

    /**
     * Vérifie si un joueur a gagné la partie (a fait un "Puissance 4").
     * Cette méthode vérifie les lignes, les colonnes et les deux diagonales.
     *
     * @return Le nombre maximum de jetons consécutifs du même joueur trouvés dans les lignes, colonnes ou diagonales.
     */
    public int puissance4() {
        /* la position du dernier coup */
        int x = dernierCoup[0];
        int y = dernierCoup[1];
        int jeton = grille[x][y];
        /* verifier si il y a un puissance 4 en ligne en colonne ou en diag */
        int ligne = verifierLigne(x, y, jeton);
        int colonne = verifierColonne(x, y, jeton);
        int diagDescGD = verifierDiagDescGaucheDroite(x, y, jeton);
        int diagAscGD = verifierDiagAscGaucheDroite(x, y, jeton);
        return Integer.max(Integer.max(ligne, colonne), Integer.max(diagAscGD, diagDescGD));
    }

    /**
     * Vérifie si la grille de jeu est complètement remplie.
     *
     * @return Vrai si la grille est remplie, faux sinon.
     */
    public boolean estRemplie() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (grille[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Vérifie si un joueur a fait un "Puissance 4" sur une ligne.
     *
     * @param x     La colonne du dernier coup.
     * @param y     La ligne du dernier coup.
     * @param jeton Le jeton du joueur à vérifier.
     * @return Le nombre de jetons consécutifs du même joueur trouvés sur la ligne.
     */
    private int verifierLigne(int x, int y, int jeton) {
        int ligne = 1;
        boolean versGauche = true;
        boolean versDroite = true;
        for (int i = 1; i < 4; i++) {
            if (versDroite && (y + i) < 7 && grille[x][y + i] != 0 && grille[x][y + i] == jeton) {
                ligne++;
            } else {
                versDroite = false;
            }
            if (versGauche && (y - i) >= 0 && grille[x][y - i] != 0 && grille[x][y - i] == jeton) {
                ligne++;
            } else {
                versGauche = false;
            }
            if (!versDroite && !versGauche) {
                break;
            }
        }
        return ligne;
    }

    /**
     * Vérifie si un joueur a fait un "Puissance 4" sur une colonne.
     *
     * @param x     La colonne du dernier coup.
     * @param y     La ligne du dernier coup.
     * @param jeton Le jeton du joueur à vérifier.
     * @return Le nombre de jetons consécutifs du même joueur trouvés sur la colonne.
     */
    private int verifierColonne(int x, int y, int jeton) {
        int colonne = 1;
        for (int i = 1; i < 4; i++) {
            if ((x - i) >= 0 && grille[x - i][y] != 0 && grille[x - i][y] == jeton) {
                colonne++;
            } else {
                break;
            }
        }
        return colonne;
    }

    /**
     * Vérifie si un joueur a fait un "Puissance 4" sur une diagonale ascendante de gauche à droite.
     *
     * @param x     La colonne du dernier coup.
     * @param y     La ligne du dernier coup.
     * @param jeton Le jeton du joueur à vérifier.
     * @return Le nombre de jetons consécutifs du même joueur trouvés sur la diagonale ascendante de gauche à droite.
     */
    private int verifierDiagAscGaucheDroite(int x, int y, int jeton) {
        int diagAsc = 1;
        boolean versHautDroite = true;
        boolean verBasGauche = true;
        for (int i = 1; i < 4; i++) {
            if (versHautDroite && (x + i) < 7 && (y + i) < 7 && grille[x + i][y + i] != 0 && grille[x + i][y + i] == jeton) {
                diagAsc++;
            } else {
                versHautDroite = false;
            }
            if (verBasGauche && (x - i) >= 0 && (y - i) >= 0 && grille[x - i][y - i] != 0 && grille[x - i][y - i] == jeton) {
                diagAsc++;
            } else {
                verBasGauche = false;
            }
            if (!versHautDroite && !verBasGauche) {
                break;
            }
        }
        return diagAsc;
    }

    /**
     * Vérifie si un joueur a fait un "Puissance 4" sur une diagonale descendante de gauche à droite.
     *
     * @param x     La colonne du dernier coup.
     * @param y     La ligne du dernier coup.
     * @param jeton Le jeton du joueur à vérifier.
     * @return Le nombre de jetons consécutifs du même joueur trouvés sur la diagonale descendante de gauche à droite.
     */
    private int verifierDiagDescGaucheDroite(int x, int y, int jeton) {
        int diagDesc = 1;
        boolean versBasDroite = true;
        boolean versHautGauche = true;
        for (int i = 1; i < 4; i++) {
            if (versBasDroite && (x + i) < 7 && (y - i) >= 0 && grille[x + i][y - i] != 0 && grille[x + i][y - i] == jeton) {
                diagDesc++;
            } else {
                versBasDroite = false;
            }
            if (versHautGauche && (x - i) >= 0 && (y + i) < 7 && grille[x - i][y + i] != 0 && grille[x - i][y + i] == jeton) {
                diagDesc++;
            } else {
                versHautGauche = false;
            }
            if (!versBasDroite && !versHautGauche) {
                break;
            }
        }
        return diagDesc;
    }

    /**
     * Récupère une copie de la grille de jeu.
     *
     * @return Une copie de la grille de jeu.
     */
    public int[][] getGrille() {
        return grille.clone();
    }

    /**
     * Effectue une rotation de la grille de jeu dans le sens spécifié.
     *
     * @param sensRotation Le sens de la rotation ("d" pour droite, autre pour gauche).
     * @return Une nouvelle grille de jeu après la rotation.
     * @throws InputException Si le sens de rotation n'est pas valide.
     */
    public Grille rotation(String sensRotation) throws InputException  {
        Grille nouvelleGrille = new Grille();
        if (sensRotation.equals("d")) {
            for (int x = 6; x >= 0; x--) {
                for (int y = 6; y >= 0; y--) {
                    if (grille[x][y] != 0) {
                        nouvelleGrille.insererJeton(y + 1, grille[x][y]);
                    }
                }
            }
        } else {
            for (int x = 0; x < 7; x++) {
                for (int y = 6; y >= 0; y--) {
                    if (grille[x][y] != 0) {
                        nouvelleGrille.insererJeton(6 - y + 1, grille[x][y]);
                    }
                }
            }
        }
        return nouvelleGrille;
    }


}
