package GRAPHIQUE;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// Classe qui représente un fil entre deux composants
public class Wire {
    private AbstractComponent startComponent; // Composant de départ
    private AbstractComponent endComponent;   // Composant de fin
    private java.awt.Point startPoint;        // Point de départ du fil
    private java.awt.Point endPoint;          // Point de fin du fil
    public Ligne line;
    private Color color;

    // Constructeur pour créer un fil entre deux composants
    public Wire(AbstractComponent start, AbstractComponent end,Ligne l) {
        this.startComponent = start;
        this.endComponent = end;
        this.line=l;

        // Initialiser les points de départ et de fin à la position des composants
        this.startPoint = new java.awt.Point(start.getX(), start.getY());
        this.endPoint = new java.awt.Point(end.getX(), end.getY());
    }

    // Méthode pour mettre à jour la position du fil en fonction des déplacements des composants
    public void updateWirePosition() {
        // Met à jour la position du point de départ et de fin en fonction de la position des composants
        startPoint.setLocation(startComponent.getX(), startComponent.getY());
        endPoint.setLocation(endComponent.getX(), endComponent.getY());
    }
    public void setColor(Color c)
    {
    	this.color=c;
    }

    // Méthode pour dessiner le fil sur l'écran
    public void drawWire(Graphics g) {
        g.setColor(Color.BLACK); // Définit la couleur du fil (noir)
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y); // Dessine la ligne entre les points
    }

    // Méthode pour lier un fil à un composant, utile pour la mise à jour
    public void setStartComponent(AbstractComponent start) {
        this.startComponent = start;
        updateWirePosition();  // Met à jour la position après modification
    }

    public void setEndComponent(AbstractComponent end) {
        this.endComponent = end;
        updateWirePosition();  // Met à jour la position après modification
    }

    public java.awt.Point getStartp()
    {
    	return this.startPoint;
    }
    public java.awt.Point getEndp()
    {
    	return this.endPoint;
    }
}
