import java.util.*;

// Clasă separată pentru algoritmul A*
public class AStar {
    public static Result solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost + heuristic(a.path))); // Coada de prioritate pentru A*
        pq.add(new Node(Collections.singletonList(0), 0)); // Adaugă orașul de start
        int minCost = Integer.MAX_VALUE; // Costul minim inițializat la o valoare mare
        List<Integer> bestPath = new ArrayList<>(); // Cea mai bună rută

        while (!pq.isEmpty()) {
            Node node = pq.poll(); // Preluarea nodului cu costul minim și euristică

            if (node.path.size() == TSP.n + 1) { // Dacă ruta include toate orașele și se întoarce la start
                if (node.cost < minCost) { // Actualizează costul și ruta minimă
                    minCost = node.cost;
                    bestPath = node.path;
                }
                continue; // Continuă la următorul nod
            }

            int lastCity = node.path.get(node.path.size() - 1); // Ultimul oraș vizitat

            for (int nextCity = 0; nextCity < TSP.n; nextCity++) {
                if (!node.path.contains(nextCity)) { // Verifică dacă orașul următor nu este deja în rută
                    List<Integer> newPath = new ArrayList<>(node.path); // Creează o nouă rută
                    newPath.add(nextCity); // Adaugă orașul următor
                    int newCost = node.cost + TSP.distances[lastCity][nextCity]; // Calculează noul cost

                    if (newPath.size() == TSP.n) { // Dacă toate orașele au fost vizitate
                        newPath.add(0); // Adaugă orașul de start pentru închidere
                        newCost += TSP.distances[nextCity][0]; // Adaugă costul de întoarcere la start
                    }

                    pq.add(new Node(newPath, newCost)); // Adaugă noul nod în coadă
                }
            }
        }

        return new Result(minCost, bestPath); // Returnează rezultatul final
    }

    // Funcție euristică pentru A*
    private static int heuristic(List<Integer> path) {
        int maxRemainingEdge = 0; // Inițializarea distanței maxime restante
        Set<Integer> remainingCities = new HashSet<>(); // Set pentru orașele rămase de vizitat
        for (int i = 0; i < TSP.n; i++) {
            if (!path.contains(i)) remainingCities.add(i); // Adaugă orașele care nu sunt încă în rută
        }

        for (int city : path) {
            for (int remainingCity : remainingCities) {
                maxRemainingEdge = Math.max(maxRemainingEdge, TSP.distances[city][remainingCity]); // Calculează distanța maximă
            }
        }

        return maxRemainingEdge; // Returnează distanța maximă restantă ca euristică
    }
}
