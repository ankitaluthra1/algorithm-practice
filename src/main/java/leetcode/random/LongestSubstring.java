package leetcode.random;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.ArrayList;
import java.util.List;

public class LongestSubstring {

    int maxLength = Integer.MIN_VALUE;

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int start = 0;
        int end = 0;
        List<Character> alreadyPresent = new ArrayList<>();
        while (end < s.length()) {
            char currentCharacter = s.charAt(end);
            if (alreadyPresent.contains(currentCharacter)) {
                int currentLength = end - start;
                if (maxLength < currentLength)
                    maxLength = currentLength;
                while (s.charAt(start) != currentCharacter) {
                    alreadyPresent.remove((Character) s.charAt(start));
                    start++;
                }

                start++;
                end++;
            } else {
                alreadyPresent.add(currentCharacter);
                end++;
            }
        }
        int currentLength = end - start;
        if (maxLength < currentLength) {
            maxLength = currentLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {

        LongestSubstring ls = new LongestSubstring();
        int result = ls.lengthOfLongestSubstring("pwwkew");

        System.out.println(result);
    }

}
