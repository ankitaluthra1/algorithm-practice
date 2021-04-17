package leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/longest-valid-parentheses/
// incomplete
public class LongestValidParenthesis {

    class Stack {
        List<Character> list;

        Stack() {
            list = new ArrayList<>();
        }

        void push(Character c) {
            list.add(c);
        }

        char pop() {
            if (list.isEmpty())
                throw new IllegalStateException("Stack is empty...");
            return list.remove(list.size() - 1);
        }

        boolean isEmpty() {
            return list.isEmpty();
        }
    }

    int maxLength;

    public int longestValidParentheses(String s) {

        if (s.isEmpty())
            return 0;
        maxLength = 0;
        int start = 0;
        int end = 0;

        while (start < s.length()) {
            boolean flag = true;
            int length = 0;
            Stack stack = new Stack();
            while (flag && end < s.length()) {
                char c = s.charAt(end);
                if (c == '(')
                    stack.push(c);
                else {
                    if (!stack.isEmpty()) {
                        stack.pop();
                        if (stack.isEmpty()) {
                            maxLength = Math.max(maxLength, length);
                            length += 2;
                        }
                        else {
                            maxLength = Math.max(maxLength, length);
                            length = 2;
                        }
                    } else {
                        flag = false;
                    }
                }
                end++;
            }
            maxLength = Math.max(maxLength, length);
            start = end;
        }
        return maxLength;
    }

    public static void main(String[] args) {
//        LongestValidParenthesis lg = new LongestValidParenthesis();
//        int result = lg.longestValidParentheses("()(()");
//
//        System.out.println(result);

        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('a', 1);
        map1.put('b', 5);

        Map<Character, Integer> map2 = new HashMap<>();
        map2.put('a', 1);
        map2.put('b', 15);

        System.out.println(map1.hashCode()+"     "+map2.hashCode());
        System.out.println(map1.equals(map2));

    }
}
