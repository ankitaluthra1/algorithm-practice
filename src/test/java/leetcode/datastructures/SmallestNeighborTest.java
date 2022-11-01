package leetcode.datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SmallestNeighborTest {

    @Test
    public void shouldReturnSmallestNeighborCity(){

        SmallestNeighbor smallestNeighbor = new SmallestNeighbor();
        int[][] edges = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        int cityNumber = smallestNeighbor.findTheCity(5, edges,2);

        assertEquals(3, cityNumber);

    }

    @Test
    public void shouldReturnThresholdNeigbors(){
        SmallestNode smallestNode0 = new SmallestNode(0, 4);
        SmallestNode smallestNode1 = new SmallestNode(1, 4);
        SmallestNode smallestNode2 = new SmallestNode(2, 4);
        SmallestNode smallestNode3 = new SmallestNode(3, 4);
        smallestNode0.addNeighbor(smallestNode1, 3);
        smallestNode1.addNeighbor(smallestNode2, 1);
        smallestNode1.addNeighbor(smallestNode3, 4);
        smallestNode2.addNeighbor(smallestNode3, 1);
        smallestNode3.addNeighbor(smallestNode1, 4);
        smallestNode3.addNeighbor(smallestNode2, 1);

        ArrayList<SmallestNode> previousNodes = new ArrayList<>();
        previousNodes.add(smallestNode3);
        Set<Integer> result = smallestNode3.thresholdNeighbors(4, previousNodes);

        System.out.println(result);
    }


}