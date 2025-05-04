package GRAPHIQUE;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class ComposantPanel extends JPanel {
    private String activeTool = "None";
    private DefaultMutableTreeNode wirrayNode;
    private DefaultTreeModel treeModel;
    private static JTree sharedTree;

    public ComposantPanel() {
        setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Composants");

        DefaultMutableTreeNode Gates = new DefaultMutableTreeNode("Gates");
        DefaultMutableTreeNode Perxers = new DefaultMutableTreeNode("Plexers");
        DefaultMutableTreeNode Memory = new DefaultMutableTreeNode("Memory");
        DefaultMutableTreeNode Arithmetic = new DefaultMutableTreeNode("Arithmetic");
        wirrayNode = new DefaultMutableTreeNode("Wiring");

        root.add(Gates);
        root.add(Perxers);
        root.add(Memory);
        root.add(Arithmetic);
        root.add(wirrayNode);

        // Ajout des éléments sous "Gates"
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
        Gates.add(new DefaultMutableTreeNode("Controlled_Buffer"));
        Gates.add(new DefaultMutableTreeNode("Controlled_inverter"));
        // Ajout des éléments sous "Perxers"
        Perxers.add(new DefaultMutableTreeNode("Multiplexer"));
        Perxers.add(new DefaultMutableTreeNode("Demultiplexer"));
        Perxers.add(new DefaultMutableTreeNode("Decoder"));
        Perxers.add(new DefaultMutableTreeNode("Priority Encoder"));
        Perxers.add(new DefaultMutableTreeNode("Bit Selector"));

        // Ajout des éléments sous "Memory"
        Memory.add(new DefaultMutableTreeNode("D flip flop"));
        Memory.add(new DefaultMutableTreeNode("T flip flop"));
        Memory.add(new DefaultMutableTreeNode("JK flip flop"));
        Memory.add(new DefaultMutableTreeNode("RS flip flop"));
        Memory.add(new DefaultMutableTreeNode("Register"));
        Memory.add(new DefaultMutableTreeNode("Counter"));
        Memory.add(new DefaultMutableTreeNode("RAM"));
        Memory.add(new DefaultMutableTreeNode("ROM"));

        // Ajout des éléments sous "Arithmetic"
        Arithmetic.add(new DefaultMutableTreeNode("Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Subtractor"));
        Arithmetic.add(new DefaultMutableTreeNode("Multiplier"));
        Arithmetic.add(new DefaultMutableTreeNode("Shifter"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Finder"));
        Arithmetic.add(new DefaultMutableTreeNode("Comparator"));
        Arithmetic.add(new DefaultMutableTreeNode("Divider"));
        Arithmetic.add(new DefaultMutableTreeNode("Negator"));

        // Ajout des éléments sous "Wirray"
        wirrayNode.add(new DefaultMutableTreeNode("LED"));
        wirrayNode.add(new DefaultMutableTreeNode("Clock"));
        wirrayNode.add(new DefaultMutableTreeNode("Generator_ON_OFF"));
        wirrayNode.add(new DefaultMutableTreeNode("Power"));
        wirrayNode.add(new DefaultMutableTreeNode("Ground"));
        wirrayNode.add(new DefaultMutableTreeNode("Generator_Undetermine"));
        wirrayNode.add(new DefaultMutableTreeNode("saved_x"));
        wirrayNode.add(new DefaultMutableTreeNode("Transistor"));
        wirrayNode.add(new DefaultMutableTreeNode("Splitter"));
        wirrayNode.add(new DefaultMutableTreeNode("lampe_state"));
        afficherXMLCount();

        treeModel = new DefaultTreeModel(root);
        addXMLNodes();

        JTree tree = new JTree(treeModel);
        sharedTree = tree;

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tree.getRowForLocation(e.getX(), e.getY());
                if (row == -1) return;

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null || !selectedNode.isLeaf()) return;

                String nodeName = selectedNode.getUserObject().toString();
                switch (nodeName) {
                    case "Poke Tool" -> activatePokeTool();
                    case "Edit Tool" -> activateEditTool();
                    case "Select Tool" -> activateSelectTool();
                    case "Writing Tool" -> activateWritingTool();
                    case "Text Tool" -> activateTextTool();
                    case "OutPut" -> activateOutputTool();
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
                    System.out.println("Outil activé : " + activeTool);
                }
            }
        });

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
                if (node == null) return null;
                return new StringSelection(node.getUserObject().toString());
            }
        });

        tree.setCellRenderer(new ComposantTreeCellRenderer());

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(300, 250));

        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> refreshXMLNodes());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(refreshButton, BorderLayout.NORTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addXMLNodes() {
        File folder = new File(".");
        
        // Liste des fichiers XML dans le dossier
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (files != null && files.length > 0) {
            System.out.println("Fichiers XML trouvés : " + files.length);
            
            // Parcourir les fichiers XML trouvés
            for (File file : files) {
                String baseName = file.getName().replaceFirst("[.][^.]+$", "");  // Enlever l'extension .xml
                DefaultMutableTreeNode xmlNode = new DefaultMutableTreeNode("saved_" + baseName);  // Création du nœud
                wirrayNode.add(xmlNode);  // Ajout du nœud au parent
            }
            // Recharger le modèle du JTree pour afficher les nouveaux nœuds
            treeModel.reload(wirrayNode);
        } else {
            System.out.println("Aucun fichier XML trouvé.");
        }
    }
    
    private void refreshXMLNodes() {
        wirrayNode.removeAllChildren();
        wirrayNode.add(new DefaultMutableTreeNode("LED"));
        wirrayNode.add(new DefaultMutableTreeNode("Generator_ON_OFF"));
        wirrayNode.add(new DefaultMutableTreeNode("Generator_Undetermine"));
        addXMLNodes();
        treeModel.reload();
    }

    private void activatePokeTool() {
        activeTool = "Poke Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void activateEditTool() {
        activeTool = "Edit Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    private void activateSelectTool() {
        activeTool = "Select Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    private void activateWritingTool() {
        activeTool = "Writing Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    private void activateTextTool() {
        activeTool = "Text Tool";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    private void activateOutputTool() {
        activeTool = "OutPut";
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    // MÉTHODE PUBLIQUE STATIQUE POUR AJOUTER UN NOEUD DANS WIRRAY
    public static void addSavedComponent(String name) {
        if (sharedTree == null) return;

        DefaultTreeModel model = (DefaultTreeModel) sharedTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        Enumeration<?> e = root.children();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode category = (DefaultMutableTreeNode) e.nextElement();
            if ("Wirray".equals(category.getUserObject().toString())) {
                category.add(new DefaultMutableTreeNode(name));
                model.reload(category);
                break;
            }
        }
    }

    public int getXMLFileCount() {
        // Récupérer le répertoire contenant le fichier .java
        String path = ComposantPanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File directory = new File(path).getParentFile(); // On récupère le répertoire parent du fichier .java

        // Compter les fichiers XML dans ce répertoire
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
        return (files != null) ? files.length : 0;
    }

    // Utilisation dans un autre contexte :
    public void afficherXMLCount() {
        System.out.println("Nombre de fichiers XML : " + getXMLFileCount());
    }

    static class ComposantTreeCellRenderer extends DefaultTreeCellRenderer {
        private Icon resizeIcon(String path, int width, int height) {
            URL resource = getClass().getResource(path);
            if (resource == null) return new ImageIcon();
            ImageIcon originalIcon = new ImageIcon(resource);
            Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            String nodeName = ((DefaultMutableTreeNode) value).getUserObject().toString();
            switch (nodeName) {
                case "OR" -> setIcon(resizeIcon("/icons2/or.png", 25, 25));
                case "BUFFER" -> setIcon(resizeIcon("/icons2/buffer.png", 25, 25));
                case "AND" -> setIcon(resizeIcon("/icons2/and.png", 25, 25));
                case "NOT" -> setIcon(resizeIcon("/icons2/not.png", 25, 25));
                case "NOR" -> setIcon(resizeIcon("/icons2/nor.png", 25, 25));
                case "XOR" -> setIcon(resizeIcon("/icons2/xor.png", 25, 25));
                case "NAND" -> setIcon(resizeIcon("/icons2/nand.png", 25, 25));
                case "XNOR" -> setIcon(resizeIcon("/icons2/xnor.png", 25, 25));
                case "ODD PARITY", "EVEN PARITY" -> setIcon(resizeIcon("/icons2/parity.png", 25, 25));
                case "RAM" -> setIcon(resizeIcon("/icons2/ram.png", 25, 25));
                case "ROM" -> setIcon(resizeIcon("/icons2/ROM.png", 25, 25));
                case "Multiplexer" -> setIcon(resizeIcon("/icons2/multiplixeur.png", 25, 25));
                case "Demultiplexer" -> setIcon(resizeIcon("/icons2/demultiplixeur.png", 25, 25));
                case "Priority Encoder" -> setIcon(resizeIcon("/icons2/bit.jpg", 25, 25));
                case "Register", "Counter" -> setIcon(resizeIcon("/icons2/counter.png", 25, 25));
                case "D flip flop", "T flip flop" -> setIcon(resizeIcon("/icons2/D.png", 25, 25));
                case "JK flip flop" -> setIcon(resizeIcon("/icons2/JK.png", 25, 25));
                case "RS flip flop" -> setIcon(resizeIcon("/icons2/RS.png", 25, 25));
                case "Decoder" -> setIcon(resizeIcon("/icons2/decoder.png", 25, 25));
                case "Adder" -> setIcon(resizeIcon("/icons2/adder.png", 25, 25));
                case "Subtractor" -> setIcon(resizeIcon("/icons2/substractor.png", 25, 25));
                case "Multiplier" -> setIcon(resizeIcon("/icons2/mult.png", 25, 25));
                case "Shifter" -> setIcon(resizeIcon("/icons2/shifter.png", 25, 25));
                case "Bit Adder" -> setIcon(resizeIcon("/icons2/substractor.png", 25, 25));
                case "Comparator" -> setIcon(resizeIcon("/icons2/comparateur.png", 25, 25));
                case "Divider" -> setIcon(resizeIcon("/icons2/diviseur.png", 25, 25));
                case "Bit Finder" -> setIcon(resizeIcon("/icons2/bitfinder.png", 25, 25));
                case "Negator" -> setIcon(resizeIcon("/icons2/negator.png", 25, 25));
            }
            return this;
        }
    }
}
