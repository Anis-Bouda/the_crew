package projet;

public class Main4 {
    public static void main(String[] args) {
        Circuit circuit = new Circuit(); 
        
        Decodeur deco = new Decodeur("deco", 0, 2);
        
        ControlledInverter cnv = new ControlledInverter("cnv",0, 0);
        
        ControlledBuffer cbf = new ControlledBuffer("cbf",0, 0);
        
        Pin pin = new Pin("pin", 1, 1, true);
        pin.state = State.False;
        Power power = new Power("power", 2, 2);
        
        Fil fil1 = new Fil("fil1", pin, deco, 0, 0);  
        Fil fil2 = new Fil("fil2", power, deco, 1, 0);  
        Fil fil3 = new Fil("fil3", deco, cnv, 0, 0);
        Fil fil4 = new Fil("fil3", deco, cnv, 1, 1);
        Fil fil5 = new Fil("fil3", deco, cbf, 0, 2);
        Fil fil6 = new Fil("fil3", deco, cbf, 1, 3);
        
        circuit.addComposant(power);
        circuit.addComposant(pin);
        circuit.addComposant(deco);
        circuit.addComposant(cnv);
        circuit.addComposant(cbf);

        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);
        circuit.addFil(fil6);

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
