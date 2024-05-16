package modele.jeuNim;
import modele.EtatPartie;
import modele.Strategie;
import modele.jeuNim.EtatPartieJeuDeNim;
import modele.jeuNim.PartieJeuDeNim;
import modele.puissance4.StrategiePuissance4;
import vue.Coup;
import vue.jeuNim.CoupJeuDeNim;

public class StrategieJeuDeNimGagnante implements Strategie {
    
    public Coup genererCoup(EtatPartie etatPartie) {
        // Cast l'objet Partie en PartieJeuDeNim pour accéder aux méthodes spécifiques de cette classe
        EtatPartieJeuDeNim etatPartieJN  = (EtatPartieJeuDeNim) etatPartie;
         // Initialise le résultat de l'opération XOR à 0
        int resultatXor = 0;
            // Parcourt tous les tas de la partie
        for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
             // Effectue une opération XOR sur le nombre d'allumettes de chaque tas
            resultatXor ^= etatPartieJN.getNbAllumettes(i);
        }
        // Si le résultat de l'opération XOR est 0
        if (resultatXor == 0) {
            // Parcourt tous les tas de la partie
            for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
                // Si le tas courant contient plus de 0 allumettes
                if (etatPartieJN.getNbAllumettes(i) > 0) {
                    // Crée un nouveau coup
                    CoupJeuDeNim coup = new CoupJeuDeNim();
        for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
            // Si le tas courant contient plus de 0 allumettes
            if (etatPartieJN.getNbAllumettes(i) > 0) {
                // Crée un nouveau coup
                CoupJeuDeNim coup = new CoupJeuDeNim();
                // Définit le numéro du joueur qui joue le coup (ici, on suppose que l'IA est le joueur 2)
                coup.setNumJoueur(2);
                // Définit le numéro du tas sur lequel le coup est joué
                coup.setNumTas(i);
                // Définit le nombre d'allumettes à retirer du tas (ici, 1)
                coup.setNbAllu(1);
                // Retourne le coup
                return coup;
            }
        }
    } else {
        // Parcourt tous les tas de la partie
        for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
            // Calcule le nombre d'allumettes restantes dans le tas après l'opération XOR
            int nb = etatPartieJN.getNbAllumettes(i) ^ resultatXor;
            // Si le nombre d'allumettes restantes est inférieur au nombre d'allumettes actuel dans le tas
            if (nb < etatPartieJN.getNbAllumettes(i)) {
                // Crée un nouveau coup
                CoupJeuDeNim coup = new CoupJeuDeNim();
                // Définit le numéro du joueur qui joue le coup (ici, on suppose que l'IA est le joueur 2)
                coup.setNumJoueur(2);
                // Définit le numéro du tas sur lequel le coup est joué
                coup.setNumTas(i);
                // Définit le nombre d'allumettes à retirer du tas (le nombre d'allumettes actuel dans le tas moins le nombre d'allumettes restantes après l'opération XOR)
                coup.setNbAllu(etatPartieJN.getNbAllumettes(i) - nb);
                // Retourne le coup
                return coup;
            }
        }
    }

    // Si aucun coup n'a été trouvé, retourne null
    return null;
    }
    public Strategie nouvelleInstance(){
        return new StrategieJeuDeNimGagnante();
    }
}
