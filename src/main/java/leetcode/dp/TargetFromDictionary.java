package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TargetFromDictionary {
    String[] words;
    String target;
    int lengthOfTarget;

    Map<String, Long> cache = new HashMap<>();

    public int numWays(String[] words, String target) {
        this.words = words;
        this.target = target;
        lengthOfTarget = target.length() - 1;
        this.cache = new HashMap<>();
        double result = findCount(new ArrayList<>(), 0, 0);
        int finalResult = (int) (result % (Math.pow(10, 9) + 7));
        return finalResult;
    }

    private long findCount(List<String> chars, int col, int targetIndex) {
        String key = col + " - " + targetIndex;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long count = 0;
        for (int j = col; j < words[0].length(); j++) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].charAt(j) == target.charAt(targetIndex)) {
                    if (targetIndex == lengthOfTarget) {
                        count++;
                    } else {
                        ArrayList<String> chars1 = new ArrayList<>(chars);
                        chars1.add(i + ", " + j);
                        count = count + (int)(findCount(chars1, j + 1, targetIndex + 1) % ((Math.pow(10, 9) + 7)));
                    }
                }
            }
        }
        cache.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        TargetFromDictionary targetFromDictionary = new TargetFromDictionary();
        String[] words = {"cabbaacaaaccaabbbbaccacbabbbcb","bbcabcbcccbcacbbbaacacaaabbbac","cbabcaacbcaaabbcbaabaababbacbc","aacabbbcaaccaabbaccacabccaacca","bbabbaabcaabccbbabccaaccbabcab","bcaccbbaaccaabcbabbacaccbbcbbb","cbbcbcaaaacacabbbabacbaabbabaa","cbbbbbbcccbabbacacacacccbbccca","bcbccbccacccacaababcbcbbacbbbc","ccacaabaaabbbacacbacbaaacbcaca","bacaaaabaabccbcbbaacacccabbbcb","bcbcbcabbccabacbcbcaccacbcaaab","babbbcccbbbbbaabbbacbbaabaabcc","baaaacaaacbbaacccababbaacccbcb","babbaaabaaccaabacbbbacbcbababa","cbacacbacaaacbaaaabacbbccccaca","bcbcaccaabacaacaaaccaabbcacaaa","cccbabccaabbcbccbbabaaacbacaaa","bbbcabacbbcabcbcaaccbcacacccca","ccccbbaababacbabcaacabaccbabaa","caaabccbcaaccabbcbcaacccbcacba","cccbcaacbabaacbaaabbbbcbbbbcbb","cababbcacbabcbaababcbcabbaabba","aaaacacaaccbacacbbbbccaabcccca","cbcaaaaabacbacaccbcbcbccaabaac","bcbbccbabaccabcccacbbaacbbcbba","cccbabbbcbbabccbbabbbbcaaccaab","acccacccaabbcaccbcaaccbababacc","bcacabaacccbbcbbacabbbbbcaaaab","cacccaacbcbccbabccabbcbabbcacc","aacabbabcaacbaaacaabcabcaccaab","cccacabacbabccbccaaaaabbcacbcc","cabaacacacaaabaacaabababccbaaa","caabaccaacccbaabcacbcbbabccabc","bcbbccbbaaacbaacbccbcbababcacb","bbabbcabcbbcababbbbccabaaccbca","cacbbbccabaaaaccacbcbabaabbcba","ccbcacbabababbbcbcabbcccaccbca","acccabcacbcbbcbccaccaacbabcaab","ccacaabcbbaabaaccbabcbacaaabaa","cbabbbbcabbbbcbccabaabccaccaca","acbbbbbccabacabcbbabcaacbbaacc","baaababbcabcacbbcbabacbcbaaabc","cabbcabcbbacaaaaacbcbbcacaccac"};
        String target = "acbaccacbbaaabbbabac";
        int count = targetFromDictionary.numWays(words, target);

        System.out.println(count);
    }

}
