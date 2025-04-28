public class test_gates {
   
    public static void main(String[] args) {
        System.out.println("******************** On commence les test ********************\n");
      
        testET();
        testOU();
        testNON();
        testXOR();
        testXNOR();
        testNOR();
        testNAND();
        testEVENParity();
        testODDParity();
        System.out.println("********************   On fini les test  ********************\n");
        
}

public static void testET() {
    /*on donne un id et des coordnnée x et y a chaque composant puis on initialise les inpout et on appelle 
      la fonction getOutput*/
    Composant PorteET = new ET("ET", 0, 2);
    PorteET.setInputs(0, State.TRUE);
    PorteET.setInputs(1, State.FALSE);
    PorteET.evaluate();
    System.out.println("Test de la porte ET (TRUE,FALSE) -> " + PorteET.getOutput(0) );
}

public static void testOU() {
    Composant PorteOU = new OU("OU", 1, 1);
    PorteOU.setInputs(0, State.TRUE);
    PorteOU.setInputs(1, State.FALSE);
    PorteOU.evaluate();
    System.out.println("Test de la porte OU (TRUE,FALSE) -> " + PorteOU.getOutput(0));
}

public static void testNON() {
    Composant PorteNON = new NON("NON", 2, 3);
    PorteNON.setInputs(0, State.TRUE);
    PorteNON.evaluate();
    System.out.println("Test de la porte NON (TRUE) -> " + PorteNON.getOutput(0));
}

public static void testXOR() {
    Composant PorteXOR = new XOR("XOR", 3,4);
    PorteXOR.setInputs(0, State.TRUE);
    PorteXOR.setInputs(1, State.FALSE);
    PorteXOR.evaluate();
    System.out.println("Test de la porte XOR (TRUE,FALSE) -> " + PorteXOR.getOutput(0) );
}

public static void testXNOR() {
    Composant PorteXOOR = new XNOR("XNOR", 4, 5);
    PorteXOOR.setInputs(0, State.TRUE);
    PorteXOOR.setInputs(1, State.FALSE);
    PorteXOOR.evaluate();
    System.out.println("Test de la porte XNOR (TRUE,FALSE) -> " + PorteXOOR.getOutput(0) );
}

public static void testNOR() {
    Composant PorteNOR = new NOR("NOR", 5, 6);
    PorteNOR.setInputs(0, State.FALSE);
    PorteNOR.setInputs(1, State.FALSE);
    PorteNOR.evaluate();
    System.out.println("Test de la porte NOR (FALSE,FALSE) -> " + PorteNOR.getOutput(0) );
}

public static void testNAND() {
    Composant nandGate = new NAND("NAND1", 6, 0);
    nandGate.setInputs(0, State.TRUE);
    nandGate.setInputs(1, State.TRUE);
    nandGate.evaluate();
    System.out.println("Test NAND (1,1) -> " + nandGate.getOutput(0) + " (Attendu : FALSE)");
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

}