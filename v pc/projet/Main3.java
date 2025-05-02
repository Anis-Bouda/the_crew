package projet;

public class Main3 {
	    public static void main(String[] args) {
	        // Création du circuit
	        Circuit circuit = new Circuit();

	     // Création de l'état d'entrée
	        State input1 = State.True; 
	        
	        // Création d'un splitter
	        Splitter SPL = new Splitter("SPL", 1, 2, 2);
	       
	        // Création de la porte logique OU
	        OU ou = new OU("ou", 2, 1);
	     
	        // creation de constant 
	        Constant cons = new Constant("cons",1,1, input1);
	        
	        // Creation du bite Extender 
	        BitExtender bitExtenderSign = new BitExtender("ExtSign", 2, 4, 2, 4, ExtensionType.ONE);
	        testExtension(bitExtenderSign, new State[]{State.True, State.False});

	        
	        // Création des fils reliant les composants
	        // sortie 0 du bit extender est l'entree du splitter
	        Fil fil1 = new Fil("fil1", bitExtenderSign, SPL, 0, 0);
	        // sortie 2 du bit extender est l'entree 0 du ou 
	        Fil fil2 = new Fil("fil2", bitExtenderSign, ou,  0, 3);
	        // sortie 0 du constant est entree 1 du ou
	        Fil fil3 = new Fil("fil3", cons, ou,  1, 0);
	        

	        // Ajout des composants au circuit
	        circuit.addComposant(bitExtenderSign);
	        circuit.addComposant(SPL);
	        circuit.addComposant(cons);
	        circuit.addComposant(ou);

	        // Ajout des fils au circuit
	        circuit.addFil(fil1);
	        circuit.addFil(fil2);
	        circuit.addFil(fil3);
	      

	        // Affichage de l'état initial
	        System.out.println("===================================================================================");
	        System.out.println("===============================État initial du circuit:============================");
	        System.out.println("Les composants : ");
	        System.out.println("================");
	        circuit.afficheState();

	        System.out.println("Les fils : ");
	        System.out.println("============");
	        for (int i = 0; i < circuit.fils.size(); i++) {
	            System.out.println("Fil " + circuit.fils.get(i).getId() + " " + circuit.fils.get(i).getValue());
	        }

	        System.out.println("Les entrées et sorties des composants : ");
	        System.out.println("========================================");
	        for (int i = 0; i < circuit.composants.size(); i++) {
	            System.out.println("Composant " + circuit.composants.get(i).getid() 
	                    + " les entrées : " + circuit.composants.get(i).getInputs() 
	                    + " les sorties : " + circuit.composants.get(i).getOutputs());
	        }

	        // Simulation du circuit
	        try {
	            circuit.simulate(50);  // Max 50 itérations
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }

	        // Affichage de l'état après simulation
	        System.out.println("==================================================================================");
	        System.out.println("=====================État du circuit après simulation:=============================");
	        System.out.println("Les composants : ");
	        System.out.println("================");
	        circuit.afficheState();

	        System.out.println("Les fils : ");
	        System.out.println("============");
	        for (int i = 0; i < circuit.fils.size(); i++) {
	            circuit.fils.get(i).update();
	            System.out.println("Fil " + circuit.fils.get(i).getId() + " " + circuit.fils.get(i).getValue());
	        }

	        System.out.println("Les entrées et sorties des composants : ");
	        System.out.println("========================================");
	        for (int i = 0; i < circuit.composants.size(); i++) {
	            circuit.composants.get(i).evaluate();
	            System.out.println("Composant " + circuit.composants.get(i).getid() 
	                    + " les entrées : " + circuit.composants.get(i).getInputs() 
	                    + " les sorties : " + circuit.composants.get(i).getOutputs());
	        }
	    }
	
	public static void testExtension(BitExtender bitExtender, State[] inputs) {
        System.out.print("Entrée : ");
        for (int i = 0; i < inputs.length; i++) {
            bitExtender.setInputs(i, inputs[i]);
            System.out.print(inputs[i] + " ");
        }

        bitExtender.evaluate();

        System.out.print("\nSortie : ");
        for (int i = 0; i < bitExtender.outputs.size(); i++) {
            System.out.print(bitExtender.getOutput(i) + " ");
        }
        System.out.println("\n----------------------------------");
        

    }
	
	
}
