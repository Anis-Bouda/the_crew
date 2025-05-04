package projet;

public class BoutonTest {
    public static void main(String[] args) {
        Bouton bouton = new Bouton("B1", 50, 100);

        // État initial
        System.out.println("État initial du bouton : " + bouton.getState());

        // Évaluation initiale
        bouton.evaluate();

        // on evalue une deuxieme fois
        bouton.evaluate();
     
        // On force l'état à ERROR
        bouton.setState(State.ERROR);
        bouton.evaluate();
        
     // On force l'état à UNKNOWN
        bouton.setState(State.UNKNOWN);
        bouton.evaluate();
    }
}
