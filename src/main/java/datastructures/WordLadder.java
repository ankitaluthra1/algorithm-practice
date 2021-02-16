package datastructures;

import java.util.*;

public class WordLadder {

    private Map<String, List<String>> wordGraph = new HashMap<>();
    private String endWord;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord))
            return 0;

        addInGraph(beginWord, wordList);

        for (String word : wordList) {
            addInGraph(word, wordList);
        }

        this.endWord = endWord;

        return bfs(beginWord);

    }

    int bfs(String beginWord) {

        List<String> queue = new ArrayList<>();

        queue.add(beginWord);
        int level = 0;
        List<String> visited = new ArrayList<>();
        while (!queue.isEmpty()) {

            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                String current = queue.remove(0);
                if (visited.contains(current))
                    continue;
                visited.add(current);
                if (current.equals(endWord))
                    return level;
                queue.addAll(wordGraph.get(current));
            }

        }
        return 0;
    }

    private void addInGraph(String node, List<String> wordList) {

        List<String> adjList = new ArrayList<>();

        for (String word : wordList) {
            if (word.equals(node))
                continue;
            int changed = 0;
            for (int i = 0; i < node.length(); i++) {
                if (node.charAt(i) != word.charAt(i))
                    changed++;
            }
            if (changed == 1)
                adjList.add(word);
        }

        wordGraph.put(node, adjList);

    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");


        WordLadder wordLadder = new WordLadder();
        int count = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.println(count);

    }

}
