import Principale.*;
import gates.*;
import Arithmetics.*;
import Memory.*;
import plexers.*;
import wiring.*;
import Input_output.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.security.Principal;
/*car on manipule le point de départ du fil et sa fin */
import java.awt.Point;
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
     
     public void addFil(Connection f) {
    	   connections.add(f);
     }
     
     public void simulate(int MAX_ITERATIONS) throws Exception {
    	    boolean stable=true;
    	    int iteration;
    	    for (iteration = 0; iteration < MAX_ITERATIONS; iteration++) {

    	        // Évaluation des composants
    	        for (AbstractComponent component : components) {
    	            /*if (component instanceof Generator_ON_OFF) 
    	            {
        		((Generator_ON_OFF) component).updateOutputFromState(); // forcer la sortie logique
    		    }*/
    	            List<Principale.State> inputs=component.getInputStates();
		    if (!inputs.isEmpty()) 
		    {
    			component.setInputsStates(inputs);
		    }
    	            Composant composant=component.getComposant();
    	            if(composant!=null)
    	            {
    	            	composant.evaluate();
    	            }
    	            component.updateOutputState();
    	            component.repaint();
    	        }
		        /*Mise à jour des fils*/
    	        for (Connection conn : connections) 
    	        {
				   Point start = conn.compSource.getGlobalPortLocation(conn.portTypeSource, conn.portIndexSource);
				   Point end = conn.compTarget.getGlobalPortLocation(conn.portTypeTarget, conn.portIndexTarget);
				   /*mettre la point de départ du fil et son point fin à jour */
			       conn.line.setConnection(start, end);
				   /*affecter la nouvelle ligne graphique au wire graphique */
				   conn.filview.setline(conn.line);
                   if (conn.filLogic != null && conn.filview != null) 		        {
        			Principale.State previousValue = conn.filLogic.getValue();
            			conn.filLogic.update(); // met à jour la valeur logique
            			if (previousValue != conn.filLogic.getValue()) {
    	                stable = false;
    	            }

            	/*Met à jour la couleur selon la valeur logique*/
                   switch (conn.filLogic.getValue()) 
                   {
                	case TRUE:
                    	    conn.filview.line.setConnection(conn.filview.line.getStart(),conn.filview.line.getEnd(),java.awt.Taskbar.State.NORMAL);
                            break;
                	case ERROR:
                    	    conn.filview.line.setConnection(conn.filview.line.getStart(),conn.filview.line.getEnd(),java.awt.Taskbar.State.ERROR);
                            break;
                 	case FALSE:
                	    conn.filview.line.setConnection(conn.filview.line.getStart(),conn.filview.line.getEnd(),java.awt.Taskbar.State.OFF);
                            break;
                	default:
                    	    conn.filview.line.setConnection(conn.filview.line.getStart(),conn.filview.line.getEnd(),java.awt.Taskbar.State.INDETERMINATE);
                            break;
            	}
        	}
             }
         }
	 if (stable) 
	 {
    	 	System.out.println("Point fixe atteint après " + (iteration + 1) + " itérations.");
    	 	return;
    	  }
		  throw new Exception("Erreur : circuit instable après " + MAX_ITERATIONS + " itérations.");
    	  }

     
     public List<AbstractComponent> getComponents() {
         return components;
     }

     public List<Connection> getConnections() {
         return connections;
     }
}