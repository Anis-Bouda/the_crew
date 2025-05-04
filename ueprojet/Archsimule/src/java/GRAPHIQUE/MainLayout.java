package GRAPHIQUE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import GRAPHIQUE_IG.*;
import Logique.Principale.Composant;
import Logique.Principale.State;


/*les imports pour la création du circuit */
import java.util.List;
import java.util.ArrayList;

/*les imports pours les fonctions copier et coller */
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import java.awt.MouseInfo;
import javax.swing.SwingUtilities;

// Note : Ne pas importer java.awt.Point ici pour éviter le conflit avec votre classe custom Point
	//
public class MainLayout extends JPanel {
    private PropertiesPanel propertiesPanel;
    // Modification : utiliser Map<Object, java.awt.Point> pour pouvoir stocker Square (qui n'est pas un JLabel)
 // Remplace par :
    private Map<AbstractComponent, java.awt.Point> composantsMap = new HashMap<>();
    private Map<Point, java.awt.Point> panelPositions = new HashMap<>();
    public class ConnectionState {
        public String firstState;
        public String lastState;

        public ConnectionState(String firstState, String lastState) {
            this.firstState = firstState;
            this.lastState = lastState;
        }
    }
    
  	public static List<ConnectionState> connectionStates = new ArrayList<>();
    
    
    
 // Classe racine de la session
  	private static class SessionData {
  	    List<ComponentData> components;
  	    List<ConnectionState> connections;
  	}

