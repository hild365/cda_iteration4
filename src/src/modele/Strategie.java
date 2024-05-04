package modele;
import vue.Coup;
public interface Strategie {
    public Coup genererCoup(Partie etatPartie);
}
