import java.util.ArrayList;
import java.util.List;

public abstract class Composant {
	protected String id;
	protected State state;
	protected List<State> inputs;
	protected List<State> outputs;
	protected double x, y;

    public Composant() {
	}

	public Composant(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
        this.state=State.UNKNOWN;
        this.inputs=new ArrayList<>();
        this.outputs=new ArrayList<>();
	}
	
	public void addInput(State C) {
		inputs.add(C);
	}
	
	public void addInputs(List<State> bits) {
		inputs.addAll(bits);
	} 

	public void addOutputs(List<State> bits) {
		outputs.addAll(bits);
	} 

	public void addOutput(State C) {
		outputs.add(C);
	}
	
	public State getstate() {
		return this.state; 
	}
	
	public String getid() {
		return id;
	}
	
	public void setstate(State state) {
		this.state = state;
	}
	public void setInputs(int index, State value)
    {
        this.inputs.set( index, value );
    }

	public void setOutputs(int index, State value)
	{
		this.outputs.set( index,value );
	}

	public State getInput(int index)
	{
		return this.inputs.get(index);
	}

	public List<State> getOutputs()
	{
		return this.outputs;
	}

	public List<State> getInputs()
	{
		return this.inputs;
	}

    public State getOutput(int index)
    {
        return this.outputs.get(index);
    }
	
	public abstract void evaluate();
}