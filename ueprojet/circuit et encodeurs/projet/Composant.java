package projet;

import java.util.ArrayList;
import java.util.List;

public abstract class Composant {
	protected String id;
	protected boolean state;
	protected List<Composant> inputs = new ArrayList<>();
	protected List<Composant> outputs = new ArrayList<>();
	protected int x, y;

	public Composant(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public void addinput(Composant C) {
		inputs.add(C);
	}
	
	public void addOutput(Composant C) {
		outputs.add(C);
	}
	
	public boolean getstate() {
		return state; 
	}
	
	public String getid() {
		return id;
	}
	
	public void setstate(boolean state) {
		this.state = state;
	}
	
	public abstract void Calculer();
}
