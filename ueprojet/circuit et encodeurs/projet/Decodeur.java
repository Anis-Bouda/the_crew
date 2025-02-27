package projet;

public class Decodeur extends Composant{
	public Decodeur(String id, int x, int y) {
        super(id,x,y);
    }
    public void Calculer() {
        int valeur = state ? 1 : 0;
        for (int i = 0; i < outputs.size(); i++) {
        	// Pour i = 0, (1 & (1 << 0)) sera 1, donc la sortie 0 sera activée (true).
        	// Pour i = 1, (1 & (1 << 1)) sera 0, donc la sortie 1 sera désactivée (false).
            outputs.get(i).setstate((valeur & (1 << i)) != 0);
   }}}

