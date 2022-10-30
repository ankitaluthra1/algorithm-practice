package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TargetFromDictionary {
    String[] words;
    int count;
    String target;
    int lengthOfTarget;

    Map<String, Integer> cache = new HashMap<>();
    public int numWays(String[] words, String target) {
        this.words = words;
        this.target = target;
        lengthOfTarget = target.length() - 1;
        findCount(new ArrayList<>(),0, 0);
        return count;
    }

    private void findCount(List<String> chars, int col, int targetIndex) {

        for (int j = col; j < words[0].length(); j++) {
            boolean flag = true;
            for (int i = 0; i < words.length; i++) {
                if (words[i].charAt(j) == target.charAt(targetIndex)) {
                    if (targetIndex == lengthOfTarget) {
                        flag = true;
                        count++;
                    }
                    else {
                        ArrayList<String> chars1 = new ArrayList<>(chars);
                        chars1.add(i+", "+j);
                        findCount(chars1,j + 1, targetIndex + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TargetFromDictionary targetFromDictionary = new TargetFromDictionary();
        String[] words = {"abba","baab"};
        String target = "bab";
        int count = targetFromDictionary.numWays(words, target);

        System.out.println(count);
    }

}
