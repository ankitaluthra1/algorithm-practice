package leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/largest-time-for-given-digits/

public class LargestTime {

    class TimeInterval implements Comparable<TimeInterval> {
        int hour;
        int min;

        @Override
        public int compareTo(TimeInterval t2) {
            if (this.hour > t2.hour)
                return 1;
            if (this.hour < t2.hour)
                return -1;
            return Integer.compare(this.min, t2.min);
        }

        TimeInterval(String time) {
            StringBuilder sb = new StringBuilder();
            if (time.charAt(0) != '0')
                sb.append(time.charAt(0));
            sb.append(time.charAt(1));

            hour = Integer.parseInt(sb.toString());
            sb = new StringBuilder();
            if (time.charAt(2) != '0')
                sb.append(time.charAt(2));
            sb.append(time.charAt(3));
            min = Integer.parseInt(sb.toString());
        }

        boolean isValid() {
            return (hour >= 0 && hour <= 23) && (min >= 0 && min <= 59);
        }

        @Override
        public String toString() {
            StringBuilder sg = new StringBuilder();
            if (hour < 10) {
                sg.append("0");
            }
            sg.append(hour).append(":");
            if (min < 10){
                sg.append("0");
            }
            sg.append(min);
            return sg.toString();
        }
    }

    List<String> combinations;

    public String largestTimeFromDigits(int[] arr) {
        combinations = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }

        combinations(list, new ArrayList<>());

        List<TimeInterval> intervals = combinations.stream().map(s -> new TimeInterval(s)).filter(t -> t.isValid()).sorted().collect(Collectors.toList());

        if (intervals.size() == 0)
            return "";
        return intervals.get(intervals.size() - 1).toString();
    }

    void combinations(List<Integer> leftOver, List<Integer> combined) {
        if (leftOver.size() == 0) {
            StringBuilder sg = new StringBuilder();
            for (Integer c : combined) {
                sg.append(c);
            }
            combinations.add(sg.toString());
            return;
        }

        for (int i = 0; i < leftOver.size(); i++) {
            List<Integer> temp = new ArrayList<>(leftOver);
            List<Integer> tempCombined = new ArrayList<>(combined);
            int c = temp.remove(i);
            tempCombined.add(c);
            combinations(temp, tempCombined);
        }

    }

    public static void main(String[] args) {

        LargestTime lg = new LargestTime();
        int[] arr = {0, 0, 0, 0};
        String s = lg.largestTimeFromDigits(arr);

        System.out.println(s);

    }
}
