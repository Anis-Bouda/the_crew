package projet;

public class ByteSelctor extends Composant {
	public ByteSelctor(String id, int x, int y) {
        super(id,x,y);
    }
	@Override
    public void Calculer() {
        if (inputs.size() < 9) return;
        int index = 0;
        for (int i = 1; i <= 8; i++) {
            if (inputs.get(i).getstate()) {
                index |= (1 << (i - 1));
            }
        }
        state = inputs.get(0).getstate();
    }
}

