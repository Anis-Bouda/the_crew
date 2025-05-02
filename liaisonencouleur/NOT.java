public class NOT extends Composant {

	    public NOT(String id,int x, int y)
	    {
	        super(id,x,y);
	        this.addInput(State.UNKNOWN);
	        this.addOutput(State.UNKNOWN);
	    }

	    @Override
	    public void evaluate()
	    {
	        if (this.inputs.size() == 1 && this.outputs.size()==1)
	        {
	            State input1=this.inputs.get(0);
	            State output;
	            System.out.println("💡 NOT " + id + " inputs = " + inputs);
	            if(input1==State.ERROR || input1==State.UNKNOWN)
	            {
	                output=input1;
	            }
	            else
	            {
	                if(input1==State.True)
	                {
	                    output=State.False;
	                }
	                else
	                {
	                    output=State.True;
	                }
	            }
	            this.outputs.set(0,output);
	            this.state=output;
	            System.out.println("💡 NOT " + id + " outputs= " + inputs);  
	        }
	        else
	        {
	            throw new IllegalStateException("Erreur d'évaluation : La porte NON doit avoir exactement 1 input et 1 output.");
	        }
	    }
	    
	    public void setInputState(State s) {
	        this.inputs.set(0, s);
	    }

	    public State getInputState(int index) {
	        return this.inputs.get(index);
	    }

	    public State getOutputState() {
	        return this.outputs.get(0);
	    }
	}
