import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;


/*
 *  NumberListener met à jour l'état des valeurs (int) dans un GridObject
 *  ainsi que la validité (boolean) de ces valeurs.
 *
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
            if(!isOk(Integer.parseInt(g.getText())))
                grid.setValid(i,j,false);
            else
                grid.setValid(i,j,true);
        
            grid.setValue(i,j,Integer.parseInt(g.getText()));
        }
        catch(StringIndexOutOfBoundsException e)
        {}
    }
    
    public boolean isOk(int n)
    {
        for(int k=0;k<9;k++)
                if((grid.getValue(k,j)==n)||(grid.getValue(i,k)==n))
                    return false;
        
        int w,m;
        int wi,mj;
        
        if(i%3==0) {w=i;}
        else if(i%3==3) {w=i-1;}
        else {w=i-2;}
        
        if(j%3==0) {m=j;}
        else if(j%3==3) {m=j-1;}
        else {m=j-2;}
        
        for(wi=w+2;wi>w;wi--)
        {
            for(mj=m+2;mj>m;mj--)
            {
                if(n==grid.getValue(wi,mj))
                    return false;
            }
        }
        
        
        return true;
    }
    
}

