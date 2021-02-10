package glassdoorQuestions.walmart;

import kotlin.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class NMeeting1Room {

    static class MyMeeting {
        Integer start;
        Integer end;

        MyMeeting(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyMeeting myMeeting = (MyMeeting) o;
            return start.equals(myMeeting.start) &&
                    end.equals(myMeeting.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    private static Map<MyMeeting, List<MyMeeting>> cache = new HashMap<>();

    static List<MyMeeting> dp(List<MyMeeting> sortedMyMeetings, List<Pair<Integer, Integer>> bookedSlots, List<MyMeeting> addedMyMeetings) {
        if (sortedMyMeetings.isEmpty())
            return addedMyMeetings;

        MyMeeting myMeeting = sortedMyMeetings.get(0);
        if (cache.containsKey(myMeeting)) {
            return cache.get(myMeeting);
        }
        List<MyMeeting> remainingMyMeetings = new ArrayList<>();
        remainingMyMeetings.addAll(sortedMyMeetings);
        remainingMyMeetings.remove(myMeeting);

        for (Pair<Integer, Integer> slot : bookedSlots) {
            if ((myMeeting.start >= slot.getFirst() && myMeeting.start <= slot.getSecond())
                    || (myMeeting.end >= slot.getFirst() && myMeeting.end <= slot.getSecond())
                    || (myMeeting.start <= slot.getFirst() && myMeeting.end >= slot.getSecond())) {
                return dp(remainingMyMeetings, bookedSlots, addedMyMeetings);
            }
        }

        List<Pair<Integer, Integer>> newBookedSlots = new ArrayList<>();
        newBookedSlots.addAll(bookedSlots);
        newBookedSlots.add(new Pair<>(myMeeting.start, myMeeting.end));

        List<MyMeeting> newAddedMyMeetings = new ArrayList<>(addedMyMeetings);
        newAddedMyMeetings.add(myMeeting);
        List<MyMeeting> withMyMeeting = dp(remainingMyMeetings, newBookedSlots, newAddedMyMeetings);
        List<MyMeeting> withoutMyMeeting = dp(remainingMyMeetings, bookedSlots, addedMyMeetings);

        if (withMyMeeting.size() > withoutMyMeeting.size()) {
            cache.put(myMeeting, withMyMeeting);
            return withMyMeeting;
        } else {
            cache.put(myMeeting, withoutMyMeeting);
            return withoutMyMeeting;
        }

    }

    static void maxMeetings(int start[], int end[], int n) {

        List<MyMeeting> myMeetings = new ArrayList<>(start.length);
        for (int i = 0; i < start.length; i++) {
            myMeetings.add(new MyMeeting(start[i], end[i]));
        }

        List<MyMeeting> result = dp(myMeetings, new ArrayList<>(), new ArrayList<>());
//        for (MyMeeting m: result){
//            System.out.println(m.start +" "+m.end);
//        }

        String output = result.stream().map(m -> {
            int index = myMeetings.indexOf(m) + 1;
            return index + "";
        }).collect(Collectors.joining(" "));
        System.out.println(output);
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        maxMeetings(start, end, start.length);
    }

}
