package GRAPHIQUE;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;

public class Point extends JPanel {
    private Consumer<java.awt.Point> onMoveListener;
    public void setOnMoveListener(Consumer<java.awt.Point> listener) {
        this.onMoveListener = listener;
    }

    private static final int DOT_SPACING = 10;
    private static final int SQUARE_SIZE = 50;
    private static final int SELECTION_RADIUS = 10;

    private int squareX, squareY;
    private int mouseX, mouseY;
    private boolean dragging = false;

    private List<int[]> lignes = new ArrayList<>();
    private int startX = -1, startY = -1, endX = -1, endY = -1;
    private boolean drawing = false;
    private int selectedLineIndex = -1;

    public Point() {
        setLayout(new BorderLayout());

        // Navbar (barre de navigation)
        add(new Navbar(), BorderLayout.NORTH);

        // Panel de dessin
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.LIGHT_GRAY);
                for (int x = 0; x < getWidth(); x += DOT_SPACING) {
                    for (int y = 0; y < getHeight(); y += DOT_SPACING) {
                        g2d.fillOval(x - 1, y - 1, 3, 3);
                    }
                }

            

              

                if (drawing) {
                    drawLine(g2d, startX, startY, endX, endY);
                }
            }

            private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
                if (x1 == x2 || y1 == y2) {
                    g2d.drawLine(x1, y1, x2, y2);
                } else {
                    g2d.drawLine(x1, y1, x2, y1);
                    g2d.drawLine(x2, y1, x2, y2);
                }
            }

            private void drawSmallSquare(Graphics2D g2d, int x, int y) {
                final int smallSquareSize = 6;
                g2d.fillRect(x - smallSquareSize / 2, y - smallSquareSize / 2, smallSquareSize, smallSquareSize);
            }
        };
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setFocusable(true);

        // Ajout du listener pour gérer l'interaction avec la souris
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();

                for (int i = 0; i < lignes.size(); i++) {
                    int[] ligne = lignes.get(i);
                    if (isNearPoint(ligne[0], ligne[1], e.getX(), e.getY()) ||
                        isNearPoint(ligne[2], ligne[3], e.getX(), e.getY())) {
                        selectedLineIndex = i;
                        repaint();
                        return;
                    }
                }

                if (e.getX() >= squareX && e.getX() <= squareX + SQUARE_SIZE &&
                    e.getY() >= squareY && e.getY() <= squareY + SQUARE_SIZE) {
                    dragging = true;
                    mouseX = e.getX() - squareX;
                    mouseY = e.getY() - squareY;
                } else {
                    if (!drawing) {
                        startX = alignToGrid(e.getX());
                        startY = alignToGrid(e.getY());
                        endX = startX;
                        endY = startY;
                        drawing = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
                if (drawing) {
                    if (startX != endX || startY != endY) {
                        lignes.add(new int[]{startX, startY, endX, endY});
                    }
                    drawing = false;
                    repaint();
                }
            }
        });

        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragging) {
                    squareX = e.getX() - mouseX;
                    squareY = e.getY() - mouseY;
                    repaint();
                } else if (drawing) {
                    endX = alignToGrid(e.getX());
                    endY = alignToGrid(e.getY());
                    repaint();
                }
            }
        });

        add(drawPanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && selectedLineIndex != -1) {
                    lignes.remove(selectedLineIndex);
                    selectedLineIndex = -1;
                    repaint();
                }
            }
        });
    }

    private int alignToGrid(int coord) {
        return Math.round((float) coord / DOT_SPACING) * DOT_SPACING;
    }

    private boolean isNearPoint(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) < SELECTION_RADIUS && Math.abs(y1 - y2) < SELECTION_RADIUS;
    }

    // Classe Navbar (barre de navigation)
    private class Navbar extends JPanel {
        public Navbar() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setBackground(Color.DARK_GRAY);
            setPreferredSize(new Dimension(getWidth(), 40));

            JLabel title = new JLabel("Circuit Design Tool");
            title.setForeground(Color.WHITE);
            add(title);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Carré et lignes interactives");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            Point panel = new Point();
            frame.add(panel);
            frame.setVisible(true);
            panel.requestFocusInWindow();
        });
    }
}
