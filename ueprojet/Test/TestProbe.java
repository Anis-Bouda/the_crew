package projet;

public class TestProbe {
    public static void main(String[] args) {
        System.out.println("=== Début du test Probe ===");

        Probe probe = new Probe("probe1", 0, 0);

        System.out.println("Test avec entrée FALSE :");
        probe.setInputs(0, State.False);
        probe.evaluate();

        System.out.println("Test avec entrée TRUE :");
        probe.setInputs(0, State.True);
        probe.evaluate();

        System.out.println("Test avec entrée UNKNOWN :");
        probe.setInputs(0, State.UNKNOWN);
        probe.evaluate();

        System.out.println("Test avec entrée ERROR :");
        probe.setInputs(0, State.ERROR);
        probe.evaluate();

        System.out.println("=== Fin du test Probe ===");
    }
}
