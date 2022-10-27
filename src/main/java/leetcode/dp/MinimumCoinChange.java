package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/coin-change/

public class MinimumCoinChange {

    private int[] coins;
    Map<Integer, Integer> cache;

    public int coinChange(int[] coins, int amount) {
        cache = new HashMap<>();
        if (amount == 0)
            return 0;

        this.coins = coins;

        int index = coins.length - 1;

        return findMinChange(amount);

    }

    private int findMinChange(int amountLeft) {

        if (amountLeft == 0)
            return 0;

        if (amountLeft < 0)
            return -1;

        if (cache.containsKey(amountLeft)) {
            return cache.get(amountLeft);
        }

        int minChange = Integer.MAX_VALUE;

        for (int coin: coins) {
            int updatedAmountLeft = amountLeft - coin;

            int minChange1 = findMinChange(updatedAmountLeft);

            if (minChange1 != -1) {
                minChange = Math.min(minChange, 1 + minChange1);
            }
        }
        if (minChange == Integer.MAX_VALUE){
            cache.put(amountLeft,-1);
            return -1;
        }
        cache.put(amountLeft,minChange);
        return minChange;
    }

    public static void main(String[] args) {

        MinimumCoinChange mn = new MinimumCoinChange();
        int[] coins = {120,6,320,300,100,192,212,89,106,461};
        int result = mn.coinChange(coins, 8332);
        System.out.print(result);
    }

}
