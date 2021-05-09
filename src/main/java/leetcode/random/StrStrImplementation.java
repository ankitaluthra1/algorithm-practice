package leetcode.random;

//https://leetcode.com/problems/implement-strstr/
public class StrStrImplementation {

    public int strStr(String haystack, String needle) {
        if (needle.equals(""))
            return 0;
        if(needle.length() > haystack.length())
            return -1;
        int i = 0;
        while (i < haystack.length()) {
            boolean found = true;
            int start = i;
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 1;
                i++;
                while (j < needle.length()) {
                    if (i == haystack.length() || haystack.charAt(i) != needle.charAt(j)) {
                        found = false;
                        break;
                    }
                    i++;
                    j++;
                }
                if (found)
                    return start;
                else {
                    i = start + 1;
                }
            }
            else
                i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStrImplementation str = new StrStrImplementation();
        int result = str.strStr("mississippi", "issip");

        System.out.println(result);
    }

}
