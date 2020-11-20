/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarcilh
 */
public class Joueur {
    String Nom;
    String Couleur;
    Jeton ListeJetons []= new Jeton[21];
    int nombreDesintegrateurs=0;
    int nombreJetonsrestantts=0; 
    
    
    public Joueur (String unNom){
        Nom=unNom;
    }
    public String lireCouleur(){
        return Couleur;
    }
    
    public void affecterCouleur(String uneCouleur){
        
        Couleur=uneCouleur;
       
    }
    public void ajouterJeton(Jeton unJeton){
     int i=0;
     while (ListeJetons[i]!=null){
         i++;
     }
     nombreJetonsrestantts++;
      ListeJetons[i]=unJeton;
    }
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs++;
    }
    
    public boolean utiliserDesintegrateur(){
        if(nombreDesintegrateurs<=0){
            return false;
            
        }
        nombreDesintegrateurs--;
        return true;
    }
    public void supprimerJeton(){
        int i=0;
     while (ListeJetons[i]==null){
         i++;
     }
    
      ListeJetons[i]=null;
    }
    
}
