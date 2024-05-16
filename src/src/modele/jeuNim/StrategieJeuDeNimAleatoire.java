package modele.jeuNim;
import modele.EtatPartie;
import modele.Strategie;
import modele.puissance4.StrategiePuissance4;
import vue.Coup;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import vue.jeuNim.CoupJeuDeNim;

public class StrategieJeuDeNimAleatoire implements Strategie {
    private List<CoupJeuDeNim> listeCoupsPossible = new ArrayList<>();
    private int nbAlluMax;

    public Coup genererCoup(EtatPartie etatPartie) {
        EtatPartieJeuDeNim etatPartieJN  = (EtatPartieJeuDeNim) etatPartie;
        this.nbAlluMax= etatPartieJN.getNbAlluMax();
        listeCoupsPossible.clear(); // Vider la liste pour s'assurer qu'elle ne contient que des coups valides

        // Recalculer tous les coups possibles en respectant la contrainte du nombre maximal d'allumettes
        for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
            int nbAllumettesDansTas = etatPartieJN.getNbAllumettes(i);
            if (nbAllumettesDansTas > 0) {
                int maxAllumettes = Math.min(nbAllumettesDansTas, nbAlluMax);
                for (int j = 1; j <= maxAllumettes; j++) {
                    CoupJeuDeNim coup = new CoupJeuDeNim();
                    coup.setNumJoueur(2);
                    coup.setNumTas(i);
                    coup.setNbAllu(j);
                    listeCoupsPossible.add(coup);
                }
            }
        }
        Random random = new Random();
        int index = random.nextInt(listeCoupsPossible.size());
        return listeCoupsPossible.get(index);

    }
    public Strategie nouvelleInstance(){
        return new StrategieJeuDeNimAleatoire();
    }
}
