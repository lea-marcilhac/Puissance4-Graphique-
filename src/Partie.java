/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarcilh
 */
import java.util.Random;//on importe les bibliotheques necessaires
import java.util.Scanner;

public class Partie {

    Joueur ListeJoueurs[] = new Joueur[2];//on crée 2 joueurs
    Grille laGrille = new Grille();//pareil pour la grille de jeu
    Joueur joueurCourant;//le joueur qui joue
    Random rand = new Random();//on créé une valeur aléatoire qui servira a attribuer une couleur aléatoire au joueurs

    public void attribuerCouleursAuxJoueurs() {
        int r = rand.nextInt(2);//on donne à r une valeur entière aléatoire 0 ou 1
        if (r == 1) {
            ListeJoueurs[0] = new Joueur("R");
            ListeJoueurs[0].affecterCouleur("R");//le premier joueur aura la couleur rouge
            ListeJoueurs[1] = new Joueur("J");
            
            ListeJoueurs[1].affecterCouleur("J");//le deuxieme joueur aura la couleur jaune
            joueurCourant = ListeJoueurs[0];//le joueur courant, donc le premier a jouer, est le joueur 1
            for (int i = 0; i < 21; i++) {//ici on donne à chaque joueur ses 21 jetons de la bonne couleur
                ListeJoueurs[0].ajouterJeton(new Jeton("R"));
                ListeJoueurs[1].ajouterJeton(new Jeton("J"));
            }

        } else {// meme chose qu'avant mais en inversant les couleurs
            ListeJoueurs[0] = new Joueur("J");
            ListeJoueurs[0].affecterCouleur("J");
            ListeJoueurs[1] = new Joueur("R");
            ListeJoueurs[1].affecterCouleur("R");
            joueurCourant = ListeJoueurs[0];
            for (int i = 0; i < 21; i++) {
                ListeJoueurs[0].ajouterJeton(new Jeton("J"));
                ListeJoueurs[1].ajouterJeton(new Jeton("R"));
            }
        }
       
    }

    public void initialiserPartie() {
        
       System.out.println("Quel est le nom de joueurs 1 : ");//on demande au début du jeu le nom des joueurs
        Scanner sc;
        sc = new Scanner(System.in);
        Joueur Joueur1 = new Joueur(sc.nextLine());
        Joueur1=ListeJoueurs[0];
        

        System.out.println("Quel est le nom de joueurs 2 : ");
        
   
      Joueur Joueur2= new Joueur(sc.nextLine());
        Joueur2=ListeJoueurs[1];
        
       

        laGrille.viderGrille();//on commence par vider la grille pour supprimer tous les jetons et pieges
        int d = 0;
        int x;
        int y;

        for (int i = 0; i < 5; i++) {//on fait 5 tours de boucle pour poser 5 desintegrateurs

            x = rand.nextInt(6);
            y = rand.nextInt(7);

            while (!laGrille.placerTrouNoir(x, y)) {//on vérifie que aux coordonnées x,y, il n'y ai pas de trou noir avec le"!" qui signifie
                x = rand.nextInt(6);//                l'opposé
                y = rand.nextInt(7);
            }
            if (d < 2) {
                laGrille.placerDesintegrateur(x, y);//pour pouvoir y mettre un désintégrateur
                d++;
            }

        }

        for (int i = 0; i < 3; i++) {

            x = rand.nextInt(6);
            y = rand.nextInt(7);

            while (laGrille.Cellules[x][y].presenceTrouNoir() || !laGrille.placerDesintegrateur(x, y)) {
                x = rand.nextInt(6);
                y = rand.nextInt(7);
            }

        }
    }

    public void debuterPartie() {

        attribuerCouleursAuxJoueurs();//on commence la partie par attribue les couleurs aux joueurs
        initialiserPartie();
 System.out.println("couleur du joueur 1 : "+ ListeJoueurs[0].Nom); 
        Scanner sc;
        int ligne;
        int colonne;
        boolean end = false;
        int ij = 0;
        
        do {
            laGrille.afficherGrilleSurConsole();
            sc = new Scanner(System.in);
            System.out.println("1: Jouer 2: Desintegrateur 3: recuperer un jeton ");//un petit menu qui s'affiche a chaque tour
            int choix = sc.nextInt();

            switch (choix) {
                case 1://cas ou n veut jouer un jeton
                    sc = new Scanner(System.in);
                    System.out.println("chosir colonne");//on choisi la colonne pour jouer
                    colonne = sc.nextInt();
                    laGrille.ajouterJetonDansColonne(new Joueur(joueurCourant.lireCouleur()), colonne);//le jeton est ajouté dans la colonne chpoisie
                    joueurCourant.supprimerJeton();//on enleve un jeton au joueur
                    joueurCourant.obtenirDesintegrateur();
                    break;
                case 2:
                    sc = new Scanner(System.in);//cas pour poser un desintegrateur sur la grille
                    System.out.println("chosir colonne");//on choisit la colonne
                    colonne = sc.nextInt();
                    sc = new Scanner(System.in);
                    System.out.println("chosir ligne");//on choisit la ligne
                    ligne = sc.nextInt();
                    laGrille.placerDesintegrateur(ligne, colonne);//on place le désintegrateur

                    break;

                case 3:
                    sc = new Scanner(System.in);//cas pour enlever un jeton
                    System.out.println("chosir colonne");//on choisit le jeton avec sa ligne et sa colonne
                    colonne = sc.nextInt();
                    sc = new Scanner(System.in);
                    System.out.println("chosir ligne");
                    ligne = sc.nextInt();
                    Jeton leJeton = laGrille.recupererJeton(ligne, colonne);//on récupere le jeton
                    joueurCourant.ajouterJeton(leJeton);//on le rend au joueur
                    
                 
                    break;

            }

            end = laGrille.etreGagnantPourJoueur(joueurCourant);//si il y a un gagnant, fin de la partie
            ij = (ij + 1) % 2;
            joueurCourant = ListeJoueurs[ij];
        } while (!end);
        laGrille.afficherGrilleSurConsole();
    }
}
