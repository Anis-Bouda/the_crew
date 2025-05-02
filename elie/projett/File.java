
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
   @Override
   public boolean equals(Object obj) { 
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    File objet = (File) obj;
    boolean res=true;
    res= res && this.sourceOutputIndex == objet.sourceOutputIndex &&
           this.destinationInputIndex == objet.destinationInputIndex &&
           (this.Source != null && this.Source.equals(objet.Source)) &&
           (this.Destination != null && this.Destination.equals(objet.Destination)) &&
           this.value == objet.value;
    return res;
   }

  @Override
   public int hashCode() {
    int result = 17;
    result = 17 * result + (Source != null ? Source.hashCode() : 0);
    result = 17 * result + (Destination != null ? Destination.hashCode() : 0);
    result = 17 * result + (value != null ? value.hashCode() : 0);
    result = 17 * result + sourceOutputIndex;
    result = 17 * result + destinationInputIndex;
    return result;
  }

   @Override
   public String toString() {
    return "File{" +
           "Source=" + (Source != null ? Source.getId() : "null") +
           ", Destination=" + (Destination != null ? Destination.getId() : "null") +
           ", value=" + value +
           ", sourceOutputIndex=" + sourceOutputIndex +
           ", destinationInputIndex=" + destinationInputIndex +
           '}';
}
}