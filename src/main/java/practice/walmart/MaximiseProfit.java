package practice.walmart;

public class MaximiseProfit {

    public int maxProfit(int[] prices) {

        int profit = 0;
        int index = 0;
        int k = 1;
        while (index + k < prices.length) {

            int currProfit = prices[index + k] - prices[index];

            if (currProfit < 0) {
                index = index + k;
                k = 1;
            } else {
                if (currProfit > profit) {
                    profit = currProfit;
                }
                k++;
            }
        }
        return profit;
    }

    public int maxProfitWithMultipleTransactions(int[] prices) {

        int totProfit = 0;
        int maxIndex = 0;
        while (maxIndex < prices.length) {
            int index = maxIndex;
            int profit = 0;
            int k = 1;
            while (true) {
                if (index + k == prices.length)
                    return totProfit + profit;

                int currProfit = prices[index + k] - prices[index];

                if (currProfit < profit) {
                    maxIndex = index + k;
                    break;
                } else {
                    if (currProfit > profit) {
                        profit = currProfit;
                    }
                    k++;
                }
            }
            totProfit = totProfit + profit;
        }

        return totProfit;
    }

    public static void main(String[] args) {
        int[] input = {7,6,4,3,1};
        MaximiseProfit mx = new MaximiseProfit();
        int result1 = mx.maxProfit(input);
        int result2 = mx.maxProfitWithMultipleTransactions(input);

        System.out.println(result2);
    }
}
