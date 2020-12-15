import java.awt.*;
import javax.swing.*;


/**
 *  Ouvre une fênetre.
 *
 *  @version 1.0
 *  @auteur Luke Austin
 */
public class Fenetre
{
    public Fenetre(String nomboutton)
    {
        int buttoncount=2;
        
        GridObject grid = new GridObject();
        
        JFrame window = new JFrame();
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initialisation de la grille (conteneur de l'objet PaintGrid)
        JPanel gridpanel = new JPanel();
        PaintGrid gridpaint = new PaintGrid(grid,gridpanel);
        gridpanel.setLayout(new BorderLayout());
        gridpanel.add(gridpaint);

        //initialisation des bouttons
        JButton ouvrir = new JButton("Ouvrir");
        boolean mode=false;
        if(nomboutton=="Sauvegarder")
        {
            mode=true;
            buttoncount++;
        }
        ouvrir.addActionListener(new ButtonListener(grid,gridpaint,mode));
        
        JButton saveorsolve = new JButton(nomboutton);
        saveorsolve.addActionListener(new ButtonListener(grid,gridpaint));
        
        JPanel choix = new JPanel();
        choix.setLayout(new GridLayout(1,buttoncount));
        choix.add(ouvrir);
        choix.add(saveorsolve);
        
        //initialisation des champs texte
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
        inputpanel.addMouseListener(new ClickListener(grid,inputpanel));
        
        JPanel group = new JPanel();
        OverlayLayout ov = new OverlayLayout(group);
        group.setLayout(ov);
        
        group.add(inputpanel);
        group.add(gridpanel);
        
        window.add(group,BorderLayout.CENTER);
        window.add(choix,BorderLayout.NORTH);
                
        //initialisation du boutton Vide
        if(nomboutton == "Sauvegarder")
        {
            JButton empty = new JButton("Effacer");
            empty.addActionListener(new ButtonListener(grid,gridpaint));
            choix.add(empty);
        }
        
        window.setSize(550,600);

        window.setVisible(true);
    }
}