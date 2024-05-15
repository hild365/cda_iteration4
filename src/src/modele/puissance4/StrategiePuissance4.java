package modele.puissance4;

import modele.EtatPartie;
import modele.InputException;
import modele.Strategie;
import vue.Coup;
import vue.puissance4.CoupP4;
import java.util.SortedSet;
import java.util.TreeSet;

public class StrategiePuissance4 implements Strategie {
    private final Grille grille;
    private final Case[][] grilleCase;
    public StrategiePuissance4() {
        grille = new Grille();
        grilleCase = new Case[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                grilleCase[i][j] = new Case(0, i);
            }
        }
    }
    public Coup genererCoup(EtatPartie etatPartie) {
        EtatPartiePuissance4 etatPartieP4 = (EtatPartiePuissance4) etatPartie;
        CoupP4 coup=new CoupP4();
        try {
                if(etatPartieP4.getDernierCoup()[0]==-1) {
                    grille.rotation("d");
                } else if (etatPartieP4.getDernierCoup()[0]==-2) {
                    grille.rotation("g");
                } else {
                    grille.insererJeton(etatPartieP4.getDernierCoup()[0] + 1, 1);
                }
                Grille grilleTest=(Grille) grille.clone();
            if(etatPartieP4.getRotationPossible()){
                grilleTest.rotation("d");
                if (quiGagne(grilleTest)==1) {
                    coup.setTypeCoup(1);
                    coup.setRotation("d");
                    grille.rotation("d");
                    return coup;
                }
                grilleTest=(Grille) grille.clone();
                grilleTest.rotation("g");
                if (quiGagne(grilleTest)==1) {
                    coup.setTypeCoup(1);
                    coup.setRotation("g");
                    grille.rotation("g");
                    return coup;
                }
            }

            //Test des coups possibles
            SortedSet<Case> coupsPossibles = new TreeSet<>();
            for(int i = 0; i < 7; i++) {
                if(grille.nbJetons(i)==7) {
                    continue;
                }
                grilleCase[i][grille.nbJetons(i)].setScore(newScore(i,grille.nbJetons(i)));
                coupsPossibles.add(grilleCase[i][grille.nbJetons(i)]);
            }

            Case coupCourant;

            while(!coupsPossibles.isEmpty()){
                coupCourant=coupsPossibles.first();
                coupsPossibles.remove(coupCourant);
                //Je met le jeton
                grille.insererJeton(coupCourant.getCol()+1,2);
                //Si j'ai puissance 4 directement je joue
                if(grille.puissance4(coupCourant.getCol(),grille.nbJetons(coupCourant.getCol())-1,2)[0]>3) {
                    coup.setTypeCoup(0);
                    coup.setColonne(coupCourant.getCol() + 1);
                    return coup;
                }
                //Je teste si il me ferait perdre avec une rotation a droite
                grilleTest=(Grille) grille.clone();

                if(etatPartieP4.getRotationPossible()) {

                    grilleTest.rotation("d");
                    //si oui alors je ne joue pas ce jeton
                    if (quiGagne(grilleTest) == 2) {
                        grille.retirerJeton(coupCourant.getCol());
                    } else {
                        //Sinon je teste si il me ferait perdre avec une rotation a gauche
                        grilleTest = (Grille) grille.clone();
                        grilleTest.rotation("g");
                        //si oui alors je ne joue pas ce jeton
                        if (quiGagne(grilleTest) == 2) {
                            grille.retirerJeton(coupCourant.getCol());
                        } else {
                            //Sinon je joue le jeton
                            coup.setTypeCoup(0);
                            coup.setColonne(coupCourant.getCol() + 1);
                            return coup;
                        }
                    }
                } else {
                    //Sinon je joue le jeton
                    coup.setTypeCoup(0);
                    coup.setColonne(coupCourant.getCol() + 1);
                    return coup;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return coup;
    }

    public Strategie nouvelleInstance(){
        return new StrategiePuissance4();
    }
    private int quiGagne(Grille grille) {
        int[] p4=grille.puissance4();
        if(p4[0]==-1){
            return 0;
        } else if (p4[0] > 3) {
            if (p4[1] == 2) {
                return 1;
            }
            return 2;
        }
        return 0;
    }
    private int newScore(int col, int lig) {
        int score;
        try {
            grille.insererJeton(col+1, 2);
            switch (grille.puissance4(col, lig, 2)[0]) {
                case 4 :
                    score=7;
                    break;
                case 3 :
                    score=5;
                    break;
                case 2 :
                    score=3;
                    break;
                default:
                    score=1;
            }
            grille.retirerJeton(col);
            grille.insererJeton(col+1,1);
            int score2 = 0;
            switch (grille.puissance4(col, lig, 1)[0]) {
                case 4 :
                    score2=6;
                    break;
                case 3 :
                    score2=4;
                    break;
                case 2 :
                    score2=2;
                    break;
                default:
                    score=1;
            }
            score= Integer.max(score, score2);
            grille.retirerJeton(col);
        } catch (InputException e) {
            throw new RuntimeException(e);
        }

        return score;
    }
    public static class Case implements Comparable<Case> {
            private int couleur;
            private int score;
            private final int col;

            public Case(int couleur, int score, int col) {
                this.couleur = couleur;
                this.score = score;
                this.col = col;
            }
            public Case(int score,int col) {
                this.couleur = 0;
                this.score = score;
                this.col=col;
            }

            public int compareTo(Case c) {
                return c.score-this.score;
            }

            public int getCouleur() {
                return couleur;
            }

            public int getScore() {
                return score;
            }

            public int getCol() {
                return col;
            }

            public void setScore(int score) {
                    this.score = score;
                }

            public void setCouleur(int couleur) {
                this.couleur = couleur;
            }
    }

}