  	// Pour chaque composant
  	private static class ComponentData {
  	    String name;
  	    int x, y;
  	    List<String> states;
  	}

    
    
    
  	 private JLayeredPane circuitDesignArea;

     
    public MainLayout(JLayeredPane externalDesignArea) {
        // Set layout to BorderLayout (équivalent à BorderPane en JavaFX)
    	
        //circuitDesignArea = new JLayeredPane();
        //circuitDesignArea.setLayout(null);
        //circuitDesignArea.setPreferredSize(new Dimension(1500,1000));
        //add(circuitDesignArea, BorderLayout.CENTER);
        //ConnectionManager.setLayeredPane(circuitDesignArea);

    	
    	
    	  this.circuitDesignArea = externalDesignArea;

        setLayout(new BorderLayout());

        //  Liste des composants supportés (Placeholder Panel)
        JPanel composants = new JPanel();
        composants.add(new JLabel("Composants ici")); // Ajout d'un label pour éviter qu'il soit vide
        
        composantsMap = new HashMap<>();
        circuitDesignArea = new JLayeredPane();
        //  Panneau de contrôle avec boutons "Run", "Stop", "Fil"
        JPanel control = new JPanel();
        JButton run=new JButton("Run");
        run.addActionListener(e->executerCircuit(circuitDesignArea));
        control.add(run);
        control.add(new JButton("Stop"));
        control.add(new JButton("Fil"));

        //  Zone pour afficher les propriétés des composants
        propertiesPanel = new PropertiesPanel();
        propertiesPanel.setBorder(BorderFactory.createTitledBorder("Propriétés"));

        //  Création du panneau latéral gauche (VBox en JavaFX → BoxLayout en Swing)
        JPanel simulationControl = new JPanel();
        simulationControl.setLayout(new BoxLayout(simulationControl, BoxLayout.Y_AXIS));
        simulationControl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        simulationControl.add(control);
        simulationControl.add(new ComposantPanel()); // Replace old composants with hierarchical list
        simulationControl.add(propertiesPanel);
        add(simulationControl, BorderLayout.WEST);

        //  Zone de conception des circuits (Placeholder)
        circuitDesignArea.setLayout(null);
        circuitDesignArea.setPreferredSize(new Dimension(1500, 1000));
        showLoadSessionDialog();
        
        add(circuitDesignArea, BorderLayout.CENTER);
     // Dans le constructeur de MainLayout, après la création de circuitDesignArea :
        ConnectionManager.setLayeredPane(circuitDesignArea);

        // Utilisation de votre classe custom Point pour l'affichage du fond
        Point pointPanel = new Point();  // Ici, "Point" est votre classe qui étend JPanel et dessine la grille
        pointPanel.setOnMoveListener(newPosition -> {
            // Mise à jour des propriétés
            propertiesPanel.updateProperties(newPosition.x, newPosition.y, "Carré Rouge");

            // Mise à jour de la HashMap
            panelPositions.put(pointPanel, newPosition);

        });

          /*l'ajout du raccourcis Clavier Ctrl+Z pour arreter l'exécution  */
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "stop");
	actionMap.put("stop", new AbstractAction()
	{
    		@Override
    		public void actionPerformed(ActionEvent e) {
        	circuit.stop();
    		}
	});
               /*l'ajout des raccourcis Clavier Ctrl+C et Ctrl+V pour copier et coller*/
        /* Copier le composant sélectionné avec CTRL + C*/
	inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "copy");
	actionMap.put("copy", new AbstractAction()
	{
    		@Override
    		public void actionPerformed(ActionEvent e) {
        	if (selectedComponent != null) {
        	    copiedComponent = 	selectedComponent.cloneComponent();
            if (copiedComponent == null) {
                    System.err.println("Erreur : le composant cloné est null !");
            } else {
                    System.out.println("Copier action triggered!");
            }


        	}
    		}
	});



        /*Coller le composant copié avec CTRL + V*/
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "paste");
	    actionMap.put("paste", new AbstractAction() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
        	if (copiedComponent != null) {
                AbstractComponent newComp = copiedComponent.cloneComponent();
                System.out.println("Coller action triggered!");
		newComp.setSize(newComp.getPreferredSize());
            	java.awt.Point pasteLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(pasteLocation, circuitDesignArea);
            	int newX = alignToGrid(pasteLocation.x);
            	int newY = alignToGrid(pasteLocation.y);
                /*juste pour testers c'est quoi le probleme je fixe la position */
            	//int newX = alignToGrid(100);
                //int newY = alignToGrid(100);

                newComp.setLocation(newX, newY);
            	propertiesPanel.updateProperties(newX, newY, newComp.getComponentName());
                composantsMap.put(newComp, new java.awt.Point(newX, newY));

            	newComp.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mousePressed(MouseEvent e) {
                    	newComp.putClientProperty("offsetX", e.getX());
                    	newComp.putClientProperty("offsetY", e.getY());
                	    circuitDesignArea.requestFocusInWindow();
                        selectedComponent = newComp;

                    }
                	@Override
                	public void mouseClicked(MouseEvent e) {
                    	//selectedComponent = newComp;
                        //circuitDesignArea.requestFocusInWindow();
                    propertiesPanel.updateProperties(newComp.getX(), newComp.getY(), newComp.getComponentName());
                	circuitDesignArea.requestFocusInWindow();
                }
            	});

            	newComp.addMouseMotionListener(new MouseAdapter() 		 {
                	@Override
                	public void mouseDragged(MouseEvent e) {
                    	int offsetX = (int) newComp.getClientProperty("offsetX");
                    	int offsetY = (int) newComp.getClientProperty("offsetY");

                    	int draggedX = e.getXOnScreen() - circuitDesignArea.getLocationOnScreen().x - offsetX;
                    	int draggedY = e.getYOnScreen() - circuitDesignArea.getLocationOnScreen().y - offsetY;

                    	draggedX = alignToGrid(draggedX);
                    	draggedY = alignToGrid(draggedY);

                    	newComp.setLocation(draggedX, draggedY);
                    	propertiesPanel.updateProperties(draggedX, draggedY, newComp.getComponentName());
                    	ConnectionManager.updateConnectionsForComponent(newComp);
                    circuitDesignArea.repaint();
                	}
            	});

                newComp.setBounds(newComp.getX(), newComp.getY(),
                                           	         newComp.getPreferredSize().width,
                                  newComp.getPreferredSize().height);
                 /*Enregistrer la copie dans la HashMap pour suivi*/
                 composantsMap.put(newComp, new java.awt.Point(newComp.getX(), newComp.getY()));
            	 /*Ajouter de la copie sur un calque supérieur*/
                 circuitDesignArea.add(newComp, Integer.valueOf(2));
                 circuitDesignArea.setPreferredSize(new Dimension(20000, 12000));
                 circuitDesignArea.setBackground(new Color(200, 220, 255));/*Bleu Clair*/
                 circuitDesignArea.revalidate();
                 circuitDesignArea.repaint();
 propertiesPanel.updateProperties(newComp.getX(), newComp.getY(), newComp.getComponentName());
        	}
    	}
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
                        newComponent = new Comparator_ig();
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
                        newComponent = new CMPT_ig();
                        
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
                    case "Clock":
                        newComponent = new Clock_ig();
                        
                        break;
                    case "Power":
                        newComponent = new Power_ig();
                        
                        break;   
                    case "Ground":
                        newComponent = new Ground_ig();
                        
                        break;         
                    case "LED":
                        newComponent = new LED_ig("LED1", alignedX, alignedY); // Ajuste l'id et la position selon tes besoins
                        break;
                    
                    
                    

                    // Ajoutez d'autres cas pour d'autres composants
                    default:
                       // int index = extractIndexFromFileName(componentName);

                        if (componentName.startsWith("saved_circuit_")) {
                            // Lire dynamiquement le nombre d'entrées dans le fichier XML
                            int numInputs = ReadInputCount.getInputCount(componentName + ".xml");
                            newComponent = new SavedX_ig(numInputs);
                        } else {
                        	   //  Récupère le nombre d’entrées enregistré dans le XML
                            int numInputs = ReadInputCount.getInputCount(componentName + ".xml");
                            //  Chemin vers ton XML
                            String xmlPath = "custom_components/" + componentName + ".xml";
                            //  Crée la vue graphique
                            MyGate_ig view = new MyGate_ig(2, xmlPath);
                            //  Crée le composant logique à attacher
                            MyGate logic = new MyGate(
                                componentName + "#" + System.currentTimeMillis(),
                                alignedX, alignedY,
                                xmlPath
                            );
                            //  Lie la vue et la logique
                            view.setComposant(logic);
                            newComponent = view;
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
    private static int saveCounter = 1; //  Début du compteur

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

            for (Entry<AbstractComponent, java.awt.Point> entry : composantsMap.entrySet()) {
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

        JMenuItem mnuNewFile = new JMenuItem("New File");
        mnuNewFile.setIcon(new ImageIcon(getClass().getResource("/icons1/new.png")));
        mnuNewFile.setMnemonic('N');
        mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuNewFile);

        JMenuItem mnuOpenFile = new JMenuItem("Open File");
        mnuOpenFile.setIcon(new ImageIcon(getClass().getResource("/icons1/open.png")));
        mnuOpenFile.setMnemonic('O');
        mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuOpenFile);

        JMenuItem mnuSaveFile = new JMenuItem("Save File");
        mnuSaveFile.setIcon(new ImageIcon(getClass().getResource("/icons1/save.png")));
        mnuSaveFile.setMnemonic('S');
        mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveFile.addActionListener(e -> saveCircuit());
        mnuFile.add(mnuSaveFile);
     //  Sauvegarde/chargement de session 
        JMenuItem mnuSaveSession = new JMenuItem("Save Session");
        mnuSaveSession.setMnemonic('T');
        mnuSaveSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveSession.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("session.xml"));
            chooser.setFileFilter(new FileNameExtensionFilter("Session XML (*.xml)", "xml"));
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                saveSession(chooser.getSelectedFile());
            }
        });
  // réutilise votre méthode existante
        mnuFile.add(mnuSaveSession);

        JMenuItem mnuLoadSession = new JMenuItem("Load Session");
        mnuLoadSession.setMnemonic('L');
        mnuLoadSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        mnuLoadSession.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Session XML (*.xml)", "xml"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File xmlFile = chooser.getSelectedFile();

                //  Crée une nouvelle fenêtre
                JFrame frame = new JFrame("Session chargée : " + xmlFile.getName());
                JLayeredPane circuitDesignArea1 = new JLayeredPane();
                circuitDesignArea1.setLayout(null);
                circuitDesignArea1.setPreferredSize(new Dimension(1500, 1000));
                //  Instancie un nouveau MainLayout
                MainLayout newLayout = new MainLayout(circuitDesignArea1);
                newLayout.loadSession(xmlFile);
                // Monte la barre de menu et le contenu
                frame.setJMenuBar(newLayout.createMenuBar());
                frame.setContentPane(newLayout);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

            }
        });
        mnuFile.add(mnuLoadSession);

        //mnuFile.add(mnuLoadSession);

        
        JMenuItem mnuSaveComponent = new JMenuItem("Save Component");
        mnuSaveComponent.setIcon(new ImageIcon(getClass().getResource("/icons1/save.png")));
        mnuSaveComponent.setMnemonic('C');
        mnuSaveComponent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveComponent.addActionListener(e -> saveCustomComponent());
        mnuFile.add(mnuSaveComponent);

        JMenuItem mnuExitFile = new JMenuItem("Exit");
        mnuExitFile.setIcon(new ImageIcon(getClass().getResource("/icons1/exit.png")));
        mnuExitFile.setMnemonic('E');
        mnuExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        mnuExitFile.addActionListener(this::ExitBtn);
        mnuFile.add(mnuExitFile);

        menuBar.add(mnuFile);

        // Outils d'édition
        menuBar.add(new JButton("Poke Tool"));
        menuBar.add(new JButton("Edit Tool"));
        menuBar.add(new JButton("Select Tool"));
        menuBar.add(new JButton("Wiring Tool"));
        menuBar.add(new JButton("Text Tool"));

        return menuBar;
    }
  
    
    private void saveCustomComponent() {
        //  Choix du nombre d’entrées
        Integer[] options = {1, 2, 3};
        Integer numInputs = (Integer) JOptionPane.showInputDialog(
            null,
            "Nombre d'entrées :",
            "Créer composant personnalisé",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (numInputs == null) return;  // Annulé

        //  Nom du composant
        String componentName = JOptionPane.showInputDialog(
            null,
            "Nom du composant :",
            "Créer composant personnalisé",
            JOptionPane.PLAIN_MESSAGE
        );
        if (componentName == null || componentName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nom invalide.");
            return;
        }

        // Table de vérité
        JTextArea truthTableArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(truthTableArea);
        int result = JOptionPane.showConfirmDialog(
            null,
            scrollPane,
            "Entrez la table de vérité (ex: 00=0, 01=1, ...)",
            JOptionPane.OK_CANCEL_OPTION
        );
        if (result != JOptionPane.OK_OPTION) return;
        String truthTable = truthTableArea.getText().trim();
        if (truthTable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Table de vérité vide !");
            return;
        }

        //  Sélection d’une icône… (inchangé)

        // Enregistrement XML en incluant numInputs
        try {
            File file = new File("custom_components/" + componentName + ".xml");
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(file);
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<component>");
            writer.println("  <name>"      + componentName + "</name>");
            writer.println("  <inputs>"    + numInputs     + "</inputs>");
            String imageFileName = null;
			writer.println("  <icon>/icons2/" + imageFileName + "</icon>");
            writer.println("  <truthTable><![CDATA[" + truthTable + "]]></truthTable>");
            writer.println("</component>");
            writer.close();
            JOptionPane.showMessageDialog(null, "Composant sauvegardé !");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde !");
        }
    }

    private ImageIcon getComponentIconByName(String name) {
        String iconPath;
        switch (name) {
            case "Bit Adder": iconPath = "/icons2/bit_adder.png"; break;
            case "Bit Finder": iconPath = "/icons2/bit_finder.png"; break;
            case "Comparator": iconPath = "/icons2/comparator.png"; break;
            case "Divider": iconPath = "/icons2/divider.png"; break;
            case "Negator": iconPath = "/icons2/negator.png"; break;
            case "Bin": iconPath = "/icons2/bin.png"; break;
            case "Probe": iconPath = "/icons2/probe.png"; break;
            case "Tunnel": iconPath = "/icons2/tunnel.png"; break;
            case "Clock": iconPath = "/icons2/clock.png"; break;
            case "Bus Register": iconPath = "/icons2/bus_register.png"; break;
            default: iconPath = "/icons2/default.png"; break;
        }

        java.net.URL url = getClass().getResource(iconPath);
        if (url == null) {
            System.err.println("Icon not found: " + iconPath);
            return new ImageIcon(); // Retourne une icône vide si l’image n’est pas trouvée
        }

        ImageIcon icon = new ImageIcon(url);
        icon.setDescription(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private String extractIconFileName(ImageIcon icon) {
        String desc = icon.getDescription();
        if (desc != null && desc.contains("/")) {
            return desc.substring(desc.lastIndexOf('/') + 1);
        }
        return "default.png";
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
                    
                  

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    int x = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());

                    //int x = Integer.parseInt(((Document) element).getElementsByTagName("x").item(0).getTextContent());
                   // int y = Integer.parseInt(((Document) element).getElementsByTagName("y").item(0).getTextContent());
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
    
    public static File showLoadSessionDialog() {
        return showLoadSessionDialog(null);
    }
    
    
    
    public void loadSession(File file) {
        try {
            //  Parser XML
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            // Réinitialisation
            composantsMap.clear();
            connectionStates.clear();
            circuitDesignArea.removeAll();
         // Recréer le fond (grille)
            Point pointPanel = new Point();
            pointPanel.setOpaque(false);
            pointPanel.setBounds(0, 0, circuitDesignArea.getWidth(), circuitDesignArea.getHeight());
            circuitDesignArea.add(pointPanel, Integer.valueOf(0));

            
            //  Reconstruction des composants
            NodeList comps = doc.getElementsByTagName("component");
            for (int i = 0; i < comps.getLength(); i++) {
                Element el = (Element) comps.item(i);
                String name = el.getAttribute("name");
                int x = Integer.parseInt(el.getAttribute("x"));
                int y = Integer.parseInt(el.getAttribute("y"));
                String stateS = el.getAttribute("state");

                AbstractComponent comp = createComponentByName(name);
                comp.setBounds(x, y, comp.getPreferredSize().width, comp.getPreferredSize().height);

                if (stateS != null && !stateS.isEmpty()) {
                    String[] tokens = stateS.split(",");
                    List<State> states = new ArrayList<>();
                    for (String tok : tokens) {
                        try {
                            states.add(State.valueOf(tok.trim()));
                        } catch (IllegalArgumentException ignored) {}
                    }
                    try {
                        comp.setInputsStates(states);
                    } catch (Exception ignored) {}
                }

                circuitDesignArea.add(comp, Integer.valueOf(2));
                composantsMap.put(comp, new java.awt.Point(x, y));

            }

            //  Reconstruction des connexions depuis connectionStates
            NodeList conns = doc.getElementsByTagName("conn");
            for (int i = 0; i < conns.getLength(); i++) {
                Element el = (Element) conns.item(i);
                connectionStates.add(new ConnectionState(el.getAttribute("from"), el.getAttribute("to")));
            }

            System.out.println(">> Composants : " + composantsMap.size());
            System.out.println(">> Connexions : " + connectionStates.size());

            //  Dessiner
            ConnectionManager.setLayeredPane(circuitDesignArea);
            ConnectionManager.refreshAllConnections();
            circuitDesignArea.revalidate();
            circuitDesignArea.repaint();

            JOptionPane.showMessageDialog(this, "Session chargée !");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erreur lors du chargement de la session.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
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
        circuit.simulate(7); // 20 itérations max, ajustable
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

/**
 * Enregistre l’état courant (composants + connexions) dans un fichier XML de session.
 */
private void saveSession(File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<session>");
        for (Entry<AbstractComponent, java.awt.Point> e : composantsMap.entrySet()) {
            if (e.getKey() instanceof AbstractComponent) {
                AbstractComponent c = (AbstractComponent)e.getKey();
                java.awt.Point p = e.getValue();
                String states = c.getInputStates().stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
                writer.printf("  <component name=\"%s\" x=\"%d\" y=\"%d\" state=\"%s\"/>%n",
                              c.getComponentName(), p.x, p.y, states);
            }
        }
        writer.println("  <connections>");
        for (ConnectionState cs : connectionStates) {
            writer.printf("    <conn from=\"%s\" to=\"%s\"/>%n",
                          cs.firstState, cs.lastState);
        }
        writer.println("  </connections>");
        writer.println("</session>");
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde de la session.");
    }
}
private void doSaveSession() {
    JFileChooser chooser = new JFileChooser();
    String defaultName = "session_" + System.currentTimeMillis() + ".xml";
    chooser.setSelectedFile(new File(defaultName));
    chooser.setFileFilter(new FileNameExtensionFilter("Session XML", "xml"));
    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        saveSession(chooser.getSelectedFile());
    }
}

private void doLoadSession() {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Session XML", "xml"));
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        loadSession(chooser.getSelectedFile());
    }
}
public static File showLoadSessionDialog(Component parent) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("Session XML (*.xml)", "xml"));
    int res = chooser.showOpenDialog(parent);
    return (res == JFileChooser.APPROVE_OPTION)
         ? chooser.getSelectedFile()
         : null;
}

