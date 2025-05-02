package Principale;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            System.out.println("[" + id + "] setInputs(" + index + ", " + value + ") - inputs.size = " + inputs.size());
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

	public List<State> getOutputs()
	{
		return this.outputs;
	}

	public List<State> getInputs()
	{
		return this.inputs;
	}

	public abstract void evaluate();

	public boolean equals(Object obj)
	{
		if(!(obj instanceof Composant))
		{
			return false;
		}
		if(this==obj)
		{
			return true;
		}
		Composant objet = (Composant)obj;
		if(this.outputs.size() != objet.outputs.size() || this.inputs.size() !=objet.inputs.size() )
		{
			return false;
		}
		boolean input=true;
		for(int i=0;i<this.inputs.size();i++)
		{
			input=input && this.inputs.get(i)==objet.inputs.get(i);
		}
		boolean output=true;
		for(int i=0;i<this.outputs.size();i++)
		{
			input=input && this.outputs.get(i)==objet.outputs.get(i);
		}
		boolean res=true;
		res=res && input && output && this.id.equals(objet.id) && Objects.equals(this.P, objet.P) && this.state==objet.state;
		return res;
	}
	public String toString()
	{
			return "Composant{" + 
					"id='" + this.id + '\'' +
					", state=" + this.state +
					", inputs=" + this.inputs +
					", outputs=" + this.outputs +
					", P=" + this.P +
					'}';
	}
	public int hashCode()
	{
	int res = 71;
    res = 27 * res + (id != null ? id.hashCode() : 0);
    res = 27 * res + (state != null ? state.hashCode() : 0);

    for (State s : inputs) {
        res = 27 * res + (s != null ? s.hashCode() : 0);
    }
    for (State s : outputs) {
        res = 27 * res + (s != null ? s.hashCode() : 0);
    }
    res = 27 * res + (P != null ? P.hashCode() : 0);

    return res;

	}
	
}