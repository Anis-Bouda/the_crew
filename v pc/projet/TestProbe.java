package projet;

public class TestProbe {
    public static void main(String[] args) {
        System.out.println("=== Début du test Probe ===");

        // Créer un objet Probe
        Probe probe = new Probe("probe1", 0, 0);

        // Tester avec une entrée FALSE
        System.out.println("Test avec entrée FALSE :");
        probe.setInputs(0, State.False);
        probe.evaluate();  // Appeler evaluate pour afficher l'état

        // Tester avec une entrée TRUE
        System.out.println("Test avec entrée TRUE :");
        probe.setInputs(0, State.True);
        probe.evaluate();  // Appeler evaluate pour afficher l'état

        // Tester avec une entrée UNKNOWN
        System.out.println("Test avec entrée UNKNOWN :");
        probe.setInputs(0, State.UNKNOWN);
        probe.evaluate();  // Appeler evaluate pour afficher l'état

        // Tester avec une entrée ERROR
        System.out.println("Test avec entrée ERROR :");
        probe.setInputs(0, State.ERROR);
        probe.evaluate();  // Appeler evaluate pour afficher l'état

        System.out.println("=== Fin du test Probe ===");
    }
}
