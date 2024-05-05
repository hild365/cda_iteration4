package modele;
import modele.jeuNim.PartieJeuDeNim;
import vue.Coup;
import vue.jeuNim.CoupJeuDeNim;

public class StrategieJeuDeNimGagnante implements Strategie {
    public Coup genererCoup(Partie etatPartie) {
        PartieJeuDeNim etatPartieJN  = (PartieJeuDeNim) etatPartie;
        int resultatXor = 0;
        for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
            resultatXor ^= etatPartieJN.getNbAllumettes(i);
        }

        if (resultatXor == 0) {
            for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
                if (etatPartieJN.getNbAllumettes(i) > 0) {
                    CoupJeuDeNim coup = new CoupJeuDeNim();
                    coup.setNumJoueur(2); // En supposant que l'IA est le joueur 2
                    coup.setNumTas(i);
                    coup.setNbAllu(1);
                    return coup;
                }
            }
        } else {
            for (int i = 1; i <= etatPartieJN.getNbTas(); i++) {
                int nb = etatPartieJN.getNbAllumettes(i) ^ resultatXor;
                if (nb < etatPartieJN.getNbAllumettes(i)) {
                    CoupJeuDeNim coup = new CoupJeuDeNim();
                    coup.setNumJoueur(2); // En supposant que l'IA est le joueur 2
                    coup.setNumTas(i);
                    coup.setNbAllu(etatPartieJN.getNbAllumettes(i) - nb);
                    return coup;
                }
            }
        }

        return null;
    }
}
