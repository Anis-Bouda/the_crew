package GRAPHIQUE;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/*pour controler la taille de la fenetre*/
import java.awt.Dimension;
//import java.awt.Toolkit;

public class MainView extends JFrame {

    public MainView() {
        // Set window title
        setTitle("Logisim");

        // Set main layout panel
        MainLayout mainLayout = new MainLayout();
        setContentPane(mainLayout);

        // Set menu barNOT
        setJMenuBar(mainLayout.createMenuBar());

        //Enable maximized window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        /*Forcer la taille minimum de la fenetre*/
        setMinimumSize(new Dimension(400, 400));
        /*Centrer la fenêtre */
        setLocationRelativeTo(null);
        
        /*pour que les raccourcis clavier fonctionne correctement*/
        setFocusable(true);
        requestFocusInWindow();


        // Default close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Empêcher la fermeture automatique

        // Ajouter un listener pour la fermeture
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int closedBtn = JOptionPane.showConfirmDialog(
                        MainView.this,
                        "Voulez-vous quitter la page ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (closedBtn == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0); // Fermer complètement l'application
                }
            }
        });

        // Show window
        setVisible(true);
    }
    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(MainView::new);
    }
}