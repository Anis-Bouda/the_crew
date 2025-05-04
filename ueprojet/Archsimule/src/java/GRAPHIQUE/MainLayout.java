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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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

/*import pour forcer l'affichage des messages d'aides */
import javax.swing.ToolTipManager;



public class MainLayout extends JPanel {
    private PropertiesPanel propertiesPanel;


    private Map<AbstractComponent, java.awt.Point> composantsMap = new HashMap<>();
    private Map<Point, java.awt.Point> panelPositions = new HashMap<>();

    /*attributs pour les actions copier coller */
    AbstractComponent selectedComponent=null;
    AbstractComponent copiedComponent=null;
    /*Circuit de départ pour utiliser circuit.stop() en cliquant sur le bouton stop */
    Circuit circuit=null;


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

        // Liste des composants supportés (Placeholder Panel)
        JPanel composants = new JPanel();
        composants.add(new JLabel("Composants ici")); // Ajout d'un label pour éviter qu'il soit vide

        composantsMap = new HashMap<>();
        circuitDesignArea = new JLayeredPane();
        //  Panneau de contrôle avec boutons "Run", "Stop", "Fil"
        JPanel control = new JPanel();
        JButton run=new JButton("Run");
        run.setToolTipText("Cliquez ici pour lancer la simulation Ctrl+R");
        run.addActionListener(e->executerCircuit(circuitDesignArea));
        control.add(run);
        JButton stop=new JButton("Stop");
        stop.addActionListener(e->/*appel à la fonction d'arret*/circuit.stop());
        stop.setToolTipText("Cliquez ici pour arrêter la simulation Ctrl+Z");
        control.add(stop);
        JButton fil=new JButton("Fil");
        fil.setToolTipText("Cliquez ici pour créer un fil");
        control.add(fil);

