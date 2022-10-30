package leetcode.classDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Coordinate{
    final int x;
    final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

interface Direction{
    public abstract Coordinate move(Coordinate coordinate);
}

class UpDirection implements Direction{

    @Override
    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(coordinate.x, coordinate.y-1);
    }
}

class DownDirection implements Direction{

    @Override
    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(coordinate.x, coordinate.y+1);
    }
}
class LeftDirection implements Direction{

    @Override
    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(coordinate.x-1, coordinate.y);
    }
}
class RightDirection implements Direction{

    @Override
    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(coordinate.x+1, coordinate.y);
    }
}

class DirectionFactory{

    public static Direction getDirectionFrom(String direction){
        if("L".equals(direction))
            return new LeftDirection();
        if("R".equals(direction))
            return new RightDirection();
        if("U".equals(direction))
            return new UpDirection();
        if("D".equals(direction))
            return new DownDirection();
        throw new IllegalArgumentException("Not correction direction");
    }
}

class Snake{
    List<Coordinate> body;


    public Snake() {
        this.body = new ArrayList<>();
        this.body.add(new Coordinate(0,0));
    }

    public Coordinate getHead(){
        return this.body.get(0);
    }
    public void moveAHead(Coordinate newHead){
        int lastIndex = body.size() - 1;
        body.remove(lastIndex);

        if(this.body.contains(newHead))
            throw new GameOverException();

        body.add(0, newHead);
    }

    public int increase(Coordinate newPosition) {
        body.add(0, newPosition);
        return body.size() - 1;
    }
}

class GameOverException extends RuntimeException{

}

public class SnakeGame {

    List<Coordinate> food;
    int height;
    int width;

    Snake snake;

    public SnakeGame(int height, int width, List<Coordinate> food) {
        this.height = height;
        this.width = width;
        this.food = food;
        snake = new Snake();
    }

    public int move(String direction){
        Direction command = DirectionFactory.getDirectionFrom(direction);
        Coordinate newPosition = command.move(this.snake.getHead());

        if(!newPosition.equals(food.remove(0))) {
            try {
                snake.moveAHead(newPosition);
                return 0;
            }
            catch (GameOverException e){
                System.out.println("Game Over !!");
                return -1;
            }
        }else {
            return snake.increase(newPosition);
        }
    }

}
