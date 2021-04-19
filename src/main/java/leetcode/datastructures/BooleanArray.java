package leetcode.datastructures;

import java.util.HashMap;
import java.util.Map;

class Action {
    long timestamp;
    Boolean value;

    Action(long timestamp, Boolean value) {
        this.timestamp = timestamp;
        this.value = value;
    }
}

public class BooleanArray {
    boolean[] source;
    Map<String, Action> actions;
    Map<Integer, Action> changelog;
    static int clock = 0;

    BooleanArray(boolean[] source) {
        this.source = source;
        this.actions = new HashMap<>();
        this.changelog = new HashMap<>();
    }

    void setAll(boolean value) {
        clock++;
        actions.put("all", new Action(clock, value));
    }

    void set(int index, boolean value) {
        clock++;
        changelog.put(index, new Action(clock, value));
    }

    void toggle() {
        clock++;
        if (actions.containsKey("toggle")) {
            boolean currentValue = actions.get("toggle").value;
            actions.put("toggle", new Action(clock, !currentValue));
        } else {
            actions.put("toggle", new Action(clock, true));
        }
    }

    boolean get(int index) {
        Action allAction = actions.get("all");
        long allTime = 0;
        if (allAction != null) {
            allTime = allAction.timestamp;
        }

        Action toggleAction = actions.get("toggle");
        long toggleTime = 0;
        if (toggleAction != null) {
            toggleTime = toggleAction.timestamp;
        }

        Action changedValue = changelog.get(index);
        long changedValueTime = 0;
        if (changedValue != null)
            changedValueTime = changedValue.timestamp;

        if (changedValueTime > toggleTime && changedValueTime > allTime) {
            return changedValue.value;
        }

        if (allTime > toggleTime && allTime > changedValueTime) {
            return allAction.value;
        }

        if (toggleTime > changedValueTime && toggleTime > allTime) {
            if (allTime > changedValueTime)
                return toggledAction(toggleAction.value, allAction.value);
            else
                return toggledAction(toggleAction.value, changedValue == null ? source[index] : changedValue.value);
        }

        return source[index];
    }

    boolean toggledAction(boolean toggleValue, boolean currentValue) {
        if (toggleValue)
            return !currentValue;

        return currentValue;
    }

    public static void main(String[] args) {

        boolean[] src = {true, true, false, true, false};
        BooleanArray ba = new BooleanArray(src);

        ba.toggle();
        System.out.println(ba.get(0));
        System.out.println(ba.get(2));
        System.out.println("--------------------------------");
        ba.setAll(false);
        System.out.println(ba.get(0));
        System.out.println(ba.get(1));
        System.out.println(ba.get(2));
        System.out.println("--------------------------------");
        ba.set(1, true);
        System.out.println(ba.get(1));
        System.out.println("-------------------------------");
        ba.toggle();
        System.out.println(ba.get(1));
        System.out.println(ba.get(2));
    }

}
