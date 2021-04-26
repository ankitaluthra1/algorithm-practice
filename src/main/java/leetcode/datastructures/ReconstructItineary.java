package leetcode.datastructures;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/reconstruct-itinerary/
public class ReconstructItineary {
    List<List<String>> tickets;
    Map<String, List<String>> graph;

    public List<String> findItinerary(List<List<String>> tickets) {
        this.tickets = tickets;
        graph = new HashMap<>();
        for(List<String> edges : tickets){
            String src = edges.get(0);
            String dest = edges.get(1);
            List<String> neighbors = graph.getOrDefault(src, new ArrayList<>());
            if(!neighbors.isEmpty()){
                int index = -1;
                for(int i = 0; i < neighbors.size(); i++){
                    if(stringCompare(neighbors.get(i), dest) >= 0){
                        index = i;
                        break;
                    }
                }
                if(index == -1)
                    neighbors.add(dest);
                else{
                    neighbors.add(index,dest);
                }
            }
            else{
                neighbors.add(dest);
            }
            graph.put(src, neighbors);
        }
        List<String> itineary = new ArrayList<>();
        itineary.add("JFK");
        return dfs("JFK", itineary, 0, new HashMap<>());
    }

    List<String> dfs(String src, List<String> itineary, int journeyCount,Map<String, Set<Integer>> visited){
        if (!graph.containsKey(src)){
            if (journeyCount == tickets.size())
                return itineary;
            return new ArrayList<>();
        }
        List<String> neighbors = graph.get(src);
        Set<Integer> visitedNeighbors = visited.getOrDefault(src, new HashSet<>());
        if(isSameList(neighbors, visitedNeighbors)){
            if (journeyCount == tickets.size())
                return itineary;
        }
        int index = 0;
        for(String neighbor : neighbors){
            if (!visitedNeighbors.contains(index    )) {
                List<String> newItineary = new ArrayList<>(itineary);
                newItineary.add(neighbor);
                Map<String, Set<Integer>> newVisited = new HashMap<>(visited);
                Set<Integer> newNeighbors = new HashSet<>(newVisited.getOrDefault(src, new HashSet<>()));
                newNeighbors.add(index);
                newVisited.put(src, newNeighbors);
                List<String> result = dfs(neighbor, newItineary, journeyCount + 1, newVisited);
                if(!result.isEmpty())
                    return result;
            }
            index++;
        }
        return new ArrayList<>();
    }

    private boolean isSameList(List<String> neighbors, Set<Integer> visitedNeighbors) {
        return neighbors.size() == visitedNeighbors.size();
    }

    private int stringCompare(String first, String second){
        int length = Math.min(first.length(), second.length());
        for (int i=0;i<length;i++){
            if(first.charAt(i) > second.charAt(i)) {
                return 1;
            }
            if(first.charAt(i) < second.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ReconstructItineary ri = new ReconstructItineary();
        List<List<String>> tickets = Arrays.asList(Arrays.asList("EZE","AXA"),
                Arrays.asList("TIA","ANU"),Arrays.asList("ANU","JFK"), Arrays.asList("JFK","ANU"),
                Arrays.asList("ANU","EZE"), Arrays.asList("TIA","ANU"), Arrays.asList("AXA","TIA"), Arrays.asList("TIA","JFK"),
                Arrays.asList("ANU","TIA"), Arrays.asList("JFK","TIA")
        );
        List<String> result = ri.findItinerary(tickets);
        System.out.println(result.stream().collect(Collectors.joining(", ")));
    }

}
