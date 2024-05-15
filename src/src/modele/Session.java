package modele;
import modele.jeuNim.PartieJeuDeNim;
import modele.jeuNim.StrategieJeuDeNimAleatoire;
import modele.jeuNim.StrategieJeuDeNimGagnante;
import modele.puissance4.PartiePuissance4;
import modele.puissance4.StrategiePuissance4;
import modele.puissance4.StrategiePuissance4Optimisee;
import vue.Coup;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * La classe Session représente une session de jeu.
 * Elle contient des informations sur la partie en cours, le vainqueur de la partie,
 * la liste des joueurs, le joueur courant et le type de partie.
 * Elle fournit des méthodes pour jouer un coup, passer au joueur suivant, initialiser une partie,
 * récupérer le vainqueur de la partie et de la session, récupérer les scores totaux, vérifier si la partie est finie,
 * et récupérer les informations de victoire et le plateau de la partie en cours.
 */
public class Session {
    /**
     * Cette variable contient la partie de jeu en cours.
     * La partie de jeu est mise à jour chaque fois qu'un nouveau jeu est lancé.
     */
    private Partie partieCourante;

    /**
     * Cette variable est une map où chaque entrée représente une partie jouée pendant la session.
     * La clé est la partie (Partie) et la valeur est le joueur (Joueur) qui a gagné cette partie.
     * Cette map est utilisée pour garder une trace de toutes les parties jouées et de leurs vainqueurs respectifs.
     */
    private Map<Partie, Joueur> lesParties;

    /**
     * Cette variable contient le nom du joueur qui a gagné la partie en cours.
     * Le vainqueur est mis à jour à la fin de chaque partie.
     */
    private Joueur vainqueurPartie;

    /**
     * Cette variable contient la liste des joueurs participant à la session en cours.
     */
    private final List<Joueur> lesJoueurs;

    /**
     * Cette variable représente le joueur courant dans la partie.
     * Le joueur courant est celui qui doit jouer le prochain coup.
     */
    private Joueur joueurCourant;

    /**
     * Cette variable contient le type de partie pour la session en cours.
     * Le type de partie est défini lors de la création de la session.
     * Par exemple, 1 pour Puissance 4, 2 pour Jeu de Nim, etc.
     */
    private int typePartie;

    /**
     * Constructeur de la classe Session.
     * Initialise une nouvelle session de jeu avec une liste de noms de joueurs et un type de partie.
     *
     * @param nomsJoueurs Un ensemble de noms de joueurs. Chaque nom de joueur est unique.
     * @param typePartie  Le type de partie à jouer. Par exemple, 1 pour Puissance 4, 2 pour Jeu de Nim, etc.
     */
    public Session(Set<String> nomsJoueurs, int typePartie,int[] infoSupp) {
        // Initialisation de la liste des parties jouées lors de la session
        lesParties = new LinkedHashMap<>();
        // Création d'une nouvelle liste pour stocker les joueurs de la session
        lesJoueurs = new ArrayList<>();
        this.typePartie=typePartie;
        // Pour chaque nom de joueur dans le tableau de noms de joueurs
        for (String nomJoueur : nomsJoueurs) {
            // Création d'un nouvel objet Joueur avec le nom du joueur
            if (nomJoueur.equals("IA")){
                // utilisation de infoSupp pour déterminer le type de l'IA avec la methode creerIA et ajout de l'IA dans la liste des joueurs
                Joueur joueurIA = creerIA(infoSupp);
                lesJoueurs.add(joueurIA);
            }else {
                Joueur joueur = new JoueurHumain(nomJoueur);
                lesJoueurs.add(joueur);
            }
        }
        // Initialisation du joueurCourant pour qu'il ne soit pas null au départ et qu'il prenne en premier le joueur1
        joueurCourant=lesJoueurs.get(0);
    }


    /**
     * Initialise une nouvelle partie de Puissance 4.
     */
    private void initialiserPartiePuissance4(boolean rotationPossible) {
        partieCourante = new PartiePuissance4(lesJoueurs,rotationPossible);
    }


    /**
     * Initialise une nouvelle partie de Jeu de Nim.
     *
     * @param nbTas                Le nombre de tas pour la partie de Jeu de Nim.
     * @param nbAllumettesAutorise Le nombre d'allumettes autorisées pour la partie de Jeu de Nim.
     */
    private void initialiserPartieJeuDeNim(int nbTas, int nbAllumettesAutorise) {
        partieCourante = new PartieJeuDeNim(lesJoueurs, nbTas, nbAllumettesAutorise);
    }


