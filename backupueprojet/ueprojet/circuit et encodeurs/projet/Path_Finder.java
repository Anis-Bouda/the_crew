package projet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Path_Finder {
	  public static List<int[]> FindPath(boolean[][] matrice, int x1, int y1, int x2, int y2) {
	        int n = matrice.length;
	        Queue<List<int[]>> F = new LinkedList<>();
	        Set<String> V = new HashSet<>();

	        F.add(Arrays.asList(new int[]{x1, y1}));
	        V.add(x1 + "," + y1);
            while (!F.isEmpty()) {
	        	// on explore les chemins 
	            List<int[]> C = F.poll();
	            // on extrait le chemin c de la file
	            int[] path_extrait = C.get(C.size() - 1);
	            // peak la tete (x,y) de c 
	            int x = path_extrait[0], y = path_extrait[1];
	            
	            if (x == x2 && y == y2) return C;
	            
	            if (x < 0 || x >= n || y < 0 || y >= n || !matrice[x][y] || V.contains(x + "," + y)) {
	                continue;
	            }

	            V.add(x + "," + y);
                int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	            for (int[] dir : directions) {
	                // les nouvelles coordonnées apres le deplacmeent 
	                int nx = x + dir[0]; 
	                int ny = y + dir[1];
	                List<int[]> new_path = new ArrayList<>(C);
	                new_path.add(new int[]{nx, ny});
	                // ajouter le chemin a la file
	                F.add(new_path);
	           }}
	        throw new RuntimeException("Aucun chemin trouvé");
	}}
