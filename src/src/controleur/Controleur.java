package controleur;

import modele.*;
import vue.*;
import vue.jeuNim.IhmJeuDeNim;
import vue.puissance4.IhmPuissance4;

import java.util.Set;
import java.util.LinkedHashSet;

/**
 * La classe Controleur est responsable de la gestion du jeu.
 * Elle initialise l'interface utilisateur, la session de jeu et gère le déroulement des parties.
 */
public class Controleur {
    /**
     * Cette variable contient le type de la partie choisi sous forme d'int
     */
    private final int typePartie;
    // Interface Homme-Machine (IHM) pour interagir avec l'utilisateur
    /**
     * Cette variable contient l'ihm initalisé ainsi que l'ihm
     */
    private Ihm ihm;
    private boolean adversaireEstIA;

    /**
     * Constructeur de la classe Controleur.
     * Initialise la liste des types de parties et l'IHM.
     *
     * @param ihm L'interface homme-machine à utiliser.
     */
    public Controleur(Ihm ihm, int typePartie,int choixAdversaire) {
        this.ihm = ihm;
        this.typePartie = typePartie;
        this.adversaireEstIA = (choixAdversaire == 2);
    }

    /**
     * Crée et retourne une nouvelle instance de IhmJeuDeNim.
     *
     * @return Une nouvelle instance de IhmJeuDeNim.
     */
    private Ihm ihmJeuDeNim() {
        return new IhmJeuDeNim();
    }

    /**
     * Crée et retourne une nouvelle instance de IhmPuissance4.
     *
     * @return Une nouvelle instance de IhmPuissance4.
     */
    private Ihm ihmPuissance4() {
        return new IhmPuissance4();
    }


    /**
     * La méthode jouer() est responsable de la gestion du déroulement d'une partie de jeu.
     * Elle recueille les informations nécessaires, initialise la session de jeu et gère le déroulement de la partie.
     */
    public void jouer() {

        // Demande les noms des joueurs et les stocke dans le Set nomsJoueurs
        Set<String> nomsJoueurs;
        if (adversaireEstIA) {
            ihm.affichage("Vous avez choisi de jouer contre l'IA.");
            nomsJoueurs = getNomsJoueurs(1);
            nomsJoueurs.add("IA");
        }else {
            nomsJoueurs = getNomsJoueurs(2);
        }




        // Initialise l'interface graphique en fonction du type de partie choisi
        if (typePartie == 1) {
            ihm = ihmPuissance4();
        } else if (typePartie == 2) {
            ihm = ihmJeuDeNim();
        }
        int[] infoInitiales = ihm.demanderInfoInitiales();
        // Initialise la session de jeu
        Session session = new Session(nomsJoueurs, typePartie,infoInitiales);

        // Commence une nouvelle partie
        session.nouvellePartie(infoInitiales);
        jouerPartie(session);

        // Demande à l'utilisateur s'il souhaite rejouer
        boolean jouer = ihm.demanderRejouer();

        // Si l'utilisateur souhaite rejouer, commence une nouvelle partie
        while (jouer) {
            session.nouvellePartie(ihm.demanderInfoSupplementaires());
            jouerPartie(session);
            jouer = ihm.demanderRejouer();
        }

        // Récupère le vainqueur de la session et les scores totaux
        String vainqueur = session.getVainqueurSession();
        String[][] scores = session.getScoresTotaux();

        // Affiche les informations de fin de session
        ihm.affichageFinSession(vainqueur, scores);
    }
    private Set<String> getNomsJoueurs(int nombreJoueurs){
        Set<String> nomsJoueurs = new LinkedHashSet<>();
        // Demande les noms des joueurs et les stocke dans le Set nomsJoueurs
        for (int i = 0; i < nombreJoueurs ; i++) {
            String nom = ihm.DemanderNom(i + 1);
            while (nomsJoueurs.contains(nom)) {
                ihm.erreurNom();
                nom = ihm.DemanderNom(i + 1);
            }
            nomsJoueurs.add(nom);
        }
        return nomsJoueurs;
    }
    /**
     * La méthode jouerPartie() gère le déroulement d'une partie individuelle dans une session de jeu.
     * Elle fait avancer le jeu tour par tour jusqu'à ce que la partie soit terminée.
     *
     * @param session La session de jeu en cours.
     */
    private void jouerPartie(Session session) {
        // Commence par le joueur suivant
        //J'ai enleve cette ligne car il fallait que le joueur1 commence toujours la partie
        //session.joueurSuivant();

        // Continue à jouer tant que la partie n'est pas terminée
        while (!session.partieFinie()) {
            // Récupère l'état actuel du plateau de jeu
            String plateau = session.getPlateau();

            // Affiche le plateau de jeu
            ihm.afficherPlateau(plateau);

            // Récupère le joueur courant
            String joueurCourant = session.getJoueurCourant();

            // Récupère l'état actuel de la partie
            EtatPartie etatPartie = session.getEtatPartie();

            Coup coups;
            // Demande le coup à jouer au joueur courant
            if (joueurCourant.equals("IA")) {
                // Récupère le coup à jouer par l'IA
                 coups = session.demanderCoupIA();
                 ihm.affichage("L'IA a joué : " + coups.toString());
            } else {
                 coups = ihm.demanderCoups(joueurCourant, etatPartie);
            }
            try {
                // Joue le coup et passe au joueur suivant
                session.jouerTour(coups);
                session.joueurSuivant();
            } catch (Exception e) {
                //Affiche l'erreur si le coup n'est pas valide
              ihm.afficherErreur(e.getMessage()+"\nVeuillez rejouer.");
            }
        }
        //Passe au joueur suivant pour afficher le plateau final par ce qu'il ya dans le try catch session.joueurSuivant();
        //alors que la partie est finie
        session.joueurSuivant();
        // Récupère et affiche l'état final du plateau de jeu
        String plateau = session.getPlateau();
        ihm.afficherPlateau(plateau);

        // Récupère le vainqueur de la partie et affiche le résultat
        String vainqueurPartie = session.getVainqueurPartie();
        ihm.afficherResultatPartie(vainqueurPartie, session.getInformationsVictoire());
    }
}
