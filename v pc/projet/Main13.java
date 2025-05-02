package projet;

public class Main13 {
    public static void main(String[] args) {
        // Création du circuit
        Circuit circuit = new Circuit();
        
        // Création de mux
        MUX mux = new MUX("mux", 2, 0, 0);
        mux.setInputs(0, State.True);  // Entrée 0
        mux.setInputs(1, State.False); // Entrée 1
        mux.setInputs(2, State.UNKNOWN);  // Entrée 2
        mux.setInputs(3, State.ERROR); // Entrée 3
        mux.setInputs(4, State.True);  // Sélection bit 0
        mux.setInputs(5, State.True); // Sélection bit 1
         
        // Création de demux
        DEMUX demux = new DEMUX("demux", 2, 0, 0);
        demux.setInputs(0, State.True); // Entrée
        demux.setInputs(1, State.True); // Sélection bit 0
        demux.setInputs(2, State.True);  // Sélection bit 1
        
        // Création de parity odd
        ParityODD odd = new ParityODD("odd",3, 1, 1);
        
        // Création de transistor
        Transistor transistor = new Transistor("transistor", 1, 1);
        
        // Création des fils reliant les composants
        Fil fil1 = new Fil("fil1", mux, transistor, 0, 0);
        Fil fil2 = new Fil("fil2", demux, transistor, 1, 0);
        Fil fil3 = new Fil("fil3", demux, odd, 0, 1);
        Fil fil4 = new Fil("fil4", demux, odd, 1, 2);
        Fil fil5 = new Fil("fil5", demux, odd, 2, 3);
        
        
        // Ajout des composants au circuit
        circuit.addComposant(mux);
        circuit.addComposant(demux);
        circuit.addComposant(transistor);
        circuit.addComposant(odd);
        
        
        // Ajout des fils au circuit
        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);
       
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
        System.out.println("===================================================================================");
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
}