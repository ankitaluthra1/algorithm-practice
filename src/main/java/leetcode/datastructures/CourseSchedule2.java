package leetcode.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule2 {
    boolean[] visited;
    Map<Integer, List<Integer>> map;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            List<Integer> list = map.getOrDefault(from, new ArrayList<>());
            list.add(to);
            map.put(from, list);
        }
        visited = new boolean[numCourses];

        List<Integer> schedule = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            boolean possible = topologicalSort(i, new ArrayList<>(), schedule);
            if(!possible)
                return new int[0];
        }
        int output[] = new int[numCourses];
        int index = 0;
        for (int x: schedule) {
            output[index] = x;
            index++;
        }

        return output;
    }

    private Boolean topologicalSort(int curr, List<Integer> waiting, List<Integer> scheduled) {
        if (visited[curr])
            return true;

        if (waiting.contains(curr))
            return false;
        List<Integer> preRequired = map.getOrDefault(curr, new ArrayList<>());
        waiting.add(curr);
        for (int i : preRequired) {
            if (!topologicalSort(i, waiting, scheduled))
                return false;
        }
        visited[curr] = true;
        scheduled.add(curr);
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule2 cs = new CourseSchedule2();
        int[][] arr = {{0,1}, {1,0}};
        int[] result = cs.findOrder(2, arr);

        for (int i = 0;i < result.length; i++)
            System.out.print(result[i]+", ");
        System.out.println("");
    }

}
