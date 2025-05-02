package projet;

public class Main14 {
    public static void main(String[] args) {
        // Création du circuit
        Circuit circuit = new Circuit();
        
        // Création de counter
        Counter counter = new Counter("Counter_Test1", 2, 0, 0);
        counter.setInputs(0, State.False);  // Initial Count
        counter.setInputs(1, State.False);  // Initial Count
        counter.setInputs(2, State.True);  // Horloge
        counter.setInputs(3, State.True);  // Incrémentation
        
        // Création de transistor
        Transistor transistor = new Transistor("transistor", 1, 1);
        
        // Création des fils reliant les composants
        Fil fil1 = new Fil("fil1", counter, transistor, 0, 0);
        Fil fil2 = new Fil("fil2", counter, transistor, 1, 1);
                                                                              
        // Ajout des composants au circuit
        circuit.addComposant(counter);
        counter.evaluate();
        circuit.addComposant(transistor);
        transistor.evaluate();
        
        // Ajout des fils au circuit
        circuit.addFil(fil1);
        circuit.addFil(fil2);

       
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

     /*   // Simulation du circuit
        try {
            circuit.simulate(50);  // Max 50 itérations
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
*/
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