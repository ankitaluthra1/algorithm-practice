package leetcode.random;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// Rate limit for X requests per Y mins per client
public class RateLimiter {

    class Threshold {
        private final int MAX_CAPACITY = 500;
        private int threshold;
        private int seconds;
        private int credits;

        public Threshold(int threshold, int seconds) {
            this.threshold = threshold;
            this.seconds = seconds;
        }

        public void addCredits(int additionalCredits){
            int count = Math.min(MAX_CAPACITY - this.credits, additionalCredits);
            this.credits = this.credits + count;
        }

        public int thresholdWithCredits(){
            return this.credits + this.threshold;
        }

        public int getThreshold() {
            return threshold;
        }

        public int getSeconds() {
            return seconds;
        }

        public void deductCredit() {
            this.credits--;
        }
    }
    public RateLimiter() {
        requestQueueMap = new HashMap<>();
        customerThresholdMap = new HashMap<>();
    }

    Map<String, Queue<String>> requestQueueMap;
    Map<String, Threshold> customerThresholdMap;

    private boolean rateLimit(String customerId) {
        Instant currentTime = Instant.now(); //captures the current time in UTC
        removeOlderRequestFromRequestQueueMap(customerId, currentTime);
        Queue<String> existingRequests = requestQueueMap.get(customerId);
        Threshold threshold = customerThresholdMap.get(customerId);
        if (existingRequests.size() < threshold.thresholdWithCredits()) {
            if (existingRequests.size() >= threshold.getThreshold()){
                threshold.deductCredit();
            }
            existingRequests.add(currentTime.toString());
            requestQueueMap.put(customerId, existingRequests);
            threshold.addCredits(customerThresholdMap.get(customerId).threshold - existingRequests.size());
            customerThresholdMap.put(customerId, threshold);
            return true;
        }
        return false;
    }

    private void removeOlderRequestFromRequestQueueMap(String customerId, Instant currentTime) {
        Queue<String> existingRequestQueue = requestQueueMap.get(customerId);
        while (existingRequestQueue.size() > 0) {
            Instant lastRequestInstant = Instant.parse(existingRequestQueue.peek());
            if (Duration.between(currentTime, lastRequestInstant).getSeconds() > customerThresholdMap.get(customerId).getSeconds())
                existingRequestQueue.poll();
            else
                break;
        }
    }

}

