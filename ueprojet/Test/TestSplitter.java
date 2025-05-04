package projet;

public class TestSplitter {
    public static void main(String[] args) {
        System.out.println("=== Début du test Splitter ===");

        System.out.println("\nTest avec Splitter à 3 sorties :");
        Splitter splitter3 = new Splitter("splitter3", 0, 0, 3);
        splitter3.setInputs(0, State.True); 
        splitter3.evaluate(); 
        System.out.println("Etat des sorties après évaluation : ");
        for (int i = 0; i < 3; i++) {
            System.out.println("Sortie " + i + ": " + splitter3.getOutput(i));
        }

        System.out.println("\nTest avec Splitter à 5 sorties :");
        Splitter splitter5 = new Splitter("splitter5", 1, 0, 5);
        splitter5.setInputs(0, State.ERROR); 
        splitter5.evaluate();
        System.out.println("Etat des sorties après évaluation : ");
        for (int i = 0; i < 5; i++) {
            System.out.println("Sortie " + i + ": " + splitter5.getOutput(i));
        }

        try {
            System.out.println("\nTest avec un Splitter invalide (nombre de sorties négatif) :");
            Splitter invalidSplitter = new Splitter("invalidSplitter", 2, 0, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println("=== Fin du test Splitter ===");
    }
}
