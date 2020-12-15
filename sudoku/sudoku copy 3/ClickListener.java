import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;

/*
 *
 *  Click listener fait apparaître le JTextField correspondant au lieu cliqué.
 *
 */
public class ClickListener implements MouseListener
{
    private GridObject grid;
    
    public ClickListener(GridObject grid)
    {
        this.grid = grid;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        
        int i = ClickListener.getCoordonate(e.getY(),grid.getTextField(0,0).getParent().getHeight());
        int j = ClickListener.getCoordonate(e.getX(),grid.getTextField(0,0).getParent().getHeight());
        
        if(grid.isClickable(i,j))
        {
            grid.getTextField(i,j).setVisible(true);
            grid.getTextField(i,j).requestFocus();
        }
    }
    
    
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

