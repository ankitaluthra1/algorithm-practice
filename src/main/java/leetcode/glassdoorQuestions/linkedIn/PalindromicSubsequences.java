package leetcode.glassdoorQuestions.linkedIn;

//InComplete
public class PalindromicSubsequences {

    int[][] cache;

    public int countPalindromicSubsequences(String input) {
        int length = input.length();
        cache = new int[length][length];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++)
                cache[i][j] = -1;
        }

        return count(0, length - 1, input);
    }

    private int count(int i, int j, String input) {
        if (i > j)
            return 0;
        if (cache[i][j] != -1)
            return cache[i][j];
        int result;
        if (i == j)
            result = 1;
        else {
            if (input.charAt(i) == input.charAt(j))
                result = 1 + count(i + 1, j, input) + count(i, j - 1, input) - count(i + 1, j - 1, input) % (int) Math.pow(10.0, 9.0);
            else
                result = count(i + 1, j, input) + count(i, j - 1, input) - count(i + 1, j - 1, input) % (int) Math.pow(10.0, 9.0);
        }
        cache[i][j] = result;

        return result;
    }

    public static void main(String[] args) {
        PalindromicSubsequences pl = new PalindromicSubsequences();
        int result = pl.countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba");

        System.out.println(result);
    }

}
