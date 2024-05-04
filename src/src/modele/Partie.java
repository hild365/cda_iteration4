package modele;

import vue.Coup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Partie est une classe abstraite qui représente une partie de jeu.
 * Elle contient une liste de joueurs et définit les méthodes de base pour gérer une partie.
 */
public abstract class Partie {
    protected Map<Joueur, EtatJoueur> lesJoueurs;

    /**
     * Constructeur de la classe Partie.
     * Initialise une nouvelle partie avec une liste vide de joueurs.
     */
    public Partie() {
        lesJoueurs = new HashMap<>();
    }

    /**
     * Méthode abstraite pour jouer un tour de jeu.
     * Cette méthode doit être implémentée par les sous-classes.
     *
     * @param coups Le coup à jouer.
     * @throws InputException Si le coup est invalide.
     */
    public abstract void jouerTour(Coup coups) throws InputException;

    /**
     * Méthode abstraite pour obtenir une représentation du plateau de jeu.
     * Cette méthode doit être implémentée par les sous-classes.
     *
     * @return Une chaîne de caractères représentant le plateau de jeu.
     */
    public abstract String getPlateau();

    /**
     * Méthode abstraite pour vérifier si la partie est terminée.
     * Cette méthode doit être implémentée par les sous-classes.
     *
     * @return Vrai si la partie est terminée, faux sinon.
     */
    public abstract boolean partieFinie();

    /**
     * Récupère les informations de victoire pour la partie en cours.
     *
     * @return Un tableau de chaînes de caractères contenant les informations de victoire, ou null si la partie n'est pas terminée.
     */
    public String[] getInformationsVictoire() {
        return null;
    }

    /**
     * Vérifie si la partie en cours est un match nul.
     *
     * @return Vrai si la partie est un match nul, faux sinon.
     */
    public boolean testExAequo() {
        return false;
    }

    /**
     * Méthode abstraite pour rejouer la partie avec des informations supplémentaires.
     * Cette méthode doit être implémentée par les sous-classes.
     *
     * @param infosSupplementaires Un tableau d'entiers contenant des informations supplémentaires pour rejouer la partie.
     * @return Une nouvelle instance de la partie pour rejouer.
     */
    public abstract Partie rejouer(int[] infosSupplementaires);

    /**
     * Méthode abstraite pour obtenir l'état actuel de la partie.
     * Cette méthode doit être implémentée par les sous-classes.
     *
     * @return L'état actuel de la partie.
     */
    public abstract EtatPartie getEtatPartie();

    public List<Joueur> getListeLesJoueurs() {
        List<Joueur> lesJoueurs = new ArrayList<>();
        lesJoueurs.addAll(this.lesJoueurs.keySet());
        return lesJoueurs;
    }

    /**
     * Récupère le joueur correspondant au numéro donné.
     *
     * @param numJoueur Le numéro du joueur à récupérer.
     * @return Le joueur correspondant au numéro donné, ou null si aucun joueur ne correspond à ce numéro.
     */
    protected Joueur getJoueur(int numJoueur) {
        for (Joueur joueur : lesJoueurs.keySet()) {
            if (joueur.getNumJoueur() == numJoueur) {
                return joueur;
            }
        }
        return null;
    }
    // les methodes suivantes sont ajoutees pour le jeu de Nim ce sont des methodes abstraites
    public abstract int getNbTas();
    public abstract int getNbAllumettes(int numTas);
}
