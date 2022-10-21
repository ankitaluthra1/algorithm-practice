package leetcode.classDesign;

import java.util.*;

class Person {
    final int name;
    int count;
    int lastVote;

    public Person(int name) {
        this.name = name;
        count = 0;
    }

    public void addVote(int index) {
        this.count++;
        this.lastVote = index;
    }
}

public class OnlineElection {

    Map<Integer, Person> voteCount = new HashMap<>();
    SortedMap<Integer, Person> leaderPerTime = new TreeMap<>();
    Person leader = null;
    public OnlineElection(int[] persons, int[] times) {

        for (int i = 0; i < persons.length; i++) {
            int candidate = persons[i];
            Person currentPerson = voteCount.getOrDefault(candidate, new Person(candidate));
            currentPerson.addVote(i);
            voteCount.put(candidate, currentPerson);
            if(leader == null)
                leader = currentPerson;
            else
                leader = findLeader(leader, currentPerson);
            leaderPerTime.put(times[i], leader);
        }

    }

     Person findLeader(Person leader, Person currentPerson) {
         if (leader.count == currentPerson.count) {
             return leader.lastVote > currentPerson.lastVote ? leader : currentPerson;
         }
         return leader.count > currentPerson.count ? leader : currentPerson;
    }

    public int q(int t) {
        if (leaderPerTime.containsKey(t))
            return leaderPerTime.get(t).name;
        int key = search(leaderPerTime.keySet(), t);
        return leaderPerTime.get(key).name;
    }

    private int search(Set<Integer> set, int term) {
        return binarySearch(new ArrayList<>(set), term, 0, set.size() - 1);
    }

    private int binarySearch(List<Integer> list, int term, int start, int end) {
        if(end - start == 1|| end == start)
            return list.get(start);
        int mid = (end + start) / 2;
        if (list.get(mid) < term)
            return binarySearch(list, term, mid, end);
        else
            return binarySearch(list, term, 0, mid);

    }
}
