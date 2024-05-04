package vue.jeuNim;

import modele.EtatPartie;
import vue.Coup;
import vue.Ihm;

/**
 * La classe IhmJeuDeNim est une sous-classe de la classe Ihm qui gère l'interaction entre l'utilisateur et le jeu de Nim.
 */
public class IhmJeuDeNim extends Ihm {
    /**
     * Constructeur de la classe IhmJeuDeNim.
     * Initialise le scanner pour lire les entrées de l'utilisateur.
     */
    public IhmJeuDeNim() {
        super();
    }

    /**
     * Demande à l'utilisateur de jouer un coup dans le jeu de Nim.
     *
     * @param joueurCourant      Le nom du joueur courant.
     * @param etatPartieJeuDeNim L'état actuel de la partie de Nim.
     * @return Le coup joué par l'utilisateur.
     */
    public Coup demanderCoups(String joueurCourant, EtatPartie etatPartieJeuDeNim) {
        affichage("\n A " + texteVert(joueurCourant) + " de jouer. Vous pouvez jouer un coup de la forme \"" + texteRouge("m n") + "\" :\n");
        CoupJeuDeNim coups = new CoupJeuDeNim();
        if (sc.hasNextInt()) {
            int numTas = sc.nextInt();
            if (numTas <= 0) {
                affichage(texteRouge("\nErreur de saisie, veuillez entrer un chiffre supérieur ou égal à 0.\n"));
                return demanderCoups(joueurCourant, etatPartieJeuDeNim);
            }
            coups.setNumTas(numTas);
            if (sc.hasNextInt()) {
                int nbAllu = sc.nextInt();
                if (nbAllu <= 0) {
                    affichage(texteRouge("\nErreur de saisie, veuillez entrer un chiffre supérieur ou égal à 0.\n"));
                    return demanderCoups(joueurCourant, etatPartieJeuDeNim);
                }
                ignorer(sc.nextLine());
                coups.setNbAllu(nbAllu);
                return coups;
            } else {
                affichage(texteRouge("\nErreur de saisie pour le choix du nombre d'allumettes, veuillez entrer un chiffre.\n"));
                return demanderCoups(joueurCourant, etatPartieJeuDeNim);
            }
        } else {
            affichage(texteRouge("\nErreur de saisie pour le choix du tas, veuillez entrer un chiffre.\n"));
        }
        return demanderCoups(joueurCourant, etatPartieJeuDeNim);
    }

    /**
     * Demande des informations initiales à l'utilisateur pour le jeu de Nim.
     *
     * @return Un tableau d'entiers contenant les informations initiales.
     */
    public int[] demanderInfoInitiales() {
        affichage("\nAvec combien de tas souhaitez vous jouer?\n");
        if (sc.hasNextInt()) {
            int nbTas = sc.nextInt();
            ignorer(sc.nextLine());
            if (nbTas < 1) {
                affichage(texteRouge("\nErreur de saisie, veuillez entrer un entier supérieur ou égal a 1.\n"));
                return demanderInfoInitiales();
            } else {
                return new int[]{nbTas, demanderInfoSupplementaires()[0]};
            }
        } else {
            sc.nextLine();
            affichage(texteRouge("\nErreur de saisie, veuillez entrer un entier.\n"));
            return demanderInfoInitiales();
        }
    }

    /**
     * Demande des informations supplémentaires à l'utilisateur pour le jeu de Nim.
     *
     * @return Un tableau d'entiers contenant les informations supplémentaires.
     */
    public int[] demanderInfoSupplementaires() {
        affichage("\nSouhaitez vous jouer avec un nombre d'allumettes maximum par coup? (o/n)\n");
        String rep = sc.next();
        ignorer(sc.nextLine());
        while ((!rep.equals("o")) && (!rep.equals("n"))) {
            affichage(texteRouge("\nJe n'ai pas bien compris votre réponse...\n"));
            rep = sc.next();
            ignorer(sc.nextLine());
        }
        while (rep.equals("o")) {
            affichage("\nCombien d'allumettes maximum par coup souhaitez vous?\n");
            while (!sc.hasNextInt()) {
                affichage(texteRouge("\nErreur de saisie, veuillez entrer un entier.\n"));
                sc.nextLine();
                affichage("\nCombien d'allumettes maximum par coup souhaitez vous?\n");
            }
            int nbAlluMax = sc.nextInt();
            ignorer(sc.nextLine());
            if (nbAlluMax < 1) {
                affichage(texteRouge("\nErreur de saisie, veuillez entrer un entier supérieur ou égal a 1.\n"));
            } else {
                return new int[]{nbAlluMax};

            }
        }
        return new int[]{0};
    }
}
