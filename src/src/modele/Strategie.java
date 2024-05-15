package modele;
import vue.Coup;
public interface Strategie {
    public Coup genererCoup(EtatPartie etatPartie);
    public Strategie nouvelleInstance();
}
