public class Fil {
   private Composant Source;
   private Composant Destination;
   private State value;
   private int sourceOutputIndex;
   private int destinationInputIndex; 
   private String id;
   // private int[] path; 
   
   public Fil(String name,Composant Source, Composant Destination, 
		int destinationInputIndex, int sourceOutputIndex) {
		this.id=name;
	    this.Source = Source;
	    this.Destination = Destination;
	    this.value = State.UNKNOWN;
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
       System.out.println("🔁 Mise à jour du fil " + id + " : " + newValue);

       if (newValue != value) {
           this.value = newValue;
           if (Destination != null) { // Vérification que Destination n'est pas null
               Destination.setInputs(destinationInputIndex, this.value); // Mettez à jour l'entrée du composant
           } else {
               System.out.println("Erreur : Le fil n'a pas de destination valide !");
           }
       }
   }
}

