package GRAPHIQUE ;
import java.awt.Taskbar.State;

public class Port {
    private java.awt.Point position;
    private State state;

    public Port(java.awt.Point position) {
        this.position = position;
        this.state = State.INDETERMINATE; // état initial
    }

    public java.awt.Point getPosition() {
        return position;
    }

    public void setPosition(java.awt.Point position) {
        this.position = position;
    }

    public State getState() {
        return state;
    }

    public void setState(State n) {
        this.state = n;
    }
}
