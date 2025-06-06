package GRAPHIQUE_IG;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Taskbar.State;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ligne extends JPanel {
    private List<LineSegment> segments = new ArrayList<>();
    private LineSegment currentSegment;
    private boolean drawing = false;
    private Point start;
    private Point end;

    private final double DELETE_THRESHOLD = 5.0;
    private final int MIN_LINE_LENGTH = 10;
    private final int GRID_SPACING = 10;

    public Ligne() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = alignToGrid(e.getPoint());
                if (SwingUtilities.isRightMouseButton(e)) {
                    for (int i = segments.size() - 1; i >= 0; i--) {
                        if (isPointNearSegment(p, segments.get(i))) {
                            segments.remove(i);
                            repaint();
                            System.out.println("Segment supprimé !");
                            return;
                        }
                    }
                } else {
                    if (!drawing) {
                        currentSegment = new LineSegment(p, p, false, State.INDETERMINATE);
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

    private Point alignToGrid(Point p) {
        int x = Math.round(p.x / (float) GRID_SPACING) * GRID_SPACING;
        int y = Math.round(p.y / (float) GRID_SPACING) * GRID_SPACING;
        return new Point(x, y);
    }

    // Ajoute une ligne avec un état spécifique
    public void setConnection(Point start, Point end, State state) {
        segments.clear();
        Point s = alignToGrid(start);
        Point e = alignToGrid(end);
        if (s.distance(e) >= MIN_LINE_LENGTH) {
            LineSegment seg = new LineSegment(s, e, true, state);
            segments.add(seg);
            System.out.println("Connexion créée avec état : " + state);
        } else {
            System.out.println("Connexion trop courte, annulée.");
        }
        currentSegment = null;
        drawing = false;
        repaint();
    }

    // Surcharge : si pas de state fourni, on utilise INDETERMINATE
    public void setConnection(Point start, Point end) {
        setConnection(start, end, State.INDETERMINATE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        for (LineSegment seg : segments) {
            g2d.setColor(getColorFromState(seg.state));
            drawSegment(g2d, seg);
        }

        if (currentSegment != null) {
            g2d.setColor(Color.BLUE); // couleur temporaire pour segment en cours
            drawSegment(g2d, currentSegment);
        }
    }

    private Color getColorFromState(State state) {
        switch (state) {
            case NORMAL:
                return Color.GREEN;
            case OFF:
                return Color.BLUE;
            case ERROR:
                return Color.RED;
            default:
                return Color.GRAY;
        }
    }

    private void drawSegment(Graphics g, LineSegment seg) {
        if (seg.start.x != seg.end.x && seg.start.y != seg.end.y) {
            g.drawLine(seg.start.x, seg.start.y, seg.end.x, seg.start.y);
            g.drawLine(seg.end.x, seg.start.y, seg.end.x, seg.end.y);
        } else {
            g.drawLine(seg.start.x, seg.start.y, seg.end.x, seg.end.y);
        }
    }

    private boolean isPointNearSegment(Point p, LineSegment seg) {
        if (seg.start.x != seg.end.x && seg.start.y != seg.end.y) {
            Point corner = new Point(seg.end.x, seg.start.y);
            return isPointNearLine(p, seg.start, corner, DELETE_THRESHOLD)
                    || isPointNearLine(p, corner, seg.end, DELETE_THRESHOLD);
        } else {
            return isPointNearLine(p, seg.start, seg.end, DELETE_THRESHOLD);
        }
    }

    private boolean isPointNearLine(Point p, Point a, Point b, double threshold) {
        return ptSegDist(a.x, a.y, b.x, b.y, p.x, p.y) <= threshold;
    }

    private double ptSegDist(double x1, double y1, double x2, double y2, double px, double py) {
        double dx = x2 - x1, dy = y2 - y1;
        if (dx == 0 && dy == 0)
            return Math.hypot(px - x1, py - y1);
        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        if (t < 0) return Math.hypot(px - x1, py - y1);
        if (t > 1) return Math.hypot(px - x2, py - y2);
        double projX = x1 + t * dx;
        double projY = y1 + t * dy;
        return Math.hypot(px - projX, py - projY);
    }

    //  Classe interne avec état
    private static class LineSegment {
        Point start;
        Point end;
        boolean isConnection;
        State state;

        public LineSegment(Point start, Point end, boolean isConnection, State state) {
            this.start = start;
            this.end = end;
            this.isConnection = isConnection;
            this.state = state;
        }

        public void setState(State s) {
            this.state = s;
        }
    }

    public void setStart(Point start){
        this.start=start;
    }
    
    public void setEnd(Point end){
        this.end=end;
    }
    
    public Point getStart(){
        return this.start;
    }
    
    public Point getEnd(){
        return this.end;
    }
}
