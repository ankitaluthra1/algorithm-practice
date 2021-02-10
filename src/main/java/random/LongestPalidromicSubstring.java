package random;

public class LongestPalidromicSubstring {

    int maxLength = Integer.MIN_VALUE;
    int maxStart = -1;
    int maxEnd = -1;

    public String longestPalindrome(String input) {


        StringBuilder sb = new StringBuilder();

        sb.append("#");
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i) + "#");
        }
        String s = sb.toString();

        for (int i = 0; i < s.length(); i++) {

            int tempStart = i;
            int tempEnd = i;

            while (tempStart >= 0 && tempEnd < s.length() && s.charAt(tempStart) == s.charAt(tempEnd)) {
                int currentLength = tempEnd - tempStart + 1;
                if (currentLength >= maxLength) {
                    maxLength = currentLength;
                    maxStart = tempStart;
                    maxEnd = tempEnd;
                }

                tempStart--;
                tempEnd++;
            }
        }

        return s.substring(maxStart, maxEnd + 1).replaceAll("#", "");

    }

    public static void main(String[] args) {
        LongestPalidromicSubstring lp = new LongestPalidromicSubstring();
        String result = lp.longestPalindrome("abb");
        System.out.println(result);
    }

}
