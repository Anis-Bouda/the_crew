class DEMUX extends Composant{

    public DEMUX (String id)
    {
        super(id,3,4);
    }

    public void evaluate()
    {
        if (inputs.length < 3|| outputs.length < 4) {
            throw new IllegalStateException("DEMUX: Nombre d'entrées ou sorties incorrect !");
        }
        boolean a,b, entree;
        entree=this.inputs[0];
        a=this.inputs[1];
        b=this.inputs[2];
        for(int i=0; i<4;i++)
        {
            this.outputs[i]=false;
        }
        if(a==false && b==false) this.outputs[0]=entree;
        if(a==false && b==true) this.outputs[1]=entree;
        if(a==true && b==false) this.outputs[2]=entree;
        if(a==true && b==true) this.outputs[3]=entree;
    }
}

