package practice.walmart;

import java.util.*;

public class NRoomKeyProblem {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.size() == 0)
            return true;

        Set<Integer> openedRooms = new HashSet<>();
        openedRooms.add(0);
        List<Integer> availableKeys = new ArrayList<>(rooms.get(0));

        while (!availableKeys.isEmpty()) {
            int key = availableKeys.remove(0);
            if (openedRooms.contains(key))
                continue;
            openedRooms.add(key);
            availableKeys.addAll(rooms.get(key));
        }

        return openedRooms.size() == rooms.size();
    }

    public static void main(String[] args) {
        NRoomKeyProblem np = new NRoomKeyProblem();
        boolean result = np.canVisitAllRooms(new ArrayList<>());
        System.out.println(result);
    }


}
