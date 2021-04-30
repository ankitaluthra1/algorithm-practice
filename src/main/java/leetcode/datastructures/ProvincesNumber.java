package leetcode.datastructures;

import java.util.*;

public class ProvincesNumber {
    Map<Integer, List<Integer>> graph;
    public int findCircleNum(int[][] isConnected) {
        graph = new HashMap<>();
        for (int i = 0; i< isConnected.length; i++){
            for(int j = 0;j< isConnected[0].length;j++){
                if(isConnected[i][j] == 1){
                    List<Integer> neighbors = graph.getOrDefault(i, new ArrayList<>());
                    neighbors.add(j);
                    graph.put(i, neighbors);
                }
            }
        }
        int connected = 0;
        Set<Integer> visited = new HashSet<>();
        for(int i = 0;i<isConnected.length;i++){
            if(visited.contains(i))
                continue;
            connected++;
            dfs(i, visited);
        }
        return connected;
    }
    public void dfs(int i, Set<Integer> visited){
        if(visited.contains(i))
            return;
        visited.add(i);
        for(Integer in : graph.get(i))
            dfs(in, visited);
    }

    public static void main(String[] args) {
        ProvincesNumber pv =  new ProvincesNumber();
        int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
        int result = pv.findCircleNum(isConnected);

        System.out.println(result);
    }

}
