package leetcode.classDesign;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterWithMultipleFixedWindows {
    class RequestCount {
        private Map<Integer, Map<Integer, Integer>> requestsPerMilliSecond;

        public RequestCount() {
            this.requestsPerMilliSecond = new HashMap<>();
        }

        public int getCountForLastSecond(int second, int milliSecond) {
            return requestsPerMilliSecond.getOrDefault(second, new HashMap<>()).getOrDefault(milliSecond, 0);
        }

        public void addCountFor(int second, int milliSecond) {
            if(requestsPerMilliSecond.containsKey(second) && requestsPerMilliSecond.get(second).containsKey(milliSecond)){
                Map<Integer, Integer> countMap = requestsPerMilliSecond.get(second);
                int count = countMap.get(milliSecond);
                countMap.put(milliSecond, count + 1);
                requestsPerMilliSecond.put(second, countMap);
            }
            //check in current second
            for (int i = milliSecond; i > 1; i--){
                if(requestsPerMilliSecond.containsKey(second)) {
                    Map<Integer, Integer> countMap = requestsPerMilliSecond.get(second);
                    if(countMap.containsKey(i)){
                        countMap.put(milliSecond,countMap.get(i) + 1);
                        return;
                    }
                }
            }
            // check in previous second
            for (int i = 1000; i >= milliSecond; i--){
                if(requestsPerMilliSecond.containsKey(second - 1)) {
                    Map<Integer, Integer> countMap = requestsPerMilliSecond.get(second - 1);
                    if(countMap.containsKey(i)){
                        countMap.put(milliSecond,countMap.get(i) + 1);
                        return;
                    }
                }
            }
            //add new entry
            Map<Integer, Integer> millsCountMap = new HashMap<>();
            millsCountMap.put(milliSecond, 1);
            requestsPerMilliSecond.put(second, millsCountMap);

            //add credits here as new second is added here
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

    boolean rateLimit(String customerId) {
        Instant currentTime = Instant.now();
        int currentSecond = currentTime.get(ChronoField.SECOND_OF_MINUTE);
        int currentMilliSecond = currentTime.get(ChronoField.MILLI_OF_SECOND);
        RequestCount customerRequestCount = requestCount.getOrDefault(customerId, new RequestCount());

        if (customerRequestCount.getCountForLastSecond(currentSecond, currentMilliSecond) < limitMap.get(customerId)) {
            customerRequestCount.addCountFor(currentSecond, currentMilliSecond);
            return true;
        }

        return false;
    }

}
