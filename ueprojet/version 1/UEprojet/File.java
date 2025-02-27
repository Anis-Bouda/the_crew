class File {
    private Composant source;
    private int sourceOutputIndex;
    private Composant destination;
    private int destinationInputIndex;
    private boolean value; 

    public void Wire(Composant source, int sourceOutputIndex, Composant destination, int destinationInputIndex, boolean I) {
        this.source = source;
        this.sourceOutputIndex = sourceOutputIndex;
        this.destination = destination;
        this.destinationInputIndex = destinationInputIndex;
        this.value = I; // Initialisation par défaut
    }
    public int getdestination(){
        return this.destinationInputIndex;
    }
    public boolean getValue() {
        return value;
    }

    public void update() {
        boolean newValue = source.getOutput(sourceOutputIndex);
        if (newValue != value) {
            value = newValue;
            destination.setInputs(this.destinationInputIndex, this.value);
        }
    }
}