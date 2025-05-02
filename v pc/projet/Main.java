package projet;

public class Main {
    public static void main(String[] args) {
        // Création du circuit
        Circuit circuit = new Circuit();

        // Création de l'état d'entrée
        State input1 = State.True;  
        State input2 = State.False;

        // Création des résistances (PullResistor)
        PullResistor RES1 = new PullResistor("RES1", 0, 1, input1);
        PullResistor RES2 = new PullResistor("RES2", 0, 1, input2);

        // Création de la porte logique OU
        OU ou = new OU("ou", 0, 0);

        // Création des fils reliant les composants
        Fil filRES1 = new Fil("filgen1", RES1, ou, 0, 0);  // Résistance 1 -> OU (entrée 0)
        Fil filRES2 = new Fil("filgen2", RES2, ou, 1, 0);  // Résistance 2 -> OU (entrée 1)

        // Ajout des composants au circuit
        circuit.addComposant(RES1);
        circuit.addComposant(RES2);
        circuit.addComposant(ou);

        // Ajout des fils au circuit
        circuit.addFil(filRES1);
        circuit.addFil(filRES2);

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
