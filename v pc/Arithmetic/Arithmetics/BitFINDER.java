package Arithmetics ;
public class BitFINDER extends Composant{
    private int n;
    private int index = -1;
    
    public BitFINDER(String id,int n,int index, int x, int y)
    {
        super(id,x,y);
        this.n = n;
        this.index=index;
        /*une seule entree mais de taille n genre c'est un mot memoire */
        for(int i=0;i<n;i++)
        {
            this.addInput(State.UNKNOWN);
        }
        /* une sortie de taille 1 bit*/
        this.addOutput(State.UNKNOWN);
    }

    @Override
    public void evaluate()
    {
        if (this.inputs.size() == n && this.outputs.size() == 1)
        {
            if(this.index> -1 && this.index <n)
            {
                /*renvoyer l'a valeur du bit à la position introduite */
                this.outputs.set(0,this.inputs.get(this.index));
                this.setstate(this.getOutput(0));
            }
            else
            {
                throw new IllegalStateException("BitFINDER : Faut mettre une valeur dans l'attribut i du composent supérieur a -1 inférieur a n! car : new BiteFINDER(id,n,index,x,y)");
            }
            
        }
        else
        {
            throw new IllegalStateException("BitFINDER : doit avoir une seule entree de taille n 'n bits' donc n entree , et une seule sortie de 1 bit.");
        }
    }
}