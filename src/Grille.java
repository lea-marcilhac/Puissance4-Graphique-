/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarcilh
 */
public class Grille {

    Cellule Cellules[][] = new Cellule[6][7];
     public Grille () {
        for (int i=0; i<6;i++){
            for (int j =0; j<7; j++){
                Cellules[i][j] =new Cellule();
            }
        }
    }

    public boolean ajouterJetonDansColonne(Joueur unJoueur, int numColonne) {//méthode pour ajouter un jeton dans une colonne
         Jeton unJeton = unJoueur.ListeJetons[unJoueur.nombreJetonsrestantts - 1];
        unJoueur.nombreJetonsrestantts--;
        
        int i = 0;
        while (i < 6 && Cellules[i][numColonne].jetonCourant == null) {//on vérifie 
            i++;

        }
        if (i==0){
            return false;
        }
        
       
         Cellules[i - 1][numColonne].affecterJeton(unJeton);
        
            if (Cellules[i - 1][numColonne].presenceTrouNoir()) {
                Cellules[i - 1][numColonne].activerTrouNoir();
            } 

               if (Cellules[i-1][numColonne].presenceDesintegrateur()) {
            Cellules[i-1][numColonne].recupererDesintegrateur();
            unJoueur.nombreDesintegrateurs++;
            
           
        }
            
            return true;
        }
        

    public boolean etreRemplie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].jetonCourant == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void viderGrille() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Cellules[i][j] = new Cellule();
            }
        }
    }

    public void afficherGrilleSurConsole() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Cellules[i][j].recuperJeton() == null) {
                    if (Cellules[i][j].presenceTrouNoir()) {
                        System.out.print("N ");    // La presence d'un trou noir est representé par un N
                    } else if (Cellules[i][j].presenceDesintegrateur()) {
                        System.out.print("D ");   // La presence d'un desintegrateur est representé par un D
                    } else {
                        System.out.print("X "); // une cellule vide est representé par un X
                    }
                } else {
                    System.out.print(Cellules[i][j].lireCouleurDuJeton() + " ");
                }
            }
            System.out.println("");
        }
    }

    public boolean celluleOccupee(int numLigne, int numColonne) {
        if (Cellules[numLigne][numColonne].jetonCourant == null) {
            return false;
        }
        return true;
    }

    public String lireCouleurDuJeton(int numLigne, int numColonne) {
        return Cellules[numLigne][numColonne].lireCouleurDuJeton();
    }

    public boolean ligneValide(String couleur, int numLigne) {
        int a = 0;

        for (int i = 0; i < 7; i++) {
            if (celluleOccupee(numLigne, i) && lireCouleurDuJeton(numLigne, i).equals(couleur)) {
                a++;
                for (int j = i + 1; j < 7 && j < i + 4; j++) {
                    if (celluleOccupee(numLigne, j) && lireCouleurDuJeton(numLigne, j).equals(couleur)) {
                        a++;
                    }
                }

                if (a == 4) {
                    return true;
                }
                a = 0;
            }
        }
        return false;

    }

    public boolean colonneValide(String couleur, int numColonne) {
        int a = 0;  // a est notre conteur de jetons de la meme couleur
        for (int i = 0; i < 6; i++) { //On parcours nos 7 colonnes afin de regarder si 4 jetons de meme couleurs sont alignées
            if (celluleOccupee(i, numColonne) && lireCouleurDuJeton(i, numColonne).equals(couleur)) {
                a++;
                for (int j = i + 1; j < 6 && j < i + 4; j++) {
                    if (celluleOccupee(j, numColonne) && lireCouleurDuJeton(j, numColonne).equals(couleur)) {
                        a++;
                    }

                }
                if (a == 4) {
                    return true;
                }
                a = 0;
            }
        }
        return false;
    }

    public boolean diagonaleValide(String couleur, int numColonne, int numLigne) {
        int a = 0;// a est notre conteur de jetons de la meme couleur

        for (int i = 0; i < 7; i++) {//on fait 7 boucles pour faire toute la ligne
            for (int j = 0; i < 6; j++) {
                if (celluleOccupee(numLigne, numColonne) && lireCouleurDuJeton(numLigne, numColonne).equals(couleur)) {//on se place à l'endroit ou on est
                    a++;//on incrémente le compteur de jetons                                                             //pour récuperer la couleur du jeton
                    for (int x = j + 1; x < 6 && x < j + 4; x++) {
                        for (int y = i + 1; y < 7 && y < i + 4; y++) {
                            if (celluleOccupee(x, y) && lireCouleurDuJeton(x, y).equals(couleur)) {//on compare la oculeur du jeton d'avant a celle de celui de la 
                                a++;//on incrémente si c'est le cas                                //la case d'à coté
                            }//des que ce n'est plus le cas on sort de la boucle et on continue la partie
                        }
                    }
                    if (a == 4) {//si le compteur atteint 4, on renvoie true, pour la suite 
                        return true;
                    }
                    a = 0;
                }

            }
        }
        return false;
    }

    public boolean etreGagnantPourJoueur(Joueur unJoueur) {  //
        String couleur = unJoueur.lireCouleur();
        for (int i = 0; i < 6; i++) {
            if (ligneValide(couleur, i)) {
                return true;
            }

        }
        for (int i = 0; i < 7; i++) {
            if (colonneValide(couleur, i)) {
                return true;
            }
        }
        //for (int i = 0; i < 7; i++) {
            //for (int j = 0; i < 6; j++) {
                //if (diagonaleValide(couleur, i, j)) {
                   // return true;
                //}
            //}
        //}
        return false;
    }



    

    public void tasserGrille(int uneColonne) {
        int i = 5;//on se place en haut d'une colonne

        while (i > 0 && celluleOccupee(i, uneColonne)) {//et on descend progressivement
            i--;

        }

        if (i != 0) {
            Jeton unJeton;
            for (int j = i; j > 0; j--) {
                unJeton = Cellules[j - 1][uneColonne].recuperJeton();
                if (unJeton == null) {
                    Cellules[j][uneColonne].supprimerJeton();
                    Cellules[j][uneColonne].affecterJeton(null);
                } else {
                   Cellules[j][uneColonne].affecterJeton(unJeton);
                }
            }
            Cellules[0][uneColonne].affecterJeton(null);
        }
    }

    public boolean colonneRemplie(int uneColonne) {//on se place dans une colonne
        for (int i = 0; i < 6; i++) {
            if (!celluleOccupee(i, uneColonne)) {//on vérifie si une ligne de cette colonne n'est pas occupée
                return false;
            }

        }
        return true;
    }

    public boolean placerTrouNoir(int numLigne, int numColonne) {
        if (!Cellules[numLigne][numColonne].trouNoir) {
            Cellules[numLigne][numColonne].trouNoir = true;
            return true;
        }
        return false;
    }

    public boolean placerDesintegrateur(int numLigne, int numColonne) {
        if (!Cellules[numLigne][numColonne].desintegrateur) {
            Cellules[numLigne][numColonne].desintegrateur = true;
            return true;
        }
        return false;
    }

    public boolean supprimerJeton(int numLigne, int numColonne) {
        if (Cellules[numLigne][numColonne].supprimerJeton()) {
            return true;

        }
        return false;
    }

    public Jeton recupererJeton(int numLigne, int numColonne) {
        Jeton unJeton = Cellules[numLigne][numColonne].recuperJeton();
        if (unJeton != null) {//si le jeton existe
            Cellules[numLigne][numColonne].supprimerJeton();//on l'enleve de la grille

        }
        return unJeton;//et on le rend a son prepriétaire
    }
}
