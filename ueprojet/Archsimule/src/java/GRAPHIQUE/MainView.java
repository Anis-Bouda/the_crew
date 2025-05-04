package GRAPHIQUE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JLayeredPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MainView extends JFrame {
    private JLayeredPane circuitDesignArea = null;

    public MainView() {
        super("Logisim");
        
        // Définir la taille minimale de la fenêtre
        setMinimumSize(new Dimension(400, 400));
        
        // Look and Feel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Window settings
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        MainView.this,
                        "Voulez-vous quitter l'application ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        // Initial editor (empty circuit)
        openNewCircuitEditor();

        setVisible(true);
    }

    /**
     * Crée un circuit vide et l'affiche.
     */
    public void openNewCircuitEditor() {
        // crée une nouvelle zone de design
        circuitDesignArea = new JLayeredPane();
        circuitDesignArea.setLayout(null);
        circuitDesignArea.setPreferredSize(new Dimension(1500, 1000));

        // injecte dans MainLayout
        MainLayout mainLayout = new MainLayout(circuitDesignArea);
        setContentPane(mainLayout);
        setJMenuBar(mainLayout.createMenuBar());

        revalidate();
        repaint();
    }

    /**
     * Ouvre une session depuis un fichier XML.
     */
    public void openLoadSessionDialog() {
        File xmlFile = MainLayout.showLoadSessionDialog(this);
        if (xmlFile != null) {
            // 1) nouvelle zone de design
            JLayeredPane loadedArea = new JLayeredPane();
            loadedArea.setLayout(null);
            loadedArea.setPreferredSize(new Dimension(1500, 1000));

            // 2) nouveau layout + chargement
            MainLayout mainLayout = new MainLayout(loadedArea);
            mainLayout.loadSession(xmlFile);

            // 3) affichage dans la fenêtre principale
            setContentPane(mainLayout);
            setJMenuBar(mainLayout.createMenuBar());
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
