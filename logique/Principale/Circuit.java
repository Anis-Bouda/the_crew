package Principale ;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    
               @Override
        public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Circuit)) return false;
        Circuit other = (Circuit) obj;
        
        return Objects.equals(this.composants, other.composants)
                && Objects.equals(this.fils, other.fils);
        }

        @Override
        public int hashCode() {
        return Objects.hash(composants, fils);
        }
        @Override
        public String toString()
        {
            String composantsStr = "[";
            for (int i = 0; i < composants.size(); i++) {
            composantsStr += composants.get(i).getid();
            if (i != composants.size() - 1) composantsStr += ", ";
            }
             composantsStr += "]";

            String filsStr = "[";
            for (int i = 0; i < fils.size(); i++) {
            filsStr += fils.get(i).getSource().getid() + " -> " + fils.get(i).getDestination().getid();
            if (i != fils.size() - 1) filsStr += ", ";
            }
            filsStr += "]";

            return "Circuit{" + 
            "composants=" + composantsStr + 
            ", fils=" + filsStr + 
            '}';
         }
    }