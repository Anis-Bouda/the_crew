package GRAPHIQUE;

import Logique.Principale.*;
import Logique.gates.*;
import Logique.Arithmetics.*;
import Logique.Memory.*;
import Logique.plexers.*;
import Logique.wiring.*;
import Logique.Input_output.*;

import java.util.ArrayList;
import java.util.List;

import java.security.Principal;
public class Circuit{
     protected List<AbstractComponent> components;
     protected List<Connection> connections;
     
     public Circuit(List<AbstractComponent> c,List<Connection> connections) {
    	  this.components = c;
    	  this.connections = connections;
     }
     
     public void addComposant(AbstractComponent c) {
    	   components.add(c);
     }
     
  public void simulate(int MAX_ITERATIONS) throws Exception {
    	    boolean stable=true;
    	    int iteration;
    	    for (iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
    	        if(stop)
    	        {
                  System.out.println("Simulation arrêtée par l'utilisateur.");
                  return;
    	        }

    	        // Évaluation des composants
    	        for (AbstractComponent component : components) {
    	             if(stop)
    	        {
                  System.out.println("Simulation arrêtée par l'utilisateur.");
                  return;
    	        }
    	            List<State> inputs=component.getInputStates();
		    if (!inputs.isEmpty())
		    {
    			component.setInputsStates(inputs);
		    }
    	            Composant composant=component.getComposant();
    	            if(composant!=null)
    	            {
    	            	composant.evaluate();
    	            	Thread.sleep(200);
    	            }
    	            component.updateOutputState();
    	            ConnectionManager.updateConnectionsForComponent(component);
    //SwingUtilities.invokeLater(component::repaint);

    // Pause entre les composants
    //Thread.sleep(300);
    	        }
		// Mise à jour des fils
    	        for (Connection conn : connections)
    	        {
    	             if(stop)
    	        {
                  System.out.println("Simulation arrêtée par l'utilisateur.");
                  return;
    	        }
                   if (conn.filLogic != null && conn.filview != null) 		        {
        			State previousValue = conn.filLogic.getValue();
            			conn.filLogic.update(); // met à jour la valeur logique
            			if (previousValue != conn.filLogic.getValue()) {
    	                stable = false;
    	            }

                  //Thread.sleep(150);
    	          }
             }
    }
	 if (stable)
	 {
    	 	System.out.println("Point fixe atteint après " + (iteration + 1) + " itérations.");
    	 	return;
    	  }
    	  System.out.println("Fin de la simulation après " + MAX_ITERATIONS + " cycles.");
    	}


     
     public List<AbstractComponent> getComponents() {
         return components;
     }

     public List<Connection> getConnections() {
         return connections;
     }
}
