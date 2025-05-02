package projet;

public class TestSplitter {
    public static void main(String[] args) {
        System.out.println("=== Début du test Splitter ===");

        // Test 1 : Vérification avec un Splitter à 3 sorties
        System.out.println("\nTest avec Splitter à 3 sorties :");
        Splitter splitter3 = new Splitter("splitter3", 0, 0, 3);
        splitter3.setInputs(0, State.True);  // Définir l'entrée à True
        splitter3.evaluate();  // L'évaluation devrait propager l'état True aux sorties
        System.out.println("Etat des sorties après évaluation : ");
        for (int i = 0; i < 3; i++) {
            System.out.println("Sortie " + i + ": " + splitter3.getOutput(i));
        }

        // Test 2 : Vérification avec un Splitter à 5 sorties
        System.out.println("\nTest avec Splitter à 5 sorties :");
        Splitter splitter5 = new Splitter("splitter5", 1, 0, 5);
        splitter5.setInputs(0, State.ERROR);  // Définir l'entrée à False
        splitter5.evaluate();  // L'évaluation devrait propager l'état False aux sorties
        System.out.println("Etat des sorties après évaluation : ");
        for (int i = 0; i < 5; i++) {
            System.out.println("Sortie " + i + ": " + splitter5.getOutput(i));
        }

        // Test 3 : Vérification de la gestion d'une exception pour un nombre de sorties invalide
        try {
            System.out.println("\nTest avec un Splitter invalide (nombre de sorties négatif) :");
            Splitter invalidSplitter = new Splitter("invalidSplitter", 2, 0, -1);  // Cela devrait lancer une exception
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println("=== Fin du test Splitter ===");
    }
}
