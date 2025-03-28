import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class ComposantPanel extends JPanel {
	private String activeTool = "None";
    public ComposantPanel() {
        setLayout(new BorderLayout());

        // 🔹 Création du nœud racine
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Composants");

        // 🔹 Création des catégories
        DefaultMutableTreeNode Gates = new DefaultMutableTreeNode("Gates");
        DefaultMutableTreeNode Perxers = new DefaultMutableTreeNode("Perxers");
        DefaultMutableTreeNode Memory = new DefaultMutableTreeNode("Memory");
        DefaultMutableTreeNode Arithmetic = new DefaultMutableTreeNode("Arithmetic");
        DefaultMutableTreeNode Wirray = new DefaultMutableTreeNode("Wirray");

        // 🔹 Ajout des catégories au nœud racine
        root.add(Gates);
        root.add(Perxers);
        root.add(Memory);
        root.add(Arithmetic);
        root.add(Wirray);

        // 🔹 Ajout des sous-composants
        Gates.add(new DefaultMutableTreeNode("NOT"));
        Gates.add(new DefaultMutableTreeNode("OR"));
        Gates.add(new DefaultMutableTreeNode("AND"));
        Gates.add(new DefaultMutableTreeNode("BUFFER"));
        Gates.add(new DefaultMutableTreeNode("NOR"));
        Gates.add(new DefaultMutableTreeNode("XOR"));
        Gates.add(new DefaultMutableTreeNode("XNOR"));
        Gates.add(new DefaultMutableTreeNode("NAND"));
        Gates.add(new DefaultMutableTreeNode("ODD PARITY"));
        Gates.add(new DefaultMutableTreeNode("EVEN PARITY"));

        Perxers.add(new DefaultMutableTreeNode("Multiplexer"));
        Perxers.add(new DefaultMutableTreeNode("Demultiplexer"));
        Perxers.add(new DefaultMutableTreeNode("Decoder"));
        Perxers.add(new DefaultMutableTreeNode("Priority Encoder"));
        Perxers.add(new DefaultMutableTreeNode("Bit Selector"));

        Memory.add(new DefaultMutableTreeNode("D flip flop"));
        Memory.add(new DefaultMutableTreeNode("T flip flop"));
        Memory.add(new DefaultMutableTreeNode("JK flip flop"));
        Memory.add(new DefaultMutableTreeNode("RS flip flop"));
        Memory.add(new DefaultMutableTreeNode("Register"));
        Memory.add(new DefaultMutableTreeNode("Counter"));
        Memory.add(new DefaultMutableTreeNode("RAM"));
        Memory.add(new DefaultMutableTreeNode("ROM"));

        Arithmetic.add(new DefaultMutableTreeNode("Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Subtractor"));
        Arithmetic.add(new DefaultMutableTreeNode("Multiplier"));
        Arithmetic.add(new DefaultMutableTreeNode("Shifter"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Finder"));
        Arithmetic.add(new DefaultMutableTreeNode("Comparator"));
        Arithmetic.add(new DefaultMutableTreeNode("Divider"));
        Arithmetic.add(new DefaultMutableTreeNode("Negator"));
       
      
       

        // 🔹 Création de l'arbre JTree
        JTree tree = new JTree(new DefaultTreeModel(root));
        
        
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tree.getRowForLocation(e.getX(), e.getY());
                if (row == -1) return; // Si aucun élément n'est sélectionné, on ignore
                
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null || !selectedNode.isLeaf()) return; // Ignore les catégories

                String nodeName = selectedNode.getUserObject().toString();
                
                // Vérifier si l'élément cliqué appartient à "Wirray"
                if (nodeName.equals("Poke Tool")) {
                    activatePokeTool();
                } else if (nodeName.equals("Edit Tool")) {
                    activateEditTool();
                } else if (nodeName.equals("Select Tool")) {
                    activateSelectTool();
                } else if (nodeName.equals("Writing Tool")) {
                    activateWritingTool();
                } else if (nodeName.equals("Text Tool")) {
                    activateTextTool();
                } else if (nodeName.equals("OutPut")) {
                    activateOutputTool();
                }

                System.out.println("Outil activé : " + nodeName);
            }
        });

        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.isLeaf()) {
                    activeTool = selectedNode.getUserObject().toString();
                    // Si propertiesPanel n'est pas défini dans cette classe, commentez ou supprimez cette ligne
                    // propertiesPanel.updateProperties(0, 0, activeTool);
                    System.out.println("Outil activé : " + activeTool);
                }
            }
        });


     // Active le glisser-déposer sur l'arbre
        tree.setDragEnabled(true);
        tree.setTransferHandler(new TransferHandler() {
            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }
            
            @Override
            protected Transferable createTransferable(JComponent c) {
                JTree tree = (JTree) c;
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) {
                    return null;
                }
                // Exporte le nom du composant sous forme de String
                return new StringSelection(node.getUserObject().toString());
            }
        });

        tree.setCellRenderer(new ComposantTreeCellRenderer());

        // 🔹 Ajout du défilement
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(300, 250));

        // 🔹 Ajout de l'arbre au panneau
        add(scrollPane, BorderLayout.CENTER);
    }
    private void activatePokeTool() {
        System.out.println("🔹 Poke Tool activé : Permet d'inspecter un composant.");
        activeTool = "Poke Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Curseur main (👆)
    }

    private void activateEditTool() {
        System.out.println("✏️ Edit Tool activé : Permet d'éditer un composant.");
        activeTool = "Edit Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)); // Curseur texte (|) 
    }

    private void activateSelectTool() {
        System.out.println("🖱️ Select Tool activé : Permet de sélectionner un composant.");
        activeTool = "Select Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); // Curseur croix (+)
    }

    private void activateWritingTool() {
        System.out.println("📝 Writing Tool activé : Permet d'écrire sur le canvas.");
        activeTool = "Writing Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)); // Curseur texte (|)
    }

    private void activateTextTool() {
        System.out.println("🔤 Text Tool activé : Ajoute du texte.");
        activeTool = "Text Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)); // Curseur texte (|)
    }

    private void activateOutputTool() {
        System.out.println("📤 Output Tool activé : Permet de gérer la sortie du circuit.");
        activeTool = "OutPut";
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Curseur normal (↖️)
    }


    // 🔹 Classe interne pour personnaliser l'affichage des icônes
    static class ComposantTreeCellRenderer extends DefaultTreeCellRenderer {

        private Icon resizeIcon(String path, int width, int height) {
            URL resource = getClass().getResource(path);
            if (resource == null) {
                System.err.println("⚠️ Image not found: " + path);
                return new ImageIcon(); // Retourne une icône vide pour éviter les erreurs
            }

            ImageIcon originalIcon = new ImageIcon(resource);
            Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            String nodeName = node.getUserObject().toString();

            // 🔹 Assignation des icônes
            switch (nodeName) {
                

                case "OR":
                    setIcon(resizeIcon("icons2/or.png", 54, 54));
                    break;
                case "BUFFER":
                    setIcon(resizeIcon("icons2/buffer.png", 54, 54));
                    break;
                case "AND":
                    setIcon(resizeIcon("icons2/and.png", 54, 54));
                    break;
                case "NOT":
                    setIcon(resizeIcon("icons2/not.png", 54, 54));
                    break;
                case "NOR":
                    setIcon(resizeIcon("icons2/nor.png", 54, 54));
                    break;
                case "XOR":
                    setIcon(resizeIcon("icons2/xor.png", 54, 54));
                    break;
                case "NAND":
                    setIcon(resizeIcon("icons2/nand.png", 54, 54));
                    break;
                case "XNOR":
                    setIcon(resizeIcon("icons2/xnor.png", 54, 54));
                    break;
                case "ODD PARITY":
                case "EVEN PARITY":
                    setIcon(resizeIcon("icons2/parity.png", 54, 54));
                    break;
                case "RAM":
                    setIcon(resizeIcon("icons2/Capture_d_écran_2025-03-19_à_12.48.20-removebg-preview.png", 54, 54));
                    break;
                case "ROM":
                    setIcon(resizeIcon("icons2/ROM.png", 54, 54));
                    break;
                case "Multiplexer":
                    setIcon(resizeIcon("icons2/multiplixeur.png", 54, 54));
                    break;
                case "Demultiplexer":
                    setIcon(resizeIcon("icons2/demultiplixeur.png", 54, 54));
                    break;
                case "Priority Encoder":
                    setIcon(resizeIcon("icons2/bit.jpg", 54, 54));
                    break;
                    
                    
                case "Register":
                    setIcon(resizeIcon("icons2/counter.png", 54, 54));
                    break;
                case "Counter":
                    setIcon(resizeIcon("icons2/counter.png", 54, 54));
                    break;
                case "D flip flop":
                    setIcon(resizeIcon("icons2/D.png", 54, 54));
                    break;
                case "T flip flop":
                    setIcon(resizeIcon("icons2/D.png", 54, 54));
                    break;
                case "JK flip flop":
                    setIcon(resizeIcon("icons2/JK.png", 54, 54));
                    break;
                case "RS flip flop":
                    setIcon(resizeIcon("icons2/RS.png", 54, 54));
                    break;
                case "Decoder":
                    setIcon(resizeIcon("icons2/decoder.png", 54, 54));
                    break;
                case "Adder":
                    setIcon(resizeIcon("icons2/adder.png", 54, 54));
                    break;
                case "Subtractor":
                    setIcon(resizeIcon("icons2/substractor.png", 54, 54));
                    break;
                case "Multiplier":
                    setIcon(resizeIcon("icons2/mult.png", 54, 54));
                    break;
                case "Shifter":
                    setIcon(resizeIcon("icons2/shifter.png", 54, 54));
                    break;
                case "Bit Adder":
                    setIcon(resizeIcon("icons2/substractor.png", 54, 54));
                    break;
               
                case "Comparator":
                    setIcon(resizeIcon("icons2/comparateur.png", 54, 54));
                    break;
                case "Divider":
                    setIcon(resizeIcon("icons2/diviseur.png", 54, 54));
                    break;
                    
                    
                case "Bit Finder":
                    setIcon(resizeIcon("icons2/bitfinder.png", 54, 54));
                    break;
                    
                case "Negator":
                    setIcon(resizeIcon("icons2/negator.png", 54, 54));
                    break;
                default:
                 
                    break;
            }

            return this;
        }
    }
}     
