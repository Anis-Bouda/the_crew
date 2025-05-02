package projet;

public class Sept_Segments extends Composant {
	private boolean[] segments;
	
	public Sept_Segments(String id, int x, int y) {
		super(id,x,y);
		this.segments = new boolean[7];
	}
 
	public void Calculer() {
		int value = 0;
		for (int i = 0; i < inputs.size(); i++) {
	        if (inputs.get(i).getstate()) {
	              value |= (1 << i);
	    }}
		
		switch (value) {
        case 0: 
        	segments = new boolean[]{true, true, true, true, true, true, false}; 
        	break;
        case 1: 
        	segments = new boolean[]{false, true, true, false, false, false, false}; 
        	break;
        case 2: 
        	segments = new boolean[]{true, true, false, true, true, false, true}; 
        	break;
        case 3: 
        	segments = new boolean[]{true, true, true, true, false, false, true}; 
        	break;
        case 4: 
        	segments = new boolean[]{false, true, true, false, false, true, true}; 
        	break;
        case 5: 
        	segments = new boolean[]{true, false, true, true, false, true, true}; 
        	break;
        case 6: 
        	segments = new boolean[]{true, false, true, true, true, true, true}; 
        	break;
        case 7: 
        	segments = new boolean[]{true, true, true, false, false, false, false}; 
        	break;
        case 8: 
            segments = new boolean[]{true, true, true, true, true, true, true}; 
            break;
        case 9: 
        	segments = new boolean[]{true, true, true, true, false, true, true}; 
        	break;
        default: 
        	segments = new boolean[]{false, false, false, false, false, false, false}; 
        	break;
      }
      for (int i = 0; i < segments.length; i++) {
            outputs.get(i).setstate(segments[i]);
      }}}