        /*l'ajout des raccourcis Clavier*/
        InputMap inputMap = circuitDesignArea.getInputMap(JLayeredPane.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = circuitDesignArea.getActionMap();
        /*raccourcis clavier pour RUN CTRL + R*/
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), "run");
	actionMap.put("run", new AbstractAction()
	{
    		@Override
    		public void actionPerformed(ActionEvent e) {
        	executerCircuit(circuitDesignArea);
    		}
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





        // Zone pour afficher les propriétés des composants
        propertiesPanel = new PropertiesPanel();
        propertiesPanel.setBorder(BorderFactory.createTitledBorder("Propriétés"));

        // Création du panneau latéral gauche (VBox en JavaFX → BoxLayout en Swing)
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
                        newComponent = new Substractor_ig();
                        break;
                    case "Multiplier":
                        newComponent = new Multiplier_ig();
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

                    case "Generator_Determinate":
                        newComponent = new Generator_Determinate();
                        break;
                    case "Splitter":
                        newComponent = new Splitter_ig();
                        break;
                    case "Transistor":
                        newComponent = new Transistor_ig();
                        break;
                    case " Controlled_Buffer":
                        newComponent = new  Controlled_Buffer_ig();
                        break;
                     case "lampe_state":
                        newComponent = new LED_couleur_ig(componentName, getX(), getY());
                        break;
                    case "Controlled_inverter":
                        newComponent =new Controlled_inverter_ig();
                        break;
                    
                    // Ajoutez d'autres cas pour d'autres composants
                    default:
                       // int index = extractIndexFromFileName(componentName);

                        if (componentName.startsWith("saved_circuit_")) {
                            // Lire dynamiquement le nombre d'entrées dans le fichier XML
                            int numInputs = ReadInputCount.getInputCount(componentName + ".xml");
                            newComponent = new SavedX_ig(numInputs);
                        }else {
                            // 1) Recherche du fichier XML dans les dossiers 1, 2 et 3 entrées
                            String xmlPath = null;
                            for (int i = 1; i <= 3; i++) {
                                String candidate = "custom_components" + i + "/" + componentName + ".xml";
                                if (new File(candidate).exists()) {
                                    xmlPath = candidate;
                                    break;
                                }
                            }
                            if (xmlPath == null) {
                                throw new IllegalStateException("Fichier XML introuvable pour le composant " + componentName);
                            }

                            // 2) Récupère le nombre d’entrées depuis ce fichier
                            int numInputs = ReadInputCount.getInputCount(xmlPath);
                            System.out.println("NumInputs lu = " + numInputs);

                            // 3) Crée la vue graphique adaptée
                            MyGate_ig view = new MyGate_ig(numInputs, xmlPath);

                            // 4) Crée la logique correspondante
                            String uniqueId = componentName + "#" + System.currentTimeMillis();
                            Composant logic;
                            if (numInputs == 1) {
                                logic = new MyGate1(uniqueId, alignedX, alignedY, xmlPath);
                            } else if (numInputs == 2) {
                                logic = new MyGate(uniqueId, alignedX, alignedY, xmlPath);
                            } else { // numInputs == 3
                                logic = new MyGate3(uniqueId, alignedX, alignedY, xmlPath);
                            }

                            // 5) Lie la vue et la logique
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
                            selectedComponent=newComponent;
                            circuitDesignArea.requestFocusInWindow();

                        }
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            propertiesPanel.updateProperties(newComponent.getX(), newComponent.getY(), newComponent.getComponentName());
                            //selectedComponent=newComponent;
                            circuitDesignArea.requestFocusInWindow();

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



    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    // Création de la barre de menu
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu File
        JMenu mnuFile = new JMenu("File");
        mnuFile.setMnemonic('F');

        JMenuItem mnuNewFile = new JMenuItem("New File");
        mnuNewFile.setIcon(new ImageIcon(getClass().getResource("/icons1/new.png")));
        /*nuage d'aide */
        mnuNewFile.setToolTipText("Cliquez ici pour créer une nouvelle session");

        mnuNewFile.setMnemonic('N');
        mnuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuNewFile);

        JMenuItem mnuOpenFile = new JMenuItem("Open File");
        mnuOpenFile.setIcon(new ImageIcon(getClass().getResource("/icons1/open.png")));
        /*nuage d'aide */
        mnuOpenFile.setToolTipText("Cliquez ici pour ouvrir une session déjà existante");

        mnuOpenFile.setMnemonic('O');
        mnuOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        mnuFile.add(mnuOpenFile);

        JMenuItem mnuSaveFile = new JMenuItem("Save File");
        mnuSaveFile.setIcon(new ImageIcon(getClass().getResource("/icons1/save.png")));
        /*nuage d'aide */
        mnuSaveFile.setToolTipText("Cliquez ici pour sauvegarder une session");

        mnuSaveFile.setMnemonic('S');
        mnuSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveFile.addActionListener(e -> saveCircuit());
        mnuFile.add(mnuSaveFile);
     // Sauvegarde/chargement de session
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

        mnuFile.add(mnuSaveSession);

        JMenuItem mnuLoadSession = new JMenuItem("Load Session");
        /*nuage d'aide */
        mnuSaveFile.setToolTipText("Cliquez ici pour charger une session");

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
                //  Monte la barre de menu et le contenu
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
        /*nuage d'aide */
        mnuSaveFile.setToolTipText("Cliquez ici pour Créer un composant avec un nom , icon et une table de verité");

        mnuSaveComponent.setMnemonic('C');
        mnuSaveComponent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
        mnuSaveComponent.addActionListener(e -> saveCustomComponent());
        mnuFile.add(mnuSaveComponent);

        JMenuItem mnuExitFile = new JMenuItem("Exit");
        mnuExitFile.setIcon(new ImageIcon(getClass().getResource("/icons1/exit.png")));
        /*nuage d'aide */
        mnuExitFile.setToolTipText("Cliquez ici pour quitter la session");

        mnuExitFile.setMnemonic('E');
        mnuExitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        mnuExitFile.addActionListener(this::ExitBtn);
        mnuFile.add(mnuExitFile);


        /*enregistrer les JMenuItems auprès de ToolTipManager pour forcer l'affichage des message d'aide sur les JMenuItems*/
        ToolTipManager.sharedInstance().registerComponent(mnuNewFile);
        ToolTipManager.sharedInstance().registerComponent(mnuOpenFile);
        ToolTipManager.sharedInstance().registerComponent(mnuSaveFile);
        ToolTipManager.sharedInstance().registerComponent(mnuLoadSession);
        ToolTipManager.sharedInstance().registerComponent(mnuSaveComponent);
        ToolTipManager.sharedInstance().registerComponent(mnuExitFile);


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
        // 1) Choix du nombre d’entrées
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

        // 2) Nom du composant
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

        // 3) Table de vérité
        JTextArea truthTableArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(truthTableArea);
        int result = JOptionPane.showConfirmDialog(
            null,
            scrollPane,
            "Entrez la table de vérité (ex: 0=0, 1=1 ou 00=0, 01=1, ...)",
            JOptionPane.OK_CANCEL_OPTION
        );
        if (result != JOptionPane.OK_OPTION) return;
        String truthTable = truthTableArea.getText().trim();
        if (truthTable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Table de vérité vide !");
            return;
        }

        // 4) Sélection d’une icône : trois cas
        String imageFileName;
        String[] iconChoices = {"Défaut", "Parcourir fichier...", "Choisir parmi icônes"};
        int iconMode = JOptionPane.showOptionDialog(
            null,
            "Comment choisir l'icône ?",
            "Sélection icône",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            iconChoices,
            iconChoices[0]
        );

        if (iconMode == 0) {
            // Cas “Défaut”
            switch (numInputs) {
                case 1: imageFileName = "default1.png"; break;
                case 2: imageFileName = "default2.png"; break;
                case 3: imageFileName = "default3.png"; break;
                default: imageFileName = "default.png";
            }

        } else if (iconMode == 1) {
            // Cas “Parcourir fichier...”
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Sélectionnez votre icône");
            chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Images PNG/JPG", "png", "jpg", "jpeg"));
            int fc = chooser.showOpenDialog(null);
            if (fc == JFileChooser.APPROVE_OPTION) {
                File imgFile = chooser.getSelectedFile();
                // Copie dans /icons2/
                File dest = new File("icons2/" + imgFile.getName());
                try {
                    java.nio.file.Files.createDirectories(dest.getParentFile().toPath());
                    java.nio.file.Files.copy(
                        imgFile.toPath(),
                        dest.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                    imageFileName = imgFile.getName();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                        "Impossible de copier l'icône, utilisation de l'icône par défaut.",
                        "Erreur", JOptionPane.WARNING_MESSAGE);
                    imageFileName = "default.png";
                }
            } else {
                // Annulé → défaut
                imageFileName = "default.png";
            }

        } else if (iconMode == 2) {
            // Cas “Choisir parmi icônes”

            ImageIcon chosen = (ImageIcon) JOptionPane.showInputDialog(
                null,
                "Choisissez une icône existante :",
                "Icônes disponibles",
                JOptionPane.PLAIN_MESSAGE,
                null,
                getAllAvailableIcons(),
                getComponentIconByName("default")
            );
            if (chosen != null) {
                imageFileName = extractIconFileName(chosen);
            } else {
                // Annulé → défaut
                imageFileName = "default.png";
            }

        } else {
            // Si la boîte est fermée autrement
            imageFileName = "default.png";
        }

        // 5) Enregistrement XML dans custom_components{n}
        String folderName = "custom_components" + numInputs;
        File dir = new File(folderName);
        dir.mkdirs();
        File file = new File(dir, componentName + ".xml");
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<component>");
            writer.println("  <name>"      + componentName + "</name>");
            writer.println("  <inputs>"    + numInputs     + "</inputs>");
            writer.println("  <icon>/icons2/" + imageFileName + "</icon>");
            writer.println("  <truthTable><![CDATA[" + truthTable + "]]></truthTable>");
            writer.println("</component>");
            JOptionPane.showMessageDialog(null,
                "Composant sauvegardé dans « " + folderName + " » !"
            );
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erreur lors de la sauvegarde : " + ex.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 6) Instanciation et ajout dans le circuit (inchangé)
        String xmlPath = file.getAbsolutePath();
        int addX = 50, addY = 50;
        MyGate_ig view;
        switch (numInputs) {
            case 1: view = new MyGate_ig(1, xmlPath); break;
            case 2: view = new MyGate_ig(2, xmlPath); break;
            default: view = new MyGate_ig(3, xmlPath); break;
        }
        String uniqueId = componentName + "#" + System.currentTimeMillis();
        Composant logic;
        if (numInputs == 1) logic = new MyGate1(uniqueId, addX, addY, xmlPath);
        else if (numInputs == 2) logic = new MyGate(uniqueId, addX, addY, xmlPath);
        else logic = new MyGate3(uniqueId, addX, addY, xmlPath);

        view.setComposant(logic);
        view.setBounds(addX, addY, view.getPreferredSize().width, view.getPreferredSize().height);
        circuitDesignArea.add(view, Integer.valueOf(2));
        composantsMap.put(view, new java.awt.Point(addX, addY));
        circuitDesignArea.revalidate();
        circuitDesignArea.repaint();
        ConnectionManager.refreshAllConnections();
    }

    /**
     * Exemple : retourne un tableau d'ImageIcon déjà chargées
     * (à adapter selon votre gestion des icônes).
     */
    private ImageIcon[] getAllAvailableIcons() {
        // Récupère tous les noms de composants dans la Map
        Set<String> names = ICON_PATHS.keySet();
        ImageIcon[] icons = new ImageIcon[names.size()];
        int i = 0;
        for (String componentName : names) {
            icons[i++] = getComponentIconByName(componentName);
        }
        return icons;
    }

    private static final Map<String, String> ICON_PATHS = new HashMap<>();
    static {
        ICON_PATHS.put("NOT",                "/icons2/not.png");
        ICON_PATHS.put("OR",                 "/icons2/or.png");
        ICON_PATHS.put("AND",                "/icons2/and.png");
        ICON_PATHS.put("BUFFER",             "/icons2/buffer.png");
        ICON_PATHS.put("NOR",                "/icons2/nor.png");
        ICON_PATHS.put("XOR",                "/icons2/xor.png");
        ICON_PATHS.put("XNOR",               "/icons2/xnor.png");
        ICON_PATHS.put("NAND",               "/icons2/nand.png");
        ICON_PATHS.put("ODD PARITY",         "/icons2/odd_parity.png");
        ICON_PATHS.put("EVEN PARITY",        "/icons2/even_parity.png");
        ICON_PATHS.put("Multiplexer",        "/icons2/multiplexer.png");
        ICON_PATHS.put("Demultiplexer",      "/icons2/demultiplexer.png");
        ICON_PATHS.put("Decoder",            "/icons2/decoder.png");
        ICON_PATHS.put("Priority Encoder",   "/icons2/priority_encoder.png");
        ICON_PATHS.put("Bit Selector",       "/icons2/bit_selector.png");
        ICON_PATHS.put("D flip flop",        "/icons2/d_flip_flop.png");
        ICON_PATHS.put("T flip flop",        "/icons2/t_flip_flop.png");
        ICON_PATHS.put("JK flip flop",       "/icons2/jk_flip_flop.png");
        ICON_PATHS.put("RS flip flop",       "/icons2/rs_flip_flop.png");
        ICON_PATHS.put("Register",           "/icons2/register.png");
        ICON_PATHS.put("Counter",            "/icons2/counter.png");
        ICON_PATHS.put("RAM",                "/icons2/ram.png");
        ICON_PATHS.put("ROM",                "/icons2/rom.png");
        ICON_PATHS.put("Adder",              "/icons2/adder.png");
        ICON_PATHS.put("Subtractor",         "/icons2/subtractor.png");
        ICON_PATHS.put("Multiplier",         "/icons2/multiplier.png");
        ICON_PATHS.put("Shifter",            "/icons2/shifter.png");
        ICON_PATHS.put("Bit Adder",          "/icons2/bit_adder.png");
        ICON_PATHS.put("Bit Finder",         "/icons2/bit_finder.png");
        ICON_PATHS.put("Comparator",         "/icons2/comparator.png");
        ICON_PATHS.put("Divider",            "/icons2/divider.png");
        ICON_PATHS.put("Negator",            "/icons2/negator.png");
        ICON_PATHS.put("Bin",                "/icons2/bin.png");
        ICON_PATHS.put("Probe",              "/icons2/probe.png");
        ICON_PATHS.put("Tunnel",             "/icons2/tunnel.png");
        ICON_PATHS.put("Clock",              "/icons2/clock.png");
        ICON_PATHS.put("Bus Register",       "/icons2/bus_register.png");
        // etc. : ajoute ici les autres associations chemin ↔ nom
    }

    /**
     * Retourne l'icône redimensionnée pour un nom de composant donné.
     */
    private ImageIcon getComponentIconByName(String name) {
        String iconPath = ICON_PATHS.getOrDefault(name, "/icons2/default.png");
        URL resource = getClass().getResource(iconPath);
        if (resource == null) {
            System.err.println("Icon not found: " + iconPath);
            return new ImageIcon();  // Icône vide en secours
        }
        ImageIcon original = new ImageIcon(resource);
        Image scaled = original.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
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

            //  Réinitialisation
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

    /*Extraire tous les composants graphiques de la liste de connexions*/
    for (Connection conn : ConnectionManager.connections) {
        if (!composants.contains(conn.compSource)) {
            composants.add(conn.compSource);
        }
        if (!composants.contains(conn.compTarget)) {
            composants.add(conn.compTarget);
        }
    }

    circuit = new Circuit(composants, ConnectionManager.connections);

    /* Lancer la simulation dans un thread séparé pour pas gélé l'interface graphique*/
    Thread simulationThread = new Thread(() -> {
        try {
            circuit.simulate(20); // 20 itérations
            for (AbstractComponent comp : composants) {
                ConnectionManager.updateConnectionsForComponent(comp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution du circuit : " + e.getMessage())
            );
        }
    });

    simulationThread.start();
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
            comp = new Substractor_ig();
            break;
        case "Multiplier":
            comp = new Multiplier_ig();
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
        case "Generator_Determinate":
            comp = new Generator_Determinate();
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
        case "Splitter":
            comp = new Splitter_ig();
            break;
        case "Transistor": 
            comp = new Transistor_ig();
            break;
        case " Controlled_Buffer":
            comp = new  Controlled_Buffer_ig();
            break;
         case "lampe_state":
            comp = new LED_couleur_ig(componentName, getX(), getY());
            break;
        case "Controlled_inverter":
            comp =new Controlled_inverter_ig();
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
