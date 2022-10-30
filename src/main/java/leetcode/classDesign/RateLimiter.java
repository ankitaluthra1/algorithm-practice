package leetcode.classDesign;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
* Imagine we are building an application that is used by many different customers. We want to avoid one customer being able to overload the system by sending too many requests, so we enforce a per-customer rate limit. The rate limit is defined as:

“Each customer can make X requests per Y seconds”

Assuming that customer ID is extracted somehow from the request, implement the following function.


// Perform rate limiting logic for provided customer ID. Return true if the
// request is allowed, and false if it is not.
* Extension
*
* one of our customers have bursty traffic, and are complaining about being rate limited. We want to better accomodate those customers, so we want to adopt a ‘credit’ based system. It will work as follows:

For each bucket of time, any capacity available below the limit is converted into credits for that customer

There is some maximum number of credits that a customer can accumulate

When a customer exceeds their normal request limit for a window, the credit count starts to decrease. When there are 0 credits, the customer is rate limited.
* */
public class RateLimiter {

    public RateLimiter(Map<String, Threshold> customerThresholdMap) {
        requestQueueMap = new HashMap<>();
        this.customerThresholdMap = customerThresholdMap;
    }

    Map<String, Queue<String>> requestQueueMap;
    Map<String, Threshold> customerThresholdMap;

    public boolean rateLimit(String customerId) {
        Instant currentTime = Instant.now(); //captures the current time in UTC
        removeOlderRequestFromRequestQueueMap(customerId, currentTime);
        Queue<String> existingRequests = requestQueueMap.getOrDefault(customerId, new LinkedList<>());
        Threshold threshold = customerThresholdMap.get(customerId);
        if (existingRequests.size() < threshold.thresholdWithCredits()) {
            if (existingRequests.size() >= threshold.getThreshold()){
                threshold.deductCredit();
            }
            existingRequests.add(currentTime.toString());
            requestQueueMap.put(customerId, existingRequests);
            customerThresholdMap.put(customerId, threshold);
            return true;
        }
        return false;
    }

    private void removeOlderRequestFromRequestQueueMap(String customerId, Instant currentTime) {
        Queue<String> existingRequestQueue = requestQueueMap.getOrDefault(customerId, new LinkedList<>());
        while (existingRequestQueue.size() > 0) {
            Instant lastRequestInstant = Instant.parse(existingRequestQueue.peek());
            if (Duration.between(currentTime, lastRequestInstant).getSeconds() > customerThresholdMap.get(customerId).getSeconds())
                existingRequestQueue.poll();
            else
                break;
        }
    }

}

class Threshold {
    private final int MAX_CAPACITY = 500;
    private int threshold;
    private int seconds;
    private int credits;

    public Threshold(int threshold, int seconds) {
        this.threshold = threshold;
        this.seconds = seconds;
        this.credits = 0;
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

