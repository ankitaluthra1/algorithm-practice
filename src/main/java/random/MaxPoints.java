package random;

import java.util.*;
import java.util.stream.Collectors;

public class MaxPoints {

    class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{x=" + x + ", y=" + y + "}";
        }
    }

    public int maxPoints(int[][] points) {

        if (points.length == 0)
            return 0;

        if (points.length == 1)
            return 1;

        List<Point> allPoints = new ArrayList<>();
        Map<Double, Set<Point>> slopeMap = new HashMap<>();

        for (int[] ints : points) {
            allPoints.add(new Point(ints[0], ints[1]));
        }

        for (Point point : allPoints) {
            Set<Point> visited = new HashSet<>();
            for (Point neighbor : allPoints) {
                if (point.equals(neighbor) || visited.contains(neighbor))
                    continue;

                double slope;

                if (neighbor.x - point.x == 0)
                    slope = Double.MAX_VALUE;
                else
                    slope = (neighbor.y - point.y) / (neighbor.x - point.x);
                Set<Point> existingSlopes = slopeMap.getOrDefault(slope, new HashSet<>());
                visited.addAll(existingSlopes);
                existingSlopes.add(point);
                existingSlopes.add(neighbor);
                slopeMap.put(slope, existingSlopes);
            }
        }

        slopeMap.forEach((k,v) -> System.out.println(k+", "+v.stream().map(val->val.toString()).collect(Collectors.joining(" "))));

        List<Integer> counts = slopeMap.values().stream().map(v -> v.size()).collect(Collectors.toList());
        return counts.stream().max(Integer::compareTo).get();
    }

    public static void main(String[] args) {
        MaxPoints mx = new MaxPoints();
        int[][] points = {{1,1},{2,1},{2,2},{1,4},{3,3}};
        int result = mx.maxPoints(points);

        System.out.println(result);
    }

}
