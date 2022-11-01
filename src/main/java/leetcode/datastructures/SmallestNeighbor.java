package leetcode.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class SmallestNode implements Comparable<SmallestNode>{
    final int value;
    final int threshold;
    Map<SmallestNode, Integer> neighbors;

    public SmallestNode(int value, int threshold){
        this.value = value;
        this.threshold = threshold;
        this.neighbors = new HashMap<>();
    }

    public void addNeighbor(SmallestNode neighbor, int weight){
        this.neighbors.put(neighbor, weight);
    }

    public Set<Integer> thresholdNeighbors(int currentThreshold, List<SmallestNode> previousNodes){
        List<Integer> immediateNeighbors = this.neighbors.entrySet().stream()
                .filter(e -> !previousNodes.contains(e.getKey()) && e.getValue() <= currentThreshold).map(e -> e.getKey().value).collect(Collectors.toList());

        List<Integer> nestedNeighbors = this.neighbors.entrySet().stream()
                .filter(e -> !previousNodes.contains(e.getKey()) && e.getValue() <= currentThreshold)
                .flatMap(e -> {
                    List<SmallestNode> nodes = new ArrayList<>(previousNodes);
                    nodes.add(e.getKey());
                    return e.getKey().thresholdNeighbors(currentThreshold - e.getValue(), nodes).stream();
                }).collect(Collectors.toList());

        Set<Integer> allNeigbors = new HashSet<>();
        allNeigbors.addAll(immediateNeighbors);
        allNeigbors.addAll(nestedNeighbors);
        return allNeigbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallestNode that = (SmallestNode) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(@NotNull SmallestNode o) {
        ArrayList<SmallestNode> list1 = new ArrayList<>();
        list1.add(this);
        int currentSize = this.thresholdNeighbors(threshold, list1).size();
        ArrayList<SmallestNode> list2 = new ArrayList<>();
        list2.add(o);
        int otherSize = o.thresholdNeighbors(threshold, list2).size();

        if(currentSize == otherSize)
            return o.value - this.value;

        return currentSize - otherSize;
    }
}

public class SmallestNeighbor {
    Map<Integer, SmallestNode> neighborGraph = new HashMap<>();
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        for(int i = 0; i < edges.length; i++){
            SmallestNode startSmallestNode = neighborGraph.getOrDefault(edges[i][0], new SmallestNode(edges[i][0], distanceThreshold));
            SmallestNode endSmallestNode = neighborGraph.getOrDefault(edges[i][1], new SmallestNode(edges[i][1], distanceThreshold));
            if(edges[i][2] <= distanceThreshold) {
                startSmallestNode.addNeighbor(endSmallestNode, edges[i][2]);
                endSmallestNode.addNeighbor(startSmallestNode, edges[i][2]);
            }
            neighborGraph.put(edges[i][0], startSmallestNode);
            neighborGraph.put(edges[i][1], endSmallestNode);
        }

        PriorityQueue<SmallestNode> heap = new PriorityQueue<>();

        for(int i = 0; i< n ;i ++){
            SmallestNode currentSmallestNode = neighborGraph.getOrDefault(i, new SmallestNode(i, distanceThreshold));
            heap.add(currentSmallestNode);
        }
        return heap.poll().value;
    }

}
