package GRAPHIQUE;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

public class ComposantPanel extends JPanel {
    private String activeTool = "None";
    private DefaultMutableTreeNode wiringNode;
    private DefaultMutableTreeNode newcomp1;
    private DefaultMutableTreeNode newcomp2;
    private DefaultMutableTreeNode newcomp3;
    private DefaultMutableTreeNode Saved;
    private DefaultTreeModel treeModel;
    private static JTree sharedTree;

    public ComposantPanel() {
        setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Composants");

        DefaultMutableTreeNode Gates = new DefaultMutableTreeNode("Gates");
        DefaultMutableTreeNode Plexers = new DefaultMutableTreeNode("Plexers");
        DefaultMutableTreeNode Memory = new DefaultMutableTreeNode("Memory");
        DefaultMutableTreeNode Arithmetic = new DefaultMutableTreeNode("Arithmetic");
         Saved = new DefaultMutableTreeNode("Saved");
        wiringNode = new DefaultMutableTreeNode("Wiring");
        newcomp1 = new DefaultMutableTreeNode("newcomp1");
        newcomp2 = new DefaultMutableTreeNode("newcomp2");
        newcomp3 = new DefaultMutableTreeNode("newcomp3");

        root.add(Gates);
        root.add(Plexers);
        root.add(Memory);
        root.add(Arithmetic);
        root.add(wiringNode);
        root.add(newcomp1);
        root.add(newcomp2);
        root.add(newcomp3);
        root.add(Saved);

        // Ajout des éléments sous "Gates"
        Gates.add(new DefaultMutableTreeNode("NOT"));
        Gates.add(new DefaultMutableTreeNode("BUFFER"));
        Gates.add(new DefaultMutableTreeNode("AND"));
        Gates.add(new DefaultMutableTreeNode("NAND"));
        Gates.add(new DefaultMutableTreeNode("OR"));
        Gates.add(new DefaultMutableTreeNode("NOR"));
        Gates.add(new DefaultMutableTreeNode("XOR"));
        Gates.add(new DefaultMutableTreeNode("XNOR"));
        Gates.add(new DefaultMutableTreeNode("ODD PARITY"));
        Gates.add(new DefaultMutableTreeNode("EVEN PARITY"));
        Gates.add(new DefaultMutableTreeNode("Controlled_Buffer"));
        Gates.add(new DefaultMutableTreeNode("Controlled_inverter"));

        // Ajout des éléments sous "Plexers"
        Plexers.add(new DefaultMutableTreeNode("Multiplexer"));
        Plexers.add(new DefaultMutableTreeNode("Demultiplexer"));
        Plexers.add(new DefaultMutableTreeNode("Decoder"));
        Plexers.add(new DefaultMutableTreeNode("Priority Encoder"));
        Plexers.add(new DefaultMutableTreeNode("Bit Selector"));

        // Ajout des éléments sous "Memory"
        Memory.add(new DefaultMutableTreeNode("D flip flop"));
        Memory.add(new DefaultMutableTreeNode("T flip flop"));
        Memory.add(new DefaultMutableTreeNode("JK flip flop"));
        Memory.add(new DefaultMutableTreeNode("RS flip flop"));
        Memory.add(new DefaultMutableTreeNode("RAM"));
        Memory.add(new DefaultMutableTreeNode("ROM"));

        // Ajout des éléments sous "Arithmetic"
        Arithmetic.add(new DefaultMutableTreeNode("Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Adder"));
        Arithmetic.add(new DefaultMutableTreeNode("Bit Finder"));
        Arithmetic.add(new DefaultMutableTreeNode("Comparator"));
        Arithmetic.add(new DefaultMutableTreeNode("Divider"));
        Arithmetic.add(new DefaultMutableTreeNode("Multiplier"));
        Arithmetic.add(new DefaultMutableTreeNode("Negator"));
        Arithmetic.add(new DefaultMutableTreeNode("Shifter"));
        Arithmetic.add(new DefaultMutableTreeNode("Subtractor"));


        // Ajout des éléments sous "Wiriring"
        wiringNode.add(new DefaultMutableTreeNode("LED"));
        wiringNode.add(new DefaultMutableTreeNode("Clock"));
        wiringNode.add(new DefaultMutableTreeNode("Generator_ON_OFF"));
        wiringNode.add(new DefaultMutableTreeNode("Generator_Determinate"));
        wiringNode.add(new DefaultMutableTreeNode("Power"));
        wiringNode.add(new DefaultMutableTreeNode("Ground"));
        wiringNode.add(new DefaultMutableTreeNode("saved_x"));
        wiringNode.add(new DefaultMutableTreeNode("Transistor"));
        wiringNode.add(new DefaultMutableTreeNode("lampe_state"));
        wiringNode.add(new DefaultMutableTreeNode("Splitter"));
        afficherXMLCount();

        treeModel = new DefaultTreeModel(root);
        addXMLNodes();
        addCustomComponents();

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
    
    private void afficherNewComp() {
        File dir = new File("./custom_components"); // Par exemple, un sous-dossier du projet
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".xml")); // ou .class, etc.
            if (files != null) {
                for (File file : files) {
                    String name = file.getName().replaceFirst("[.][^.]+$", "");
                    newcomp2.add(new DefaultMutableTreeNode(name));
                }
            }
        }

        if (treeModel != null && newcomp2.getParent() == null) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
            if (root != null) {
                root.add(newcomp2);
            }
        }

        if (treeModel != null) {
            treeModel.reload(newcomp2);
        }
    }
    private void addCustomComponents() {
        addFolderToNode("custom_components1", newcomp1);
        addFolderToNode("custom_components2", newcomp2);
        addFolderToNode("custom_components3", newcomp3);
    }
    private void addFolderToNode(String folderName, DefaultMutableTreeNode parent) {
        File dir = new File(folderName);
        if (!dir.exists() || !dir.isDirectory()) return;
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xml"));
        if (files == null) return;
        for (File f : files) {
            String base = f.getName().replaceFirst("\\.[^.]+$", "");
            parent.add(new DefaultMutableTreeNode(base));
        }
        treeModel.reload(parent);
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
                Saved.add(xmlNode);  // Ajout du nœud au parent
            }
            // Recharger le modèle du JTree pour afficher les nouveaux nœuds
            treeModel.reload(wiringNode);
        } else {
            System.out.println("Aucun fichier XML trouvé.");
        }
    }
    
    private void refreshXMLNodes() {
        wiringNode.removeAllChildren();
        wiringNode.add(new DefaultMutableTreeNode("LED"));
        wiringNode.add(new DefaultMutableTreeNode("Generator_ON_OFF"));
        wiringNode.add(new DefaultMutableTreeNode("Generator_Undetermine"));
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

    // methode pour ajouter un noeud 
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
                case "Bit Selector" -> setIcon(resizeIcon("/icons2/bitSelector.png", 25, 25));
                case "Controlled_Buffer" -> setIcon(resizeIcon("/icons2/ControlledBuffer.png", 40, 40));
                case "Controlled_inverter" -> setIcon(resizeIcon("/icons2/ControlledInverter.png", 40, 40));
                
                case "Priority Encoder" -> setIcon(resizeIcon("/icons2/encodeur.jpg", 25, 25));
                case "LED" -> setIcon(resizeIcon("/icons2/Lampe.png", 25, 25));
                case "Splitter" -> setIcon(resizeIcon("/icons2/splitter.png", 25, 25));
                case "Transistor" -> setIcon(resizeIcon("/icons2/Transistor.png", 25, 25));
                case "Clock" -> setIcon(resizeIcon("/icons2/clock.png", 25, 25));
                case "Generator_ON_OFF" -> setIcon(resizeIcon("/icons2/generatorOn.png", 25, 25));
                case "Generator_Determinate" -> setIcon(resizeIcon("/icons2/generatorDET.png", 25, 25));
                case "Power" -> setIcon(resizeIcon("/icons2/POWER.png", 25, 25));
                case "Ground" -> setIcon(resizeIcon("/icons2/ground.png", 25, 25));
                 case "lampe_state" -> setIcon(resizeIcon("/icons2/lampe_ON.png", 25, 25));
                 default -> {
                     if (nodeName.startsWith("saved_circuit_")) {
                         setIcon(resizeIcon("/icons2/default.png", 25, 25));
                     } else {
                         try (InputStream is = getClass().getResourceAsStream("/custom_components1/" + nodeName + ".xml")) {
                             if (is != null) {
                            	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                            	 DocumentBuilder db = dbf.newDocumentBuilder();
                            	 org.w3c.dom.Document doc = db.parse(is);
                            	 doc.getDocumentElement().normalize();
                            	 NodeList iconTags = doc.getElementsByTagName("icon");
                                 if (iconTags.getLength() > 0) {
                                     String iconPath = iconTags.item(0).getTextContent().trim();
                                     setIcon(resizeIcon(iconPath, 25, 25));
                                 } else {
                                     setIcon(resizeIcon("/icons2/default.png", 25, 25));
                                 }
                             } else {
                                 setIcon(resizeIcon("/icons2/default.png", 25, 25));
                             }
                         } catch (Exception e) {
                             setIcon(resizeIcon("/icons2/default.png", 25, 25));
                         }
                     }
                 }
             }
            
            return this;
        }
    }
}
