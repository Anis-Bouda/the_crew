public class EVEN extends Composant {
    public EVEN(String id, int x, int y) {
        super(id, x, y);
        /*Initialisation des entrées 4*/
        for (int i = 0; i < 5; i++) {
            this.addInput(State.UNKNOWN); 
        }
        this.addOutput(State.UNKNOWN);  
    }

    public void setInputState(int index, State state) {
        if (index >= 0 && index < inputs.size()) {
            inputs.set(index, state);
        } else {
            throw new IndexOutOfBoundsException("Index d'entrée invalide : " + index);
        }
    }

    public State getOutputState() {
        return outputs.get(0);
    }
    
    @Override
    public void evaluate() {
        if (this.inputs.size() == 5 && this.outputs.size() == 1) {
            int onesCount = 0;
            State res=State.True;
            /*Compter le nombre de bits à true donc 1*/
            for (int i = 0; i < 5; i++) {
                State bitState = this.inputs.get(i);
                
                if (bitState == State.ERROR) {
                    res=State.ERROR; 
                    break; 
                } 
                else 
                {
                    if (bitState == State.UNKNOWN) 
                    {
                        res=State.UNKNOWN;
                        break; 
                    }
                    else 
                    {
                        if (bitState == State.True) 
                        {
                            /*on compte les bits a true donc 1 */
                            onesCount++;  
                        }
                    }
                }
            }
            if(res!=State.ERROR && res!=State.UNKNOWN)
            {
                /* Si le nombre de bits à 1 est paire, la sortie est TRUE*/
                if (onesCount % 2 == 0) 
                {
                    res=State.True;
                } 
                else 
                {
                    res=State.False; 
                }
            }
            this.outputs.set(0,res);
        } else {
            throw new IllegalStateException("ParityEVEN : doit avoir n entrées et 1 sortie.");
        }
    }
}
