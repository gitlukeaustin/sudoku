import javax.swing.*;

/**
 *  Rassemble tout les informations concernant une grille.
 *
 *  @version 1.0
 *  @auteur Luke Austin
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
        
        ButtonListener.emptyGrid(this);
    }

    /*
     *  Retourn une valeur dans la table de valeurs.
     */
    public int getValue(int i, int j)
    {
        return this.tab[i][j];
    }
    
    /*
     *  Affecte une valeur de la table.
     */
    public void setValue(int i, int j, int value)
    {
        
        if(!this.isOK(i,j,value))
            this.setValid(i,j,false);
        else
            this.setValid(i,j,true);
        
        this.tab[i][j] = value;
        
        if(value>9999)
        {
            this.tab[i][j]=0;
            this.setValid(i,j,false);
        }
    }
    
    /*
     *  Affecte si une valeur peut être cliqué ou non.
     */
    public void setClickable(int i, int j, boolean clickable)
    {
        this.clickable[i][j] = clickable;
    }
    
    /*
     *  Indique si une valeur peut être cliqué
     *  Si une case ne peut pas être cliqué NumberListener l'ignore
     *  et Grid dessine le chiffre correspondant en gras.
     */
    public boolean isClickable(int i, int j)
    {
        return this.clickable[i][j];
    }
    
    /*
     *  Change la validité d'un élement.
     *  Si une valeur n'est pas valide Grid dessine le chiffre en ruge
     *  , la fonction getAnswer dans ButtonListener traite la réponse comme fausse,
     *  et la valeur ne sera pas sauvegardé.
     */
    public void setValid(int i, int j, boolean valid)
    {
        this.valid[i][j] = valid;
    }
    /*
     *  Indique si une valeur est valide
     */
    public boolean isValid(int i, int j)
    {
        return this.valid[i][j];
    }
    
    /*
     *  Initialise un JTextField
     */
    public void setTextField(int i, int j)
    {
        this.textarray[i][j] = new JTextField();
        this.textarray[i][j].setVisible(false);
        this.textarray[i][j].addActionListener(new NumberListener(this,i,j));

    }
    
    /*
     *  Renvoie un JTextField
     */
    public JTextField getTextField(int i, int j)
    {
        return textarray[i][j];
    }
    
    /*
     *  Détermine si une valeur est valide.
     */
    public boolean isOK(int i, int j, int n)
    {
        int occurence = 0;
        
        //vérification des colonnes et des lignes.
        for(int k=0;k<9;k++)
        {
            if((getValue(i,k)==n)||(getValue(k,j)==n))
                occurence++;
        }
        
        if(getValue(i,j)==n)
            occurence -= 2;
        
        if(occurence>0)
            return false;
        
        //vérification des champs de cases
        int ii,jj;
        int wi,mj;
        
        ii = i-(i%3);
        
        jj = j-(j%3);
        
        for(wi=ii+2;wi>=ii;wi--)
        {
            for(mj=jj+2;mj>=jj;mj--)
            {
                if(n==getValue(wi,mj))
                    if(!((wi==i)&&(mj==j)))
                        return false;
            }
        }
        
        if((n>9)||(n<=0))
            return false;
        
        return true;
    }
    
    
    /*
     *  Vérifie qu'une grille est completé
     */
    public boolean checkVictory()
    {
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                if(!isValid(i,j))
                    return false;
            }
        
        return true;
    }
}