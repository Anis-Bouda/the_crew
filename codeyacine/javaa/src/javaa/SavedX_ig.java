import java.awt.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class SavedX_ig extends AbstractComponent {

    private final int width = 50;  // Exemple de largeur
    private final int height = 60; // Exemple de hauteur

    public SavedX_ig() {
        super("saved_x", 1, 1); // 1 entrée, 1 sortie
        setOpaque(false); // Fond transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);

        int x = 10, y = 0;
        int w = width, h = height;

        // Dessin d'un rectangle pour le composant
        g2.fillRect(x, y, w, h);

        // Ajouter d'autres détails graphiques si nécessaire
    }
}
