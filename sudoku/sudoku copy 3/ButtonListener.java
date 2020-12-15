import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/*
 *  ButtonListener observe les JButtons du programme.
 *  Il sert à ouvrir/sauvegarder/effacer une grille.
 *
 */
public class ButtonListener implements ActionListener
{
    private GridObject grid;
    private Grid gridpaint;
    
    public ButtonListener(GridObject g, Grid p)
    {
        this.grid = g;
        this.gridpaint = p;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Sauvegarder")
            saveFile(grid);
        else if(e.getActionCommand() == "Ouvrir")
            openFile(grid);
        else if(e.getActionCommand() == "Vide")
            emptyGrid(grid);
        
        gridpaint.repaint();
    }
    
    public void saveFile(GridObject grid)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(grid.getTextField(0,0).getParent());
        
        try
        {
            FileOutputStream file = new FileOutputStream(chooser.getSelectedFile());
            DataOutputStream data = new DataOutputStream(file);
            
            int line;
            int position;
            
            for(int i=0;i<9;i++)
            {
                line=0;
                position = 100000000;
                
                for(int j=0;j<9;j++)
                {
                    if(grid.isValid(i,j))
                        line += position*grid.getValue(i,j);
                
                    position /= 10;
                }
                try
                {
                    data.writeInt(line);
                }
                catch(IOException e)
                {}
            }
        }
        catch(FileNotFoundException f)
        {}

    }
    public void openFile(GridObject grid)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(grid.getTextField(0,0).getParent());
        
        try
        {
            FileInputStream file = new FileInputStream(chooser.getSelectedFile());
            DataInputStream data = new DataInputStream(file);
            
            int line;
            int position;
            int valeur;
            
            for(int i=0;i<9;i++)
            {
                try
                {
                    line=data.readInt();
                    position=100000000;
                    
                    
                    for(int j=0;j<9;j++)
                    {
                        valeur = line/position;
                        
                        if(valeur%10!=0)
                            grid.setClickable(i,j,false);
                        
                        grid.setValue(i,j,valeur%10);
                        
                        position /= 10;
                    }
                }
                catch(IOException e)
                {}
            }
        }
        catch(FileNotFoundException f)
        {}
        
    }
    /*
     *  Remplit une grille de valeurs vides
     *
     */
    public void emptyGrid(GridObject grid)
    {
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                grid.setValue(i,j,0);
                grid.setClickable(i,j,true);
            }
         
    }
}