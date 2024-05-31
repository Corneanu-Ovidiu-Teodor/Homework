import java.util.*;

// Clasa principală pentru problema TSP
public class TSP {
    public static int[][] distances; // Matricea de distanțe între orașe
    public static int n; // Numărul de orașe

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner pentru citirea intrărilor
        System.out.print("Enter the number of cities: ");
        n = scanner.nextInt(); // Citirea numărului de orașe

        generateRandomGraph(n); // Generarea unui graf random de distanțe

        System.out.println("Distances between cities:");
        printDistances(); // Afișarea matricei de distanțe

        // Măsurarea și afișarea rezultatelor pentru BFS
        long startTime = System.currentTimeMillis(); // Timpul de start
        Result bfsResult = BFS.solve(); // Rezultatul BFS
        long endTime = System.currentTimeMillis(); // Timpul de final
        System.out.println("BFS: Cost: " + bfsResult.cost + " Route: " + bfsResult.path + " Time: " + (endTime - startTime) + "ms");

        // Măsurarea și afișarea rezultatelor pentru UCS
        startTime = System.currentTimeMillis(); // Timpul de start
        Result ucsResult = UCS.solve(); // Rezultatul UCS
        endTime = System.currentTimeMillis(); // Timpul de final
        System.out.println("UCS: Cost: " + ucsResult.cost + " Route: " + ucsResult.path + " Time: " + (endTime - startTime) + "ms");

        // Măsurarea și afișarea rezultatelor pentru A*
        startTime = System.currentTimeMillis(); // Timpul de start
        Result astarResult = AStar.solve(); // Rezultatul A*
        endTime = System.currentTimeMillis(); // Timpul de final
        System.out.println("A*: Cost: " + astarResult.cost + " Route: " + astarResult.path + " Time: " + (endTime - startTime) + "ms");
    }

    // Generarea unui graf random cu numărul dat de orașe
    public static void generateRandomGraph(int size) {
        Random random = new Random(); // Obiect Random pentru generarea valorilor random
        distances = new int[size][size]; // Inițializarea matricei de distanțe

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    distances[i][j] = 0; // Distanța de la un oraș la el însuși este 0
                } else {
                    distances[i][j] = 10 + random.nextInt(90); // Distanță random între 10 și 100
                    distances[j][i] = distances[i][j]; // Graf simetric
                }
            }
        }
    }

    // Afișează matricea de distanțe
    public static void printDistances() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(distances[i][j] + " "); // Afișează distanța
            }
            System.out.println(); // Linie nouă pentru următorul rând din matrice
        }
    }
}
