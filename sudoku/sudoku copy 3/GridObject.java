import javax.swing.*;
import java.awt.*;


/*
 *
 * Un GridObject rassemble tout les informations concernant une grille.
 *
 *
 */
public class GridObject
{
    private int tab[][];
    private JTextField textarray[][];
    private boolean valid[][];
    private boolean clickable[][];
    
    public GridObject()
    {
        this.tab = new int[9][9];
        this.textarray = new JTextField[9][9];
        this.valid = new boolean[9][9];
        this.clickable = new boolean[9][9];
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                valid[i][j]=true;
                clickable[i][j]=true;
            }
    }


    public int getValue(int i, int j)
    {
        return this.tab[i][j];
    }
    
    public void setValue(int i, int j, int value)
    {
        this.tab[i][j] = value;
        
        if((value>9)||(value<0))
            this.setValid(i,j,false);
    }
    
    
    public void setClickable(int i, int j, boolean clickable)
    {
        this.clickable[i][j] = clickable;
    }
    
    public boolean isClickable(int i, int j)
    {
        return this.clickable[i][j];
    }
    
    
    public void setValid(int i, int j, boolean valid)
    {
        this.valid[i][j] = valid;
    }
    public boolean isValid(int i, int j)
    {
        return this.valid[i][j];
    }
    
    public void setTextField(int i, int j)
    {
        this.textarray[i][j] = new JTextField();
        this.textarray[i][j].setVisible(false);
        this.textarray[i][j].addActionListener(new NumberListener(this,i,j));

    }
    
    public JTextField getTextField(int i, int j)
    {
        return textarray[i][j];
    }
}