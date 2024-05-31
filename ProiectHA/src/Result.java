import java.util.List;

// Clasa Result pentru a ține costul și calea
public class Result {
    public int cost; // Costul total al rutei
    public List<Integer> path; // Lista orașelor din rută

    public Result(int cost, List<Integer> path) {
        this.cost = cost; // Inițializarea costului
        this.path = path; // Inițializarea rutei
    }
}
