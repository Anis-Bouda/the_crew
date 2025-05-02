package Classe_Principale ;
import java.util.ArrayList;
import java.util.List;

    public class Circuit {
        private List<Composant> composants;
        private List<File> fils;
        
        public Circuit() {
            this.composants = new ArrayList<>();
            this.fils = new ArrayList<>();
        }
        
        public void addComposant(Composant c) {
            composants.add(c);
        }
        
        public void addFil(File f) {
            fils.add(f);
        }
        
        
        public void afficheState() {
            for(Composant C: composants) {
                System.out.println(C.getid() + "," + C.getstate());
        }}
        
        public void simulate(int MAX_ITERATIONS) throws Exception {
            boolean stable;
            for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
                stable = true;
                for (Composant composant : composants) {
                    composant.evaluate();
                }

                for (File file : fils) {
                    State previousValue = file.getValue();
                    file.update();

                    if (previousValue != file.getValue()) {
                        stable = false;
                    }
                }

                if (stable) {
                    System.out.println("Point fixe atteint après " + (iteration + 1) + " itérations.");
                    return;
                }
            }

            throw new Exception("Erreur : circuit instable après " + MAX_ITERATIONS + " itérations.");
        }
    }