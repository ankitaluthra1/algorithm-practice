package leetcode.datastructures;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTeamsByVoteTest {

    @Test
    public void getFinalRank(){
        RankTeamsByVote rankTeamsByVote = new RankTeamsByVote();
        String[] input = {"ABC","ACB","ABC","ACB","ACB"};
        String result = rankTeamsByVote.getFinalRank(input);

        assertEquals("ACB",result);
    }

    @Test
    public void getFinalRank2(){
        RankTeamsByVote rankTeamsByVote = new RankTeamsByVote();
        String[] input = {"WXYZ","XYZW"};
        String result = rankTeamsByVote.getFinalRank(input);

        assertEquals("XWYZ",result);
    }

    @Test
    public void getFinalRank3(){
        RankTeamsByVote rankTeamsByVote = new RankTeamsByVote();
        String[] input = {"FVSHJIEMNGYPTQOURLWCZKAX","AITFQORCEHPVJMXGKSLNZWUY","OTERVXFZUMHNIYSCQAWGPKJL","VMSERIJYLZNWCPQTOKFUHAXG","VNHOZWKQCEFYPSGLAMXJIUTR","ANPHQIJMXCWOSKTYGULFVERZ","RFYUXJEWCKQOMGATHZVILNSP","SCPYUMQJTVEXKRNLIOWGHAFZ","VIKTSJCEYQGLOMPZWAHFXURN","SVJICLXKHQZTFWNPYRGMEUAO","JRCTHYKIGSXPOZLUQAVNEWFM","NGMSWJITREHFZVQCUKXYAPOL","WUXJOQKGNSYLHEZAFIPMRCVT","PKYQIOLXFCRGHZNAMJVUTWES","FERSGNMJVZXWAYLIKCPUQHTO","HPLRIUQMTSGYJVAXWNOCZEKF","JUVWPTEGCOFYSKXNRMHQALIZ","MWPIAZCNSLEYRTHFKQXUOVGJ","EZXLUNFVCMORSIWKTYHJAQPG","HRQNLTKJFIEGMCSXAZPYOVUW","LOHXVYGWRIJMCPSQENUAKTZF","XKUTWPRGHOAQFLVYMJSNEIZC","WTCRQMVKPHOSLGAXZUEFYNJI"};
        String result = rankTeamsByVote.getFinalRank(input);

        assertEquals("VWFHSJARNPEMOXLTUKICZGYQ",result);
    }

    @Test
    public void shouldGetTeamRankMap(){
        RankTeamsByVote rankTeamsByVote = new RankTeamsByVote();
        String[] input = {"ABC","ACB","ABC","ACB","ACB"};
        Map<Character, RankTeamsByVote.TeamRank> characterRankMap = rankTeamsByVote.getCharacterRankMap(input);

        RankTeamsByVote.TeamRank teamRank = characterRankMap.get('A');

        assertEquals(Character.valueOf('A'), teamRank.team);
        assertEquals(5, teamRank.ranks[0]);
        assertEquals(0, teamRank.ranks[1]);
        assertEquals(0, teamRank.ranks[2]);

        RankTeamsByVote.TeamRank teamRank2 = characterRankMap.get('B');
        assertEquals(Character.valueOf('B'), teamRank2.team);
        assertEquals(0, teamRank2.ranks[0]);
        assertEquals(2, teamRank2.ranks[1]);
        assertEquals(3, teamRank2.ranks[2]);

        RankTeamsByVote.TeamRank teamRank3 = characterRankMap.get('C');
        assertEquals(Character.valueOf('C'), teamRank3.team);
        assertEquals(0, teamRank3.ranks[0]);
        assertEquals(3, teamRank3.ranks[1]);
        assertEquals(2, teamRank3.ranks[2]);
    }


}