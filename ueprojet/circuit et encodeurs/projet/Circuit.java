package projet;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
     private List<Composant> composants;
     private List<Fil> fils;
     
     public Circuit() {
    	  this.composants = new ArrayList<>();
    	  this.fils = new ArrayList<>();
     }
     
     public void addComposant(Composant c) {
    	   composants.add(c);
     }
     
     public void addFil(Fil f) {
    	   fils.add(f);
     }
     
     public void Calculer() {
    	 for(Composant C : composants) {
    		 C.Calculer();
     }}
     
     public void afficheState() {
    	 for(Composant C: composants) {
    		 System.out.println(C.getid() + "," + C.getstate());
     }}}

