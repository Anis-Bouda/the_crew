package projet;

public class Main23 {
		/*test de GENERATOR, Power, ET,OU,XOR,XNOR,NON,NAND,NOR*/
		    public static void main(String[] args) {
		        /*Création du circuit*/
		        Circuit circuit = new Circuit();
		        /*Création de l'état d'entrée*/
		        State input1 = State.True;  /*Première entrée de la porte AND*/
		        State input2 = State.False; /* Deuxième entrée de la porte AND*/
		        /*Créer des composants générateurs pour fournir des valeurs aux entrées de la porte AND*/
		        GENERATOR gen1 = new GENERATOR("GEN1", 0, 1, input1);
		        /*public File(Composant Source, Composant Destination, 
				int destinationInputIndex, int sourceOutputIndex)*/
		        GENERATOR gen2 = new GENERATOR("GEN2", 0, 2, input2);
		        Power pwr1=new Power("pwr1",0,0);
		        Power pwr2=new Power("pwr2",0,0);
		        /* Création d'une portes logiques*/
		        ET et = new ET("AND1", 0, 0);
		        OU ou = new OU("OU1",0,0);
		        /*le files qui lient les porte et et ou avec les sources*/
		        Fil filgen1= new Fil("filgen1",gen1,et,0,0);
		        Fil filgen2= new Fil("filgen2",gen2,et,1,0);
		        Fil filpwr1= new Fil("filpwr1",pwr1,ou,0,0);
		        Fil filpwr2= new Fil("filpwr2",pwr2,ou,1,0);
		        /*files des sorties des deux portes logiques*/
		        NON non=new NON("NON1",0,0);
		        Fil filet= new Fil("filet",et,non,0,0);
		        XOR xor= new XOR("XOR1",0,0);
		        Fil filnon=new Fil("filnon",non,xor,0,0);
		        Fil filou= new Fil("filou",ou,xor,1,0);
		        /*file de sorties de xor*/
		        Power pwr3=new Power("pwr3",0,0);
		        XNOR xnor=new XNOR("XNOR1",0,0);
		        Fil filxor=new Fil("filxor",xor,xnor,0,0);
		        Fil filpwr3=new Fil("filpwr3",pwr3,xnor,1,0);
		        /*file de sortie de xnor*/
		        GENERATOR gen3=new GENERATOR("gen3",0,0,State.True);
		        NAND nand=new NAND("NAND1",0,0);
		        Fil filxnor=new Fil("filxnor",xnor,nand,0,0);
		        Fil filgen3=new Fil("filgen3",gen3,nand,1,0);
		        /*file de sortie de nand*/
		        GENERATOR gen4=new GENERATOR("gen4",0,0,State.False);
		        NOR nor=new NOR("NOR1",0,0);
		        Fil filnand=new Fil("filnand",nand,nor,0,0);
		        Fil filgen4=new Fil("filgen4",gen4,nor,1,0);
		        /*file de sortie de nor*/
		        Ground ground=new Ground("Ground1",0,0);
		        Fil filnor=new Fil("filenor",nor,ground,0,0);

		        /* Ajout des générateurs au circuit*/
		        circuit.addComposant(gen1);
		        circuit.addComposant(gen2);
		        circuit.addComposant(gen3);
		        circuit.addComposant(gen4);
		        circuit.addComposant(pwr1);
		        circuit.addComposant(pwr2);
		        circuit.addComposant(pwr3);
		        circuit.addComposant(et);
		        circuit.addComposant(ou);
		        circuit.addComposant(xor);
		        circuit.addComposant(xnor);
		        circuit.addComposant(non);
		        circuit.addComposant(nand);
		        circuit.addComposant(nor);
		        circuit.addComposant(ground);

		        /*Ajouter les fils au circuit*/
		        circuit.addFil(filgen1);
		        circuit.addFil(filgen2);
		        circuit.addFil(filgen3);
		        circuit.addFil(filgen4);
		        circuit.addFil(filpwr1);
		        circuit.addFil(filpwr2);
		        circuit.addFil(filpwr3);
		        circuit.addFil(filet);
		        circuit.addFil(filou);
		        circuit.addFil(filnon);
		        circuit.addFil(filxor);
		        circuit.addFil(filxnor);
		        circuit.addFil(filnand);
		        circuit.addFil(filnor);
		        /*Affichage de l'état initial*/
		        System.out.println("===================================================================================");
		        System.out.println("===============================État initial du circuit:============================");
		        System.out.println("Les composants : ");
		        System.out.println("================");
		        circuit.afficheState();
		        System.out.println("les files : ");
		        System.out.println("============");
		        for(int i=0;i<14;i++)
		        {
		          System.out.println("Fil"+circuit.fils.get(i).getId()+" "+circuit.fils.get(i).getValue());
		        }
		        System.out.println("les entrees et sorties des composants : ");
		        System.out.println("========================================");
		        for(int i=0;i<14;i++)

		        {

		                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());

		        }
		        /*Simulation du circuit*/
		        try {
		            circuit.simulate(20); /*Max 20 itérations*/
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        }

		        /* Affichage de l'état après simulation*/
		        System.out.println("===================================================================================");
		        System.out.println("=====================État du circuit après simulation:=============================");
		        System.out.println("les composants : ");
		        System.out.println("================");
		        circuit.afficheState();
		        System.out.println("les files : ");
		        System.out.println("============");
		        for(int i=0;i<14;i++)
		        {
		                circuit.fils.get(i).update();
		                filnor.update();
		                System.out.println("Fil"+circuit.fils.get(i).getId()+" "+circuit.fils.get(i).getValue());
		        }
		        System.out.println("les entrees et sorties des composants : ");
		        System.out.println("========================================");
		        for(int i=0;i<14;i++)
		        {
		                circuit.composants.get(i).evaluate();
		                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());
		        }

		    }
}
