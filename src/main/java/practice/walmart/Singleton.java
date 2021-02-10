package practice.walmart;

public class Singleton {

    private static Singleton instance;

    private Singleton() {
        instance = new Singleton();
    }

    static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    return new Singleton();
            }
        }
        return instance;
    }

    static class SingletonHelper {
        private static Singleton instance;

        static Singleton createInstance() {
            if (instance == null)
                instance = new Singleton();
            return instance;
        }
    }

    public static Singleton getBullPughInstance() {
        return SingletonHelper.createInstance();
    }

}
