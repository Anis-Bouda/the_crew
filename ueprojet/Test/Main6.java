package projet;

public class Main6 {
    public static void main(String[] args) {
        Circuit circuit = new Circuit();
        
        Buffer buf = new Buffer("buf", 0, 0);
        buf.setInputs(0, State.True);

        Constant con1 = new Constant("con1", 1, 1, State.True);
        Constant con2 = new Constant("con2", 1, 1, State.False);

        Power pow = new Power("pow", 2, 2);

        BasculeT bascule = new BasculeT("bascule", 3, 3);
        bascule.setInputs(0, State.False);
        bascule.setOutputs(0, State.False);

        TransmissionGate gate = new TransmissionGate("gate", 4, 4);

        Sept_Segments sept = new Sept_Segments("sept", 5, 5);

        Fil fil1 = new Fil("fil1", con1, bascule, 1, 0);
        Fil fil2 = new Fil("fil2", buf, gate, 0, 0);
        Fil fil3 = new Fil("fil3", pow, gate, 1, 0);
        Fil fil4 = new Fil("fil4", con2, sept, 0, 0);
        Fil fil5 = new Fil("fil5", bascule, sept, 1, 0);
        Fil fil6 = new Fil("fil6", gate, sept, 2, 0);
        Fil fil7 = new Fil("fil7", bascule, sept, 3, 1);

        circuit.addComposant(buf);
        circuit.addComposant(con1);
        circuit.addComposant(bascule);
        circuit.addComposant(pow);
        circuit.addComposant(gate);
        circuit.addComposant(con2);
        circuit.addComposant(sept);

        circuit.addFil(fil1);
        circuit.addFil(fil2);
        circuit.addFil(fil3);
        circuit.addFil(fil4);
        circuit.addFil(fil5);
        circuit.addFil(fil6);
        circuit.addFil(fil7);

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
