package Memory;
import Principale.*;
public class ROM extends Composant {
	// Matrice qui represente la memoire 
    private State[][] memory; 
    // taille d'un mot memoire 
    private int wordSize; 
    // la taille d'une adresse memoire 
    private int addressSize;
    
    // pour la ROM contrairement a la RAM, 
    // l'utilisateur doit remplir la memoire 
    // on peut faire la meme chose pour la RAM 
    // mais comme on peut ecrire, on initialise tous 
    // a unknown par defaut 
    public ROM(String id, int x, int y, int addressSize, int wordSize, State[][] LAROM) {
        super(id, x, y);
        this.addressSize = addressSize;
        this.wordSize = wordSize; 
        // nombre de lignes de la memoire = 2^bits adresse
        int memorySize = (int) Math.pow(2, addressSize);  
        
        if (LAROM.length != memorySize || LAROM[0].length != wordSize) {
            throw new IllegalArgumentException("Erreurn different de la taille de la ROM.");
        }
        
        this.memory = new State[memorySize][wordSize];
        // initialisation de la memoire 
        for (int i = 0; i < memorySize; i++) {
            for (int j = 0; j < wordSize; j++) {
                this.memory[i][j] = LAROM[i][j];
        }}

        // on a adressSize entrées pour l'adresse
        for (int i = 0; i < addressSize; i++) {
            this.addInput(State.UNKNOWN);
        }
       
        // on a wordSize sorties pour le mot memoire retourné
        for (int i = 0; i < wordSize; i++) {
            this.addOutput(State.UNKNOWN);
        }
    }
	 
	 public void evaluate() {
	        if (this.inputs.size() == addressSize && this.outputs.size() == wordSize) {
	        	// Récupération de l'adresse
	            int address = 0;
	            for (int i = 0; i < addressSize; i++) {
	            	if (inputs.get(i) == State.TRUE) {
	            	    address += (int) Math.pow(2, i);
	            	}
	            	// exemple : 100 = 2^0 = 1 (adresse 1 de la memoire)
	            	// exemple : 101 = 2^0 + 2^2 = 5 (adresse 5 de la memoire)
	           }
	            
	           // Lecture d'un mot memoire 
	           for (int i = 0; i < wordSize; i++) {
	                outputs.set(i, memory[address][i]);
	           }}
	     else {
	          throw new IllegalStateException("Erreur d'évaluation : La ROM doit avoir exactement"+ addressSize + "entrée et" + wordSize +" sortie.");
  }}
	 
	// affichage de la memoire 
	    public void display() {
	        System.out.println("État de la ROM:");
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
    if(!(obj instanceof ROM))
    {
        return false;
    }
	ROM rom = (ROM)obj;

	if(this.memory.length!=rom.memory.length)
	{
		return false;
	}

    for(int i=0;i<this.memory.length;i++)
	{
       if(this.memory[i].length != rom.memory[i].length)
       {
        return false;
       }
	}

	for(int i=0;i<this.memory.length;i++)
	{
        for(int j=0;j<this.memory[i].length;j++)
        {
            if(this.memory[i][j]!= rom.memory[i][j])
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