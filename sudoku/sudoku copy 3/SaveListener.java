import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.io.*;
import javax.swing.*;

public class SaveListener implements MouseListener
{
    private JButton b;
    private int tab[][];
    private static boolean valid[][];
    private boolean save;
    
    public SaveListener(JButton b,int[][] tab)
    {
        this.tab = tab;
        this.b = b;
        this.save = true;
        this.valid = new boolean[9][9];
    }
    
    public SaveListener(JPanel p)
    {
        this.p = p;
        this.save = false;
        this.valid = new boolean[9][9];
    }
    
    public void mouseClicked(MouseEvent e)
    {
        
        if(!vide)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(b);
            
            int number;
            
            try
            {
                FileOutputStream file = new FileOutputStream(chooser.getSelectedFile());
                DataOutputStream data = new DataOutputStream(file);
            
                for(int i=0;i<9;i++)
                {
                    
                        for(int j=8;j>=0;j++)
                        {
                            tab[i][j] = number%10;
                            
                            
                            
                            number/=10;
                        }
                    }
                    catch(IOException io)
                    {
                            System.err.println("problème de lecture du fichier");

                    }
                }
            }
            catch(FileNotFoundException fe)
            {
                System.err.println("problème: fichier non trouvé");
            }
        }
        else
        {
            for(int k=0;k<9;k++)
                for(int n=0;n<9;n++)
                    valid[k][n]=true;
        }
        b.getParent().setVisible(false);
        b.setVisible(false);
    }
    
    public static boolean isClickable(int i, int j)
    {
        return valid[i][j];
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

