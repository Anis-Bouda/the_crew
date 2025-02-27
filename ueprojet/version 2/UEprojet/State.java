import java.util.ArrayList;
import java.util.List;

enum State {
   ZERO(0),
   UN(1),
   I(-1),
   ERROR(-2);

   private final int value;
   public State(int value)
   {
    this.value=value;
   }
   public int getValue() {
    return this.value;
   } 
}
