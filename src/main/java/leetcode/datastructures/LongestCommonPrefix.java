package leetcode.datastructures;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        int index = 0;
        StringBuilder sg = new StringBuilder();
        while (true) {
            Character c = null;
            for (String s : strs) {
                if (index >= s.length())
                    return sg.toString();
                if (c == null)
                    c = s.charAt(index);
                else {
                    if (s.charAt(index) != c)
                        return sg.toString();
                }
            }
            sg.append(c);
            index++;
        }
    }

    public static void main(String[] args) {
        LongestCommonPrefix lp = new LongestCommonPrefix();
        String[] strs = {""};
        String prefix = lp.longestCommonPrefix(strs);

        System.out.println(prefix);
    }

}
