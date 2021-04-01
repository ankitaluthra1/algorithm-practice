package leetcode.random;

import java.util.ArrayList;
import java.util.List;

//Incomplete
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
        LongestValidParenthesis lg = new LongestValidParenthesis();
        int result = lg.longestValidParentheses("()(()");

        System.out.println(result);
    }
}
