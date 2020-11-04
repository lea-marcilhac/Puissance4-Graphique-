
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
    
    public CelluleGraphique (Cellule uneCellule) {
        celluleAssociee = uneCellule;
        
    }
    
   @Override
    public void paintComponent (Graphics G) {
        super.paintComponent(G);
        
        setIcon(img_vide);
    }
}
