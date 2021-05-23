package leetcode.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode.com/problems/interleaving-string/
public class InterleavingString {

    private Map<Triple, Boolean> cache = new HashMap<>();

    private static class Triple {
        int ch1;
        int ch2;
        int ch3;

        public Triple(int ch1, int ch2, int ch3) {
            this.ch1 = ch1;
            this.ch2 = ch2;
            this.ch3 = ch3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Triple)) return false;
            Triple triple = (Triple) o;
            return ch1 == triple.ch1 && ch2 == triple.ch2 && ch3 == triple.ch3;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ch1, ch2, ch3);
        }
    }

    String s1;
    String s2;
    String s3;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return checkForIndex(0, 0, 0);
    }

    private boolean checkForIndex(int index1, int index2, int index3) {
        Triple key = new Triple(index1, index2, index3);

        if (cache.containsKey(key))
            return cache.get(key);

        if (index1 == s1.length() && index2 == s2.length() && index3 == s3.length()) {
            cache.put(key, true);
            return cache.get(key);
        }


        if (index2 == s2.length()) {
            if (index3 == s3.length() || index1 == s1.length()) {
                cache.put(key, false);
                return false;
            }
            boolean result = s1.substring(index1).equals(s3.substring(index3));
            cache.put(key, result);
            return result;
        }

        if (index1 == s1.length()) {
            if (index3 == s3.length()) {
                cache.put(key, false);
                return false;
            }
            boolean result = s2.substring(index2).equals(s3.substring(index3));
            cache.put(key, result);
            return result;
        }

        if (index3 == s3.length()) {
            cache.put(key, false);
            return false;
        }

        if (s1.charAt(index1) == s2.charAt(index2) && s3.charAt(index3) == s1.charAt(index1)) {
            boolean result = checkForIndex(index1 + 1, index2, index3 + 1) || checkForIndex(index1, index2 + 1, index3 + 1);
            cache.put(key, result);
            return result;
        }

        if (s1.charAt(index1) == s3.charAt(index3)) {
            boolean result = checkForIndex(index1 + 1, index2, index3 + 1);
            cache.put(key, result);
            return result;
        }

        if (s2.charAt(index2) == s3.charAt(index3)) {
            boolean result = checkForIndex(index1, index2 + 1, index3 + 1);
            cache.put(key, result);
            return result;
        }

        cache.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        InterleavingString is = new InterleavingString();
        boolean result = is.isInterleave("aaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(result);
    }

}
