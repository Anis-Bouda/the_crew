public class Main {
    public static void main(String[] args) {
        try {
            // Création du circuit
            Circuit circuit = new Circuit();

            // Création des composants logiques
            Composant etGate = new ET("ET1", 0, 0);
            Composant ouGate = new OU("OU1", 1, 0);
            Composant nonGate = new NON("NON1", 2, 0);
            Composant xorGate = new XOR("XOR1", 3, 0);
            Composant xnorGate = new XNOR("XNOR1", 4, 0);
            Composant norGate = new NOR("NOR1", 5, 0);
            Composant nandGate = new NAND("NAND1", 6, 0);
            Composant decodeur = new Decodeur("DEC1", 7, 0);
            Composant basculeSR = new BasculeSR("BasculeSR1", 8, 0);
            Composant basculeD = new BasculeD("BasculeD1", 9, 0);
            Composant basculeT = new BasculeT("BasculeT1", 10, 0);
            Composant basculeJK = new BasculeJK("BasculeJK1", 11, 0);
            Composant clock = new Clock("Clock1", 12, 0, 5);
            Composant biteAdder = new BiteADDER("BiteADDER1", 13, 0);
            Composant encodeur = new Encodeur("Encodeur1", 14, 0);
            Composant adder = new ADDER("ADDER1", 2, 0,0);
            Composant sub = new SUB("SUB1", 16, 0,0);
            Composant evenParity = new EVENParity("EVENParity1", 17, 0,0);
            Composant oddParity = new ODDParity("ODDParity1", 18, 0,0);
            Composant comparator = new COMPARATOR("COMPARATOR1", 19, 0,0);

            // Ajout des composants au circuit
            circuit.addComposant(etGate);
            circuit.addComposant(ouGate);
            circuit.addComposant(nonGate);
            circuit.addComposant(xorGate);
            circuit.addComposant(xnorGate);
            circuit.addComposant(norGate);
            circuit.addComposant(nandGate);
            circuit.addComposant(decodeur);
            circuit.addComposant(basculeSR);
            circuit.addComposant(basculeD);
            circuit.addComposant(basculeT);
            circuit.addComposant(basculeJK);
            circuit.addComposant(clock);
            circuit.addComposant(biteAdder);
            circuit.addComposant(encodeur);
            circuit.addComposant(adder);
            circuit.addComposant(sub);
            circuit.addComposant(evenParity);
            circuit.addComposant(oddParity);
            circuit.addComposant(comparator);


            // Définition des entrées
            etGate.setInputs(0, State.TRUE);
            etGate.setInputs(1, State.FALSE);

            ouGate.setInputs(0, State.TRUE);
            ouGate.setInputs(1, State.FALSE);

            nonGate.setInputs(0, State.TRUE);

            xorGate.setInputs(0, State.TRUE);
            xorGate.setInputs(1, State.FALSE);

            xnorGate.setInputs(0, State.TRUE);
            xnorGate.setInputs(1, State.FALSE);

            norGate.setInputs(0, State.TRUE);
            norGate.setInputs(1, State.FALSE);

            nandGate.setInputs(0, State.TRUE);
            nandGate.setInputs(1, State.FALSE);

            decodeur.setInputs(0, State.TRUE);
            decodeur.setInputs(1, State.FALSE);

            basculeSR.setInputs(0, State.TRUE);  // S
            basculeSR.setInputs(1, State.FALSE); // R

            basculeD.setInputs(0, State.TRUE);  // D
            basculeD.setInputs(1, State.TRUE);  // CLK

            basculeT.setInputs(0, State.TRUE);  // T
            basculeT.setInputs(1, State.TRUE);  // CLK

            basculeJK.setInputs(0, State.TRUE);  // J
            basculeJK.setInputs(1, State.FALSE); // K
            basculeJK.setInputs(2, State.TRUE);  // CLK

            clock.setInputs(0, State.FALSE); // Signal d'entrée initial

            biteAdder.setInputs(0, State.TRUE);  // A
            biteAdder.setInputs(1, State.FALSE); // B
            biteAdder.setInputs(2, State.TRUE);  // Carry In

            encodeur.setInputs(0, State.FALSE);
            encodeur.setInputs(1, State.FALSE);
            encodeur.setInputs(2, State.TRUE);
            encodeur.setInputs(3, State.FALSE);

            adder.setInputs(0, State.TRUE);
            adder.setInputs(1, State.FALSE);
            adder.setInputs(2, State.TRUE);

            sub.setInputs(0, State.TRUE);
            sub.setInputs(1, State.FALSE);
            sub.setInputs(2, State.FALSE);

            evenParity.setInputs(0, State.TRUE);
            evenParity.setInputs(1, State.FALSE);
            evenParity.setInputs(2, State.TRUE);
            evenParity.setInputs(3, State.TRUE);

            oddParity.setInputs(0, State.TRUE);
            oddParity.setInputs(1, State.TRUE);
            oddParity.setInputs(2, State.TRUE);
            oddParity.setInputs(3, State.FALSE);

            comparator.setInputs(0, State.TRUE);
            comparator.setInputs(1, State.FALSE);

            // Évaluation des composants
            etGate.evaluate();
            ouGate.evaluate();
            nonGate.evaluate();
            xorGate.evaluate();
            xnorGate.evaluate();
            norGate.evaluate();
            nandGate.evaluate();
            decodeur.evaluate();
            basculeSR.evaluate();
            basculeD.evaluate();
            basculeT.evaluate();
            basculeJK.evaluate();
            clock.evaluate();
            biteAdder.evaluate();
            encodeur.evaluate();
            // Évaluation des nouveaux composants
            adder.evaluate();
            sub.evaluate();
            evenParity.evaluate();
            oddParity.evaluate();
            comparator.evaluate();

      

            // Affichage des résultats
            System.out.println("Résultats des composants après évaluation :");
            System.out.println("ET : " + etGate.getOutput(0));
            System.out.println("OU : " + ouGate.getOutput(0));
            System.out.println("NON : " + nonGate.getOutput(0));
            System.out.println("XOR : " + xorGate.getOutput(0));
            System.out.println("XNOR : " + xnorGate.getOutput(0));
            System.out.println("NOR : " + norGate.getOutput(0));
            System.out.println("NAND : " + nandGate.getOutput(0));
            System.out.println("Décodeur : " + decodeur.getOutput(0) + ", " + decodeur.getOutput(1) + ", " + decodeur.getOutput(2) + ", " + decodeur.getOutput(3));
            System.out.println("Bascule SR : " + basculeSR.getOutput(0) + ", " + basculeSR.getOutput(1));
            System.out.println("Bascule D : " + basculeD.getOutput(0) + ", " + basculeD.getOutput(1));
            System.out.println("Bascule T : " + basculeT.getOutput(0) + ", " + basculeT.getOutput(1));
            System.out.println("Bascule JK : " + basculeJK.getOutput(0) + ", " + basculeJK.getOutput(1));
            System.out.println("Clock : " + clock.getOutput(0));
            System.out.println("BiteADDER : " + biteAdder.getOutput(0) + ", " + biteAdder.getOutput(1));
            System.out.println("Encodeur : " + encodeur.getOutput(0) + ", " + encodeur.getOutput(1));
            System.out.println("\n=== Résultats des nouveaux composants de synnnnnnnnnnndia  ===");
            System.out.println("ADDER : " + adder.getOutput(0) + ", " + adder.getOutput(1));
            System.out.println("SUB : " + sub.getOutput(0) + ", " + sub.getOutput(1));
            System.out.println("Even Parity : " + evenParity.getOutput(0));
            System.out.println("Odd Parity : " + oddParity.getOutput(0));
            System.out.println("Comparator : " + comparator.getOutput(0));

            // Lancement de la simulation
            System.out.println("\nLancement de la simulation...");
            circuit.simulate(10);
            System.out.println("\nÉtat final du circuit après simulation :");
            circuit.afficheState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
