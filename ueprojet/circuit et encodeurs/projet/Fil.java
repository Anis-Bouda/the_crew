package projet;

import java.util.ArrayList;
import java.util.List;

public class Fil {
   private Composant Source;
   private Composant Destination;
   private int value;
   private List<int[]> path;
   
   public Fil(Composant Source, Composant Destination, int value) {
	    this.Source = Source;
	    this.Destination = Destination;
	    this.value = value;
	    this.path = new ArrayList<>();
   }
  
   public void define_path(List<int[]> new_path) {
	    this.path = new_path;
   }
   
   public Composant getSource() {
	    return Source;
   }
   
   public Composant getDestination() {
	   return Destination;
 }}
