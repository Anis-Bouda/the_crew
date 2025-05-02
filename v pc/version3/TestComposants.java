public class TestComposants {
    public static void main(String[] args) {
        System.out.println("=== Début des tests ===\n");

        testET();
        testOU();
        testNON();
        testXOR();
        testXNOR();
        testNOR();
        testNAND();
        testDecodeur();
        testBasculeSR();
        testBasculeD();
        testBasculeT();
        testBasculeJK();
        testClock();
        testBiteADDER();
        testEncodeur();
        System.out.println("test de syyyyyyyyyyyyynnnnnnnndddddiiaaaaaaa");
        testADDER();
        testSUB();
        testEVENParity();
        testODDParity();
        testCOMPARATOR();

        System.out.println("\n=== Fin des tests ===");
    }

    public static void testET() {
        Composant etGate = new ET("ET1", 0, 0);
        etGate.setInputs(0, State.TRUE);
        etGate.setInputs(1, State.FALSE);
        etGate.evaluate();
        System.out.println("Test ET (1,0) -> " + etGate.getOutput(0) + " (Attendu : FALSE)");
    }

    public static void testOU() {
        Composant ouGate = new OU("OU1", 1, 0);
        ouGate.setInputs(0, State.TRUE);
        ouGate.setInputs(1, State.FALSE);
        ouGate.evaluate();
        System.out.println("Test OU (1,0) -> " + ouGate.getOutput(0) + " (Attendu : TRUE)");
    }

    public static void testNON() {
        Composant nonGate = new NON("NON1", 2, 0);
        nonGate.setInputs(0, State.TRUE);
        nonGate.evaluate();
        System.out.println("Test NON (1) -> " + nonGate.getOutput(0) + " (Attendu : FALSE)");
    }

    public static void testXOR() {
        Composant xorGate = new XOR("XOR1", 3, 0);
        xorGate.setInputs(0, State.TRUE);
        xorGate.setInputs(1, State.FALSE);
        xorGate.evaluate();
        System.out.println("Test XOR (1,0) -> " + xorGate.getOutput(0) + " (Attendu : TRUE)");
    }

    public static void testXNOR() {
        Composant xnorGate = new XNOR("XNOR1", 4, 0);
        xnorGate.setInputs(0, State.TRUE);
        xnorGate.setInputs(1, State.FALSE);
        xnorGate.evaluate();
        System.out.println("Test XNOR (1,0) -> " + xnorGate.getOutput(0) + " (Attendu : FALSE)");
    }

    public static void testNOR() {
        Composant norGate = new NOR("NOR1", 5, 0);
        norGate.setInputs(0, State.FALSE);
        norGate.setInputs(1, State.FALSE);
        norGate.evaluate();
        System.out.println("Test NOR (0,0) -> " + norGate.getOutput(0) + " (Attendu : TRUE)");
    }

    public static void testNAND() {
        Composant nandGate = new NAND("NAND1", 6, 0);
        nandGate.setInputs(0, State.TRUE);
        nandGate.setInputs(1, State.TRUE);
        nandGate.evaluate();
        System.out.println("Test NAND (1,1) -> " + nandGate.getOutput(0) + " (Attendu : FALSE)");
    }

    public static void testDecodeur() {
        Composant decodeur = new Decodeur("DEC1", 7, 0);
        decodeur.setInputs(0, State.TRUE);
        decodeur.setInputs(1, State.FALSE);
        decodeur.evaluate();
        System.out.println("Test Décodeur (1,0) -> [" +
                decodeur.getOutput(0) + ", " + decodeur.getOutput(1) + ", " +
                decodeur.getOutput(2) + ", " + decodeur.getOutput(3) + "] " +
                "(Attendu : [FALSE, TRUE, FALSE, FALSE])");
    }

    public static void testBasculeSR() {
        Composant basculeSR = new BasculeSR("BasculeSR1", 8, 0);
        basculeSR.setInputs(0, State.TRUE);
        basculeSR.setInputs(1, State.FALSE);
        basculeSR.evaluate();
        System.out.println("Test Bascule SR (S=1, R=0) -> [" +
                basculeSR.getOutput(0) + ", " + basculeSR.getOutput(1) + "] " +
                "(Attendu : [TRUE, FALSE])");
    }

    public static void testBasculeD() {
        Composant basculeD = new BasculeD("BasculeD1", 9, 0);
        basculeD.setInputs(0, State.TRUE);  // D
        basculeD.setInputs(1, State.TRUE);  // CLK
        basculeD.evaluate();
        System.out.println("Test Bascule D (D=1, CLK=1) -> [" +
                basculeD.getOutput(0) + ", " + basculeD.getOutput(1) + "] " +
                "(Attendu : [TRUE, FALSE])");
    }

    public static void testBasculeT() {
        Composant basculeT = new BasculeT("BasculeT1", 10, 0);
        basculeT.setInputs(0, State.TRUE);  // T
        basculeT.setInputs(1, State.TRUE);  // CLK
        basculeT.evaluate();
        System.out.println("Test Bascule T (T=1, CLK=1) -> [" +
                basculeT.getOutput(0) + ", " + basculeT.getOutput(1) + "] ");
    }

    public static void testBasculeJK() {
        Composant basculeJK = new BasculeJK("BasculeJK1", 11, 0);
        basculeJK.setInputs(0, State.TRUE);  // J
        basculeJK.setInputs(1, State.FALSE); // K
        basculeJK.setInputs(2, State.TRUE);  // CLK
        basculeJK.evaluate();
        System.out.println("Test Bascule JK (J=1, K=0, CLK=1) -> [" +
                basculeJK.getOutput(0) + ", " + basculeJK.getOutput(1) + "] " +
                "(Attendu : [TRUE, FALSE])");
    }

    public static void testClock() {
        Composant clock = new Clock("Clock1", 12, 0, 5);
        clock.setInputs(0, State.FALSE);
        for (int i = 0; i < 10; i++) {
            clock.evaluate();
            System.out.println("Test Clock (période=5, cycle=" + (i + 1) + ") -> " + clock.getOutput(0));
        }
    }

    public static void testBiteADDER() {
        Composant biteAdder = new BiteADDER("BiteADDER1", 13, 0);
        biteAdder.setInputs(0, State.TRUE);
        biteAdder.setInputs(1, State.TRUE);
        biteAdder.setInputs(2, State.TRUE);
        biteAdder.evaluate();
        System.out.println("Test BiteADDER  -> [" +
                biteAdder.getOutput(0) + ", " + biteAdder.getOutput(1) + "] " +
                "(Attendu : [0, 1])");
    }

    public static void testEncodeur() {
        Composant encodeur = new Encodeur("Encodeur1", 14, 0);
        encodeur.setInputs(0, State.FALSE);
        encodeur.setInputs(1, State.FALSE);
        encodeur.setInputs(2, State.TRUE);
        encodeur.setInputs(3, State.FALSE);
        encodeur.evaluate();
        System.out.println("Test Encodeur (0010) -> [" +
                encodeur.getOutput(0) + ", " + encodeur.getOutput(1) + "] " +
                "(Attendu : [1, 0])");
    }

    public static void testADDER() {
        Composant adder = new ADDER("ADDER1", 2, 0, 0);
        adder.setInputs(0, State.TRUE);
        adder.setInputs(1, State.FALSE);
        adder.setInputs(2, State.FALSE);
        adder.setInputs(3, State.TRUE);
        adder.setInputs(4, State.TRUE); // Carry-in
        adder.evaluate();
        System.out.println("Test ADDER -> [" +
                adder.getOutput(0) + ", " + adder.getOutput(1) + ", " + adder.getOutput(2) + "] (Attendu : [1,1,0])");
    }

    public static void testSUB() {
        Composant sub = new SUB("SUB1", 2, 0, 0);
        sub.setInputs(0, State.TRUE);
        sub.setInputs(1, State.TRUE);
        sub.setInputs(2, State.FALSE);
        sub.setInputs(3, State.FALSE);
        sub.setInputs(4, State.FALSE); // Carry-in
        sub.evaluate();
        System.out.println("Test SUB (A=11, B=00) -> [" +
                sub.getOutput(0) + ", " + sub.getOutput(1) + "] (Attendu : [1,1])");
    }

    public static void testEVENParity() {
        Composant evenParity = new EVENParity("EVENParity1", 3, 0, 0);
        evenParity.setInputs(0, State.TRUE);
        evenParity.setInputs(1, State.FALSE);
        evenParity.setInputs(2, State.TRUE);
        evenParity.evaluate();
        System.out.println("Test EVEN Parity (1,0,1) -> " + evenParity.getOutput(0) + " (Attendu : TRUE)");
    }

    public static void testODDParity() {
        Composant oddParity = new ODDParity("ODDParity1", 3, 0, 0);
        oddParity.setInputs(0, State.TRUE);
        oddParity.setInputs(1, State.FALSE);
        oddParity.setInputs(2, State.TRUE);
        oddParity.evaluate();
        System.out.println("Test ODD Parity (1,0,1) -> " + oddParity.getOutput(0) + " (Attendu : FALSE)");
    }

    public static void testCOMPARATOR() {
        Composant comparator = new COMPARATOR("COMPARATOR1", 3, 0, 0);
        comparator.setInputs(0, State.FALSE);
        comparator.setInputs(1, State.FALSE);
        comparator.setInputs(2, State.TRUE);
        comparator.setInputs(3, State.FALSE);
        comparator.setInputs(4, State.TRUE);
        comparator.setInputs(5, State.FALSE);
        comparator.evaluate();
        System.out.println("Test COMPARATOR (A=001, B=010) -> [" +
                comparator.getOutput(0) + ", " + comparator.getOutput(1) + ", " + comparator.getOutput(2) + "] (Attendu : [TRUE, FALSE, FALSE])");
    }
}    