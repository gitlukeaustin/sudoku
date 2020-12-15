import java.awt.event.*;
import javax.swing.*;


/**
 *  Met à jour l'état des valeurs (int) dans un GridObject.
 *  L'Observateur est déclenché par la touche 'entrer'.
 *
 *  @version 1.0
 *  @auteur Luke Austin
 */
public class NumberListener implements ActionListener
{
    private GridObject grid;
    private int i;
    private int j;
    
    public NumberListener(GridObject g, int i, int j)
    {
        this.grid= g;
        this.i = i;
        this.j = j;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JTextField g = (JTextField) e.getSource();
        g.setVisible(false);
        
        try
        {
            grid.setValue(i,j,Integer.parseInt(g.getText()));
        }
        catch(StringIndexOutOfBoundsException se)
        {}
        catch(NumberFormatException ne)
        {
            grid.setValue(i,j,0);
        }
        
        if(grid.checkVictory())
        {
            JOptionPane.showMessageDialog(grid.getTextField(0,0).getParent(), "Bravo!");
        }
    }
}
