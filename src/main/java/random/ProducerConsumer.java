package random;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {

    static AtomicInteger done = new AtomicInteger(0);

    public static void main(String[] args) {

        List<Integer> myBlockingQueue = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(myBlockingQueue, i)).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(new Producer(myBlockingQueue)).start();
        }

    }

}

class Producer implements Runnable {
    final List<Integer> productQueue;

    Producer(List<Integer> productQueue) {
        this.productQueue = productQueue;
    }

    @Override
    public void run() {

        Random random = new Random();
        random.ints();

        synchronized (productQueue) {
            for (int i = 0; i < 10; i++) {
                int produced = random.nextInt(20);
                this.productQueue.add(produced);
                System.out.println("Produced: " + produced);
            }

            this.productQueue.add(Integer.MAX_VALUE);
            System.out.println("Produced: " + Integer.MAX_VALUE);
            productQueue.notifyAll();
        }
    }
}

class Consumer implements Runnable {

    final List<Integer> productQueue;
    Integer consumerNumber;

    Consumer(List<Integer> productQueue, Integer consumerNumber) {
        this.productQueue = productQueue;
        this.consumerNumber = consumerNumber;
    }

    @Override
    public void run() {
        while (true) {
            if (ProducerConsumer.done.get() >= 4) {
                System.out.println("Closing consumer " + consumerNumber);
                System.out.println("queue size: " + productQueue.size());
                break;
            }
            synchronized (productQueue) {
                if (productQueue.isEmpty()) {
                    try {
                        productQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    int consumed = this.productQueue.remove(0);
                    if (consumed == Integer.MAX_VALUE) {
                        ProducerConsumer.done = new AtomicInteger(ProducerConsumer.done.get() + 1);
                    }
                    System.out.println("Consumed by " + consumerNumber + " : " + consumed);
                    productQueue.notify();
                }
            }
        }
    }
}
