package leetcode.random;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

// Rate limit for X requests per Y mins per client
public class RateLimiter {

    class Threshold {
        private int threshold;
        private int seconds;

        public Threshold(int threshold, int seconds) {
            this.threshold = threshold;
            this.seconds = seconds;
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
        if (existingRequests.size() < customerThresholdMap.get(customerId).threshold) {
            existingRequests.add(currentTime.toString());
            requestQueueMap.put(customerId, existingRequests);
            return true;
        }
        return false;
    }

    private void removeOlderRequestFromRequestQueueMap(String customerId, Instant currentTime) {
        Queue<String> existingRequestQueue = requestQueueMap.get(customerId);
        while (existingRequestQueue.size() > 0) {
            Instant lastRequestInstant = Instant.parse(existingRequestQueue.peek());
            if (Duration.between(currentTime, lastRequestInstant).getSeconds() > customerThresholdMap.get(customerId).seconds)
                existingRequestQueue.poll();
            else
                break;
        }
    }

}

