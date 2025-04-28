public class test_Memory {
    public static void main(String[] args) {
        System.out.println("******************** On commence les test ********************\n");
      
        testBasculeSR();
        testBasculeD();
        testBasculeT();
        testBasculeJK();

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

}
