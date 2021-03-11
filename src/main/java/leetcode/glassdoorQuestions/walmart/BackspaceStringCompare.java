package leetcode.glassdoorQuestions.walmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {

        String s1 = process(S);
        String s2 = process(T);

        return s1.equals(s2);

    }

    private String process(String s) {

        List<Character> stack = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty())
                    stack.remove(stack.size() - 1);
            } else {
                stack.add(c);
            }
        }

        return Arrays.toString(stack.toArray());
    }

    public static void main(String[] args) {
        BackspaceStringCompare bc = new BackspaceStringCompare();
        boolean result = bc.backspaceCompare("ab##", "c#d#");
        System.out.println(result);
    }

}
