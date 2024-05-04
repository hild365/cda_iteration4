package main;

import controleur.Controleur;
import vue.Ihm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        int choixPartie = ihm.demanderTypePartie(new String[]{"Puissance 4", "Jeu de Nim"});
        int choixAdversaire = ihm.demanderTypeAdversaire(new String[]{"Humain", "IA"});
        Controleur controleur = new Controleur(ihm,choixPartie,choixAdversaire);
        controleur.jouer();
    }
}