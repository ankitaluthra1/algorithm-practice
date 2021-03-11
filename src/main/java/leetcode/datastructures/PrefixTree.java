package leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

public class PrefixTree {

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("apps"));
        trie.insert("tester");
        System.out.println(trie.startsWith("test"));
        System.out.println(trie.search("test"));
    }

}

class Node{
    Map<Character, Node> mappings;
    boolean isEndOfWord;

    Node(){
        mappings = new HashMap<>();
        isEndOfWord = false;
    }

    Node add(Character c){
        if (mappings.containsKey(c))
            return mappings.get(c);

        Node newNode = new Node();
        mappings.put(c, newNode);
        return mappings.get(c);
    }

}

class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node currentNode = root;
        for (int i = 0; i < word.length();i++){
            char current = word.charAt(i);
            currentNode = currentNode.add(current);
        }
        currentNode.isEndOfWord = true;
    }

    public boolean search(String word) {
        Node currentNode = root;
        for (int i = 0; i < word.length();i++){
            char ch = word.charAt(i);
            if (!currentNode.mappings.containsKey(ch)){
                return false;
            }
            currentNode = currentNode.mappings.get(ch);
        }
        return currentNode.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        Node currentNode = root;
        for (int i = 0; i < prefix.length();i++){
            char ch = prefix.charAt(i);
            if (!currentNode.mappings.containsKey(ch)){
                return false;
            }
            currentNode = currentNode.mappings.get(ch);
        }
        return true;

    }
}
