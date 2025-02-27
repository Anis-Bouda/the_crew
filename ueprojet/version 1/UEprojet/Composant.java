abstract class  Composant {
        public String id;
        public boolean[] inputs;
        public boolean[] outputs;
        
        public Composant(String id,int nbentree, int nbsortie)
        {
            this.id=id;
            this.inputs= new boolean[nbentree];
            this.outputs= new boolean[nbsortie];
        }

        public abstract void evaluate();

        public void setInputs(int index, boolean value)
        {
            this.inputs[index]=value;
        }

        public boolean getOutput(int index)
        {
            return this.outputs[index];
        }
}
