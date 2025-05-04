package projet;

public class LampeTest {
    public static void main(String[] args) {
        // Création d'une lampe
        Lampe lampe = new Lampe("Lampe1", 10, 20);
        
        // Vérification de l'état initial
        System.out.println("État initial: " + lampe.getstate());
        
        // Test avec état TRUE
        lampe.setInputs(0, State.True);
        lampe.evaluate();
        System.out.println("État après TRUE: " + lampe.getstate());
        
        // Test avec état FALSE
        lampe.setInputs(0, State.False);
        lampe.evaluate();
        System.out.println("État après FALSE: " + lampe.getstate());
        
        // Test avec état UNKNOWN
        lampe.setInputs(0, State.UNKNOWN);
        lampe.evaluate();
        System.out.println("État après UNKNOWN: " + lampe.getstate());
        
        // Test avec état ERROR
        lampe.setInputs(0, State.ERROR);
        lampe.evaluate();
        System.out.println("État après ERROR: " + lampe.getstate());
    }
}
