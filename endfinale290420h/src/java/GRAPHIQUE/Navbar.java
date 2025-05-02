package GRAPHIQUE ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar extends JPanel {
    public Navbar() {
        setPreferredSize(new Dimension(100, 40)); // Hauteur de 40px (équivalent à h-10 en Tailwind)
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        // Logo à gauche (cliquable)
        JLabel logo = new JLabel("Logo");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 16));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        logo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change le curseur pour un pointeur
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Logo cliqué");
                // Ajouter ton action ici (par exemple, ouvrir une autre fenêtre)
            }
        });
        add(logo, BorderLayout.WEST);

        // Panel pour les liens alignés à droite (cliquables)
        JPanel linksPanel = new JPanel();
        linksPanel.setOpaque(false); // Fond transparent
        linksPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));

        String[] links = {"Accueil", "Services", "Contact"};
        for (String text : links) {
            JButton linkButton = new JButton(text);
            linkButton.setForeground(Color.WHITE);
            linkButton.setFont(new Font("Arial", Font.PLAIN, 14));
            linkButton.setBackground(Color.DARK_GRAY); // Fond transparent pour les boutons
            linkButton.setFocusPainted(false); // Enlever l'effet de focus
            linkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change le curseur pour un pointeur

            // Action à effectuer lorsque le lien est cliqué
            linkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(text + " cliqué");
                    // Ajouter ton action ici pour chaque lien
                }
            });

            linksPanel.add(linkButton);
        }

        add(linksPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Navbar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 100);

            Navbar navbar = new Navbar();
            frame.add(navbar, BorderLayout.NORTH);

            frame.setVisible(true);
        });
    }
}
