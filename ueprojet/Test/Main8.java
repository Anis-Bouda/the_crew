package projet;

public class Main8 {
    public static void main(String[] args) {

        Circuit circuit = new Circuit();
        
        Encodeur enco = new Encodeur("enco", 0, 0);
        enco.setInputs(0, State.True);
        enco.setInputs(1, State.False);
        enco.setInputs(2, State.False);
        enco.setInputs(3, State.False);

        ParityODD odd = new ParityODD("odd",2, 1, 1);
 
        ParityEVEN even = new ParityEVEN("even", 2, 2, 2);

        Fil fil1 = new Fil("fil1", enco, odd, 0, 0);
        Fil fil2 = new Fil("fil2", enco, odd, 1, 1);
        Fil fil3 = new Fil("fil3", enco, even, 0, 0);
        Fil fil4 = new Fil("fil4", enco, even, 1, 1);

        circuit.addComposant(enco);
        circuit.addComposant(odd);
        circuit.addComposant(even);

        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);

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

        try {
            circuit.simulate(50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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