package glassdoorQuestions.walmart;

import java.util.ArrayList;
import java.util.List;

class MyBlockingQueue<T> {

    List<T> list = new ArrayList<>();
    final int maxSize = 10;

    void addItem(T item) throws InterruptedException {
        synchronized (list) {
            if (list.size() == maxSize) {
                list.wait();
            } else {
                list.add(item);
                list.notifyAll();
            }
        }
    }

    T dequeueItem() throws InterruptedException {
        while (true) {
            synchronized (list) {
                if (list.size() == 0) {
                    list.wait();
                } else {
                    T item = list.remove(0);
                    list.notifyAll();
                    return item;
                }
            }
        }
    }

    static class Producer implements Runnable {

        private MyBlockingQueue<Integer> myBlockingQueue;

        Producer(MyBlockingQueue<Integer> myBlockingQueue) {

            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(5000);
                this.myBlockingQueue.addItem(10);
                Thread.sleep(5000);
                this.myBlockingQueue.addItem(20);
                Thread.sleep(5000);
                this.myBlockingQueue.addItem(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer implements Runnable {

        private MyBlockingQueue<Integer> myBlockingQueue;

        Consumer(MyBlockingQueue myBlockingQueue) {
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    Integer result = myBlockingQueue.dequeueItem();
                    System.out.println(result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue<Integer> myQueue = new MyBlockingQueue<>();

        Thread t1 = new Thread(new Producer(myQueue));
        Thread t2 = new Thread(new Consumer(myQueue));

        t1.start();
        t2.start();

        System.out.println("Starting...");

    }

}