package leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Rate limit for 3 requests per 5 mins
public class RateLimiter {

    int capacity;

    public RateLimiter(int capacity) {
        this.capacity = capacity;
    }

    Map<String, List<Request>> requestMap;

    private void rateLimit(String user, Request request) {
        if (!requestMap.containsKey(user) || requestMap.get(user).size() < capacity) {
            List<Request> list = requestMap.getOrDefault(user, new ArrayList<>());
            list.add(request);
            requestMap.put(user, list);
            System.out.println("Not throttled");
            return;
        }
        boolean throttled = true;
        List<Request> requests = requestMap.get(user);

        for (Request r : requests) {
            if (request.timestamp - r.timestamp >= 5) {
                requests.remove(r);
                throttled = false;
                continue;
            }
            break;
        }
        if (throttled) {
            System.out.println("Request Throttled");
            return;
        }
        requests.add(request);
        requestMap.put(user, requests);
        System.out.println("Not throttled");
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(3);
        Request request1 = new Request(1, "/test");
        Request request2 = new Request(2, "/test");
        Request request3 = new Request(2, "/test");
        Request request4 = new Request(3, "/test");
        Request request5 = new Request(3, "/test");
        Request request6 = new Request(3, "/test");
        Request request7 = new Request(4, "/test");
        Request request8 = new Request(6, "/test");
        Request request9 = new Request(6, "/test");
        Request request10 = new Request(6, "/test");
        rateLimiter.rateLimit("user1", request1);
        rateLimiter.rateLimit("user1", request2);
        rateLimiter.rateLimit("user1", request3);
        rateLimiter.rateLimit("user1", request4);
        rateLimiter.rateLimit("user1", request5);
        rateLimiter.rateLimit("user1", request6);
        rateLimiter.rateLimit("user1", request7);
        rateLimiter.rateLimit("user1", request8);
        rateLimiter.rateLimit("user1", request9);
        rateLimiter.rateLimit("user1", request10);


    }

}

class Request {
    long timestamp;
    String url;

    public Request(long timestamp, String url) {
        this.timestamp = timestamp;
        this.url = url;
    }
}
