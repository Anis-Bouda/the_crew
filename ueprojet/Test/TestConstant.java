package projet;

public class TestConstant {
    public static void main(String[] args) {
        System.out.println("=== Début du test Constant ===");
        System.out.println("\nTest avec Constant initialisé avec State.True :");
        Constant constantTrue = new Constant("constantTrue", 0, 0, State.True);
        constantTrue.evaluate(); 
        System.out.println("Valeur de la sortie après évaluation : " + constantTrue.getValeur());

        System.out.println("\nTest de mise à jour de la valeur de Constant avec State.False :");
        constantTrue.setValeur(State.False);
        constantTrue.evaluate();
        System.out.println("Valeur de la sortie après mise à jour et évaluation : " + constantTrue.getValeur());

        try {
            System.out.println("\nTest avec une sortie incorrecte (plus d'une sortie) :");
            Constant constantInvalid = new Constant("constantInvalid", 1, 1, State.True);
            constantInvalid.addOutput(State.False); 
            constantInvalid.evaluate();
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println("=== Fin du test Constant ===");
    }
}

