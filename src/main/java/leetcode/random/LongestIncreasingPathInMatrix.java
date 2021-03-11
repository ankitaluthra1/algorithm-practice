package leetcode.random;

import kotlin.Pair;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingPathInMatrix {

    Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
    int maxLength = 1;
    int[][] matrix;

    public int longestIncreasingPath(int[][] matrix) {

        if(matrix.length == 0)
            return 0;

        this.matrix = matrix;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLength = Math.max(dfs(i, j), maxLength);
            }
        }

        return maxLength;

    }

    private int dfs(int i, int j) {

        Pair<Integer, Integer> key = new Pair<>(i, j);

        if (cache.containsKey(key))
            return cache.get(key);
        int current = this.matrix[i][j];
        int max = 1;
        if (j > 0) {
            if (this.matrix[i][j - 1] > current)
                max = Math.max(max, 1 + dfs(i, j - 1));
        }
        if (j < matrix[0].length - 1) {
            if (this.matrix[i][j + 1] > current)
                max = Math.max(max, 1 + dfs(i, j + 1));

        }
        if (i < matrix.length - 1) {
            if (this.matrix[i + 1][j] > current)
                max = Math.max(max, 1 + dfs(i + 1, j));
        }
        if (i > 0) {
            if (this.matrix[i - 1][j] > current)
                max = Math.max(max, 1 + dfs(i - 1, j));
        }

        cache.put(key, max);
        return max;

    }

    public static void main(String[] args) {

        LongestIncreasingPathInMatrix lp = new LongestIncreasingPathInMatrix();
        //int[][] matrix = {{3,4,5},{3,2,6},{2,8,7}};
        int[][] matrix = {{1,2}};
        int result = lp.longestIncreasingPath(matrix);

        System.out.println(result);

    }

}
