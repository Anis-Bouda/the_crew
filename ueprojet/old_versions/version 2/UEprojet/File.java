class File {
    private Component source;
    private int sourceOutputIndex;
    private Component destination;
    private int destinationInputIndex;
    private State value; 

    public Wire(Component source, int sourceOutputIndex, Component destination, int destinationInputIndex) {
        this.source = source;
        this.sourceOutputIndex = sourceOutputIndex;
        this.destination = destination;
        this.destinationInputIndex = destinationInputIndex;
        this.value =State.I; // Initialisation par défaut
    }

    public state getValue() {
        return this.value;
    }

    public void setState(state val) {
        this.value = val;
    }

    public void update() {
        state newValue = source.getOutput(sourceOutputIndex);
        if (!(newValue.equals(this.value))) {
            this.value = newValue;
            destination.setInput(destinationInputIndex, this.value);
        }
    }
}