package modele.puissance4;

import modele.EtatPartie;
import modele.InputException;
import modele.Strategie;
import modele.puissance4.Grille;
import vue.Coup;
import modele.puissance4.PartiePuissance4;
import vue.puissance4.CoupP4;

import java.util.*;

public class StrategiePuissance4 implements Strategie {
    private Grille grille;
    private Case[][] grilleCase;
    public StrategiePuissance4() {
        grille = new Grille();
        grilleCase = new Case[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                grilleCase[i][j] = new Case(0,i);
            }
        }
    }
    public Coup genererCoup(EtatPartie etatPartie) {
        EtatPartiePuissance4 etatPartieP4 = (EtatPartiePuissance4) etatPartie;
        CoupP4 coup=new CoupP4();
        try {
            grille.insererJeton(etatPartieP4.getDernierCoup()[0]+1,1);
            Grille grilleTest=(Grille) grille.clone();
            grilleTest.rotation("d");
            if (quiGagne(grilleTest)==1) {
                coup.setTypeCoup(2);
                coup.setRotation("d");
                return coup;
            }
            grilleTest=(Grille) grille.clone();
            grilleTest.rotation("g");
            if (quiGagne(grilleTest)==1) {
                coup.setTypeCoup(2);
                coup.setRotation("g");
                return coup;
            }

            //Test des coups possibles
            SortedSet<Case> coupsPossibles = new TreeSet<>();
            for (int i = 0; i < 7; i++) {
                grilleCase[i][grille.ligneVide(i+1)].setScore(newScore(grilleCase[i][grille.ligneVide(i+1)],i,grille.ligneVide(i+1)));
                coupsPossibles.add(grilleCase[i][grille.ligneVide(i+1)]);
                break;

            }

            Case coupCourant=coupsPossibles.first();

            while(!coupsPossibles.isEmpty()){
                //Je met le jeton
                grille.insererJeton(coupCourant.getCol()+1,2);
                //Je teste si il me ferait perdre avec une rotation a droite
                grilleTest=(Grille) grille.clone();
                grilleTest.rotation("d");
                if(quiGagne(grilleTest)==2) {
                    grille.retirerJeton(coupCourant.getCol());
                    coupCourant=coupsPossibles.first();
                } else {
                    //Sinon je teste si il me ferait perdre avec une rotation a gauche
                    grilleTest = (Grille) grille.clone();
                    grilleTest.rotation("g");
                    if (quiGagne(grilleTest) == 2) {
                        grille.retirerJeton(coupCourant.getCol());
                        coupCourant = coupsPossibles.first();
                    } else {
                        grille.retirerJeton(coupCourant.getCol());
                        coup.setTypeCoup(0);
                        coup.setColonne(coupCourant.getCol()+1);
                        return coup;
                    }
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
    private int newScore(Case c,int col, int lig) {
        int score = 0;
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
                case 1 :
                    score=1;
                    break;
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
            }
            score= Integer.max(score, score2);
            grille.retirerJeton(col);
        } catch (InputException e) {
            throw new RuntimeException(e);
        }

        return score;
    }
    public class Case implements Comparable<Case> {
            private int couleur;
            private int score;
            private int col;

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
