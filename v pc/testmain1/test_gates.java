public class test_gates {
   
    public static void main(String[] args) {
        System.out.println("******************** On commence les test ********************\n");
      
        testET();
        testOU();
        testNON();
        testXOR();
        testXNOR();
        testNOR();

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

}