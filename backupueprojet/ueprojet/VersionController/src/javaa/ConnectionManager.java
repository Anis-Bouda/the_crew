import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

public class ConnectionManager {
    private static AbstractComponent selectedComponent;
    private static String selectedPortType; // "input" ou "output"
    private static int selectedPortIndex;
    private static Point selectedGlobalPoint;
    private static JLayeredPane layeredPane;

    // Liste des connexions créées
    public static List<Connection> connections = new ArrayList<>();
    private static Circuit circuit;

    public static void setCircuit(Circuit c) {
        circuit = c;
    }

    // Permet de définir le JLayeredPane sur lequel seront ajoutées les lignes
    public static void setLayeredPane(JLayeredPane pane) {
        layeredPane = pane;
    }

    /**
     * Méthode appelée lors du clic sur un port d’un composant.
     * Si c’est le premier port sélectionné, on le stocke.
     * Sinon, si le deuxième port est compatible (types complémentaires et composants différents),
     * on crée la connexion graphique et on stocke la connexion pour mise à jour ultérieure.
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
                // Créer la ligne de connexion en utilisant votre classe Ligne
                Ligne line = new Ligne();
                line.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                line.setOpaque(false);
                System.out.println("start: " + start + ", end: " + end);
                line.setConnection(start, end);
                layeredPane.add(line, Integer.valueOf(1)); // Ajout sur le calque approprié
                layeredPane.repaint();
                System.out.println("Connexion créée !");

                // Stocker la connexion pour mise à jour ultérieure
                Connection conn = new Connection(selectedComponent, selectedPortType, selectedPortIndex,
                        comp, portType, portIndex, line);

                //Création du Fil logique (toujours de la sortie vers l'entrée)
                Composant sourceComp = selectedComponent.getComposant();
                Composant destComp = comp.getComposant();
                int sourceIndex = "output".equals(selectedPortType) ? selectedPortIndex : portIndex;
                int destIndex = "input".equals(portType) ? portIndex : selectedPortIndex;

                Fil fil = new Fil("fil_" + connections.size(), sourceComp, destComp, destIndex, sourceIndex);
                conn.setFil(fil);
 
                //Ajouter ce fil à ton Circuit
                Circuit.getInstance().addFil(fil);

                connections.add(conn);

                
            }
            reset();
        }
    }

    
    /**
     * Met à jour les connexions associées à un composant donné.
     * Cette méthode doit être appelée lors du déplacement du composant.
     */
    public static void updateConnectionsForComponent(AbstractComponent comp) {
        for (Connection conn : connections) {
            if (conn.compSource == comp || conn.compTarget == comp) {
                // Récupérer les positions globales des ports grâce à la méthode getGlobalPortLocation de AbstractComponent
                Point start = conn.compSource.getGlobalPortLocation(conn.portTypeSource, conn.portIndexSource);
                Point end = conn.compTarget.getGlobalPortLocation(conn.portTypeTarget, conn.portIndexTarget);
                conn.line.setConnection(start, end);
            }
        }
    }

    // Réinitialise la sélection de port
    public static void reset() {
        selectedComponent = null;
        selectedPortType = null;
        selectedPortIndex = -1;
        selectedGlobalPoint = null;
    }
}
