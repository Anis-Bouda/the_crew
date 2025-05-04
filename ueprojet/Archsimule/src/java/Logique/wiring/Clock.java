package Logique.wiring;
import Logique.Principale.*;

public class Clock extends Composant implements Runnable {

    private long period; /* période en millisecondes*/
    private boolean running = false;
    private Thread thread;

    public Clock(String id, int x, int y, long period) {
        super(id, x, y);
        this.period = period;

        /* Clock n'a pas d'entrées, mais une seule sortie initialisée à UNKNOWN
        this.outputs.add(State.UNKNOWN);*/
    }

    @Override
    public void evaluate() {
        /* L'évaluation ne fait rien de plus, l'horloge change d'état automatiquement */
        /* Mais on pourrait l'utiliser pour forcer une mise à jour de l'état*/
    }

    public void start() {
        if (!running) {
            running = true;
            thread = new Thread(this, id + "-Thread");
            thread.start();
        }
    }

    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        State currentState = State.FALSE;
        while (running) {
            // Alterne entre ON et OFF
            currentState = (currentState == State.TRUE) ? State.FALSE : State.TRUE;
            this.outputs.set(0, currentState);
            this.state = currentState;

            /* affiche dans la console */
            System.out.println("[" + id + "] Clock tick: " + currentState);

            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}




