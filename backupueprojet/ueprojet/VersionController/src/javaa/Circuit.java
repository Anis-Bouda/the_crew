import java.util.ArrayList;
import java.util.List;

public class Circuit {
     protected List<Composant> composants;
     protected List<Fil> fils;
     private static Circuit instance;
     
     public Circuit() {
    	  this.composants = new ArrayList<>();
    	  this.fils = new ArrayList<>();
     }
     
     public static Circuit getInstance() {
         if (instance == null) {
             instance = new Circuit();
         }
         return instance;
     }
     
     public void addComposant(Composant c) {
    	   composants.add(c);
     }
     
     public void addFil(Fil f) {
    	   fils.add(f);
     }
     
     
     public void afficheState() {
    	 for(Composant C: composants) {
    		 System.out.println(C.getid() + "," + C.getstate());
     }}
     
     public void simulate(int MAX_ITERATIONS) throws Exception {
    	    boolean stable;
    	    
    	    for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
    	        stable = true;

    	        // Évaluation des composants
    	        for (Composant composant : composants) {
    	            composant.evaluate();
    	        }

    	        // Mise à jour des fils
    	        for (Fil file : fils) {
    	            State previousValue = file.getValue();
    	            file.update();

    	            if (previousValue != file.getValue()) {
    	                stable = false;
    	            }
    	        }


    	        if (stable) {
    	            System.out.println("Point fixe atteint après " + (iteration + 1) + " itérations.");
    	            return;
    	        }
    	    }

    	    System.out.println("Fin de la simulation après " + MAX_ITERATIONS + " cycles.");
    	}

     
     public List<Composant> getComposants() {
         return composants;
     }

     public List<Fil> getFils() {
         return fils;
     }
}

