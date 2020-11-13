/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lmarcilh
 */
public class Cellule {//on initialise la classe
    Jeton jetonCourant;//le jeton qui va interagir avec le trou noir ou le desintegrateur, donc le jeton qui va etre joué
    boolean trouNoir;
    boolean desintegrateur;
    
    Cellule(){//on initialise les différents objets
        jetonCourant=null;
        trouNoir=false;
        desintegrateur=false;
    }
   public boolean affecterJeton(Jeton unJeton){
       if (jetonCourant==null){//si aucun jeton n'est acutellement selectionné, on en selectionne un
           jetonCourant=unJeton;
           return true;
       }
       else {
           return false;//sinon on fait rien
       }
   }
   
   public Jeton recuperJeton(){
       return jetonCourant;
   }
    //public Jeton recupererJeton(Jeton unJeton){
        public boolean supprimerJeton(){
        if (jetonCourant!=null){// si on a un jeton sélectionné, on le supprime de jetonCourant
            jetonCourant=null;
            return true;
            
        }
        return false;//sinon on ne fait rien
        
        
    }  
        
        public boolean placerTrouNoir(){
            if (!trouNoir){    // si trounoir false
                trouNoir=true;
                return true;
            }
                return false;
        }
   public boolean placerDesintegrateur(){
            if (!desintegrateur){    // si desintegrateur false
                desintegrateur=true;
                return true;
            }
                return false;
   }
   
   //liste de méthode pour récuperer des informations sur les trous noirs, les désintegrateur et la couleur du jeton courant
   public boolean presenceTrouNoir(){
       return trouNoir;
   }
  public boolean presenceDesintegrateur(){
       return desintegrateur;
   }
  
  public String lireCouleurDuJeton(){
      if(jetonCourant==null){
          return "vide";
      }
      return jetonCourant.lireCouleur();
  }
  
  
  public boolean recupererDesintegrateur(){
      if (presenceDesintegrateur()){//si il y a un désintegrateur
          desintegrateur=false;//on l'enlève
          return true;//on confirme que le désintegrateur a ete supprimé
      }
      return false;//sinon on retourne false pour dire qu'il s'est rien passé
  }
  public boolean activerTrouNoir(){
      if (!trouNoir){// si il n'y a pas de trou noir
          return false;//on fait rien
      }
     jetonCourant=null;//sinon, on supprime le jeton courant
     trouNoir=false;//et on enleve le trou noir
     return true;//on confirme que le trou noir a été activé
  }
     
      }
  

