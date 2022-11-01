package leetcode.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionSetTest {

    @Test
    public void shouldReturnMinLengthForIntersectionSet(){
        IntersectionSet intersectionSet = new IntersectionSet();
        int[][] intervals = {{4,14},{6,17},{7,14},{14,21},{4,7}};
        int result = intersectionSet.intersectionSizeTwo(intervals);

        System.out.println(result);
    }

}