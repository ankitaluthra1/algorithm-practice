package practice.walmart;

class Base {
    public static void show() {
        System.out.println("Base::show() called");
    }
}

class Derived extends Base {
    public static void show() {
        System.out.println("Derived::show() called");
    }
}

public class Test {

    public static void main(String[] args) {
        Base b = new Derived();;
        b.show();
    }

}
