package glassdoorQuestions.linkedIn;

public class SquareRoot {

    public int mySqrt2(int x) {

        int i = 1;

        while ((long) i *i <= x){
            i++;
        }
        return i - 1;
    }

    public int mySqrt(int x) {

        int x1 = x;
        int y1 = 1;

        while (x1 - y1 > 0.001){
            x1 = (x1 + y1) / 2;
            y1 = x / x1;
        }
        return x1;
    }

    public static void main(String[] args) {
        SquareRoot sq = new SquareRoot();
        System.out.println(sq.mySqrt(2147395600));
    }

}
