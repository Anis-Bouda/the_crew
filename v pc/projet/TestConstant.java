package projet;

public class TestConstant {
    public static void main(String[] args) {
        System.out.println("=== Début du test Constant ===");

        // Test 1 : Vérification de la création d'un composant Constant avec un état spécifique
        System.out.println("\nTest avec Constant initialisé avec State.True :");
        Constant constantTrue = new Constant("constantTrue", 0, 0, State.True);
        constantTrue.evaluate();  // L'évaluation devrait propager l'état True à la sortie
        System.out.println("Valeur de la sortie après évaluation : " + constantTrue.getValeur());

        // Test 2 : Mise à jour de la valeur de Constant avec un nouvel état
        System.out.println("\nTest de mise à jour de la valeur de Constant avec State.False :");
        constantTrue.setValeur(State.False);
        constantTrue.evaluate();  // L'évaluation devrait propager le nouvel état False à la sortie
        System.out.println("Valeur de la sortie après mise à jour et évaluation : " + constantTrue.getValeur());

        // Test 3 : Vérification de l'exception si la sortie n'est pas correcte
        try {
            System.out.println("\nTest avec une sortie incorrecte (plus d'une sortie) :");
            Constant constantInvalid = new Constant("constantInvalid", 1, 1, State.True);
            constantInvalid.addOutput(State.False);  // Ajouter une sortie supplémentaire
            constantInvalid.evaluate();  // Cela devrait lancer une exception
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println("=== Fin du test Constant ===");
    }
}

