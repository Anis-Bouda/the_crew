import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Taskbar.State;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

public class NOT_ig extends AbstractComponent {
    private final int width = 50;  // Largeur du triangle
    private final int height = 60; // Hauteur du triangle
    private State state; // État interne du NOT (optionnel)

    public NOT_ig() {
        super("NOT", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false); // Rendre le fond transparent

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY()); // Conversion en coordonnées globales

                // Vérification sur les ports d'entrée
                for (Port port : inputPorts) {
                    if (port.getPosition().distance(clickPoint) < 10) {
                        System.out.println("✅ Port d'entrée NOT sélectionné !");
                    }
                }
                // Vérification sur les ports de sortie
                for (Port port : outputPorts) {
                    if (port.getPosition().distance(clickPoint) < 10) {
                        System.out.println("✅ Port de sortie NOT sélectionné !");
                    }
                }
            }
        });
    }

    // Méthode pour affecter un signal à l'entrée du NOT
    public void setInputState(State s) {
        if (!inputPorts.isEmpty()) {
            inputPorts.get(0).setState(s);
        }
    }

    // Méthode pour récupérer le signal de sortie du NOT
    public State getOutputState() {
        if (!outputPorts.isEmpty()) {
            return outputPorts.get(0).getState();
        }
        return State.INDETERMINATE;
    }

    // Méthode d'évaluation qui inverse le signal d'entrée
    public void evaluate() {
        if (inputPorts.size() == 1 && outputPorts.size() == 1) {
            State inputState = inputPorts.get(0).getState();
            State outputState;
            if (inputState == State.ERROR || inputState == State.INDETERMINATE) {
                outputState = inputState;
            } else {
                // Inversion logique
                if (inputState == State.NORMAL) {
                    outputState = State.OFF;
                } else {
                    outputState = State.NORMAL;
                }
            }
            outputPorts.get(0).setState(outputState);
            this.state = outputState;  
            repaint();  // Met à jour l'affichage après évaluation
        } else {
            throw new IllegalStateException("Erreur d'évaluation : La porte NOT doit avoir exactement 1 entrée et 1 sortie.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
        // Définition des couleurs et de l'épaisseur des traits
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 10, y = 0;
        int w = width, h = height;

        // 🔺 Dessiner le triangle de la porte NOT
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(x, y);                 // Coin supérieur gauche
        triangle.lineTo(x + w, y + h / 2);       // Pointe droite
        triangle.lineTo(x, y + h);               // Coin inférieur gauche
        triangle.closePath();
        g2.draw(triangle);

        // ⚪ Dessiner le petit cercle de sortie (indiquant l'inversion)
        int circleRadius = 6;
        int circleX = x + w + 2;
        int circleY = y + h / 2 - circleRadius / 2;
        g2.drawOval(circleX, circleY, circleRadius, circleRadius);

        // ➡️ Dessiner l'entrée (ligne à gauche)
        g2.drawLine(x - 20, y + h / 2, x, y + h / 2);

        // ➡️ Dessiner la sortie (ligne à droite)
        g2.drawLine(circleX + circleRadius, y + h / 2, circleX + 40, y + h / 2);
    }
    
    public void updateOutput(State output) {
    	if (!outputPorts.isEmpty()) {
            // Mise à jour de l'état de la sortie dans le modèle
            outputPorts.get(0).setState(output);
            System.out.println("🖥️ Sortie mise à jour dans la vue : " + output);
           // juste pour afficher le dessin 
            repaint(); 
    }}
    
    public State getSelectedInput(int index) {
    	if (!inputPorts.isEmpty()) {
            return inputPorts.get(index).getState();
        }
    	return State.INDETERMINATE;
    }
   
}

