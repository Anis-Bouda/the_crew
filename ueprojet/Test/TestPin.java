package projet;

public class TestPin {
    public static void main(String[] args) {
        System.out.println("=== Début du test Pin ===");
        System.out.println("Test de la Pin en mode entrée :");
        Pin inputPin = new Pin("inputPin", 0, 0, true);
        inputPin.setState(State.ERROR);
        inputPin.evaluate();
        System.out.println("Etat de la sortie de la pin d'entrée : " + inputPin.getPinState());

        System.out.println("Test de la Pin en mode sortie :");
        Pin outputPin = new Pin("outputPin", 1, 0, false);
        outputPin.setInputs(0, State.False);
        System.out.println("Etat de la pin de sortie avant réception : " + outputPin.getPinState());
        outputPin.evaluate(); 
        System.out.println("Etat de la pin de sortie après réception : " + outputPin.getPinState());
        
        System.out.println("=== Fin du test Pin ===");
    }
}

