package projet;

public class DIVIDER extends Composant {
	private int n;
	public DIVIDER(String id, int n, int x, int y) {
	super(id, x, y);
	this.n = n;

	/*initialisation des entrées A[n]*/
	for (int i = 0; i < 2 * n; i++) {
		this.addInput(State.UNKNOWN); 
	}

	/*initialisation de la sortie */
	for (int i = 0; i < 2 * n; i++) 
	{
		this.addOutput(State.UNKNOWN);
	}}

	@Override
	public void evaluate() {
	    // Vérification de la taille des entrées et sorties
	    if (this.inputs.size() != 2 * n || this.outputs.size() != 2 * n) {
	        throw new IllegalStateException("Les tailles des entrées et des sorties ne sont pas correctes.");
	    }

	    int cas0 = 0;
	    int caserr = 0;
	    int casind = 0;
	    int a = 0;
	    int b = 0;
	    int quotient;
	    int reste;

	    // Boucle pour vérifier les erreurs ou inconnus dans les entrées
	    for (int i = 0; i < 2 * n; i++) {
	        if (this.inputs.get(i) == State.ERROR) {
	            caserr = caserr + 1;
	        }
	        if (this.inputs.get(i) == State.UNKNOWN) {
	            casind = casind + 1;
	        }
	        if (i >= n && this.inputs.get(i) == State.ERROR) {
	            cas0 = cas0 + 1;
	        }
	    }

	    // Traitement en cas d'erreur
	    if (caserr != 0) {
	        for (int i = 0; i < 2 * n; i++) {
	            this.outputs.set(i, State.ERROR);
	        }
	        return;
	    }

	    // Traitement en cas d'inconnu
	    if (casind != 0) {
	        for (int i = 0; i < 2 * n; i++) {
	            this.outputs.set(i, State.UNKNOWN);
	        }
	        return;
	    }

	    // Traitement en cas d'erreur sur le diviseur
	    if (cas0 != 0) {
	        for (int i = 0; i < 2 * n; i++) {
	            this.outputs.set(i, State.ERROR);
	        }
	        return;
	    }

	    // Calcul du quotient et du reste
	    for (int i = 0; i < n; i++) {
	        if (this.inputs.get(i) == State.True) {
	            a |= (1 << i);
	        }
	        if (this.inputs.get(n + i) == State.True) {
	            b |= (1 << i);
	        }
	    }

	    // Vérification de la division par zéro
	    if (b == 0) {
	        for (int i = 0; i < 2 * n; i++) {
	            this.outputs.set(i, State.ERROR);
	        }
	        return;
	    }

	    quotient = a / b;
	    reste = a % b;

	    // Stockage du quotient dans les sorties
	    for (int i = 0; i < n; i++) {
	        if ((quotient & (1 << i)) != 0) {
	            this.outputs.set(i, State.True);
	        } else {
	            this.outputs.set(i, State.False);
	        }
	    }

	    // Stockage du reste dans les sorties
	    for (int i = 0; i < n; i++) {
	        if ((reste & (1 << i)) != 0) {
	            this.outputs.set(n + i, State.True);
	        } else {
	            this.outputs.set(n + i, State.False);
	        }
	    }
	}
}