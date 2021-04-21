package leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/redundant-connection/
public class RedundantConnection {
    Map<Integer, Integer> graph = new HashMap<>();
    public int[] findRedundantConnection(int[][] edges) {
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], edge[0]);
            }
            if (graph.containsKey(edge[1])) {
                int key = edge[1];
                if (find(key,edge[0]))
                    return edge;
                while (graph.get(key) != key) {
                    int newKey = graph.get(key);
                    graph.put(key, graph.get(edge[0]));
                    key = newKey;
                }
                graph.put(key, graph.get(edge[0]));
            }else {
                graph.put(edge[1], graph.get(edge[0]));
            }
        }
        return new int[0];
    }

    private boolean find(int key1, int key2){
        while (key1 != graph.get(key1))
            key1 = graph.get(key1);
        while (key2 != graph.get(key2))
            key2 = graph.get(key2);

        return key1 == key2;
    }

    public static void main(String[] args) {
        RedundantConnection rd = new RedundantConnection();
        int[][] edges = {{2,4},{3,4},{1,4},{2,5},{4,5}};
        int[] result = rd.findRedundantConnection(edges);

        System.out.println(result[0]+" "+result[1]);
    }

}
