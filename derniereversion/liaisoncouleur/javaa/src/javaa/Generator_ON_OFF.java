import Principale.*;
import Input_output.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Taskbar.State;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Generator_ON_OFF extends AbstractComponent {
    private State currentState = State.OFF;  // On démarre par OFF
    // Seuil de proximité pour détecter le port d'entrée (optionnel)
    private final int inputThreshold = 10;
    
    public Generator_ON_OFF() {
        super("GENERATOR"+System.currentTimeMillis(), 0, 1); // 2 entrées, 1 sortie
        setOpaque(false);
        
        /*Création du composant logique associé*/
    	Generator genLogique = new Generator("GENERATOR#"+System.currentTimeMillis(), getX(), getY(),StateMapper.toLogicState(State.OFF));
    	setComposant(genLogique);
        
        // Initialisation des ports
        if (!inputPorts.isEmpty()) {
            inputPorts.get(0).setState(State.INDETERMINATE);
        }
        if (!outputPorts.isEmpty()) {
            outputPorts.get(0).setState(currentState);
        }
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Si la touche Ctrl est enfoncée, on bascule l'état
                if (e.isControlDown()) {
                    toggleState();
                } else {
                    // Sinon, on vérifie si le clic se situe sur le port d'entrée pour, par exemple, initier une connexion
                    Point clickPoint = e.getPoint();
                    boolean clicSurInput = false;
                    for (Port port : inputPorts) {
                        Point portPosition = port.getPosition();
                        if (portPosition.distance(clickPoint) <= inputThreshold) {
                            clicSurInput = true;
                            break;
                        }
                    }
                    if (clicSurInput) {
                        triggerConnection(); // Gérer la création de la ligne de connexion
                    }
                }
            }
        });
    }
    
    // Bascule l'état entre ON (NORMAL) et OFF
    public void toggleState() {
        if (currentState == State.NORMAL) {
            currentState = State.OFF;
            
        } else {
            currentState = State.NORMAL;
        }
        Composant c = getComposant();
    	if (c instanceof Generator) {
        	((Generator) c).setInputs(0, StateMapper.toLogicState(currentState));
        	c.evaluate();
        	updateOutputState(); // si tu veux mettre à jour visuellement
    	}
    	if (!outputPorts.isEmpty()) {
        outputPorts.get(0).setState(currentState);
    	}
        System.out.println("État basculé vers : " + ((currentState == State.NORMAL) ? "ON" : "OFF"));
        repaint();
    }
    
    // Exemple de méthode pour initier une connexion depuis le port d'entrée
    public void triggerConnection() {
        System.out.println("Création d'une ligne depuis le port d'entrée !");
        // Implémenter ici la logique de création de connexion
    }
    
    // Récupération de l'état de sortie
    public State getOutputState() {
        if (!outputPorts.isEmpty()) {
            return outputPorts.get(0).getState();
        }
        return State.INDETERMINATE;
    }
    
    /*public void updateOutputFromState() 
    {
    	if (composant instanceof Generator) 
    	{
        	Generator gen = (Generator) composant;
        	gen.getOutputs().set(0, StateMapper.toLogicState(currentState));  // forcer la sortie logique
    	}

    	// Mettre à jour visuellement aussi si besoin
    	if (!outputPorts.isEmpty()) 
    	{
        	outputPorts.get(0).setState(currentState);  // Port de sortie en synchronisation
    	}
   }*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
        // Dessiner le contour du composant
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);
        g2.drawRect(10, 10, 80, 50);
        
        // Couleur de fond selon l'état (vert translucide pour ON, rouge translucide pour OFF)
        if (currentState == State.NORMAL) {
            g2.setColor(new Color(0, 200, 0, 100));
        } else {
            g2.setColor(new Color(200, 0, 0, 100));
        }
        g2.fillRect(10, 10, 80, 50);
        
        // Affichage du texte indiquant l'état courant
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.setColor(Color.BLACK);
        String text = (currentState == State.NORMAL) ? "ON" : "OFF";
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textX = 10 + (80 - textWidth) / 2;
        int textY = 10 + (50 + g2.getFontMetrics().getAscent()) / 2 - 5;
        g2.drawString(text, textX, textY);
        
        // Optionnel : marquer le port d'entrée en bleu
        if (!inputPorts.isEmpty()) {
            Point inputPos = inputPorts.get(0).getPosition();
            g2.setColor(Color.BLUE);
            g2.fillOval(inputPos.x - 5, inputPos.y - 5, 10, 10);
        }
    }
}
