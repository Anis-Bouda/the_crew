package GRAPHIQUE;
import java.awt.Color;
import java.awt.Point;
import java.awt.Taskbar;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import Logique.Principale.Composant;
import Logique.Principale.File;
import Logique.Principale.State;

// ICI : supprime cette mauvaise importation !
/* import com.sun.tools.jconsole.JConsoleContext.ConnectionState; */



public class ConnectionManager {
    private static AbstractComponent selectedComponent;
    private static String selectedPortType; // "input" ou "output"
    private static int selectedPortIndex;
    private static Point selectedGlobalPoint;
    private static JLayeredPane layeredPane;
    
    // Garde ton tableau depuis MainLayout
    public static List<MainLayout.ConnectionState> connectionStates = MainLayout.connectionStates;


    // Liste des connexions créées
    public static List<Connection> connections = new ArrayList<>();

    // Permet de définir le JLayeredPane sur lequel seront ajoutées les lignes
    public static void setLayeredPane(JLayeredPane pane) {
        layeredPane = pane;
    }

    /**
     * Méthode appelée lors du clic sur un port d’un composant.
     */
    public static void portClicked(AbstractComponent comp, String portType, int portIndex, Point globalPoint) {
        if (selectedComponent == null) {
            // Premier port sélectionné
            selectedComponent = comp;
            selectedPortType = portType;
            selectedPortIndex = portIndex;
            selectedGlobalPoint = globalPoint;
            System.out.println("Premier port sélectionné (" + portType + ")");
            

          
        } else {
            // Vérifier que les types sont complémentaires et que ce n'est pas le même composant
            if (!selectedPortType.equals(portType) && selectedComponent != comp) {
                Point start, end;
                // Toujours de la sortie vers l'entrée
                if ("output".equals(selectedPortType) && "input".equals(portType)) {
                    start = selectedGlobalPoint;
                    end = globalPoint;
                } else if ("input".equals(selectedPortType) && "output".equals(portType)) {
                    start = globalPoint;
                    end = selectedGlobalPoint;
                } else {
                    reset();
                    return;
                }
                
                // Créer la ligne de connexion
                Ligne line = new Ligne();
                line.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                line.setOpaque(false);
                line.setConnection(start, end);
                line.setStart(start);
                line.setEnd(end);
                layeredPane.add(line, Integer.valueOf(1));
                layeredPane.repaint();
                System.out.println("Connexion créée !");

                // Stocker la connexion
                Connection conn = new Connection(selectedComponent, selectedPortType, selectedPortIndex,
                                                 comp, portType, portIndex, line);

                Composant srcLogic = selectedComponent.getComposant();
                Composant dstLogic = comp.getComposant();

                int outputIndex, inputIndex;

                if ("output".equals(selectedPortType)) {
                    outputIndex = selectedPortIndex;
                    inputIndex = portIndex;
                } else {
                    outputIndex = portIndex;
                    inputIndex = selectedPortIndex;
                }

                // Le fil logique
                String id = "File_" + connections.size();
                File fil = new File(id, srcLogic, dstLogic, inputIndex, outputIndex);

                // Le fil graphique
                Wire wire = new Wire(selectedComponent, comp, line);
                String componentName = comp.getComponentName();
          
                switch (fil.getValue()) {
                    case TRUE:
                        wire.setColor(Color.GREEN);
                        break;
                    case FALSE:
                        wire.setColor(Color.BLUE);
                        break;
                    case UNKNOWN:
                        wire.setColor(Color.GRAY);
                        break;
                    case ERROR:
                        wire.setColor(Color.RED);
                        break;
                }

                conn.filLogic = fil;
                conn.filview = wire;

                connections.add(conn);
            }
            reset();
        }
    }

    /**
     * Met à jour les connexions associées à un composant donné.
     */
    public static void updateConnectionsForComponent(AbstractComponent comp) {
        for (Connection conn : connections) {
            if (conn.compSource == comp || conn.compTarget == comp) {
                Point start = conn.compSource.getGlobalPortLocation(conn.portTypeSource, conn.portIndexSource);
                Point end = conn.compTarget.getGlobalPortLocation(conn.portTypeTarget, conn.portIndexTarget);
                
                // 1) Met à jour la position graphique de la ligne
                conn.line.setConnection(start, end);

                // 2) Mets aussi à jour les coordonnées mémorisées dans l'objet Ligne
                conn.line.setStart(start);
                conn.line.setEnd(end);

                // 3) (optionnel) Remet la couleur en fonction de l'état logique si besoin
                conn.line.updateConnectionState(convertPrincipaleStateToTaskbarState(conn.filLogic.getValue()));
            }
        }

        // Forcer la mise à jour du visuel
        layeredPane.repaint();
    }


        
    private static Taskbar.State convertPrincipaleStateToTaskbarState(State principaleState) {
        switch (principaleState) {
            case TRUE:
                return Taskbar.State.NORMAL; // ex : TRUE → NORMAL (vert)
            case FALSE:
                return Taskbar.State.OFF;    // ex : FALSE → OFF (bleu)
            case UNKNOWN:
                return Taskbar.State.INDETERMINATE; // ex : UNKNOWN → gris
            case ERROR:
                return Taskbar.State.ERROR;  // ex : ERROR → rouge
            default:
                return Taskbar.State.INDETERMINATE; // par défaut
        }
    }




    // Réinitialise la sélection de port
    public static void reset() {
        selectedComponent = null;
        selectedPortType = null;
        selectedPortIndex = -1;
        selectedGlobalPoint = null;
    }
    public static void refreshAllConnections() {
        if (layeredPane == null) return;
        layeredPane.removeAll();    // vide l’affichage
        connections.clear();        // vide la data
        // (… aucun autre traitement si pas de connexion …)
        SwingUtilities.invokeLater(() -> layeredPane.repaint());
    }

}
