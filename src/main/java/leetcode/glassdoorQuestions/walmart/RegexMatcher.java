package leetcode.glassdoorQuestions.walmart;

import kotlin.Pair;

import java.util.HashMap;
import java.util.Map;

public class RegexMatcher {

    Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>();
    int inputLength;
    int patternLength;
    String s;
    String p;

    private String removeDuplicatesWildCards(String p) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < p.length()) {
            if (p.charAt(index) == '*') {
                sb.append(p.charAt(index));
                while (index < p.length() && p.charAt(index) == '*')
                    index++;
            } else {
                sb.append(p.charAt(index));
                index++;
            }

        }
        return sb.toString();
    }

    public boolean isMatch(String s1, String p1) {

        s = s1;
        inputLength = s.length();
        p = removeDuplicatesWildCards(p1);
        patternLength = p.length();

        return isRegexMatch(0, 0);
    }

    private boolean isRegexMatch(int inputIndex, int patternIndex) {
        Pair<Integer, Integer> key = new Pair<>(inputIndex, patternIndex);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        while (inputIndex < inputLength && patternIndex < patternLength) {

            if (p.charAt(patternIndex) == '?' || p.charAt(patternIndex) == s.charAt(inputIndex)) {
                inputIndex++;
                patternIndex++;
                continue;
            }

            if (p.charAt(patternIndex) == '*') {
                patternIndex++;

                while (inputIndex < s.length()) {

                    boolean match = isRegexMatch(inputIndex, patternIndex);
                    if (match) {
                        cache.put(key, true);
                        return true;
                    }
                    inputIndex++;
                }
            } else {
                cache.put(key, false);
                return false;
            }
        }

        if (inputIndex < s.length()) {
            cache.put(key, false);
            return false;
        }

        if (patternIndex < p.length()) {
            while (patternIndex < p.length()) {
                if (p.charAt(patternIndex) != '*') {
                    cache.put(key, false);
                    return false;
                }
                patternIndex++;
            }
        }
        cache.put(key, true);
        return true;
    }

    public static void main(String[] args) {
        RegexMatcher rx = new RegexMatcher();
        boolean result = rx.isMatch("abcccvvgbghkkk", "*ab*nbg*");
        System.out.println(result);

    }

}
