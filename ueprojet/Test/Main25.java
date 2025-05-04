package projet;

import java.util.List;

public class Main25 {
    public static void main(String[] args) {
        int n = 7; 
        int[][] matrice = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrice[i][j] = 0;
            }
        }
        matrice[3][3] = -1;
        matrice[4][3] = -1;
        matrice[5][3] = -1;
        
        matrice[3][0] = 2;
        matrice[3][1] = 2;
        matrice[3][2] = 2;
        matrice[3][4] = 2;
        matrice[3][5] = 2;
        matrice[3][6] = 2;
        
        System.out.println("Circuit initial :");
        afficherMatrice(matrice);

        int x1 = 0, y1 = 0;
        int x2 = 6, y2 = 6;

        try {
            List<int[]> chemin = Path_Finder.FindPath(matrice, x1, y1, x2, y2);

            System.out.println("\nChemin trouvé :");
            for (int[] point : chemin) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
                matrice[point[0]][point[1]] = 1;
            }

            System.out.println("\nCircuit après recherche du chemin :");
            afficherMatrice(matrice);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void afficherMatrice(int[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++) {
                if (matrice[i][j] == -1) {
                    System.out.print(" X ");
                } else if (matrice[i][j] == 2) {
                    System.out.print(" N "); 
                } else if (matrice[i][j] == 1) {
                    System.out.print(" * "); 
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    
    }
}
