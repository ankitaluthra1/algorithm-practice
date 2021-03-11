package leetcode.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CoinChange {

    Map<CacheKey, Integer> cacheKeyMap = new HashMap<>();

    class CacheKey {
        int amount;
        int[] coins;

        CacheKey(int amount, int[] coins) {
            this.amount = amount;
            this.coins = coins;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return amount == cacheKey.amount &&
                    Arrays.equals(coins, cacheKey.coins);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(amount);
            result = 31 * result + Arrays.hashCode(coins);
            return result;
        }
    }

    public static void main(String[] args) throws IOException {

        CoinChange coinChange = new CoinChange();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        int amount = Integer.parseInt(reader.readLine());
        int[] coins = Arrays.stream(reader.readLine().replace(" ", "")
                .split(","))
                .mapToInt(c -> Integer.parseInt(c))
                .toArray();

        Integer result = coinChange.change(amount, coins);

        System.out.println(result);
    }

    private int change(int amount, int[] coins) {

        CacheKey key = new CacheKey(amount, coins);

        if (cacheKeyMap.containsKey(key)) {
            return cacheKeyMap.get(key);
        }

        if (coins.length == 0) {
            if (amount == 0) {
                cacheKeyMap.put(key, 1);
                return 1;
            }
            cacheKeyMap.put(key, 0);
            return 0;
        }

        if (coins.length == 1) {
            if (amount < coins[0]) {
                if (amount == 0) {
                    cacheKeyMap.put(key, 1);
                    return 1;
                }
                cacheKeyMap.put(key, 0);
                return 0;
            }

            if (amount % coins[0] != 0) {
                cacheKeyMap.put(key, 0);
                return 0;
            }
        }

        int factor = 0;
        int combinations = 0;

        while (true) {
            int currentCoin = coins[0];
            int currentAmount = currentCoin * factor;

            if (currentAmount > amount)
                break;
            if (currentAmount == amount) {
                combinations++;
                break;
            }
            if (coins.length > 1) {
                combinations = combinations + change(amount - currentAmount, Arrays.copyOfRange(coins, 1, coins.length));
            }
            factor++;
        }

        cacheKeyMap.put(key, combinations);
        return combinations;
    }

}

