package leetcode.random;


interface Anonymous
{
    public int getValue();
}
public class Main
{
    private int data = 15;
    static Anonymous inner = new Anonymous()
    {
        int data = 5;
        public int getValue()
        {
            return data;
        }
        public int getData()
        {
            return data;
        }
    };
    public static void main(String[] args)
    {
        Main outer = new Main();
        System.out.println(inner.getValue() + outer.data);
    }
}
class Test{
    void test(){
        Anonymous a = new Anonymous() {
            @Override
            public int getValue() {
                    return 0;
            }
        };

    }
}


