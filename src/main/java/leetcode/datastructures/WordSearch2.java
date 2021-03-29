package leetcode.datastructures;

import java.util.*;

// https://leetcode.com/problems/word-search-ii/

public class WordSearch2 {
    Node trie;
    Set<String> found;
    char[][] board;

    class Node {
        Map<Character, Node> mapping;
        boolean isLast;

        Node() {
            this.mapping = new HashMap<>();
            this.isLast = false;
        }

        Node add(Character ch) {
            if (mapping.containsKey(ch))
                return mapping.get(ch);
            mapping.put(ch, new Node());
            return mapping.get(ch);
        }
    }

    void createTrie(String s) {
        Node temp = trie;
        for (int i = 0; i < s.length(); i++) {
            temp = temp.add(s.charAt(i));
        }
        temp.isLast = true;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Map<Character, List<String>> wordMap = new HashMap<>();
        trie = new Node();
        found = new HashSet<>();
        for (String s : words) {
            createTrie(s);
        }
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfsWithTrie(i, j, trie, new StringBuilder(""), new ArrayList<>());
            }
        }

        return new ArrayList<>(found);
    }

    private void dfsWithTrie(int i, int j, Node node, StringBuilder soFar, List<int[]> traversed) {

        for (int[] arr: traversed){
            if (arr[0] == i && arr[1] == j) {
                return;
            }
        }

        char currentChar = board[i][j];
        if (!node.mapping.containsKey(currentChar))
            return;

        int[] currentTraveresed = new int[2];
        currentTraveresed[0] = i;
        currentTraveresed[1] = j;
        traversed.add(currentTraveresed);

        Node currentNode = node.mapping.get(currentChar);
        if (currentNode.isLast) {
            StringBuilder sg2 = new StringBuilder(soFar);
            found.add(sg2.append(currentChar).toString());
        }

        if (i > 0) {
                StringBuilder sg = new StringBuilder(soFar).append(currentChar);
                List<int[]> newTraversed = new ArrayList<>(traversed);
                dfsWithTrie(i - 1, j, currentNode, sg, newTraversed);
        }
        if (j > 0) {
                StringBuilder sg = new StringBuilder(soFar).append(currentChar);
                List<int[]> newTraversed = new ArrayList<>(traversed);
                dfsWithTrie(i, j - 1, currentNode, sg, newTraversed);
        }
        if (i < board.length - 1) {
                StringBuilder sg = new StringBuilder(soFar).append(currentChar);
                List<int[]> newTraversed = new ArrayList<>(traversed);
                dfsWithTrie(i + 1, j, currentNode, sg, newTraversed);
        }
        if (j < board[0].length - 1) {
                StringBuilder sg = new StringBuilder(soFar).append(currentChar);
                List<int[]> newTraversed = new ArrayList<>(traversed);
                dfsWithTrie(i, j + 1, currentNode, sg, newTraversed);
        }
    }

    public static void main(String[] args) {

        WordSearch2 ws = new WordSearch2();
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain","hklf", "hf"};
        List<String> result = ws.findWords(board, words);

        for (String s : result){
            System.out.println(s);
        }

    }


}
