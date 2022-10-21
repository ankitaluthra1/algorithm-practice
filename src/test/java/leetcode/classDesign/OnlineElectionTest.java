package leetcode.classDesign;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineElectionTest {
    OnlineElection onlineElection;

    @BeforeEach
    public void setup() {
        int[] persons = {0, 1, 1, 0, 0, 1, 0};
        int[] times = {0, 5, 10, 15, 20, 25, 30};
        onlineElection = new OnlineElection(persons, times);
    }

    @Test
    public void shouldGiveLeaderForGivenVoteTimeInput() {

        assertEquals(0, onlineElection.leader.name);
        assertEquals(4, onlineElection.leader.count);
        assertEquals(6, onlineElection.leader.lastVote);
    }

    @Test
    public void shouldCreateVoteCountForGivenVoteTimeInput() {
        Map<Integer, Person> voteCount = onlineElection.voteCount;

        assertEquals(2, voteCount.size());
        assertEquals(0, voteCount.get(0).name);
        assertEquals(4, voteCount.get(0).count);

        assertEquals(2, voteCount.size());
        assertEquals(1, voteCount.get(1).name);
        assertEquals(3, voteCount.get(1).count);
    }

    @Test
    public void shouldCreateLeaderPerTimeForGivenVoteTimeInput() {
        Map<Integer, Person> leaderPerTime = onlineElection.leaderPerTime;

        assertEquals(7, leaderPerTime.size());
        assertEquals(0, leaderPerTime.get(0).name);
        assertEquals(1, leaderPerTime.get(5).name);
        assertEquals(1, leaderPerTime.get(10).name);
        assertEquals(0, leaderPerTime.get(15).name);
        assertEquals(0, leaderPerTime.get(20).name);
    }

    @Test
    public void shouldGiveLeaderForGivenTimeVoteInput() {
        assertEquals(0, onlineElection.q(3));
        assertEquals(1, onlineElection.q(12));
        assertEquals(1, onlineElection.q(25));
        assertEquals(0, onlineElection.q(15));
    }

}
