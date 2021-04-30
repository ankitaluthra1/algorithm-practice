package leetcode.random;

import java.util.HashMap;
import java.util.Map;

class RobotInCircle {
    static class Position{

        static Map<Character,Character> turnLeftMap = new HashMap<Character, Character>(){{
            put('N','W');
            put('W','S');
            put('S','E');
            put('E','N');
        }};
        static Map<Character,Character> turnRightMap = new HashMap<Character, Character>(){{
            put('N','E');
            put('E','S');
            put('S','W');
            put('W','N');
        }};

        int x;
        int y;
        char direction;

        Position(int x, int y, char direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

    }

    static class Robot{
        Position currentPosition;
        Robot(Position currentPosition){
            this.currentPosition = currentPosition;
        }
        public void move(char instruction){
            if(instruction == 'G'){
                if(currentPosition.direction == 'N'){
                    currentPosition.y++;
                    return;
                }
                if(currentPosition.direction == 'S'){
                    currentPosition.y--;
                    return;
                }
                if(currentPosition.direction == 'E'){
                    currentPosition.x++;
                    return;
                }
                if(currentPosition.direction == 'W'){
                    currentPosition.x--;
                    return;
                }
            }
            if(instruction == 'L'){
                currentPosition.direction = Position.turnLeftMap.get(currentPosition.direction);
                return;
            }
            if(instruction == 'R'){
                currentPosition.direction = Position.turnRightMap.get(currentPosition.direction);
                return;
            }
            throw new IllegalArgumentException("invalid instruction "+instruction);


        }

    }

    public boolean isRobotBounded(String instructions) {
        Position pos = new Position(0,0,'N');
        Robot robot = new Robot(pos);

        for(int i = 0; i< instructions.length();i++){
            robot.move(instructions.charAt(i));
        }
        Position finalPosition = robot.currentPosition;

        System.out.println(finalPosition.x +", "+finalPosition.y +" "+ finalPosition.direction);

        return (0 == finalPosition.x && 0 == finalPosition.y) || 'N' != finalPosition.direction;
    }

    public static void main(String[] args) {
        RobotInCircle ric = new RobotInCircle();
        boolean result = ric.isRobotBounded("GL");

        System.out.println(result);
    }
}
