package Memory;
import Principale.*;
public class RAM extends Composant {
	// Matrice qui represente la memoire 
    private State[][] memory; 
    // taille d'un mot memoire 
    private int wordSize; 
    // la taille d'une adresse memoire 
    private int addressSize;
    
    public RAM(String id, int x, int y, int addressSize, int wordSize) {
        super(id, x, y);
        this.addressSize = addressSize;
        this.wordSize = wordSize; 
        // nombre de lignes de la memoire = 2^bits adresse
        int memorySize = (int) Math.pow(2, addressSize);  
        // matrice 
        this.memory = new State[memorySize][wordSize];

        // initialisation de la memoire 
        for (int i = 0; i < memorySize; i++) {
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
        	if (inputs.get(i) == State.TRUE) {
        	    address += (int) Math.pow(2, i);
        	}
        	// exemple : 100 = 2^0 = 1 (adresse 1 de la memoire)
        	// exemple : 101 = 2^0 + 2^2 = 5 (adresse 5 de la memoire)
       }
  
        // recuperer le signal d'ecriture
        State writeEnable = inputs.get(addressSize + wordSize);

        // Écriture en mémoire
        if (writeEnable == State.TRUE) {
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
        System.out.println("État de la RAM:");
        for (int i = 0; i < memory.length; i++) {
            System.out.print("Adresse " + i + " : ");
            for (int j = 0; j < wordSize; j++) {
                System.out.print(memory[i][j] + " ");
            }
            System.out.println();
        }
    }

@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (!super.equals(obj)) {
		return false;
	}
    if(!(obj instanceof RAM))
    {
        return false;
    }
	RAM ram = (RAM)obj;
	if(this.memory.length!=ram.memory.length)
	{
		return false;
	}
    for(int i=0;i<this.memory.length;i++)
	{
       if(this.memory[i].length != ram.memory[i].length)
       {
        return false;
       }
	}

	for(int i=0;i<this.memory.length;i++)
	{
        for(int j=0;j<this.memory[i].length;j++)
        {
            if(this.memory[i][j]!= ram.memory[i][j])
            {
                return false;
            }
        }

	}
	return true;
}
@Override
public String toString() {
    String result = super.toString() + ", MEMORY: \n";

    for (int i = 0; i < memory.length; i++) {
        result = result + "Adresse " + i + ": ";
        for (int j = 0; j < memory[i].length; j++) {
            result += memory[i][j] + " ";
        }
        result = result + "\n";
    }
    return result;
}

@Override
public int hashCode() {
    int result = super.hashCode();
    for (int i = 0; i < memory.length; i++) {
        for (int j = 0; j < memory[i].length; j++) {
            State s = memory[i][j];
            result = 31 * result + (s != null ? s.hashCode() : 0);
        }
    }
    return result;
}
}