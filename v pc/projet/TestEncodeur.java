package projet;

public class TestEncodeur {
	public static void TestEncodeur() {
        Composant encodeur = new Encodeur("Encodeur1", 10, 0);
        encodeur.setInputs(0, State.False);
        encodeur.setInputs(1, State.False);
        encodeur.setInputs(2, State.True);
        encodeur.setInputs(3, State.False);
        encodeur.evaluate();
        System.out.println("Test Encodeur (0001) -> [" +
                encodeur.getOutput(0) + ", " + encodeur.getOutput(1) + "] ");
    }
}
