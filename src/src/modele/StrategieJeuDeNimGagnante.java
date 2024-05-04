package modele;
import vue.Coup;
import vue.jeuNim.CoupJeuDeNim;

public class StrategieJeuDeNimGagnante implements Strategie {
    public Coup genererCoup(Partie etatPartie) {
        int resultatXor = 0;
        for (int i = 1; i <= etatPartie.getNbTas(); i++) {
            resultatXor ^= etatPartie.getNbAllumettes(i);
        }

        if (resultatXor == 0) {
            for (int i = 1; i <= etatPartie.getNbTas(); i++) {
                if (etatPartie.getNbAllumettes(i) > 0) {
                    CoupJeuDeNim coup = new CoupJeuDeNim();
                    coup.setNumJoueur(2); // En supposant que l'IA est le joueur 2
                    coup.setNumTas(i);
                    coup.setNbAllu(1);
                    return coup;
                }
            }
        } else {
            for (int i = 1; i <= etatPartie.getNbTas(); i++) {
                int nb = etatPartie.getNbAllumettes(i) ^ resultatXor;
                if (nb < etatPartie.getNbAllumettes(i)) {
                    CoupJeuDeNim coup = new CoupJeuDeNim();
                    coup.setNumJoueur(2); // En supposant que l'IA est le joueur 2
                    coup.setNumTas(i);
                    coup.setNbAllu(etatPartie.getNbAllumettes(i) - nb);
                    return coup;
                }
            }
        }

        return null;
    }
}
