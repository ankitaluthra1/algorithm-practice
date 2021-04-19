package leetcode.datastructures;

import java.util.*;

// https://leetcode.com/problems/network-delay-time/
// incomplete
class Pair {
    int first;
    int second;

    private Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Pair of(int first, int second) {
        return new Pair(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

class NetworkDelay {
    Map<Integer, List<Pair>> graph;
    Map<Pair, Integer> cache;

    public int networkDelayTime(int[][] times, int n, int k) {
        graph = new HashMap<>();
        for (int[] time : times) {
            List<Pair> connections = graph.getOrDefault(time[0], new ArrayList<>());
            connections.add(Pair.of(time[1], time[2]));
            graph.put(time[0], connections);
        }

        int[] distance = new int[n+1];
        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        cache = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int dfs = dfs(k, i, new ArrayList<>());
            if (dfs == -1)
                return -1;
            distance[i] = dfs;
        }

        int finalTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            finalTime = Math.max(finalTime, distance[i]);
        }
        return finalTime;
    }

    public int dfs(int src, int dest, List<Integer> visited) {
        Pair key = Pair.of(src, dest);
        if (cache.containsKey(key))
            return cache.get(key);

        if (src == dest)
            return 0;
        if (!graph.containsKey(src))
            return -1;

        List<Pair> connections = graph.get(src);
        int minDistance = Integer.MAX_VALUE;
        for (Pair connection : connections) {
            if (!visited.contains(connection.first)) {
                List<Integer> updatedVisited = new ArrayList<>(visited);
                updatedVisited.add(src);
                updatedVisited.add(connection.first);
                if (connection.first == dest) {
                    minDistance = Math.min(minDistance, connection.second);
                } else {
                    int temp = dfs(connection.first, dest, updatedVisited);
                    if (temp != -1)
                        minDistance =  Math.min(minDistance, connection.second +temp);
                }
            }
        }
        if (minDistance != Integer.MAX_VALUE) {
            cache.put(key, minDistance);
            return minDistance;
        }
        return -1;
    }

    public static void main(String[] args) {
        NetworkDelay nd = new NetworkDelay();
        int[][] times = {{1,2,1},{2,3,7},{1,3,4},{2,1,2}, {3,4,1}};
        int result = nd.networkDelayTime(times, 4, 1);

        System.out.println(result);
    }

}
