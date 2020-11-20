
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marci
 */
public class CelluleGraphique extends JButton {

    Cellule celluleAssociee;
    ImageIcon img_vide = new javax.swing.ImageIcon(getClass().getResource("/Images/cellulevide.png"));
    ImageIcon img_desintegrateur = new javax.swing.ImageIcon(getClass().getResource("/Images/desintegrateur.png"));
    ImageIcon img_JetonJaune = new javax.swing.ImageIcon(getClass().getResource("/Images/JetonJaune.png"));
    ImageIcon img_JetonRouge = new javax.swing.ImageIcon(getClass().getResource("/Images/JetonRouge.png"));
    ImageIcon img_trouNoir = new javax.swing.ImageIcon(getClass().getResource("/Images/trouNoir.png"));

    public CelluleGraphique(Cellule uneCellule) {
        celluleAssociee = uneCellule;
        System.out.println("creation de la cellule avec " + celluleAssociee);
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);

        if (celluleAssociee.presenceTrouNoir() == true) {
            setIcon(img_trouNoir);
        }
        
        else if (celluleAssociee.presenceDesintegrateur() == true) {
                setIcon(img_desintegrateur);
            } else {

                String couleur_jeton = celluleAssociee.lireCouleurDuJeton();
                switch (couleur_jeton) {
                    case "vide":
                        setIcon(img_vide);
                        break;
                    case "Rouge":
                        setIcon(img_JetonRouge);
                        break;
                    case "Jaune":
                        setIcon(img_JetonJaune);
                        break;
                }
            }

        }
    }

