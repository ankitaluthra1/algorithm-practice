package glassdoorQuestions.linkedIn;

import java.util.*;

public class IslandUnionFind {

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    connect(i, j, grid, m, n);
                    islands++;
                }
            }
        }

        return islands;
    }

    private void connect(int i, int j, char[][] grid, int m, int n) {
        if (grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        if (i - 1 >= 0) {
            connect(i-1, j, grid, m, n);
        }
        if (j - 1 >= 0) {
            connect(i, j-1, grid, m, n);
        }
        if (j + 1 < n) {
            connect(i, j+1, grid, m, n);
        }
        if (i + 1 < m) {
            connect(i+1, j, grid, m, n);
        }
    }

    public static void main(String[] args) {
        IslandUnionFind is = new IslandUnionFind();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int count = is.numIslands(grid);

        System.out.println(count);
    }
}
