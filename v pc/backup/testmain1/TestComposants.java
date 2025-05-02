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
        System.out.println("test de maciliaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        testRAM();
        testROM();
        testPower();
        testGround();
        testBuffer();
        testControlledBuffer();
        testControlledInverter();
        System.out.println("test de maciliaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa222222222222222222222222");
        testPullResistor();
        //testProbe();
        testTunnel();
        testTransistor();
        testTransmissionGate();
        System.out.println("test de syyyyyyyyyyyyynnnnnnnndddddiiaaaaaaa22222222222222222222222222");
        testMUX();
        testDEMUX();
        testBitFINDER();
        testRegister();
        testNEGATOR();
       // testCounter();
        System.out.println("test de ELLLLLLLLLLLLLLLIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        testDIVIDER() ;
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
 

public static void testRAM() {
    Composant ram = new RAM("RAM1", 16, 0);
    ram.setInputs(0, State.ERROR); // Adresse
    ram.setInputs(1, State.FALSE); // Données
    ram.setInputs(1, State.ERROR); //control
    ram.evaluate();
    System.out.println("Test RAM -> " + ram.getOutput(0));
}

public static void testROM() {
    Composant rom = new ROM("ROM1", 16, 0);
    rom.setInputs(0, State.TRUE); // Adresse
    rom.evaluate();
    System.out.println("Test ROM -> " + rom.getOutput(0));
}

public static void testPower() {
    Composant power = new Power("Power1", 0, 0);
    power.evaluate();
    System.out.println("Test Power -> " + power.getOutput(0) + " (Attendu : TRUE)");
}

public static void testGround() {
    Composant ground = new Ground("Ground1", 0, 0);
    ground.evaluate();
    System.out.println("Test Ground -> " + ground.getOutput(0) + " (Attendu : FALSE)");
}

public static void testBuffer() {
    Composant buffer = new Buffer("Buffer1", 1, 0);
    buffer.setInputs(0, State.ERROR);
    buffer.evaluate();
    System.out.println("Test Buffer (1) -> " + buffer.getOutput(0) + " (Attendu : TRUE)");
}

public static void testControlledBuffer() {
    Composant controlledBuffer = new ControlledBuffer("ControlledBuffer1", 2, 0);
    controlledBuffer.setInputs(0, State.ERROR);  // Input
    controlledBuffer.setInputs(1, State.UNKNOWN);  // Control
    controlledBuffer.evaluate();
    System.out.println("Test ControlledBuffer (1,1) -> " + controlledBuffer.getOutput(0) + " (Attendu : TRUE)");
}

public static void testControlledInverter() {
    Composant controlledInverter = new ControlledInverter("ControlledInverter1", 2, 0);
    controlledInverter.setInputs(0, State.FALSE);  // Input
    controlledInverter.setInputs(1, State.FALSE);  // Control
    controlledInverter.evaluate();
    System.out.println("Test ControlledInverter (1,1) -> " + controlledInverter.getOutput(0) + " (Attendu : FALSE)");
}

public static void testPullResistor() {
    System.out.println("Test du PullResistor avec pullState TRUE...");
    PullResistor pullResistorTrue = new PullResistor("PullResistorTrue", 0, 0, State.ERROR);
    pullResistorTrue.setInputs(0, State.FALSE);
    pullResistorTrue.evaluate();
    System.out.println("Test réussi : PullResistor TRUE -> " + pullResistorTrue.getOutput(0));

    System.out.println("Test du PullResistor avec pullState FALSE...");
    PullResistor pullResistorFalse = new PullResistor("PullResistorFalse", 0, 0, State.FALSE);
    pullResistorFalse.setInputs(0, State.TRUE);
    pullResistorFalse.evaluate();
    System.out.println("Test réussi : PullResistor FALSE -> " + pullResistorFalse.getOutput(0));
}

/*Test du Probe
public static void testProbe() {
    System.out.println("Test du Probe...");
    Probe probe = new Probe("Probe1", 1, 0);
    probe.setInputs(0, State.TRUE);
    probe.evaluate();
    System.out.println("Test Probe TRUE -> " + probe.getOutput(0));

    probe.setInputs(0, State.FALSE);
    probe.evaluate();
    System.out.println("Test Probe FALSE -> " + probe.getOutput(0));
}*/

// Test du Tunnel
public static void testTunnel() {
    System.out.println("Test du Tunnel...");
    Tunnel tunnel = new Tunnel("Tunnel1", 2, 0, "Label1");
    tunnel.setInputs(0, State.UNKNOWN);
    tunnel.evaluate();
    System.out.println("Test Tunnel -> " + tunnel.getOutput(0));
}

// Test du Transistor
public static void testTransistor() {
    System.out.println("Test du Transistor...");
    Transistor transistor = new Transistor("Transistor1", 3, 0);

    // Test avec signal de contrôle TRUE
    transistor.setInputs(0, State.FALSE); // signal d'entrée
    transistor.setInputs(1, State.TRUE); // signal de contrôle (on)
    transistor.evaluate();
    System.out.println("Test Transistor (signal contrôle TRUE) -> " + transistor.getOutput(0));

    // Test avec signal de contrôle FALSE
    transistor.setInputs(1, State.FALSE); // signal de contrôle (off)
    transistor.evaluate();
    System.out.println("Test Transistor (signal contrôle FALSE) -> " + transistor.getOutput(0));
}
public static void testTransmissionGate() {
    System.out.println("Test du TransmissionGate avec contrôle TRUE...");
    TransmissionGate transmissionGateTrue = new TransmissionGate("TransmissionGateTrue", 4, 0);
    transmissionGateTrue.setInputs(0, State.UNKNOWN); // Entrée
    transmissionGateTrue.setInputs(1, State.TRUE); // Contrôle (ON)
    transmissionGateTrue.evaluate();
    System.out.println("Test TransmissionGate contrôle TRUE -> " + transmissionGateTrue.getOutput(0) + " (Attendu : TRUE)");

    System.out.println("Test du TransmissionGate avec contrôle FALSE...");
    TransmissionGate transmissionGateFalse = new TransmissionGate("TransmissionGateFalse", 4, 0);
    transmissionGateFalse.setInputs(0, State.TRUE); // Entrée
    transmissionGateFalse.setInputs(1, State.UNKNOWN); // Contrôle (OFF)
    transmissionGateFalse.evaluate();
    System.out.println("Test TransmissionGate contrôle FALSE -> " + transmissionGateFalse.getOutput(0) + " (Attendu : FALSE)");

    // Cas où l'entrée est FALSE
    System.out.println("Test du TransmissionGate avec entrée FALSE et contrôle TRUE...");
    TransmissionGate transmissionGateFalseInput = new TransmissionGate("TransmissionGateFalseInput", 4, 0);
    transmissionGateFalseInput.setInputs(0, State.FALSE); // Entrée
    transmissionGateFalseInput.setInputs(1, State.TRUE); // Contrôle (ON)
    transmissionGateFalseInput.evaluate();
    System.out.println("Test TransmissionGate entrée FALSE -> " + transmissionGateFalseInput.getOutput(0) + " (Attendu : FALSE)");
}


public static void testMUX() {
    System.out.println("Test du MUX");

    // Test 1 : Sélection de la première entrée
    MUX mux = new MUX("MUX_Test1", 2, 0, 0);
    mux.setInputs(0, State.TRUE);  // Entrée 0
    mux.setInputs(1, State.FALSE); // Entrée 1
    mux.setInputs(2, State.UNKNOWN);  // Entrée 2
    mux.setInputs(3, State.ERROR); // Entrée 3
    mux.setInputs(4, State.ERROR);  // Sélection bit 0
    mux.setInputs(5, State.FALSE); // Sélection bit 1
    mux.evaluate();
    System.out.println("Sortie (attendue TRUE) : " + mux.getOutput(0));

    // Test 2 : Sélection de la deuxième entrée
    MUX mux2 = new MUX("MUX_Test2", 2, 0, 0);
    mux2.setInputs(0, State.TRUE);  // Entrée 0
    mux2.setInputs(1, State.FALSE); // Entrée 1
    mux2.setInputs(2, State.UNKNOWN);  // Entrée 2
    mux2.setInputs(3, State.ERROR); // Entrée 3
    mux2.setInputs(4, State.UNKNOWN);  // Sélection bit 0
    mux2.setInputs(5, State.FALSE); // Sélection bit 1
    mux2.evaluate();
    System.out.println("Sortie (attendue FALSE) : " + mux2.getOutput(0));
    
        // Test 2 : Sélection de la deuxième entrée
    MUX mux3 = new MUX("MUX_Test2", 2, 0, 0);
    mux3.setInputs(0, State.TRUE);  // Entrée 0
    mux3.setInputs(1, State.FALSE); // Entrée 1
    mux3.setInputs(2, State.UNKNOWN);  // Entrée 2
    mux3.setInputs(3, State.ERROR); // Entrée 3
    mux3.setInputs(4, State.FALSE);  // Sélection bit 0
    mux3.setInputs(5, State.TRUE); // Sélection bit 1
    mux3.evaluate();
    System.out.println("Sortie (attendue UNKNOWN) : " + mux3.getOutput(0));

     // Test 2 : Sélection de la deuxième entrée
     MUX mux4 = new MUX("MUX_Test2", 2, 0, 0);
     mux4.setInputs(0, State.TRUE);  // Entrée 0
     mux4.setInputs(1, State.FALSE); // Entrée 1
     mux4.setInputs(2, State.UNKNOWN);  // Entrée 2
     mux4.setInputs(3, State.ERROR); // Entrée 3
     mux4.setInputs(4, State.TRUE);  // Sélection bit 0
     mux4.setInputs(5, State.TRUE); // Sélection bit 1
     mux4.evaluate();
    System.out.println("Sortie (attendue ERROR) : " + mux4.getOutput(0));

}

public static void testDEMUX() {
    System.out.println("Test du DEMUX");

    // Test 1 : Distribution correcte des entrées vers les sorties
    DEMUX demux = new DEMUX("DEMUX_Test1", 2, 0, 0);
    demux.setInputs(0, State.TRUE); // Entrée
    demux.setInputs(1, State.TRUE); // Sélection bit 0
    demux.setInputs(2, State.TRUE);  // Sélection bit 1
    demux.evaluate();
    System.out.println("Sortie 0 (attendue FALSE) : " + demux.getOutput(0));
    System.out.println("Sortie 1 (attendue FALSE) : " + demux.getOutput(1));
    System.out.println("Sortie 2 (attendue FALSE) : " + demux.getOutput(2));
    System.out.println("Sortie 3 (attendue TRUE) : " + demux.getOutput(3));
}
public static void testBitFINDER() {
    System.out.println("Test du BitFINDER");

    // Test 1 : Recherche du bit à l'index 2
    BitFINDER bitFinder = new BitFINDER("BitFinder_Test1", 4, 3, 0, 0);
    bitFinder.setInputs(0, State.FALSE);
    bitFinder.setInputs(1, State.TRUE);
    bitFinder.setInputs(2, State.FALSE);
    bitFinder.setInputs(3, State.ERROR);
    bitFinder.evaluate();
    System.out.println("Sortie (attendue ) : " + bitFinder.getOutput(0));
}

public static void testRegister() {
    System.out.println("Test du Register");

    // Test 1 : Écriture sur le registre
    Register register = new Register("Register_Test1", 4, 0, 0);
    register.setInputs(0, State.TRUE);   // Donnée 0
    register.setInputs(1, State.FALSE);  // Donnée 1
    register.setInputs(2, State.TRUE);   // Donnée 2
    register.setInputs(3, State.FALSE);  // Donnée 3
    register.setInputs(4, State.TRUE);   // Horloge (activée)
    register.setInputs(5, State.TRUE);   // Write Enable (activé)
    register.evaluate();
    System.out.println("Sortie (attendue TRUEFALSETRUEFALSE  pour la 1ère entrée) : " + register.getOutput(0)+register.getOutput(1)+register.getOutput(2)+register.getOutput(3));
    register.setInputs(5, State.FALSE);   // Write Enable (activé)
    register.evaluate();
    System.out.println("Sortie (attendue TRUEFALSETRUEFALSE  pour la 1ère entrée) : " + register.getOutput(0)+register.getOutput(1)+register.getOutput(2)+register.getOutput(3));

    // Test 2 : Écriture sur le registre
    Register register2 = new Register("Register_Test2", 4, 0, 0);
    register2.setInputs(0, State.TRUE);   // Donnée 0
    register2.setInputs(1, State.FALSE);  // Donnée 1
    register2.setInputs(2, State.TRUE);   // Donnée 2
    register2.setInputs(3, State.FALSE);  // Donnée 3
    register2.setInputs(4, State.FALSE);   // Horloge (activée)
    register2.setInputs(5, State.TRUE);   // Write Enable (activé)
    register2.evaluate();
    System.out.println("Sortie (attendue UNKNOWNUNKNOWNUNKNOWNUNKNOWN pour la 1ère entrée) : " + register2.getOutput(0)+register2.getOutput(1)+register2.getOutput(2)+register2.getOutput(3));
    // Test 3 : Écriture sur le registre
    Register register3 = new Register("Register_Test3", 4, 0, 0);
    register3.setInputs(0, State.TRUE);   // Donnée 0
    register3.setInputs(1, State.FALSE);  // Donnée 1
    register3.setInputs(2, State.TRUE);   // Donnée 2
    register3.setInputs(3, State.FALSE);  // Donnée 3
    register3.setInputs(4, State.TRUE);   // Horloge (activée)
    register3.setInputs(5, State.FALSE);   // Write Enable (activé)
    register3.evaluate();
    System.out.println("Sortie (attendue UNKNOWNUNKNOWNUNKNOWNUNKNOWN pour la 1ère entrée) : " + register3.getOutput(0)+register3.getOutput(1)+register3.getOutput(2)+register3.getOutput(3));
}
public static void testNEGATOR() {
    System.out.println("Test du NEGATOR");

    // Test 1 : Inversion d'un seul bit
    NEGATOR negator = new NEGATOR("NEGATOR_Test1", 1, 0, 0);
    negator.setInputs(0, State.UNKNOWN); // Entrée TRUE
    negator.evaluate();
    System.out.println("Sortie (attendue FALSE) : " + negator.getOutput(0));
    NEGATOR negator2 = new NEGATOR("NEGATOR_Test1", 2, 0, 0);
    // Test 2 : Inversion de plusieurs bits
    negator2.setInputs(0, State.FALSE); // Entrée FALSE
    negator2.setInputs(1, State.ERROR);  // Entrée TRUE
    negator2.evaluate();
    System.out.println("Sortie 1 (attendue TRUE) : " + negator2.getOutput(0));
    System.out.println("Sortie 2 (attendue FALSE) : " + negator2.getOutput(1));
}

public static void testCounter() {
    System.out.println("Test du Counter");

    // Test 1 : Compteur en mode incrémentation
    Counter counter = new Counter("Counter_Test1", 2, 0, 0);
    counter.setInputs(0, State.FALSE);  // Initial Count
    counter.setInputs(1, State.FALSE);  // Initial Count
    counter.setInputs(2, State.TRUE);  // Horloge
    counter.setInputs(3, State.TRUE);  // Incrémentation

    counter.evaluate();
    System.out.println("Compteur après incrémentation (attendu 01 TRUE FALSE) : " + counter.getOutput(0)+ counter.getOutput(1));

    // Test 2 : Compteur en mode incrémentation
    Counter counter2 = new Counter("Counter_Test1", 2, 0, 0);
    counter2.setInputs(0, State.TRUE);  // Initial Count
    counter2.setInputs(1, State.FALSE);  // Initial Count
    counter2.setInputs(2, State.TRUE);  // Horloge
    counter2.setInputs(3, State.FALSE);  // Incrémentation

    counter2.evaluate();
    System.out.println("Compteur après incrémentation (attendu 00 FALSE FALSE) : " + counter2.getOutput(0)+ counter2.getOutput(1));

    // Test 1 : Compteur en mode incrémentation
    Counter counter3 = new Counter("Counter_Test1", 2, 0, 0);
    counter3.setInputs(0, State.FALSE);  // Initial Count
    counter3.setInputs(1, State.FALSE);  // Initial Count
    counter3.setInputs(2, State.TRUE);  // Horloge
    counter3.setInputs(3, State.TRUE);  // Incrémentation

    counter3.evaluate();
    System.out.println("Compteur après incrémentation (attendu 01 TRUE FALSE) : " + counter3.getOutput(0)+ counter3.getOutput(1));
}


public static void testDIVIDER() {
    int n = 2; // Nombre de bits pour les entrées
    Composant divider = new DIVIDER("DIV1", n, 0, 0);
    
    // A = 3 (11 en binaire), B = 2 (10 en binaire)
    divider.setInputs(0, State.TRUE);  // A[0] = 1
    divider.setInputs(1, State.TRUE);  // A[1] = 1
    divider.setInputs(2, State.FALSE); // B[0] = 0
    divider.setInputs(3, State.FALSE);  // B[1] = 1

    divider.evaluate();

    System.out.println("Test DIVIDER (A=3, B=2) -> Quotient: [" +
            divider.getOutput(0) + ", " + divider.getOutput(1) + "] Reste: [" +
            divider.getOutput(2) + ", " + divider.getOutput(3) + "] " +
            "(Attendu : Quotient=[1, 1], Reste=[1, 0])"); // 3 / 2 = 1, reste 1
}


}
