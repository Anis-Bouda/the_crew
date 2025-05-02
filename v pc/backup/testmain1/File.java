
public class File {
   private Composant Source;
   private Composant Destination;
   private State value;
   private int sourceOutputIndex;
   private int destinationInputIndex; 
   // private int[] path; 
   
   public File(Composant Source, Composant Destination, State value, 
		int destinationInputIndex, int sourceOutputIndex) {
	    this.Source = Source;
	    this.Destination = Destination;
	    this.value = value;
	    this.destinationInputIndex = destinationInputIndex; 
	    this.sourceOutputIndex = sourceOutputIndex; 
	 
     }
  

   public Composant getSource() {
	    return Source;
   }
   
   public Composant getDestination() {
	   return Destination;
 }

   public State getValue() {
	    return value;
}
   
   public void update() {
       State newValue = Source.getOutput(sourceOutputIndex);
       if (newValue != value) {
           value = newValue;
           Destination.setInputs(destinationInputIndex, value);
       }
   }
}