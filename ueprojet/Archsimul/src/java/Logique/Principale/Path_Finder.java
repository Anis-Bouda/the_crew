package Logique.Principale; 
import java.util.*;

public class Path_Finder {
	// 0: espace est libre 
	// 1: espace est occupé
	// 2: ya deux fils dans le case 
	// -1: y a un composant dans la case 
    public static List<int[]> FindPath(int[][] matrice, int x1, int y1, int x2, int y2) {
        int n = matrice.length;
        // une file pour stocker les chemins explorés
        Queue<LinkedList<int[]>> F = new LinkedList<>();
        // les cases visitées
        Set<String> V = new HashSet<>();

        // un premier chemin qui contient le point de depart (x1, y1)
        LinkedList<int[]> startPath = new LinkedList<>();
        startPath.add(new int[]{x1, y1});
        // on l'ajoute alors a l'ensemble des cases visitées
        F.add(startPath);

        // les quatres directions
        // {1,0} pour aller en bas 
        // {-1,0} pour aller en haut 
        // {0,1} pour aller a droite 
        // {0,-1} pour aller a gauche
        // il privilige la droite 
        // apres le bas 
        // apres la gauche 
        // apres le haut
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // en parcours la file tant que elle n'est pas vide
        while (!F.isEmpty()) {
        	// on recupere un chemin 
            LinkedList<int[]> C = F.poll();
            int[] dernier = C.getLast();
            // on recupere la derniere case de ce chemin 
            int x = dernier[0], y = dernier[1];

            if (x == x2 && y == y2) return new ArrayList<>(C);

            // on continue si c'est dans l'intervalle ou y a pas un mur ou on n'a pas deja visiter le point 
            if (x < 0 || x >= n || y < 0 || y >= n || V.contains(x + "," + y)) {
                continue;
            }

            if(matrice[x][y] == -1) {
            	// ya un compasant bloquant 
            	continue;
            }
            
            // quand y a une case, il evite la colonne
            if(matrice[x][y] == 2) {
            	// ne permet pas 3 fils sur la meme case 
            	continue;
            }
            V.add(x + "," + y);

            for (int[] dir : directions) {
            	// on calcule les nouvelles coordonnées pour chaque direction 
                int nx = x + dir[0], ny = y + dir[1];

                // on verifie que c'est valide 
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !V.contains(nx + "," + ny)) {
                       // on crée une copie du chemin actuel et on ajoute (nx,ny)
                	   LinkedList<int[]> newPath = new LinkedList<>(C);
                       newPath.add(new int[]{nx, ny});
                       // on ajoute le nouveau chemin a la file 
                       F.add(newPath);
                }
            }
        }

        throw new RuntimeException("Aucun chemin trouvé");
    }
}
