package projet;

public class Main22 {
	    public static void main(String[] args) {
	        Circuit circuit = new Circuit();
	        RandomGenerator randomgenerator1=new RandomGenerator("rndgen1",4,0,0);
	        RandomGenerator randomgenerator2=new RandomGenerator("rndgen2",4,0,0);
	        RandomGenerator randomgenerator3=new RandomGenerator("rndgen3",4,0,0);
	        RandomGenerator randomgenerator4=new RandomGenerator("rndgen4",4,0,0);

	        randomgenerator1.inputs.set(0,State.True);
	        randomgenerator2.inputs.set(0,State.True);
	        randomgenerator3.inputs.set(0,State.True);
	        randomgenerator4.inputs.set(0,State.True);

	        Register register1=new Register("reg1",4,0,0);
	        Register register2=new Register("reg2",4,0,0);
	        Register register3=new Register("reg3",4,0,0);
	        Register register4=new Register("reg4",4,0,0);
	        register1.inputs.set(5,State.True);
	        register1.inputs.set(4,State.True);
	        register2.inputs.set(5,State.True);
	        register2.inputs.set(4,State.True);
	        register3.inputs.set(5,State.True);
	        register3.inputs.set(4,State.True);
	        register4.inputs.set(4,State.True);
	        register4.inputs.set(5,State.True);

	        circuit.addComposant(randomgenerator1);
	        circuit.addComposant(randomgenerator2);
	        circuit.addComposant(randomgenerator3);
	        circuit.addComposant(randomgenerator4);
	        randomgenerator1.evaluate();
	        randomgenerator2.evaluate();
	        randomgenerator3.evaluate();
	        randomgenerator4.evaluate();
	        circuit.addComposant(register1);
	        circuit.addComposant(register2);
	        circuit.addComposant(register3);
	        circuit.addComposant(register4);
	        register1.evaluate();
	        register2.evaluate();
	        register3.evaluate();
	        register4.evaluate();

	        Fil fil11= new Fil("fil11",randomgenerator1,register1,0,0);
	        Fil fil12= new Fil("filg12",randomgenerator1,register1,1,1);
	        Fil fil13= new Fil("fil13",randomgenerator1,register1,2,2);
	        Fil fil14= new Fil("fil14",randomgenerator1,register1,3,3);

	        Fil fil21= new Fil("fil121",randomgenerator2,register2,0,0);
	        Fil fil22= new Fil("filg22",randomgenerator2,register2,1,1);
	        Fil fil23= new Fil("fil23",randomgenerator2,register2,2,2);
	        Fil fil24= new Fil("fil24",randomgenerator2,register2,3,3);

	        Fil fil31= new Fil("fil31",randomgenerator3,register3,0,0);
	        Fil fil32= new Fil("fil32",randomgenerator3,register3,1,1);
	        Fil fil33= new Fil("fil33",randomgenerator3,register3,2,2);
	        Fil fil34= new Fil("fil34",randomgenerator3,register3,3,3);

	        Fil fil41= new Fil("fil41",randomgenerator4,register4,0,0);
	        Fil fil42= new Fil("fil42",randomgenerator4,register4,1,1);
	        Fil fil43= new Fil("fil43",randomgenerator4,register4,2,2);
	        Fil fil44= new Fil("fil44",randomgenerator4,register4,3,3);

	        circuit.addFil(fil11);
	        circuit.addFil(fil12);
	        circuit.addFil(fil13);
	        circuit.addFil(fil14);
	        circuit.addFil(fil21);
	        circuit.addFil(fil22);
	        circuit.addFil(fil23);
	        circuit.addFil(fil24);
	        circuit.addFil(fil31);
	        circuit.addFil(fil32);
	        circuit.addFil(fil33);
	        circuit.addFil(fil34);
	        circuit.addFil(fil41);
	        circuit.addFil(fil42);
	        circuit.addFil(fil43);
	        circuit.addFil(fil44);


	        SUB sub=new SUB("SUB1",4,0,0);
	        ADDER adder=new ADDER("ADDER1",4,0,0);
	        circuit.addComposant(sub);
	        sub.inputs.set(8,State.False);
	        adder.inputs.set(8,State.True);
	        circuit.addComposant(adder);

	        Fil filadd1= new Fil("filadd1",register1,adder,0,0);
	        Fil filadd2= new Fil("filgadd2",register1,adder,1,1);
	        Fil filadd3= new Fil("filadd3",register1,adder,2,2);
	        Fil filadd4= new Fil("filadd4",register1,adder,3,3);
	        Fil filadd5= new Fil("filadd5",register2,adder,4,0);
	        Fil filadd6= new Fil("filadd6",register2,adder,5,1);
	        Fil filadd7= new Fil("filadd7",register2,adder,6,2);
	        Fil filadd8= new Fil("filadd8",register2,adder,7,3);

	        Fil filsub1= new Fil("filsub1",register3,sub,0,0);
	        Fil filsub2= new Fil("filsub2",register3,sub,1,1);
	        Fil filsub3= new Fil("filsub3",register3,sub,2,2);
	        Fil filsub4= new Fil("filsub4",register3,sub,3,3);
	        Fil filsub5= new Fil("filsub5",register4,sub,4,0);
	        Fil filsub6= new Fil("filsub6",register4,sub,5,1);
	        Fil filsub7= new Fil("filsub7",register4,sub,6,2);
	        Fil filsub8= new Fil("filsub8",register4,sub,7,3);

	        circuit.addFil(filadd1);
	        circuit.addFil(filadd2);
	        circuit.addFil(filadd3);
	        circuit.addFil(filadd4);
	        circuit.addFil(filadd5);
	        circuit.addFil(filadd6);
	        circuit.addFil(filadd7);
	        circuit.addFil(filadd8);
	        circuit.addFil(filsub1);
	        circuit.addFil(filsub2);
	        circuit.addFil(filsub3);
	        circuit.addFil(filsub4);
	        circuit.addFil(filsub5);
	        circuit.addFil(filsub6);
	        circuit.addFil(filsub7);
	        circuit.addFil(filsub8);

	        System.out.println("===================================================================================");
	        System.out.println("===============================État initial du circuit:============================");
	        System.out.println("Les composants : ");
	        System.out.println("================");
	        circuit.afficheState();
	        System.out.println("les files 16 : ");
	        System.out.println("============");
	        for(int i=0;i<16;i++)
	        {
	          System.out.println("Fil : "+circuit.fils.get(i).getId()+" "+circuit.fils.get(i).getValue());
	        }
	        System.out.println("les entrees et sorties des composants : ");
	        System.out.println("========================================");
	        for(int i=0;i<8;i++)

	        {

	                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());

	        }
	        /*try {
	            circuit.simulate(50);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }*/

