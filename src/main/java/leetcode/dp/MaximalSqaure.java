package leetcode.dp;

// https://leetcode.com/problems/maximal-square/

import java.util.HashMap;
import java.util.Map;

public class MaximalSqaure {

    Map<String, Integer> cache = new HashMap<>();
    int maxRowSize;
    int maxColSize;
    char[][] matrix;
    public int maximalSquare(char[][] matrix) {
        this.matrix = matrix;
        maxRowSize = matrix.length;
        if(maxRowSize == 0)
            return 0;
        maxColSize = matrix[0].length;
        int maxArea = 0;
        for(int i = 0;i < maxRowSize; i++){
            for(int j = 0; j < maxColSize ;j++){
                maxArea = Math.max(maxPossibleSquare(i, j), maxArea);
            }
        }
        return maxArea*maxArea;
    }

    private int maxPossibleSquare(int i, int j){
        String key = i+", "+j;
        if(matrix[i][j] == '0')
            return 0;

        if(i == (maxRowSize - 1) || j == (maxColSize - 1))
            return 1;

        if(cache.containsKey(key))
            return cache.get(key);

        int newI = i+1;
        int newJ = j+1;
        if(matrix[newI][newJ] == '0'){
            cache.put(key, 1);
            return 1;
        }

        int maxDiagonalSquare = maxPossibleSquare(newI, newJ);
        int currentCount = 1;
        for(int x = 1; x <= maxDiagonalSquare; x++){
            if(matrix[i][j+x] == '0' || matrix[i+x][j] == '0'){
                cache.put(key, currentCount);
                return currentCount;
            }
            currentCount++;
        }

        cache.put(key, currentCount);
        return currentCount;
    }

    public static void main(String[] args) {
        MaximalSqaure maximalSqaure = new MaximalSqaure();
        char[][] matrix =  {{'1','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'0','0','1','1','1'}};
        int i = maximalSqaure.maximalSquare(matrix);

        System.out.println(i);
    }

}
