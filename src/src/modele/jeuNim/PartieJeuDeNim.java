package modele.jeuNim;

import modele.EtatPartie;
import modele.InputException;
import modele.Joueur;
import modele.Partie;
import vue.Coup;
import vue.jeuNim.CoupJeuDeNim;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe PartieJeuDeNim étend la classe Partie pour représenter une partie du jeu de Nim.
 * Elle contient une liste de tas, le nombre d'allumettes autorisées par tour, une limite d'allumettes et un tableau de skins pour les allumettes.
 */
public class PartieJeuDeNim extends Partie {
    private final List<Tas> lesTas; // La liste des tas dans la partie.
    private final int nbAllumettesAutorise; // Le nombre d'allumettes qu'un joueur peut prendre par tour.
    private final boolean limiteAllumettes; // Indique si une limite d'allumettes par tour est imposée.
    private final String[] skinAllumettes = {"🗝️", "🗡️", "📍", "🖍️", "🔨", "🔧", "🪛", "🪥", "🎤", "🧨", "🥄", "🔪", "🍡", "🍗", "🥖", "🥕", "🍆", "🦴"}; // Les skins pour les allumettes.

    /**
     * Constructeur de la classe PartieJeuDeNim.
     * Initialise une nouvelle partie de Nim avec une liste de joueurs, un nombre de tas et un nombre d'allumettes autorisées par tour.
     *
     * @param lesJoueurs           La liste des joueurs de la partie.
     * @param nbTas                Le nombre de tas dans la partie.
     * @param nbAllumettesAutorise Le nombre d'allumettes qu'un joueur peut prendre par tour.
     */
    public PartieJeuDeNim(List<Joueur> lesJoueurs, int nbTas, int nbAllumettesAutorise) {
        super();
        for (Joueur joueur : lesJoueurs) {
            this.lesJoueurs.put(joueur, new EtatJoueurJeuDeNim(joueur));
        }
        limiteAllumettes = nbAllumettesAutorise != 0;
        this.nbAllumettesAutorise = nbAllumettesAutorise;
        lesTas = new ArrayList<>();
        for (int i = 1; i <= nbTas; i++) {
            lesTas.add(creerTas(i));
        }
    }

    /**
     * Crée un seul tas.
     *
     * @param n Le nombre d'allumettes dans le tas.
     * @return Un nouveau tas avec le nombre d'allumettes spécifié.
     */
    private Tas creerTas(int n) {
        return new Tas(n);
    }

    /**
     * Joue un tour en enlevant un certain nombre d'allumettes d'un tas spécifique.
     *
     * @param coups Le coup à jouer, qui contient le numéro du tas et le nombre d'allumettes à enlever.
     * @throws InputException Si le tas spécifié n'existe pas ou si le nombre d'allumettes à enlever est supérieur au nombre autorisé.
     */
    public void jouerTour(Coup coups)  throws InputException{
        CoupJeuDeNim coupsJN = (CoupJeuDeNim) coups;
        if (coupsJN.getNumTas() > lesTas.size()) {
            throw new InputException("Oups ! Ce tas n'existe pas.");
        } else if (limiteAllumettes && coupsJN.getNbAllu() > nbAllumettesAutorise) {
            throw new InputException("Mince ! le nombre d'allumettes doit être inferieur ou égal à " + nbAllumettesAutorise + ".");
        }
        lesTas.get(coupsJN.getNumTas()-1).supprimerAllumettes(coupsJN.getNbAllu());
    }

    /**
     * Vérifie si la partie est terminée.
     *
     * @return Vrai si tous les tas sont vides, faux sinon.
     */
    public boolean partieFinie() {
        for (Tas tas : lesTas) {
            if (!tas.estVide()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtient une représentation du plateau de jeu.
     *
     * @return Une chaîne de caractères représentant le plateau de jeu, avec les tas et le nombre d'allumettes dans chaque tas.
     */
    public String getPlateau() {
        String skinAllu = skinAllumettes[(int) (Math.random() % skinAllumettes.length)];
        String tasString = "\n";
        tasString += "Affichage des tas : \n";
        for (Tas tas : lesTas) {
            tasString += tas.getNumTas() + " : ";
            int numEspace = lesTas.size() - tas.getNumTas();
            for (int i = 0; i < numEspace; i++) {
                tasString += " ";
            }
            int numAllumettes = tas.getAllumette();
            for (int i = 0; i < numAllumettes; i++) {
                tasString += skinAllu;
            }
            tasString += "\n";
        }
        return tasString;
    }

    /**
     * Obtient l'état actuel de la partie.
     *
     * @return L'état actuel de la partie, qui contient les informations sur les joueurs et les tas.
     */
    public EtatPartie getEtatPartie() {
        if (limiteAllumettes) {
            return new EtatPartieJeuDeNim(lesJoueurs, new ArrayList<> (lesTas),nbAllumettesAutorise);
        } else {
            return new EtatPartieJeuDeNim(lesJoueurs, new ArrayList<> (lesTas));
        }
    }

    /**
     * Crée une nouvelle partie pour rejouer.
     *
     * @param infosSup Un tableau d'entiers contenant des informations supplémentaires pour rejouer la partie.
     * @return Une nouvelle instance de la partie pour rejouer.
     */
    public PartieJeuDeNim rejouer(int[] infosSup) {
        return new PartieJeuDeNim(getListeLesJoueurs(), this.lesTas.size(), infosSup[0]);
    }
}
