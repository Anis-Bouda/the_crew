public class RAM extends Composant {
	// Matrice qui represente la memoire 
    private State[][] memory; 
    // taille d'un mot memoire 
    private int wordSize = 3;
    // la taille d'une adresse memoire 
    private int addressSize = 3;
    
    public RAM(String id, int x, int y) {
        super(id, x, y);
        // matrice
        this.memory = new State[8][wordSize];

        // initialisation de la memoire 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < wordSize; j++) {
                this.memory[i][j] = State.UNKNOWN;
        }}

        // on a adressSize entrées pour l'adresse
        for (int i = 0; i < addressSize; i++) {
            this.addInput(State.UNKNOWN);
        }
        // on a wordSize entrées pour le mot memoire
        for (int i = 0; i < wordSize; i++) {
            this.addInput(State.UNKNOWN);
        }
        // on ajoute une entréee pour writeEnable
        this.addInput(State.UNKNOWN);

        // on a wordSize sorties pour le mot memoire retourné
        for (int i = 0; i < wordSize; i++) {
            this.addOutput(State.UNKNOWN);
        }
    }

    @Override
    public void evaluate() {
      if (this.inputs.size() == addressSize + wordSize + 1 || this.outputs.size() == wordSize) {
        // Récupération de l'adresse
        int address = 0;
        for (int i = 0; i < addressSize; i++) {
        	if (inputs.get(i) == State.True) {
        	    address += (int) Math.pow(2, i);
        	}
        	// exemple : 100 = 2^0 = 1 (adresse 1 de la memoire)
        	// exemple : 101 = 2^0 + 2^2 = 5 (adresse 5 de la memoire)
       }
  
        // recuperer le signal d'ecriture
        State writeEnable = inputs.get(addressSize + wordSize);

        // Écriture en mémoire
        if (writeEnable == State.True) {
            for (int i = 0; i < wordSize; i++) {
                memory[address][i] = inputs.get(addressSize + i);
            }
        }

        // Lecture d'un mot memoire 
        for (int i = 0; i < wordSize; i++) {
            outputs.set(i, memory[address][i]);
        }
    }
      else {
          throw new IllegalStateException("Erreur d'évaluation : La RAM doit avoir exactement " +  addressSize + wordSize + 1 +" entrée et" + wordSize + "sortie.");
      }}

    

    // affichage de la memoire 
    public void display() {
        System.out.println("État de la RAM(256x8:");
        for (int i = 0; i < memory.length; i++) {
            System.out.print("Adresse " + i + " : ");
            for (int j = 0; j < wordSize; j++) {
                System.out.print(memory[i][j] + " ");
            }
            System.out.println();
        }
    }
}
