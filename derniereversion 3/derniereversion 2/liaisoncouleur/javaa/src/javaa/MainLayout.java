
import Principale.*;
import gates.*;
import Arithmetics.*;
import Memory.*;
import plexers.*;
import wiring.*;
import Input_output.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Taskbar.State;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Note : Ne pas importer java.awt.Point ici pour éviter le conflit avec votre classe custom Point
	//🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
public class MainLayout extends JPanel {
    private PropertiesPanel propertiesPanel;
    // Modification : utiliser Map<Object, java.awt.Point> pour pouvoir stocker Square (qui n'est pas un JLabel)
    private Map<Object, java.awt.Point> composantsMap; 
    public class ConnectionState {
        public String firstState;
        public String lastState;

        public ConnectionState(String firstState, String lastState) {
            this.firstState = firstState;
            this.lastState = lastState;
        }
    }
    
  	public static List<ConnectionState> connectionStates = new ArrayList<>();
    
    
    
    
    
    
    

    public MainLayout() {
        // Set layout to BorderLayout (équivalent à BorderPane en JavaFX)
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹🔹
  
    	
    	
    	
 // Taille du tableau, ajustée selon tes besoins

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	

        setLayout(new BorderLayout());

        // 🔹 Liste des composants supportés (Placeholder Panel)
        JPanel composants = new JPanel();
        composants.add(new JLabel("Composants ici")); // Ajout d'un label pour éviter qu'il soit vide
        
        composantsMap = new HashMap<>();
        JLayeredPane circuitDesignArea = new JLayeredPane();
        // 🔹 Panneau de contrôle avec boutons "Run", "Stop", "Fil"
        JPanel control = new JPanel();
        JButton run=new JButton("Run");
        run.addActionListener(e->executerCircuit(circuitDesignArea));
        
        control.add(run);
        control.add(new JButton("Stop"));
        control.add(new JButton("Fil"));

        // 🔹 Zone pour afficher les propriétés des composants
        propertiesPanel = new PropertiesPanel();
        propertiesPanel.setBorder(BorderFactory.createTitledBorder("Propriétés"));

        // 🔹 Création du panneau latéral gauche (VBox en JavaFX → BoxLayout en Swing)
        JPanel simulationControl = new JPanel();
        simulationControl.setLayout(new BoxLayout(simulationControl, BoxLayout.Y_AXIS));
        simulationControl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        simulationControl.add(control);
        simulationControl.add(new ComposantPanel()); // Replace old composants with hierarchical list
        simulationControl.add(propertiesPanel);
        add(simulationControl, BorderLayout.WEST);

        
        
        
        
        // 🔹 Zone de conception des circuits (Placeholder)
        circuitDesignArea.setLayout(null);
        circuitDesignArea.setPreferredSize(new Dimension(1500, 1000));

        
        
        
        add(circuitDesignArea, BorderLayout.CENTER);
     // Dans le constructeur de MainLayout, après la création de circuitDesignArea :
        ConnectionManager.setLayeredPane(circuitDesignArea);

        // Utilisation de votre classe custom Point pour l'affichage du fond
        Point pointPanel = new Point();  // Ici, "Point" est votre classe qui étend JPanel et dessine la grille
        pointPanel.setOnMoveListener(newPosition -> {
            // Mise à jour des propriétés
            propertiesPanel.updateProperties(newPosition.x, newPosition.y, "Carré Rouge");

            // Mise à jour de la HashMap
            composantsMap.put(pointPanel, newPosition);
        });

        pointPanel.setOpaque(false);
        pointPanel.setBounds(0, 0, circuitDesignArea.getPreferredSize().width, circuitDesignArea.getPreferredSize().height);
        circuitDesignArea.add(pointPanel, Integer.valueOf(0));
       
        circuitDesignArea.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Lorsque la taille de circuitDesignArea change, mettre à jour la taille de pointPanel
                pointPanel.setPreferredSize(circuitDesignArea.getSize());
                circuitDesignArea.revalidate();
            }
        });
        
        circuitDesignArea.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.stringFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                Transferable t = support.getTransferable();
                try {
                    String componentName = (String) t.getTransferData(DataFlavor.stringFlavor);
                    java.awt.Point dropPoint = support.getDropLocation().getDropPoint();

                    // Aligner le drop sur la grille
                    int alignedX = alignToGrid(dropPoint.x);
                    int alignedY = alignToGrid(dropPoint.y);

                    // Créer le composant correspondant
                    AbstractComponent newComponent;
                    switch (componentName) {
                    
                    case "NOT":
                        newComponent = new NOT_ig();
                        break;
                    case "AND":
                        newComponent = new ET_ig();
                        break;
                    case "OR":
                        newComponent = new OR_ig();
                        
                        break;
                    case "Multiplexer":
                        newComponent = new MUX_ig();
                        
                        break;
                    case "Demultiplexer":
                        newComponent = new DEMUX_ig();
                        
                        break;
                    case "Decoder":
                        newComponent = new DECODER_ig();
                        
                        break;
                    case "Priority Encoder":
                        newComponent = new ENCODER_ig();
                        
                        break;
                    case "BUFFER":
                        newComponent = new BUFFER_ig();
 
                        break;
                    case "NOR":
                        newComponent = new NOR_ig();
 
                        break;
                    case "XOR":
                        newComponent = new XOR_ig();
 
                        break;
                    case "NAND":
                        newComponent = new NAND_ig();
 
                        break;
                        
                    case "XNOR":
                        newComponent = new XNOR_ig();
                        break;
                    case "Bit Selector":
                        newComponent = new BITSELECTOR_ig();
                        
                        break;
                    case "ODD PARITY":
                        newComponent = new ODD_ig();
                        
                        break;
                    case "EVEN PARITY":
                        newComponent = new EVEN_ig();
                        
                        break;
                    case "Adder":
                        newComponent = new Adder_ig();
                        break;
                        
                    case "Subtractor":
                        newComponent = new Subtractor_ig();
                        break;
                    case "Multiplier":
                        newComponent = new Multiplier();
                        break;
                    case "Comparator":
                        newComponent = new Comparator();
                        break;
                    case "Negator":
                        newComponent = new Negator_ig();
                        break;
                    case "Divider":
                        newComponent = new Divider_ig();
                        break;
                    case "Shifter":
                        newComponent = new Shifter_ig();
                        break;
                    case "Bit Adder":
                        newComponent = new BitAdder_ig();
                        break;
                    case "Bit Finder":
                        newComponent = new BitFinder_ig();
                        break;
                    case "OutPut":
                        newComponent = new OutPut();
                        break;
                    
                    case "Generator_ON_OFF":
                        newComponent = new Generator_ON_OFF();
                        break;
                    
                    case "Generator_Undetermine":
                        newComponent = new Generator_Undetermine();
                        break;

                    case "RAM":
                        newComponent = new RAM_ig();
                        
                        break;
                    case "ROM":
                        newComponent = new ROM_ig();
                        
                        break;
                    case "Counter":
                        newComponent = new CMPT();
                        
                        break;
                    case "Register":
                        newComponent = new RNG();
                        
                        break;
                    case "JK flip flop":
                        newComponent = new BJK_ig();
                        
                        break;
                    case "RS flip flop":
                        newComponent = new BRS_ig();
                        
                        break;
                    case "D flip flop":
                        newComponent = new BasculeD_ig();
                        
                        break;
                    case "T flip flop":
                        newComponent = new BasculeT_ig();
                        
                        break;
                    case "LED":
                        newComponent = new LED("LED1", alignedX, alignedY); // Ajuste l'id et la position selon tes besoins
                        break;
                    

                    // Ajoutez d'autres cas pour d'autres composants
                    default:
                    	int index = extractIndexFromFileName(componentName);
                        String firstState = ReadFirstConnectionState.getFirstConnectionState(componentName + ".xml");
                        String lastState = ReadLastConnectionState.getLastConnectionState(componentName + ".xml");

                        if (componentName.startsWith("saved_circuit_")) {
                            newComponent = new SavedX_ig(firstState, lastState);
                            //ConnectionState[] connectionStates = new ConnectionState[Math.max(index + 1, 100)];
                           // connectionStates[index] = new ConnectionState(firstState, lastState);
                           
                        } else {
                            newComponent = new SavedX_ig(firstState, lastState);
                            String state = ReadLastConnectionState.getLastConnectionState(componentName + ".xml");
                            if (state != null) {
                                System.out.println("State pour " + componentName + ": " + state);
                            }
                        }

                        break;
                }
                          
                       
                    newComponent.setBounds(alignedX, alignedY,
                                           newComponent.getPreferredSize().width,
                                           newComponent.getPreferredSize().height);

                    // Enregistrer dans la HashMap pour suivi
                    composantsMap.put(newComponent, new java.awt.Point(alignedX, alignedY));

                    // Ajouter des écouteurs pour le déplacement
                    newComponent.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            newComponent.putClientProperty("offsetX", e.getX());
                            newComponent.putClientProperty("offsetY", e.getY());
                        }
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            propertiesPanel.updateProperties(newComponent.getX(), newComponent.getY(), newComponent.getComponentName());
                        }
                    });

                    newComponent.addMouseMotionListener(new MouseAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            int offsetX = (int) newComponent.getClientProperty("offsetX");
                            int offsetY = (int) newComponent.getClientProperty("offsetY");

                            int newX = e.getXOnScreen() - circuitDesignArea.getLocationOnScreen().x - offsetX;
                            int newY = e.getYOnScreen() - circuitDesignArea.getLocationOnScreen().y - offsetY;

                            // Aligner la nouvelle position à la grille
                            newX = alignToGrid(newX);
                            newY = alignToGrid(newY);

                            newComponent.setLocation(newX, newY);
                            propertiesPanel.updateProperties(newX, newY, newComponent.getComponentName());
                            circuitDesignArea.revalidate();
                            circuitDesignArea.repaint();

                            // Mise à jour des connexions associées à ce composant
                            ConnectionManager.updateConnectionsForComponent(newComponent);
                        }
                    });

                    // Ajouter le composant sur un calque supérieur
                    circuitDesignArea.add(newComponent, Integer.valueOf(2));
                    circuitDesignArea.setPreferredSize(new Dimension(20000, 12000));
                    circuitDesignArea.setBackground(new Color(200, 220, 255)); // Bleu clair
                    circuitDesignArea.revalidate();
                    circuitDesignArea.repaint();

                    propertiesPanel.updateProperties(alignedX, alignedY, newComponent.getComponentName());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

       
        });

    }
    
    /**
     * Méthode pour récupérer l'icône associée au composant.
     * Les chemins doivent correspondre à l'emplacement de vos fichiers images dans votre projet.
     * Toutes les icônes seront redimensionnées à 64x64 pixels.
     */
    

    private ImageIcon getComponentIcon(String componentName) {
        ImageIcon icon = null;
        switch(componentName) {
        case "NOT":
            icon = new ImageIcon(getClass().getResource("/icons2/not.png"));
            break;
            case "OR":
                icon = new ImageIcon(getClass().getResource("/icons2/or.png"));
                break;
            case "AND":
                icon = new ImageIcon(getClass().getResource("/icons2/and.png"));
                break;
            case "BUFFER":
                icon = new ImageIcon(getClass().getResource("/icons2/buffer.png"));
                break;
            case "NOR":
                icon = new ImageIcon(getClass().getResource("/icons2/nor.png"));
                break;
            case "XOR":
                icon = new ImageIcon(getClass().getResource("/icons2/xor.png"));
                break;
            case "XNOR":
                icon = new ImageIcon(getClass().getResource("/icons2/xnor.png"));
                break;
            case "NAND":
                icon = new ImageIcon(getClass().getResource("/icons2/nand.png"));
                break;
            case "ODD PARITY":
                icon = new ImageIcon(getClass().getResource("/icons2/odd_parity.png"));
                break;
            case "EVEN PARITY":
                icon = new ImageIcon(getClass().getResource("/icons2/even_parity.png"));
                break;
            case "Multiplexer":
                icon = new ImageIcon(getClass().getResource("/icons2/multiplexer.png"));
                break;
            case "Demultiplexer":
                icon = new ImageIcon(getClass().getResource("/icons2/demultiplexer.png"));
                break;
            case "Decoder":
                icon = new ImageIcon(getClass().getResource("/icons2/decoder.png"));
                break;
            case "Priority Encoder":
                icon = new ImageIcon(getClass().getResource("/icons2/priority_encoder.png"));
                break;
            case "Bit Selector":
                icon = new ImageIcon(getClass().getResource("/icons2/bit_selector.png"));
                break;
            case "D flip flop":
                icon = new ImageIcon(getClass().getResource("/icons2/d_flip_flop.png"));
                break;
            case "T flip flop":
                icon = new ImageIcon(getClass().getResource("/icons2/t_flip_flop.png"));
                break;
            case "JK flip flop":
                icon = new ImageIcon(getClass().getResource("/icons2/jk_flip_flop.png"));
                break;
            case "RS flip flop":
                icon = new ImageIcon(getClass().getResource("/icons2/rs_flip_flop.png"));
                break;
            case "Register":
                icon = new ImageIcon(getClass().getResource("/icons2/register.png"));
                break;
            case "Counter":
                icon = new ImageIcon(getClass().getResource("/icons2/counter.png"));
                break;
            case "RAM":
                icon = new ImageIcon(getClass().getResource("/icons2/ram.png"));
                break;
            case "ROM":
                icon = new ImageIcon(getClass().getResource("/icons2/rom.png"));
                break;
            case "Adder":
                icon = new ImageIcon(getClass().getResource("/icons2/adder.png"));
                break;
            case "Subtractor":
                icon = new ImageIcon(getClass().getResource("/icons2/subtractor.png"));
                break;
            case "Multiplier":
                icon = new ImageIcon(getClass().getResource("/icons2/multiplier.png"));
                break;
            case "Shifter":
                icon = new ImageIcon(getClass().getResource("/icons2/shifter.png"));
                break;
            case "Bit Adder":
                icon = new ImageIcon(getClass().getResource("/icons2/bit_adder.png"));
                break;
            case "Bit Finder":
                icon = new ImageIcon(getClass().getResource("/icons2/bit_finder.png"));
                break;
            case "Comparator":
                icon = new ImageIcon(getClass().getResource("/icons2/comparator.png"));
                break;
            case "Divider":
                icon = new ImageIcon(getClass().getResource("/icons2/divider.png"));
                break;
            case "Negator":
                icon = new ImageIcon(getClass().getResource("/icons2/negator.png"));
                break;
            case "Bin":
                icon = new ImageIcon(getClass().getResource("/icons2/bin.png"));
                break;
            case "Probe":
                icon = new ImageIcon(getClass().getResource("/icons2/probe.png"));
                break;
            case "Tunnel":
                icon = new ImageIcon(getClass().getResource("/icons2/tunnel.png"));
                break;
            case "Clock":
                icon = new ImageIcon(getClass().getResource("/icons2/clock.png"));
                break;
            case "Bus Register":
                icon = new ImageIcon(getClass().getResource("/icons2/bus_register.png"));
                break;
            case "Saveeeeeeeeed":
                icon = new ImageIcon(getClass().getResource("/icons2/bus_register.png"));
                break;
            default:
                icon = new ImageIcon(getClass().getResource("/icons2/default.png"));
                break;
        }
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
  
    private int gridSpacing = 10; // Même valeur que DOT_SPACING dans votre classe Point

    private int alignToGrid(int coord) {
        return Math.round((float) coord / gridSpacing) * gridSpacing;
    }
    private static int saveCounter = 1; // ⚡ Début du compteur

    private void saveCircuit() {
        try {
            // Dossier de sauvegarde (répertoire courant)
            File folder = new File(".");

            int counter = 1;
            File file;
            do {
                // Format : saved_circuit_01.xml, saved_circuit_02.xml, etc.
                String fileName = String.format("saved_circuit_%02d.xml", counter);
                file = new File(folder, fileName);
                counter++;
            } while (file.exists()); // Tant que le fichier existe, on essaie avec +1

            FileWriter writer = new FileWriter(file);

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<circuit>\n");

            for (Map.Entry<Object, java.awt.Point> entry : composantsMap.entrySet()) {
                Object component = entry.getKey();
                java.awt.Point pos = entry.getValue();

                if (component instanceof AbstractComponent) {
                    AbstractComponent c = (AbstractComponent) component;
                    writer.write("  <component>\n");
                    writer.write("    <name>" + c.getComponentName() + "</name>\n");
                    writer.write("    <x>" + pos.x + "</x>\n");
                    writer.write("    <y>" + pos.y + "</y>\n");
                    writer.write("    <state>" + c.getInputStates() + "</state>\n");
                    writer.write("  </component>\n");
                }
            }

            writer.write("</circuit>\n");
            writer.close();

            JOptionPane.showMessageDialog(this, "Circuit sauvegardé avec succès : " + file.getName());

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du circuit.");
        }
    }

    // Création de la barre de menu
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Menu File
        JMenu mnuFile = new JMenu("File");
        mnuFile.setMnemonic('F');
       
        // MenuItem "New File"
        JMenuItem mnuNewFile = new JMenuItem("New File");
        mnuNewFile.setIcon(new ImageIcon(getClass().getResource("/icons1/new.png")));  
        mnuNewFile.setMnemonic('N');
        mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuNewFile);
        
        // MenuItem "Open File"
        JMenuItem mnuOpenFile = new JMenuItem("Open File");
        mnuOpenFile.setIcon(new ImageIcon(getClass().getResource("/icons1/open.png")));  
        mnuOpenFile.setMnemonic('O');
        mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuOpenFile);

        // MenuItem "Save File"
        JMenuItem mnuSaveFile = new JMenuItem("Save File");
        mnuSaveFile.setIcon(new ImageIcon(getClass().getResource("/icons1/save.png")));  
        mnuSaveFile.setMnemonic('S');
        mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveFile.addActionListener((e) -> saveCircuit());  // aucun changement ici
        mnuFile.add(mnuSaveFile);
        // MenuItem "Exit"
        JMenuItem mnuExitFile = new JMenuItem("Exit");
        mnuExitFile.setIcon(new ImageIcon(getClass().getResource("/icons1/exit.png")));  
        mnuExitFile.setMnemonic('E');
        mnuExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        mnuExitFile.addActionListener((e) -> ExitBtn(e));  
        mnuFile.add(mnuExitFile);

        menuBar.add(mnuFile);
        JButton pokeToolBtn = new JButton("Poke Tool");
        JButton editToolBtn = new JButton("Edit Tool");
        JButton selectToolBtn = new JButton("Select Tool");
        JButton wiringToolBtn = new JButton("Wiring Tool");
        JButton textToolBtn = new JButton("Text Tool");
        menuBar.add(editToolBtn);
        menuBar.add(editToolBtn);
        menuBar.add(selectToolBtn);
        menuBar.add(wiringToolBtn);
        menuBar.add(textToolBtn);
        return menuBar;
    }
    
    private void loadCircuitFromXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("component");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    String name = ((Document) element).getElementsByTagName("name").item(0).getTextContent();
                    int x = Integer.parseInt(((Document) element).getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(((Document) element).getElementsByTagName("y").item(0).getTextContent());
                }
            }

            repaint();
            revalidate();

            JOptionPane.showMessageDialog(this, "Circuit chargé avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement du circuit.");
        }
    }
    private void addSavedNode(String savedName) {
        ComposantPanel.addSavedComponent(savedName); // Suppose que ComposantPanel a une méthode statique ou singleton
    }

    // Méthode pour fermer l'application
    private void ExitBtn(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog(MainLayout.this, "Êtes-vous sûr de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION) {
            System.exit(0);  
        }
    }

    public void executerCircuit(JLayeredPane circuitDesignArea) {
    List<AbstractComponent> composants = new ArrayList<>(ConnectionManager.connections.size());

    // Extraire tous les composants graphiques de la liste de connexions
    for (Connection conn : ConnectionManager.connections) {
        if (!composants.contains(conn.compSource)) {
            composants.add(conn.compSource);
        }
        if (!composants.contains(conn.compTarget)) {
            composants.add(conn.compTarget);
        }
    }

    // Créer une instance de Circuit avec tous les composants + les connexions
    Circuit circuit = new Circuit(composants, ConnectionManager.connections);

    try {
        circuit.simulate(20); // 20 itérations max, ajustable
        //circuitDesignArea.repaint();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de l'exécution du circuit : " + e.getMessage());
    }
}
    public static int extractIndexFromFileName(String fileName) {
        Pattern pattern = Pattern.compile("saved_circuit_(\\d+)");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // Récupère le numéro après "saved_circuit_"
        } else {
            throw new IllegalArgumentException("Nom de fichier invalide : " + fileName);
        }
    }
public static List<ConnectionState> getStates() {
    	return connectionStates;
    }



}
