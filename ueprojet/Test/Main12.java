package projet;

public class Main12 {
    public static void main(String[] args) {

        Circuit circuit = new Circuit();

        BitFINDER bite = new BitFINDER("bite", 4, 2, 0, 0);
        bite.setInputs(0, State.False);
        bite.setInputs(1, State.False);
        bite.setInputs(2, State.True);
        bite.setInputs(3, State.UNKNOWN);

        Transistor transistor = new Transistor("transistor", 1, 1);

        Constant cons = new Constant("cons", 2, 2, State.True);

        Fil fil1 = new Fil("fil1", bite, transistor, 0, 0);
        Fil fil2 = new Fil("fil2", cons, transistor, 1, 0);

        circuit.addComposant(bite);
        circuit.addComposant(cons);
        circuit.addComposant(transistor);
        

        circuit.addFil(fil1);
        circuit.addFil(fil2);

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