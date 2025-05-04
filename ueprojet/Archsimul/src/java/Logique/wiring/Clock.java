
package Logique.wiring;
import Logique.Principale.*;

public class Clock extends Composant implements Runnable {

    private long period; // période en millisecondes
    private boolean running = false;
    private Thread thread;

    public Clock(String id, int x, int y, long period) {
        super(id, x, y);
        this.period = period;

        // Clock n'a pas d'entrées, mais une seule sortie initialisée à UNKNOWN
        this.outputs.add(State.UNKNOWN);
    }

    @Override
    public void evaluate() {
        // L'évaluation ne fait rien de plus, l'horloge change d'état automatiquement
        // Mais on pourrait l'utiliser pour forcer une mise à jour de l'état
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

            // (optionnel) affiche dans la console
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





/*public class Clock extends Composant{
//  définit la période après laquelle l'horloge change d'état.
private int period;
// compteur
private int cmpt;
private State actv=State.FALSE; 
public Clock(String id, int x, int y, int period) {
	super(id,x,y);
	this.period = period;
	this.cmpt = 0;
	// initialisitation de letat a unknown 
	//this.addInput(State.UNKNOWN);
	this.addOutput(State.UNKNOWN);

}

@Override
public void  evaluate() {
	if (this.inputs.size() == 0 && this.outputs.size() == 1)
 {
    State input = this.actv;;
    State output = State.UNKNOWN;
    
    // le cas ou le state est unknown ou error
	if(input == State.UNKNOWN || input== State.ERROR) {
		output = input;
	}
	else {
	    //start();
	    cmpt++;
    	if(cmpt >= period) {
    		 if(input == State.FALSE) {
    			 output = State.TRUE;
    		 }
    		 else if(input == State.TRUE) {
    			 output = State.FALSE;
    		 }
    		 cmpt = 0;
    }}
    this.outputs.set(0,output);
    this.state=output; 
}
	else
    {
        throw new IllegalStateException("Erreur d'évaluation : Clock doit avoir exactement 0 input et 1 output.");
    }
}
    public void stop()
    {
        this.actv=State.FALSE;
    }
    
    public void start()
    {
      this.actv=State.TRUE;
    }

@Override
public boolean equals(Object obj) {
if (this == obj) {
    return true;
}
if (!super.equals(obj)) {
    return false;
}
Clock objet = (Clock) obj;
return this.period == objet.period && this.cmpt == objet.cmpt;

}

@Override
public int hashCode() {
int result = super.hashCode();
result = 31 * result + this.period;
result = 31 * result + this.cmpt;
return result;
}

@Override
public String toString() {
return super.toString() + ", period = " + this.period + ", compteur=" + this.cmpt;
}

}*/

