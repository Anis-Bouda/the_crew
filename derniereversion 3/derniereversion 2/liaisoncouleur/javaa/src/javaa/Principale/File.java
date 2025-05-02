package Principale ;
public class File {
   private Composant Source;
   private Composant Destination;
   private int sourceOutputIndex;
   private State value;
   private int destinationInputIndex; 
   private String id;
   // private int[] path; 
   
   public File(String name,Composant Source, Composant Destination, 
		   int destinationInputIndex, int sourceOutputIndex) {
        this.id=name;
	    this.Source = Source;
        this.value = State.UNKNOWN;
	    this.Destination = Destination;
	    this.destinationInputIndex = destinationInputIndex; 
	    this.sourceOutputIndex = sourceOutputIndex; 
	 
   }
  

   public Composant getSource() {
	    return Source;
   }
   
   public String getId() {
    return this.id;
    }

   public Composant getDestination() {
	   return Destination;
 }

   public State getValue() {
	    return value;
}
   
public void update() {
    State newValue = Source.getOutput(sourceOutputIndex);
    System.out.println("Mise à jour du fil " + id + " : " + newValue);

    if (newValue != value) {
        this.value = newValue;
        if (Destination != null) { // Vérification que Destination n'est pas null
            Destination.setInputs(destinationInputIndex, this.value); // Mettez à jour l'entrée du composant
        } else {
            System.out.println("Erreur : Le fil n'a pas de destination valide !");
        }
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
           "Source=" + (Source != null ? Source.getid() : "null") +
           ", Destination=" + (Destination != null ? Destination.getid() : "null") +
           ", value=" + value +
           ", sourceOutputIndex=" + sourceOutputIndex +
           ", destinationInputIndex=" + destinationInputIndex +
           '}';
}
}