import java.awt.event.*;
import javax.swing.*;

/**
 *
 *  Fait apparaître le JTextField correspondant au lieu cliqué.
 *
 *  @version 1.0
 *  @auteur Luke Austin
 */
public class ClickListener implements MouseListener
{
    private GridObject grid;
    private JPanel panel;
    
    public ClickListener(GridObject grid, JPanel panel)
    {
        this.grid = grid;
        this.panel = panel;
    }
    
    /*
     *  Détecte une clique sur la grille et rend la case correspondante visible.
     *
     */
    public void mouseClicked(MouseEvent e)
    {
        
        int j = ClickListener.getCoordonate(e.getX(),panel.getWidth());
        int i = ClickListener.getCoordonate(e.getY(),panel.getHeight());
        
        if(grid.isClickable(i,j))
        {
            grid.getTextField(i,j).setVisible(true);
            grid.getTextField(i,j).requestFocus();
        }
    }
    
    /*
     *  Renvoie la coordonnée du tableau correspondant aux coordonnées de la souris.
     *
     */
    public static int getCoordonate(int coordonate, int dimension)
    {
        int x = 0;
        int unit = dimension/9;
        
        for(int i=0;i<9;i++)
        {
            if((i*unit<coordonate)&&(coordonate<i*unit+unit))
               x=i;
        }
        
        return x;
    }
    

    
    public void mouseEntered(MouseEvent evenement)
    {}// debut du survol
    public void mouseExited(MouseEvent evenement)
    {}// fin du survol
    public void mousePressed(MouseEvent evenement)
    {}// un bouton appuyé
    public void mouseReleased(MouseEvent evenement)
    {}
}

