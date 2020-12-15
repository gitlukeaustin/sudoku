import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 *  Observe les JButtons du programme.
 *  Sert à ouvrir/sauvegarder/effacer/résoudre une grille.
 *
 *  @version 1.0
 *  @auteur Luke Austin
 */
public class ButtonListener implements ActionListener
{
    private GridObject grid;
    private PaintGrid gridpaint;
    private boolean editmode;
    
    public ButtonListener(GridObject g, PaintGrid p)
    {
        this.grid = g;
        this.gridpaint = p;
    }
    
    public ButtonListener(GridObject g, PaintGrid p, boolean mode)
    {
        this.grid = g;
        this.gridpaint = p;
        this.editmode = mode;
    }
    
    /**
     *  Détecte l'action sur un boutton et lance une fonction en fonction du nom de la commande.
     *
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "Sauvegarder")
            saveFile(grid);
        else if(e.getActionCommand() == "Ouvrir")
        {
            emptyGrid(grid);
            openFile(grid);
        }
        else if(e.getActionCommand() == "Effacer")
            emptyGrid(grid);
        else if(e.getActionCommand() == "Resoudre")
            solveGrid(grid);
        
        gridpaint.repaint();
    }
    
    
    /*
     *  Sauvegarde une grille dans un fichier
     *
     */
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
                    if(grid.isValid(i,j))   // les valeurs incorrectes ne sont pas sauvegardés.
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
        catch(NullPointerException npe)
        {}

    }
    
    /*
     *  Ouvre un fichier et remplit une grille de son contenu.
     *
     */
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
                    //System.out.println(""+line);
                    position=100000000;
                    
                    
                    for(int j=0;j<9;j++)
                    {
                        valeur = line/position;
                        
                        if((valeur%10!=0)&&(!editmode))
                            grid.setClickable(i,j,false);
                        
                        grid.setValue(i,j,valeur%10);
                        
                        position /= 10;
                    }
                }
                catch(IOException e)
                {
                    System.out.println("problème");
                }
            }
        }
        catch(FileNotFoundException f)
        {}
        catch(NullPointerException npe)
        {}
        
    }
    
    /**
     *  Remplit une grille de valeurs vides
     *
     */
    public static void emptyGrid(GridObject grid)
    {
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                grid.setValue(i,j,0);
                grid.setClickable(i,j,true);
            }
         
    }
    
    
    /**
     *  Fait appel a une fonction de resolution et affiche le temps de resolution d'une grille.
     *
     */
    public void solveGrid(GridObject grid)
    {
        int numero=0;
        int tries=80;
        long depart = System.nanoTime();
        do
        {
            solveArea(grid);
            tries--;
        }while((!grid.checkVictory())&&(tries!=0));
        long arrivee = System.nanoTime();
        long temps = arrivee-depart;
        
        if(tries==0)
            JOptionPane.showMessageDialog(grid.getTextField(0,0).getParent(), "Résolution Echoué");
        else
            JOptionPane.showMessageDialog(grid.getTextField(0,0).getParent(), "Résolu en "+temps+" nanosecondes");
    }
    
    /**
     *  Résout en partie une grille sudoku.
     *
     */
    public void solveArea(GridObject grid)
    {
        int possible=0;
        int x=0;
        int y=0;
        
        for(int n=1;n<=9;n++)
        {
            for(int i=0;i<9;i+=3)
            {
                for(int j=0;j<9;j+=3)
                {
                    possible=0;
                    for(int ini=i+2;ini>=i;ini--)
                    {
                        for(int inj=j+2;inj>=j;inj--)
                        {
                            if(!(grid.isValid(ini,inj)))
                            {
                                grid.setValue(ini,inj,0);
                                grid.setValue(ini,inj,n);
                                if(grid.isValid(ini,inj))
                                {
                                    x=ini;
                                    y=inj;
                                    possible++;
                                }
                                grid.setValue(ini,inj,0);
                            }
                        }
                    }
                    if(possible==1) // Si il n'y a qu'une seul possibilité c'est surement la solution.
                        grid.setValue(x,y,n);
                }
            }
        }
    }
   
    
}