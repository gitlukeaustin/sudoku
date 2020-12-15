import java.awt.*;
import javax.swing.*;

/*
 *  Fields est la classe principale.
 *
 *
 */
public class Fields
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setSize(625,600);
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridObject grid = new GridObject();
        
        Grid gridpaint = new Grid(grid);
        
        
        JButton ouvrir = new JButton("Ouvrir");
        ouvrir.addActionListener(new ButtonListener(grid,gridpaint));
        
        JButton sauvegarder = new JButton("Sauvegarder");
        sauvegarder.addActionListener(new ButtonListener(grid,gridpaint));
        
        JButton empty = new JButton("Vide");
        empty.addActionListener(new ButtonListener(grid,gridpaint));
        
        JPanel choix = new JPanel();
        choix.setLayout(new GridLayout(1,2));
        choix.add(ouvrir);
        choix.add(sauvegarder);
        
        
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(9,9,12,12));
        
        inputpanel.setOpaque(false);
      
        
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                grid.setTextField(i,j);
                inputpanel.add(grid.getTextField(i,j));
            }
        }
        
        inputpanel.addMouseListener(new ClickListener(grid));
      
        
        JPanel gridpanel = new JPanel();
        gridpanel.setLayout(new BorderLayout());
        gridpanel.add(gridpaint);
        
        
        JPanel group = new JPanel();
        OverlayLayout ov = new OverlayLayout(group);
        group.setLayout(ov);
        
        group.add(inputpanel);
        group.add(gridpanel);
        
        window.add(group,BorderLayout.CENTER);
        
        window.add(empty,BorderLayout.EAST);
        window.add(choix,BorderLayout.NORTH);
        
        window.setVisible(true);
    }
    
}

