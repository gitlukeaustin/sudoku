import javax.swing.*;
import java.awt.*;

/**
 *
 *  Dessine une grille ainsi que son contenu.
 *
 *  @version 1.0
 *  @auteur Luke Austin
 */
public class PaintGrid extends JComponent
{
    private GridObject grid;
    private JPanel panel;
    
    public PaintGrid(GridObject grid, JPanel panel)
    {
        super();
        this.grid = grid;
        this.panel = panel;
    }
    
    @Override
    public void paintComponent(Graphics pinceau)
    {
       
       
        int x= panel.getWidth();
        int y= panel.getHeight();
        int xunit=x/9;
        int yunit=y/9;
        
        // traçage de la grille
        
        pinceau.setColor(Color.WHITE);
        pinceau.fillRect(0,0,x,y);
        pinceau.setColor(Color.BLACK);
        pinceau.drawRect(0,0,x-2,y-2);
        pinceau.drawRect(1,1,x-2,y-2);

        for(int i=1;i<9;i++)
        {
            pinceau.drawLine(x-xunit*i,0,x-xunit*i,y);
            pinceau.drawLine(0,y-yunit*i,x,y-yunit*i);
            
            if(i%3==0)
            {
                pinceau.drawLine((x-xunit*i)-1,0,(x-xunit*i)-1,y);
                pinceau.drawLine(0,(y-yunit*i)-1,x,(y-yunit*i)-1);
                
                pinceau.drawLine((x-xunit*i)+1,0,(x-xunit*i)+1,y);
                pinceau.drawLine(0,(y-yunit*i)+1,x,(y-yunit*i)+1);
            }
        }
        
        
        // traçage des chiffres
        
        Font big = new Font("Arial", Font.PLAIN, 25);
        Font small = new Font("Arial", Font.PLAIN, 13);
        
        Font bigbold = new Font("Arial", Font.BOLD, 25);
        Font smallbold = new Font("Arial", Font.BOLD, 13);
        
        int value;
        
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                value=grid.getValue(i,j);
                if((value>9)||(x<300))
                {
                    if(grid.isClickable(i,j))
                        pinceau.setFont(small);
                    else
                        pinceau.setFont(smallbold);

                }
                else
                {
                    if(grid.isClickable(i,j))
                        pinceau.setFont(big);
                    else
                        pinceau.setFont(bigbold);
                }
                
                if(!grid.isValid(i,j))
                    pinceau.setColor(Color.RED);
                else
                    pinceau.setColor(Color.BLACK);
                
                if(value!=0)
                    pinceau.drawString(value+"",xunit*j+xunit/2-5,yunit*i+yunit/2+yunit/4);
                

                
            }
        }
    }
    

}