package leetcode.random;

import java.util.*;

//https://leetcode.com/problems/word-break/
public class WordBreak {
    List<String> wordDict;
    Map<String, Boolean> cache;
    Map<String, List<String>> cache2;
    public boolean wordBreak(String input, List<String> wordDict) {
        this.wordDict = wordDict;
        cache = new HashMap<>();
        return rec(input);
    }

    public List<String> wordBreak2(String input, List<String> wordDict) {
        this.wordDict = wordDict;
        cache2 = new HashMap<>();
        return rec2(input);
    }

    boolean rec(String current) {
        if (current.length() > 0) {
            if (cache.containsKey(current)) {
                return cache.get(current);
            }
            boolean wordExists = false;

            for (String word : wordDict) {
                if (word.length() <= current.length()) {
                    boolean found = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (current.charAt(i) != word.charAt(i)) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        boolean isPossibleWithCurrentCut = rec(current.substring(word.length()));
                        if (isPossibleWithCurrentCut) {
                            wordExists = true;
                            break;
                        }
                    }
                }
            }
            if (!wordExists) {
                cache.put(current, false);
                return false;
            }
        }
        cache.put(current, true);
        return true;
    }

    List<String> rec2(String current) {
        List<String> output = new ArrayList<>();
        if (current.length() > 0) {
            if (cache2.containsKey(current)) {
                return cache2.get(current);
            }
            boolean wordExists = false;

            for (String word : wordDict) {
                if (word.length() <= current.length()) {
                    boolean found = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (current.charAt(i) != word.charAt(i)) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        String substring = current.substring(word.length());
                        if (substring.length() == 0){
                            output.add(word);
                            cache2.put(current, output);
                            continue;
                        }
                        List<String> isPossibleWithCurrentCut = rec2(substring);
                        if (isPossibleWithCurrentCut.size() > 0) {
                            for(String possibleWord : isPossibleWithCurrentCut){
                                output.add(word + " " + possibleWord);
                            }
                            wordExists = true;
                        }
                    }
                }
            }
            if (!wordExists) {
                cache2.put(current, output);
                return output;
            }
        }
        cache2.put(current, output);
        return output;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        List<String> dict = Arrays.asList( "aaaa", "aa","a");
        boolean result = wb.wordBreak("catsandog", dict);
        List<String> result2 = wb.wordBreak2("aaaaaa", dict);
        System.out.println(result);
        result2.forEach(s -> System.out.println(s));

    }

}
