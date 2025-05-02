package projet;

public class TestPin {
    public static void main(String[] args) {
        System.out.println("=== Début du test Pin ===");

        // Test de la Pin en mode entrée (fournissant un signal)
        System.out.println("Test de la Pin en mode entrée :");
        Pin inputPin = new Pin("inputPin", 0, 0, true); // Pin qui fournit un signal
        inputPin.setState(State.ERROR);  // Définir l'état de la pin à TRUE
        inputPin.evaluate();  // Appeler evaluate pour mettre à jour l'état de la sortie
        System.out.println("Etat de la sortie de la pin d'entrée : " + inputPin.getPinState());

        // Test de la Pin en mode sortie (recevant un signal)
        System.out.println("Test de la Pin en mode sortie :");
        Pin outputPin = new Pin("outputPin", 1, 0, false); // Pin qui reçoit un signal
        outputPin.setInputs(0, State.False);  // Définir l'état de l'entrée à FALSE
        System.out.println("Etat de la pin de sortie avant réception : " + outputPin.getPinState());
        outputPin.evaluate();  // Appeler evaluate pour mettre à jour l'état de la pin
        System.out.println("Etat de la pin de sortie après réception : " + outputPin.getPinState());
        
        System.out.println("=== Fin du test Pin ===");
    }
}