    /**
     * Lance une nouvelle partie en fonction du type de partie défini.
     * Si une partie est déjà en cours, elle est rejouée avec les informations supplémentaires fournies.
     * Sinon, une nouvelle partie est initialisée en fonction du type de partie.
     * Le vainqueur de la partie est réinitialisé à null à chaque début de nouvelle partie.
     *
     * @param infoSupplementaires Un tableau d'entiers contenant des informations supplémentaires nécessaires pour initialiser ou rejouer une partie.
     *                            Par exemple, pour le Jeu de Nim, il peut contenir le nombre de tas et le nombre d'allumettes autorisées.
     */
    public void nouvellePartie(int[] infoSupplementaires) {
        if (partieCourante == null) {
            if (typePartie == 1) {
                // Initialisation du joueurIA si le joueur 2 est une IA pour une nouvelle partie de Puissance 4
                initialiserPartiePuissance4(infoSupplementaires[0]==1);
            } else if (typePartie == 2) {
                // Initialisation du joueurIA si le joueur 2 est une IA pour une nouvelle partie de Jeu de Nim
                initialiserPartieJeuDeNim(infoSupplementaires[0], infoSupplementaires[1]);
            }
        } else {
            Joueur ia= lesJoueurs.get(1);
            if(ia instanceof JoueurIA){
                ((JoueurIA) ia).remiseAZero();
            }
            partieCourante = partieCourante.rejouer(infoSupplementaires);
        }
        vainqueurPartie = null;
    }
    // méthode pour créer une IA en fonction du type de partie et des infos supplementaires
    public JoueurIA creerIA(int[] infoSupplementaires){
        if (typePartie==1){
            if(infoSupplementaires[0]==1){
                return new JoueurIA(new StrategiePuissance4());
            } else {
                return new JoueurIA(new StrategiePuissance4Optimisee());
            }
        }else {
            if (infoSupplementaires[1]==0){
                return new JoueurIA(new StrategieJeuDeNimGagnante());
            }else { return new JoueurIA(new StrategieJeuDeNimAleatoire());
            }
        }
    }

    /**
     * Définit le type de partie pour la session en cours.
     *
     * @param typePartie Le type de partie à définir. Par exemple, 1 pour Puissance 4, 2 pour Jeu de Nim, etc.
     */
    public void setTypePartie(int typePartie) {
        this.typePartie = typePartie;
    }

    /**
     * Récupère le nom du joueur courant.
     * Cette méthode retourne le nom du joueur qui est en train de jouer dans la partie en cours.
     *
     * @return Le nom du joueur courant.
     */
    public String getJoueurCourant() {
        return joueurCourant.getNom();
    }


    /**
     * Joue un tour de la partie en cours.
     * Cette methode ajoute à la fin du tableau de coups passé en argument le numéro du joueur courant.
     * Elle appelle ensuite la méthode jouerTour de la partie en cours avec le tableau de coups mis à jour.
     *
     * @param coups Un tableau d'entiers représentant le informations du coup joué.
     * @throws InputException Si les coups fournis sont invalides.
     */
    public void jouerTour(Coup coups) throws InputException {
        coups.setNumJoueur(joueurCourant.getNumJoueur());
        partieCourante.jouerTour(coups);
    }


    /**
     * Passe au joueur suivant dans la liste des joueurs.
     * Si aucun joueur n'a encore joué (joueurCourant est null), le premier joueur de la liste est défini comme le joueur courant.
     * Sinon, le joueur courant est mis à jour pour être le joueur suivant dans la liste.
     * Si le joueur courant est le dernier joueur de la liste, le joueur courant est réinitialisé pour être le premier joueur de la liste.
     */
    public void joueurSuivant() {
        if (joueurCourant == null) {
            joueurCourant = lesJoueurs.get(0);
        } else {
            int nbJoueur = lesJoueurs.size();
            int numJoueurCourant = joueurCourant.getNumJoueur();
            if (numJoueurCourant == nbJoueur - 1) {
                joueurCourant = lesJoueurs.get(0);
            } else {
                if (numJoueurCourant+1==3) {
                    joueurCourant= lesJoueurs.get(0);
                }
                else {
                joueurCourant = lesJoueurs.get(1);}
            }
        }
    }

