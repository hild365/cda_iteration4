package vue;

import modele.EtatPartie;

import java.util.List;
import java.util.Scanner;

/**
 * La classe Ihm est une classe qui gère l'interaction entre l'utilisateur et le jeu.
 * Elle contient des méthodes pour afficher des informations à l'utilisateur et pour obtenir des informations de l'utilisateur.
 */
public class Ihm {
    // Scanner pour lire les entrées de l'utilisateur
    protected Scanner sc;

    /**
     * Constructeur de la classe Ihm.
     * Initialise le scanner pour lire les entrées de l'utilisateur.
     */
    public Ihm() {
        sc = new Scanner(System.in);
    }

    /**
     * Affiche le texte donné à l'utilisateur.
     *
     * @param texte Le texte à afficher.
     */
    public void affichage(String texte) {
        System.out.println(texte);
    }

    /**
     * Demande à l'utilisateur de choisir le type de partie à jouer.
     *
     * @param listeDesTypesDePartie La liste des types de parties disponibles.
     * @return Le numéro du type de partie choisi par l'utilisateur.
     */
    public int demanderTypePartie(String[] listeDesTypesDePartie) {
        affichage("\n Entrez le numéro correspondant au jeu que vous souhaitez jouer :\n");
        for (int i = 0; i < listeDesTypesDePartie.length; i++) {
            affichage(texteJaune(Integer.toString(i + 1)) + ". " + texteVert(listeDesTypesDePartie[i]));
        }
        affichage("\n");
        int partieChoisie;
        while (!sc.hasNextInt()) {
            affichage(texteRouge("\nErreur de saisie, veuillez entrer un numéro de partie valide.\n"));
            sc.next();
        }
        partieChoisie = sc.nextInt();
        sc.nextLine();
        if (partieChoisie < 1 || partieChoisie > listeDesTypesDePartie.length) {
            affichage(texteRouge("\nNuméro de partie invalide. Veuillez choisir un numéro valide.\n"));
            return demanderTypePartie(listeDesTypesDePartie);
        } else {
            return partieChoisie;
        }
    }
    public int demanderTypeAdversaire(String[] listeDesTypesAdversaire) {
        affichage("\n Entrez le numéro correspondant au type d'adversaire que vous souhaitez affronter :\n");
        for (int i = 0; i < listeDesTypesAdversaire.length; i++) {
            affichage(texteJaune(Integer.toString(i + 1)) + ". " + texteVert(listeDesTypesAdversaire[i]));
        }
        affichage("\n");
        int adversaireChoisi;
        while (!sc.hasNextInt()) {
            affichage(texteRouge("\nErreur de saisie, veuillez entrer un numéro d'adversaire valide.\n"));
            sc.next();
        }
        adversaireChoisi = sc.nextInt();
        sc.nextLine();
        if (adversaireChoisi < 1 || adversaireChoisi > listeDesTypesAdversaire.length) {
            affichage(texteRouge("\nNuméro d'adversaire invalide. Veuillez choisir un numéro valide.\n"));
            return demanderTypeAdversaire(listeDesTypesAdversaire);
        } else {
            return adversaireChoisi;
        }
    }

    /**
     * Ignore le reste de l'entrée de l'utilisateur après une commande.
     *
     * @param s Le reste de l'entrée de l'utilisateur.
     */
    public void ignorer(String s) {
        if (!s.isEmpty()) {
            affichage(texteGris("\nLa suite de votre entrée '") + texteRouge(s) + texteGris("' sera ignoré.\n"));
        }
    }

    /**
     * Demande à l'utilisateur d'entrer le nom du joueur.
     *
     * @param numJoueur Le numéro du joueur pour lequel entrer le nom.
     * @return Le nom du joueur entré par l'utilisateur.
     */
    public String DemanderNom(int numJoueur) {
        affichage("\nEntrez le nom du " + texteVert("joueur " + numJoueur) + " :\n");
        String nomJoueur = sc.nextLine();
        if (nomJoueur.isEmpty()) {
            affichage(texteRouge("\nErreur de saisie, veuillez entrer un nom valide qui n'est pas une chaine vide.\n"));
            return DemanderNom(numJoueur);
        } else {
            return nomJoueur;
        }
    }

    /**
     * Affiche le plateau de jeu à l'utilisateur.
     *
     * @param plateau Le plateau de jeu à afficher.
     */
    public void afficherPlateau(String plateau) {
        affichage(plateau);
    }

