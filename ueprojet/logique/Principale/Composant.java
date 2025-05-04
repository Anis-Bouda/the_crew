package Principale;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
public abstract class Composant {
	protected String id;
	protected State state;
	protected List<State> inputs;
	protected List<State> outputs;
	protected Point P;

	public Composant(String id, int x , int y ){
		this.id = id;
		this.P=new Point(x,y);
        this.inputs=new ArrayList<>();
        this.outputs=new ArrayList<>();
        this.state=State.UNKNOWN;
	}
	
	public void addInput(State C) {
		inputs.add(C);
	}
	
	public void addOutput(State C) {
		outputs.add(C);
	}
	
	public State getstate() {
		return state; 
	}
	
	public String getid() {
		return id;
	}
	
	public void setstate(State state) {
		this.state = state;
	}
	public void setInputs(int index, State value)
    {
        if (index >= 0 && index < inputs.size()) {
            this.inputs.set(index, value);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }

    public State getOutput(int index)
    {
        if (index >= 0 && index < outputs.size()) {
            return this.outputs.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index de sortie invalide : " + index);
        }
    }
    public void setOutputs(int index, State value) {
        if (index >= 0 && index < outputs.size()) {
            this.outputs.set(index, value);
        } else {
            throw new IndexOutOfBoundsException("Index de sortie invalide : " + index);
        }
    }

	public abstract void evaluate();
}