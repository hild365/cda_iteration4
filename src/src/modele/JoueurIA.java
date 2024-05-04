package modele;
import vue.Coup;
public class JoueurIA extends Joueur{
    private Strategie strategie;
    public JoueurIA(Strategie strategie){
        super("IA");
        this.strategie = strategie;
    }
    public Coup demanderCoup(Partie etatPartie){
        return strategie.genererCoup(etatPartie);
    }
}
