package leetcode.datastructures;

import java.util.*;

// https://leetcode.com/problems/surrounded-regions/
public class SurroundedRegions {
    List<String> cache;
    char[][] board;

    private void checkOnlyRowBoundries(int i) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == 'X')
                continue;
            dfs(i, j, new ArrayList<>());
        }
    }

    private void checkOnlyColBoundries(int j) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][j] == 'X')
                continue;
            dfs(i, j, new ArrayList<>());
        }
    }

    public void solve(char[][] board) {
        cache = new ArrayList<>();
        this.board = board;

        checkOnlyRowBoundries(0);
        checkOnlyRowBoundries(board.length - 1);
        checkOnlyColBoundries(0);
        checkOnlyColBoundries(board[0].length - 1);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X')
                    continue;
                String key = i + ", " + j;
                if (!cache.contains(key)){
                    board[i][j] = 'X';
                }
            }
        }


    }

    private void dfs(int i, int j, List<String> previous) {
        String key = i+", "+j;
        if(previous.contains(key) || cache.contains(key))
            return;
        cache.add(key);
        List<String> newList = new ArrayList<>(previous);
        newList.add(key);

        if(needToCheck(i-1, j)){
            dfs(i-1,j,newList);
        }
        if(needToCheck(i + 1, j)){
            dfs(i+1,j,newList);
        }
        if(needToCheck(i, j-1)){
            dfs(i,j-1,newList);
        }
        if(needToCheck(i, j+1)){
            dfs(i,j+1,newList);
        }

    }

    private boolean needToCheck(int i, int j) {
        if (i <= 0 || j <=0 || i>= (board.length -1) || j>= (board[0].length -1))
            return false;
        return board[i][j] == 'O';
    }

    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = {{'O', 'X', 'X', 'O', 'X'},
                          {'X', 'O', 'O', 'X', 'O'},
                          {'X', 'O', 'X', 'O', 'X'},
                          {'O', 'X', 'O', 'O', 'O'},
                          {'X', 'X', 'O', 'X', 'O'}};
        surroundedRegions.solve(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
