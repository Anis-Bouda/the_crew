package Logique.Memory;
import Logique.Principale.*;
public class BasculeD extends Composant {
	public BasculeD(String id, int x, int y) {
        super(id,x,y);
        this.addInput(State.UNKNOWN); // D
        this.addInput(State.UNKNOWN); // CLK
        this.addOutput(State.UNKNOWN); // Q
        this.addOutput(State.UNKNOWN); // Qbar
   }
	
    public void evaluate() {
       if (this.inputs.size() == 2 && this.outputs.size() == 2) {
        State D=this.inputs.get(0);
        State CLK=this.inputs.get(1);
        System.out.println("Évaluation de la Bascule D, D: " + D + ", CLK: " + CLK);
        State[] outputs = new State[2];
        /* recuperer les outputs */
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = this.outputs.get(i);
       }
      // le cas ou unknown 
      if (D == State.UNKNOWN && CLK == State.UNKNOWN) {
         outputs[0] = State.UNKNOWN;
         outputs[1] = State.UNKNOWN;
      } 
      else if ((D == State.ERROR || CLK == State.ERROR) || 
               (D == State.ERROR && CLK == State.UNKNOWN) ||
               (D == State.UNKNOWN && CLK == State.ERROR)){
             outputs[0] = State.ERROR;
             outputs[1] = State.ERROR;
         }
      else {
       // Condition d'évaluation de la bascule D
       if (CLK == State.TRUE) {
          outputs[0] = D; // Q reçoit D
           outputs[1] = (D == State.TRUE) ? State.FALSE : State.TRUE; // Qbar reçoit l'inverse de Q
         } }
      // si CLK = False alors on conserve Q 
      for (int i = 0; i < outputs.length; i++) {
          this.outputs.set(i, outputs[i]);
} }
     else {
       throw new IllegalStateException("Erreur d'évaluation : La Bascule D doit avoir exactement 2 inputs et 2 outputs.");
 }}}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
