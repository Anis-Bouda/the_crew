import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ligne extends JPanel {
    // Liste des segments créés (libres ou connexions)
    private List<LineSegment> segments = new ArrayList<>();
    private LineSegment currentSegment;
    private boolean drawing = false;
    
    // Seuils et constantes
    private final double DELETE_THRESHOLD = 5.0;  // Pour la suppression
    private final int MIN_LINE_LENGTH = 10;         // Longueur minimale pour valider une ligne
    private final int GRID_SPACING = 10;            // Espacement de la grille dans le designArea

    public Ligne() {
        setBackground(Color.WHITE);

        // Gestion du clic pour dessin libre et suppression
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = alignToGrid(e.getPoint());
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Suppression d'un segment si le clic est proche
                    for (int i = segments.size() - 1; i >= 0; i--) {
                        if (isPointNearSegment(p, segments.get(i))) {
                            segments.remove(i);
                            repaint();
                            System.out.println("Segment supprimé !");
                            return;
                        }
                    }
                } else {
                    // Début du dessin libre avec alignement sur la grille
                    if (!drawing) {
                        currentSegment = new LineSegment(p, p, false);
                        drawing = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e) && drawing && currentSegment != null) {
                    currentSegment.end = alignToGrid(e.getPoint());
                    if (currentSegment.start.distance(currentSegment.end) >= MIN_LINE_LENGTH) {
                        segments.add(currentSegment);
                        System.out.println("Ligne créée !");
                    } else {
                        System.out.println("Ligne trop courte, annulée.");
                    }
                    currentSegment = null;
                    drawing = false;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing && currentSegment != null) {
                    currentSegment.end = alignToGrid(e.getPoint());
                    repaint();
                }
            }
        });
    }

    /**
     * Aligne le point donné sur la grille.
     */
    private Point alignToGrid(Point p) {
        int x = Math.round(p.x / (float) GRID_SPACING) * GRID_SPACING;
        int y = Math.round(p.y / (float) GRID_SPACING) * GRID_SPACING;
        return new Point(x, y);
    }

    /**
     * Ajoute une ligne de connexion avec coude.
     * Les points sont alignés sur la grille.
     * @param start Le point de départ (global)
     * @param end Le point d'arrivée (global)
     */
    public void setConnection(Point start, Point end) {
        // Vider les segments précédents afin de ne conserver qu'une seule connexion
        segments.clear();
        Point s = alignToGrid(start);
        Point e = alignToGrid(end);
        if (s.distance(e) >= MIN_LINE_LENGTH) {
            segments.add(new LineSegment(s, e, true));
            System.out.println("Connexion créée !");
        } else {
            System.out.println("Connexion trop courte, annulée.");
        }
        currentSegment = null;
        drawing = false;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        // Dessiner tous les segments sauvegardés
        for (LineSegment seg : segments) {
            drawSegment(g, seg);
        }

        // Dessiner le segment en cours de dessin
        if (currentSegment != null) {
            drawSegment(g, currentSegment);
        }
    }

    /**
     * Dessine un segment en coude si nécessaire (d'abord horizontal, puis vertical).
     */
    private void drawSegment(Graphics g, LineSegment seg) {
        if (seg.start.x != seg.end.x && seg.start.y != seg.end.y) {
            // Dessiner un coude : segment horizontal puis vertical
            g.drawLine(seg.start.x, seg.start.y, seg.end.x, seg.start.y);
            g.drawLine(seg.end.x, seg.start.y, seg.end.x, seg.end.y);
        } else {
            g.drawLine(seg.start.x, seg.start.y, seg.end.x, seg.end.y);
        }
    }

    /**
     * Vérifie si le point p est proche d'un segment.
     */
    private boolean isPointNearSegment(Point p, LineSegment seg) {
        if (seg.start.x != seg.end.x && seg.start.y != seg.end.y) {
            Point corner = new Point(seg.end.x, seg.start.y);
            return isPointNearLine(p, seg.start, corner, DELETE_THRESHOLD)
                    || isPointNearLine(p, corner, seg.end, DELETE_THRESHOLD);
        } else {
            return isPointNearLine(p, seg.start, seg.end, DELETE_THRESHOLD);
        }
    }

    /**
     * Vérifie si p est à proximité de la droite formée par a et b.
     */
    private boolean isPointNearLine(Point p, Point a, Point b, double threshold) {
        return ptSegDist(a.x, a.y, b.x, b.y, p.x, p.y) <= threshold;
    }

    /**
     * Calcule la distance entre un point (px,py) et un segment (x1,y1)-(x2,y2).
     */
    private double ptSegDist(double x1, double y1, double x2, double y2, double px, double py) {
        double dx = x2 - x1, dy = y2 - y1;
        if (dx == 0 && dy == 0)
            return Math.sqrt((px - x1) * (px - x1) + (py - y1) * (py - y1));
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        if (t < 0) {
            dx = px - x1;
            dy = py - y1;
        } else if (t > 1) {
            dx = px - x2;
            dy = py - y2;
        } else {
            double projX = x1 + t * dx;
            double projY = y1 + t * dy;
            dx = px - projX;
            dy = py - projY;
        }
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Classe interne pour représenter un segment de ligne
    private static class LineSegment {
        Point start;
        Point end;
        boolean isConnection;

        public LineSegment(Point start, Point end, boolean isConnection) {
            this.start = start;
            this.end = end;
            this.isConnection = isConnection;
        }
    }
}
