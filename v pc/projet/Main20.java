package projet;

import java.util.List;

public class Main20 {
    public static void main(String[] args) {
        int n = 7; // Taille du circuit
        int[][] matrice = new int[n][n];

        // Initialisation : toutes les cases sont libres (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrice[i][j] = 0;
            }
        }

        // Ajout de composants bloquants (-1)
        matrice[3][3] = -1;
        matrice[4][3] = -1;
        matrice[5][3] = -1;
        
        // ajoute une case ou y a deux fils (2)
        matrice[3][6] = 2;

        // Affichage du circuit initial
        System.out.println("Circuit initial :");
        afficherMatrice(matrice);

        // Points de départ et d'arrivée
        int x1 = 0, y1 = 0;
        int x2 = 6, y2 = 6;

        try {
            // Recherche du chemin
            List<int[]> chemin = Path_Finder.FindPath(matrice, x1, y1, x2, y2);

            // Affichage du chemin trouvé
            System.out.println("\nChemin trouvé :");
            for (int[] point : chemin) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
                matrice[point[0]][point[1]] = 1; // Marquer le chemin dans la matrice
            }

            // Affichage du circuit avec le chemin
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
                    System.out.print(" X "); // Composant
                } else if (matrice[i][j] == 2) {
                    System.out.print(" N "); // 2 fils
                } else if (matrice[i][j] == 1) {
                    System.out.print(" * "); // Chemin trouvé
                } else {
                    System.out.print(" . "); // Case libre
                }
            }
            System.out.println();
        }
    }

}
