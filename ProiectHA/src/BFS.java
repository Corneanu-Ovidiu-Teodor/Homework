import java.util.*;

// Clasă separată pentru algoritmul BFS
public class BFS {
    public static Result solve() {
        Queue<Node> queue = new LinkedList<>(); // Coada pentru BFS
        queue.add(new Node(Collections.singletonList(0), 0)); // Adaugă orașul de start
        int minCost = Integer.MAX_VALUE; // Costul minim inițializat la o valoare mare
        List<Integer> bestPath = new ArrayList<>(); // Cea mai bună rută

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // Preluarea nodului din coadă

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

                    queue.add(new Node(newPath, newCost)); // Adaugă noul nod în coadă
                }
            }
        }

        return new Result(minCost, bestPath); // Returnează rezultatul final
    }
}
