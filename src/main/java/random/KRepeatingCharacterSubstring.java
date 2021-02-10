package random;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KRepeatingCharacterSubstring {

    String s;
    int k;
    int maxLength = 0;

    public int longestSubstring(String s, int k) {

        this.s = s;
        this.k = k;

        int start = 0;
        int end = s.length() - 1;

        rec(start, end);
        return maxLength;
    }

    private void rec(int start, int end) {

        if (end - start + 1 < k) {
            return;
        }

        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = start; i <= end; i++) {
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        List<Character> insufficientCountCharacters = countMap.entrySet().stream().filter(e -> e.getValue() < k).map(e -> e.getKey()).collect(Collectors.toList());

        if (insufficientCountCharacters.isEmpty()) {
            int length = end - start + 1;
            if (length > maxLength) {
                maxLength = length;
            }
            return;
        }

        int index = start;

        while (insufficientCountCharacters.contains(s.charAt(index))) {
            index++;
            if (index == end)
                return;
        }
        int newStart = index;

        while (index <= end) {
            if (insufficientCountCharacters.contains(s.charAt(index))) {
                break;
            }
            index++;
        }


        int newEnd = index - 1;

        rec(newStart, newEnd);
        rec(newEnd + 1, end);

    }

    public static void main(String[] args) {
        KRepeatingCharacterSubstring ks = new KRepeatingCharacterSubstring();
        int result = ks.longestSubstring("ababbc", 2);

        System.out.println(result);
    }

}
