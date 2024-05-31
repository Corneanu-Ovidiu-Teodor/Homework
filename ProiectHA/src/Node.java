import java.util.ArrayList;
import java.util.List;

// Clasa Node pentru a ține calea și costul
public class Node {
    public List<Integer> path; // Lista orașelor din calea curentă
    public int cost; // Costul curent al căii

    public Node(List<Integer> path, int cost) {
        this.path = new ArrayList<>(path); // Inițializarea căii
        this.cost = cost; // Inițializarea costului
    }
}
