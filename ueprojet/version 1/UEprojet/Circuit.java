import java.util.ArrayList;
import java.util.List;

class Circuit  {
    private List<Composant> composants;
    private List<File> files;

    public Circuit() {
        composants = new ArrayList<>();
        files = new ArrayList<>();
    }

    public void addComposant(Composant composant) {
        composants.add(composant);
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void simulate(int MAX_ITERATIONS) throws Exception {
        boolean stable;
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            stable = true;

            for (Composant composant : composants) {
                composant.evaluate();
            }

            for (File file : files) {
                boolean previousValue = file.getValue();
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