	        System.out.println("===================================================================================");
	        System.out.println("=====================État du circuit après simulation:=============================");
	        /*System.out.println("les composants : ");
	        System.out.println("================");
	        circuit.afficheState();*/
	        System.out.println("les files : ");
	        System.out.println("============");
	        for(int i=0;i<32;i++)
	        {
	                circuit.fils.get(i).update();
	                System.out.println("Fil"+circuit.fils.get(i).getId()+" "+circuit.fils.get(i).getValue());
	        }
	        System.out.println("les entrees et sorties des composants : ");
	        System.out.println("========================================");
	        for(int i=0;i<10;i++)
	        {
	                circuit.composants.get(i).evaluate();
	                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());
	        }
	        register1.inputs.set(5,State.False);
	        register2.inputs.set(5,State.False);
	        register3.inputs.set(5,State.False);
	        register4.inputs.set(5,State.False);
	        for(int i=0;i<8;i++)
	        {
	                circuit.composants.get(i).evaluate();
	                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());
	        }
	        register1.inputs.set(5,State.False);
	        register2.inputs.set(5,State.False);
	        register3.inputs.set(5,State.False);
	        register4.inputs.set(5,State.False);
	                for(int i=16;i<32;i++)
	        {
	                circuit.fils.get(i).update();
	                System.out.println("Fil"+circuit.fils.get(i).getId()+" "+circuit.fils.get(i).getValue());
	        }
	                for(int i=4;i<10;i++)
	        {
	                circuit.composants.get(i).evaluate();
	                System.out.println("Composant "+circuit.composants.get(i).getid()+" les entrees :  "+circuit.composants.get(i).getInputs()+" les sorties : "+circuit.composants.get(i).getOutputs());
	        }
	                /*try {
	            circuit.simulate(50);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        circuit.afficheState();*/
	    }
}
