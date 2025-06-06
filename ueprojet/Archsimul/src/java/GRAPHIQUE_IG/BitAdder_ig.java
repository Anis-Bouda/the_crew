package GRAPHIQUE_IG;
import GRAPHIQUE.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import Logique.Arithmetics.BiteADDER;

public class BitAdder_ig extends AbstractComponent {
    private final int width = 50;
    private final int height = 50;
    private final int offsetY = 5;

    public BitAdder_ig() {
        super("BIT ADDER", 3, 2); // 2 entrées (A, B) et 2 sorties (S, Carry)
        setOpaque(false);

         /*la création de la porte logique */ 
        BiteADDER BitAlogique= new BiteADDER("bitADD#"+System.currentTimeMillis(), getX(), getY());
        setComposant(BitAlogique);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Point clickPoint = e.getPoint();
                clickPoint.translate(getX(), getY() + offsetY);

                for (java.awt.Point port : getInputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port d'entrée BIT ADDER sélectionné !");
                    }
                }
                for (java.awt.Point port : getOutputPorts()) {
                    if (port.distance(clickPoint) < 10) {
                        System.out.println(" Port de sortie BIT ADDER sélectionné !");
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        int x = 8, y = 4 + offsetY;
        int w = width, h = height;

        // Dessiner un rectangle pour représenter le Bit Adder
        g2.drawRect(x, y, w, h);

        // Étiquette "Σ" pour indiquer l'addition
        g2.drawString("Σ", x + w / 2 - 5, y + h / 2 + 5);

        // Entrées (A et B)
        g2.drawLine(x - 15, y + (int)(0.1* h), x, y + (int)(0.1 * h));
        g2.drawLine(x - 15, y + (int)(0.4 * h), x, y + (int)(0.4 * h));
        g2.drawLine(x - 15, y + (int)(0.7 * h), x, y + (int)(0.7 * h));

        // Sorties (S et Carry)
        g2.drawLine(x + w, y + (int)(0.2 * h), x + w + 20, y + (int)(0.2 * h)); // S
        g2.drawLine(x + w, y + (int)(0.6 * h), x + w + 20, y + (int)(0.6 * h)); // Carry
    }
}
