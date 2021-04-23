package leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-window-substring/
public class MinimumWindowInString {

    public String minWindow(String s, String t) {

        Map<Character, Integer> inputMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            int count = inputMap.getOrDefault(t.charAt(i), 0);
            inputMap.put(t.charAt(i), count + 1);
        }

        Map<Character, Integer> resultMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int minWindow = Integer.MAX_VALUE;
        int minWindowStart = -1;
        int minWindowEnd = -1;

        for (int i = start; i < s.length(); i++) {
            char currentKey = s.charAt(i);
            if (inputMap.containsKey(currentKey)) {
                int currentResultCount = resultMap.getOrDefault(currentKey, 0);
                resultMap.put(currentKey, currentResultCount + 1);
                end = i;
            }
            if (inputMap.size() == resultMap.size()) {
                while (start <= end) {
                    if (minWindow > (end - start) && isSameMap(resultMap, inputMap)) {
                        minWindowStart = start;
                        minWindowEnd = end;
                        minWindow = end - start;
                    }
                    char temp = s.charAt(start);
                    if (!resultMap.containsKey(temp)) {
                        start++;
                        continue;
                    }
                    if (resultMap.containsKey(temp)) {
                        if (resultMap.get(temp) > inputMap.get(temp)) {
                            int tempCount = resultMap.get(temp);
                            resultMap.put(temp, tempCount - 1);
                            start++;
                        } else break;
                    }

                }
            }
        }


        if (minWindow == Integer.MAX_VALUE)
            return "";
        return s.substring(minWindowStart, minWindowEnd + 1);
    }

    private boolean isSameMap(Map<Character, Integer> resultMap, Map<Character, Integer> inputMap) {
        for (Character key: inputMap.keySet()){
            if (!resultMap.containsKey(key))
                return false;
            if (resultMap.get(key) < inputMap.get(key))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowInString mn = new MinimumWindowInString();
        String s = mn.minWindow("a", "a");
        System.out.println(s);
    }

}
