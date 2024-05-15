package modele.jeuNim;
import modele.EtatPartie;
import modele.Strategie;
import modele.puissance4.StrategiePuissance4;
import vue.Coup;
public class StrategieJeuDeNimAleatoire implements Strategie {
    public Coup genererCoup(EtatPartie etatPartie) {
        //TODO : Implémenter la stratégie aléatoire pour le jeu de Nim ici (voir énoncé)
        //TODO : tu peux utiliser les methodes etatPartie.getNbTas() et etatPartie.getNbAllumettes(i) pour obtenir le nombre de tas et le nombre d'allumettes dans chaque tas
        return null;
    }
    public Strategie nouvelleInstance(){
        return new StrategieJeuDeNimAleatoire();
    }
}