    /**
     * Demande à l'utilisateur s'il souhaite rejouer une partie.
     *
     * @return Vrai si l'utilisateur souhaite rejouer, faux sinon.
     */
    public boolean demanderRejouer() {
        affichage("\nVoulez vous " + texteVert("rejouer") + " une partie ? (o/n)\n");
        String rep = sc.next();
        if (rep.equals("o")) {
            return true;
        } else if (rep.equals("n")) {
            return false;
        } else {
            affichage(texteRouge("\nJe n'ai pas bien compris votre réponse..."));
            return demanderRejouer();
        }
    }

    /**
     * Affiche un message d'erreur à l'utilisateur.
     *
     * @param e Le message d'erreur à afficher.
     */
    public void afficherErreur(String e) {
        affichage(texteRouge(e));
    }

    /**
     * Affiche le résultat final de la session de jeu.
     *
     * @param vainqueur Le nom du vainqueur de la session.
     * @param scores    Le tableau des scores de chaque joueur.
     */
    public void affichageFinSession(String vainqueur, String[][] scores) {
        if (vainqueur != null) {
            affichage("\nFélicitations " + texteVert(vainqueur) + " ! Vous remportez la session !\n");
            String affichageScore = "Voici les Scores :\n";
            for (String[] score : scores) {
                affichageScore += texteVert(score[0]) + " : " + texteJaune(score[1] + "\n");
            }
            affichage(affichageScore);
        } else {
            affichage("\n" + texteVert("Ex-Aequo total!") + " Vous avez tout les deux remporté " + texteJaune(scores[0][1]) + " parties !\n");
        }
    }

    /**
     * Affiche le résultat d'une partie.
     *
     * @param vainqueurPartie Le nom du vainqueur de la partie.
     * @param infosVictoire   Les informations sur la victoire.
     */
    public void afficherResultatPartie(String vainqueurPartie, String[] infosVictoire) {
        // affichage different selon le vainqueur de la partie (IA ou joueur)
        if (vainqueurPartie.equals("IA")){
            affichage(texteJaune("\nDésolé, vous avez perdu la partie contre l'IA.\n"));

        }else{
        affichage("\nFélicitations " + texteVert(vainqueurPartie) + ", vous avez remporté la partie!\n");}
    }

    /**
     * Demande des informations initiales à l'utilisateur.
     *
     * @return Un tableau d'entiers contenant les informations initiales.
     */
    public int[] demanderInfoInitiales() {
        return new int[1];
    }

    /**
     * Demande des informations supplémentaires à l'utilisateur.
     *
     * @return Un tableau d'entiers contenant les informations supplémentaires.
     */
    public int[] demanderInfoSupplementaires() {
        return new int[1];
    }

    /**
     * Met le texte donné en rouge.
     *
     * @param s Le texte à mettre en rouge.
     * @return Le texte en rouge.
     */
    public String texteRouge(String s) {
        return "\u001B[31m" + s + "\u001B[0m";
    }

    /**
     * Met le texte donné en vert.
     *
     * @param s Le texte à mettre en vert.
     * @return Le texte en vert.
     */
    public String texteVert(String s) {
        return "\u001B[32m" + s + "\u001B[0m";
    }

    /**
     * Met le texte donné en jaune.
     *
     * @param s Le texte à mettre en jaune.
     * @return Le texte en jaune.
     */
    public String texteJaune(String s) {
        return "\u001B[33m" + s + "\u001B[0m";
    }

    /**
     * Met le texte donné en gris.
     *
     * @param s Le texte à mettre en gris.
     * @return Le texte en gris.
     */
    public String texteGris(String s) {
        return "\u001B[37m" + s + "\u001B[0m";
    }

    /**
     * Demande à l'utilisateur de jouer un coup.
     *
     * @param joueurCourant Le nom du joueur courant.
     * @param etatPartie    L'état actuel de la partie.
     * @return Le coup joué par l'utilisateur.
     */
    public Coup demanderCoups(String joueurCourant, EtatPartie etatPartie) {
        return null;
    }

    /**
     * Affiche un message d'erreur si le nom du joueur est déjà pris.
     */
    public void erreurNom() {
        affichage(texteJaune("Mince ! Ce nom est déja pris; Veuillez entrez un nouveau nom"));
    }

}
