package leetcode.dp;

import java.util.*;

//https://leetcode.com/problems/path-with-maximum-gold/
public class MaximumGoldPath {
    class Pair<T, S>{
        T first;
        S second;

        public Pair(T first, S second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return first.equals(pair.first) && second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
    public int getMaximumGold(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int current = grid[i][j];
                if (current != 0) {
                    List<Pair<Integer, Integer>> adjacencyList = new ArrayList<>();
                    if (i != 0) {
                        if (grid[i - 1][j] != 0)
                            adjacencyList.add(new Pair<>(i - 1, j));
                    }
                    if (i != m - 1) {
                        if (grid[i + 1][j] != 0)
                            adjacencyList.add(new Pair<>(i + 1, j));
                    }
                    if (j != 0) {
                        if (grid[i][j - 1] != 0)
                            adjacencyList.add(new Pair<>(i, j - 1));
                    }
                    if (j != n - 1) {
                        if (grid[i][j + 1] != 0)
                            adjacencyList.add(new Pair<>(i, j + 1));
                    }
                    graph.put(new Pair<>(i, j), adjacencyList);
                }
            }
        }

        int maxPath = 0;
        for (Pair<Integer, Integer> pair : graph.keySet()) {
            List<Pair<Integer, Integer>> parentList = new ArrayList<>();
            parentList.add(pair);
            maxPath = Math.max(maxPath, getMaxPath(grid, graph, pair, parentList, new HashMap<>()));
        }
        return maxPath;
    }

    private int getMaxPath(int[][] grid,
                           Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> graph,
                           Pair<Integer, Integer> index,
                           List<Pair<Integer, Integer>> parentList,
                           Map<Pair<Integer, Integer>, Integer> countMap) {

//        if (countMap.containsKey(index)) {
//            return countMap.get(index);
//        }

        int maxPath = grid[index.getFirst()][index.getSecond()];

        for (Pair<Integer, Integer> neighbor : graph.get(index)) {
            if (parentList.contains(neighbor))
                continue;
            int tempPath;

//            if (countMap.containsKey(neighbor)) {
//                tempPath = countMap.get(neighbor);
//            } else {
                List<Pair<Integer, Integer>> newParentList = new ArrayList<>(parentList);
                newParentList.add(neighbor);
                 tempPath = getMaxPath(grid,
                        graph,
                        neighbor,
                        newParentList,
                        countMap);
   //         }
            maxPath = Math.max(maxPath,  grid[index.getFirst()][index.getSecond()] + tempPath);

        }
        countMap.put(index, maxPath);
        return maxPath;
    }

    public static void main(String[] args) {

        MaximumGoldPath path = new MaximumGoldPath();
        int[][] grid = {{1,0,7,0,0,0},{2,0,6,0,1,0},{3,5,6,7,4,2},{4,3,1,0,2,0},{3,0,5,0,20,0}};
        int result = path.getMaximumGold(grid);

        System.out.println(result);
    }

}
