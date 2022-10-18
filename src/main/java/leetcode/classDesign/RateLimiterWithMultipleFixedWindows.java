package leetcode.classDesign;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterWithMultipleFixedWindows {
    class RequestCount {
        private Map<Integer, Integer> requestCountMap;

        public RequestCount() {
            this.requestCountMap = new HashMap<>();
        }

        public int getCountForSecond(int second) {
            return requestCountMap.getOrDefault(second, 0);
        }

        public void addCountFor(int second) {
            int count = requestCountMap.getOrDefault(second, 0);
            this.requestCountMap.put(second, count + 1);
        }
    }

    Map<String, Integer> limitMap;
    Map<String, RequestCount> requestCount;


    public RateLimiterWithMultipleFixedWindows() {
        limitMap = new HashMap<>();
        requestCount = new HashMap<>();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            synchronized (requestCount) {
                requestCount = new HashMap<>();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    boolean rateLimit(int customerId) {
        Instant currentTime = Instant.now();
        int currentSecond = currentTime.get(ChronoField.SECOND_OF_MINUTE);
        RequestCount customerRequestCount = requestCount.getOrDefault(customerId, new RequestCount());

        if (customerRequestCount.getCountForSecond(currentSecond) < limitMap.get(customerId)) {
            customerRequestCount.addCountFor(currentSecond);
            return true;
        }

        return false;
    }

}