private AbstractComponent createComponentByName(String componentName) {
    AbstractComponent comp;
    switch (componentName) {
        case "NOT":
            comp = new NOT_ig();
            break;
        case "AND":
            comp = new ET_ig();
            break;
        case "OR":
            comp = new OR_ig();
            break;
        case "Multiplexer":
            comp = new MUX_ig();
            break;
        case "Demultiplexer":
            comp = new DEMUX_ig();
            break;
        case "Decoder":
            comp = new DECODER_ig();
            break;
        case "Priority Encoder":
            comp = new ENCODER_ig();
            break;
        case "BUFFER":
            comp = new BUFFER_ig();
            break;
        case "NOR":
            comp = new NOR_ig();
            break;
        case "XOR":
            comp = new XOR_ig();
            break;
        case "NAND":
            comp = new NAND_ig();
            break;
        case "XNOR":
            comp = new XNOR_ig();
            break;
        case "Bit Selector":
            comp = new BITSELECTOR_ig();
            break;
        case "ODD PARITY":
            comp = new ODD_ig();
            break;
        case "EVEN PARITY":
            comp = new EVEN_ig();
            break;
        case "Adder":
            comp = new Adder_ig();
            break;
        case "Subtractor":
            comp = new Subtractor_ig();
            break;
        case "Multiplier":
            comp = new Multiplier();
            break;
        case "Comparator":
            comp = new Comparator_ig();
            break;
        case "Negator":
            comp = new Negator_ig();
            break;
        case "Divider":
            comp = new Divider_ig();
            break;
        case "Shifter":
            comp = new Shifter_ig();
            break;
        case "Bit Adder":
            comp = new BitAdder_ig();
            break;
        case "Bit Finder":
            comp = new BitFinder_ig();
            break;
        case "OutPut":
            comp = new OutPut();
            break;
        case "Generator_ON_OFF":
            comp = new Generator_ON_OFF();
            break;
        case "Generator_Undetermine":
            comp = new Generator_Undetermine();
            break;
        case "RAM":
            comp = new RAM_ig();
            break;
        case "ROM":
            comp = new ROM_ig();
            break;
        case "Counter":
            comp = new CMPT_ig();
            break;
        case "Register":
            comp = new RNG();
            break;
        case "JK flip flop":
            comp = new BJK_ig();
            break;
        case "RS flip flop":
            comp = new BRS_ig();
            break;
        case "D flip flop":
            comp = new BasculeD_ig();
            break;
        case "T flip flop":
            comp = new BasculeT_ig();
            break;
        case "LED":
            // Vous pouvez donner un ID générique ici, la position sera fixée après
            comp = new LED_ig("LED1", 0, 0);
            break;

        default:
            if (componentName.startsWith("saved_circuit_")) {
                int numInputs = ReadInputCount.getInputCount(componentName + ".xml");
                comp = new SavedX_ig(numInputs);
            } else {
                int numInputs = ReadInputCount.getInputCount(componentName + ".xml");
                String xmlPath = "custom_components/" + componentName + ".xml";

                MyGate_ig view = new MyGate_ig(2, xmlPath);
                MyGate logic = new MyGate(
                    componentName + "#" + System.currentTimeMillis(),
                    0, 0,    // position à 0,0, sera repositionné après
                    xmlPath
                );
                view.setComposant(logic);
                comp = view;
            }
            break;
    }
    return comp;
}






}