    /**
     * Met à jour le vainqueur de la partie en cours.
     * Cette méthode vérifie si la partie en cours est une égalité en appelant la méthode testExAequo de la partie en cours.
     * Si la partie n'est pas une égalité, le joueur courant est défini comme le vainqueur et son score est incrémenté.
     * Si la partie est une égalité, le vainqueur est défini à null.
     */
    private void mettreAJourVainqueurPartie() {
        if (!partieCourante.testExAequo()) {
            vainqueurPartie = joueurCourant;
            joueurCourant.incrementerScore();
        } else {
            vainqueurPartie = null;
        }
    }

    /**
     * Récupère le vainqueur de la partie en cours.
     * Cette méthode met à jour le vainqueur de la partie en appelant la méthode mettreAJourVainqueurPartie.
     * Elle ajoute ensuite la partie courante et son vainqueur à la map des parties.
     * Enfin, elle retourne le nom du vainqueur de la partie.
     *
     * @return Le nom du vainqueur de la partie.
     */
    public String getVainqueurPartie() {
        mettreAJourVainqueurPartie();
        lesParties.put(partieCourante, vainqueurPartie);
        return vainqueurPartie.getNom();
    }

    /**
     * Récupère le vainqueur de la session de jeu.
     * Cette méthode parcourt la liste des joueurs et détermine le joueur avec le score le plus élevé.
     * Si plusieurs joueurs ont le score le plus élevé, la méthode retourne null pour indiquer une égalité.
     * Sinon, elle retourne le nom du joueur avec le score le plus élevé.
     *
     * @return Le nom du vainqueur de la session, ou null en cas d'égalité.
     */
    public String getVainqueurSession() {
        Joueur v = lesJoueurs.get(0);
        int nbV = 1;
        for (int i = 1; i < lesJoueurs.size(); i++) {
            if (lesJoueurs.get(i).getScore() > v.getScore()) {
                v = lesJoueurs.get(i);
            } else if (lesJoueurs.get(i).getScore() == v.getScore()) {
                nbV++;
            }
        }
        if (nbV > 1) {
            return null;
        } else {
            return v.getNom();
        }
    }

    /**
     * Récupère les scores totaux de tous les joueurs.
     * Cette méthode crée un tableau de chaînes de caractères 2D où chaque ligne représente un joueur et les deux colonnes représentent respectivement le nom et le score du joueur.
     * La première colonne (index 0) contient le nom du joueur et la deuxième colonne (index 1) contient le score du joueur.
     *
     * @return Un tableau de chaînes de caractères 2D contenant les noms et les scores de tous les joueurs.
     */
    public String[][] getScoresTotaux() {
        String[][] scores = new String[lesJoueurs.size()][2];
        for (int i = 0; i < lesJoueurs.size(); i++) {
            scores[i][0] = lesJoueurs.get(i).getNom();
            scores[i][1] = Integer.toString(lesJoueurs.get(i).getScore());
        }
        return scores;
    }

    /**
     * Vérifie si la partie en cours est terminée.
     * Cette méthode appelle la méthode partieFinie() du jeu en cours pour vérifier si la partie est terminée.
     *
     * @return Une valeur booléenne indiquant si la partie en cours est terminée.
     */
    public boolean partieFinie() {
        return partieCourante.partieFinie();
    }

    /**
     * Récupère les informations de victoire de la partie en cours.
     * Cette méthode appelle la méthode getInfoVictoire() de l'objet partieCourante pour obtenir les informations de victoire.
     *
     * @return Un tableau de chaînes de caractères contenant les informations de victoire.
     */
    public String[] getInformationsVictoire() {
        return partieCourante.getInformationsVictoire();
    }

    /**
     * Récupère le plateau de la partie en cours.
     * Cette méthode appelle la méthode getPlateau() de l'objet partieCourante pour obtenir le plateau de jeu actuel.
     *
     * @return Une chaîne de caractères représentant le plateau de jeu.
     */
    public String getPlateau() {
        return partieCourante.getPlateau();
    }

    public EtatPartie getEtatPartie(){
        return partieCourante.getEtatPartie();
    }
    // méthode pour demander un coup à l'IA
    public Coup demanderCoupIA(){
        return joueurCourant.demanderCoup(partieCourante.getEtatPartie());
    }
}
