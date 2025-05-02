public class Splitter extends Composant {
	// le splitter a un seul signal en entrée et le split 
	// a plusieurs sous signals en sortie 
	// nombre de sorties
    private int n; 

    public Splitter(String id, int x, int y, int n) {
        super(id, x, y);
        // le n doit etre positif 
        if(n <= 0) {
        	throw new IllegalArgumentException("le" + n + "doit etre positif");
        }
        this.n = n;
        // l'entrée
        this.addInput(State.UNKNOWN);
        // les sorties 
        for (int i = 0; i < n; i++) {
            this.addOutput(State.UNKNOWN);
        }}

    @Override
    public void evaluate() {
    	 if (this.inputs.size() == 1 && this.outputs.size() == n) {
	           // on recupere leinputs 
    		   State inputs = this.inputs.get(0);
	            
    		   // les sorties recoit le meme state que l'entrée
    		   for(int i=0; i<n; i++) {
    			   this.outputs.set(i, inputs);
         }}
    	 else {
             throw new IllegalStateException("Erreur d'évaluation : Le Splitter doit avoir exactement 1 entrées et" +n+ "sortie.");
   }}}

