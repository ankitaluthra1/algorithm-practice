package leetcode.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class IntersectionSet {

    class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(@NotNull Interval o) {
            if (this.end == o.end)
                return o.start - this.start;
            return this.end - o.end;
        }

        public int[] lastTwoElements(){
            int[] result = {end-1, end};
            return result;
        }

        public boolean contains(int[] commonInterval) {
            boolean firstElementPresent = commonInterval[0] <=end && commonInterval[0] >= start;
            boolean secondElementPresent = commonInterval[1] <=end && commonInterval[1] >= start;

            return firstElementPresent || secondElementPresent;
        }

        public boolean containsBoth(int[] commonInterval) {
            boolean firstElementPresent = commonInterval[0] <=end && commonInterval[0] >= start;
            boolean secondElementPresent = commonInterval[1] <=end && commonInterval[1] >= start;

            return firstElementPresent && secondElementPresent;
        }
    }

    public int intersectionSizeTwo(int[][] intervals) {

        SortedSet<Interval> sortedSet = new TreeSet<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            sortedSet.add(new Interval(currentInterval[0], currentInterval[1]));
        }

        int[] commonInterval = null;
        int minLength = 0;
        for(Interval interval : sortedSet){
            if(commonInterval == null || !interval.contains(commonInterval)) {
                commonInterval = interval.lastTwoElements();
                minLength = minLength + 2;
                continue;
            }
           if(interval.containsBoth(commonInterval))
               continue;
           commonInterval[0] = commonInterval[1];
           commonInterval[1] = interval.end;
           minLength++;
        }
        return minLength;
    }

}
