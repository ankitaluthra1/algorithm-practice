package leetcode.datastructures;

//https://leetcode.com/problems/rank-teams-by-votes/

import java.util.*;

public class RankTeamsByVote {

    class TeamRank implements Comparable<TeamRank> {
        Character team;
        int[] ranks;

        public TeamRank(Character team, int length) {
            this.team = team;
            this.ranks = new int[length];
            for (int index = 0 ; index < length ; index++){
                this.ranks[index] = 0;
            }
        }

        public void setRank(int pos) {
            ranks[pos] = ranks[pos] + 1;
        }

        @Override
        public int compareTo(TeamRank o) {
            for (int i = 0; i < ranks.length; i++) {
                if (this.ranks[i] - o.ranks[i] != 0)
                    return this.ranks[i] - o.ranks[i];
            }
            return o.team - this.team;
        }
    }

    Map<Character, TeamRank> getCharacterRankMap(String[] votes) {
        Map<Character, TeamRank> rankMap = new HashMap<>();
        for (String vote : votes) {
            for (int pos = 0; pos < vote.length(); pos++) {
                Character currentTeam = vote.charAt(pos);
                TeamRank currentTeamRank = rankMap.getOrDefault(currentTeam, new TeamRank(currentTeam, vote.length()));
                currentTeamRank.setRank(pos);
                rankMap.put(currentTeam, currentTeamRank);
            }
        }
        return rankMap;
    }

    public String getFinalRank(String[] input){
        Map<Character, TeamRank> characterRankMap = getCharacterRankMap(input);
        SortedSet<TeamRank> sortedTeamRanks = new TreeSet<>(characterRankMap.values());

        StringBuilder sg = new StringBuilder();
        int size = sortedTeamRanks.size();
        for (int i = 0; i < size; i++){
            TeamRank last = sortedTeamRanks.last();
            sortedTeamRanks.remove(last);
            sg.append(last.team);
        }

        return sg.toString();
    }

}



