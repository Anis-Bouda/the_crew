package projet;

import java.util.Arrays;
import java.util.List;

public class Main19 {
    public static void main(String[] args) {
        boolean[][] matrice = {
            {true,  true,  false, true},
            {false, false,  false, true},
            {true,  true,  true,  true},
            {false, false, true,  true}
        };

        int x1 = 0, y1 = 0; // Départ (S)
        int x2 = 2, y2 = 2; // Arrivée (E)

        try {
            List<int[]> path = Path_Finder2.FindPath(matrice, x1, y1, x2, y2);
            for (int[] step : path) {
                System.out.println(Arrays.toString(step));
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
