import java.util.*;
public class Graph<V> {
    private Map<V, Set<V>> adjacencyList = new HashMap<>();
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        Set<V> adjacents = adjacencyList.get(v1);
        if (adjacents.contains(v2)) {
            return false;
        }
        adjacents.add(v2);
        return true;
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vertex not found");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList +
                '}';
    }

    public List<V> onePath(V v1, V v2) {
        Map<V, V> traza = new HashMap<>();
        Stack<V> abierta = new Stack<>();
        abierta.push(v1);
        traza.put(v1, null);
        boolean encontrado = false;

        while (abierta.isEmpty() && !encontrado) {
            V v = abierta.pop();

            if (v.equals(v2)) {
                encontrado = true;
            } else {
                Set<V> a = adjacencyList.get(v);
                if (a != null) {
                    for (V adj : a) {
                        if (!traza.containsKey(adj)) {
                            abierta.push(adj);
                            traza.put(adj, v);
                        }
                    }
                }
            }
        }

        if (encontrado) {
            // Reconstruir el camino desde la traza y devolverlo
            List<V> path = new ArrayList<>();
            V v = v2;
            while (v != null) {
                path.add(0, v);
                v = traza.get(v);
            }
            return path;
        } else {
            // No se ha encontrado un camino, devolver null
            return null;
        }
    }
}
