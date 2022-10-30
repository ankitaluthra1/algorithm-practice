package leetcode.classDesign;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RateLimiterTest {
    @Test
    public void shouldRateLimit5RequestsFor3RequestsIn2SecondsSetting() {
        Map<String, Threshold> map = new HashMap<String, Threshold>(){{
            put("user1", new Threshold(3, 2));
        }}; 
        RateLimiter rateLimiter = new RateLimiter(map);
        boolean rateLimited1 = rateLimiter.rateLimit("user1");
        boolean rateLimited2 = rateLimiter.rateLimit("user1");
        boolean rateLimited3 = rateLimiter.rateLimit("user1");
        boolean rateLimited4 = rateLimiter.rateLimit("user1");

        assertTrue(rateLimited1);
        assertTrue(rateLimited2);
        assertTrue(rateLimited3);
        assertFalse(rateLimited4);

    }

}