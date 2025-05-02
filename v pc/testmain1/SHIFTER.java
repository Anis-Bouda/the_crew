public class SHIFTER extends Composant {
	private int n;
    public SHIFTER(String id, int n, int x, int y) {
    super(id, x, y);
    this.n = n;

    /*initialisation des entrées */
    for (int i = 0; i < n+1; i++) {
    	this.addInput(State.UNKNOWN); 
    }

    /*initialisation de la sortie */
    for (int i = 0; i < n; i++) {
    	this.addOutput(State.UNKNOWN);
    }
    }

    @Override
    public void evaluate() {
        int caserr = 0;
        int casind = 0;

        // Vérification des erreurs sur les entrées
        for (int i = 0; i < n; i++) {
            if (this.inputs.get(i) == State.ERROR) {
                caserr = caserr + 1;
            }
            if (this.inputs.get(i) == State.UNKNOWN) {
                casind = casind + 1;
            }
        }

        // Si il y a des erreurs dans les entrées ou dans le signal de décalage
        if (this.inputs.get(n) == State.ERROR || caserr != 0) {
            for (int i = 0; i < n; i++) {
                this.outputs.set(i, State.ERROR);
            }
            return;
        }

        // Si il y a des inconnues dans les entrées
        if (this.inputs.get(n) == State.UNKNOWN || casind != 0) {
            for (int i = 0; i < n; i++) {
                this.outputs.set(i, State.UNKNOWN);
            }
            return;
        }

        // Vérifiez le signal de décalage
        boolean shiftLeft = this.inputs.get(n) == State.TRUE;
        if (shiftLeft) {
            // Décalage à gauche
            for (int i = 0; i < n - 1; i++) {  // Décale tout sauf le dernier bit
                this.outputs.set(i, this.inputs.get(i + 1));
            }
            this.outputs.set(n - 1, State.FALSE);  // Le dernier bit est mis à False
        } else {
            // Décalage à droite
            for (int i = 1; i < n; i++) {  // Décale tout sauf le premier bit
                this.outputs.set(i, this.inputs.get(i - 1));
            }
            this.outputs.set(0, State.FALSE);  // Le premier bit est mis à False
        }
    }}
