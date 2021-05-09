package leetcode.random;

//https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
public class ShipInDDays {

    int[] weights;
    int maxDays;

    int shipWithinDays(int[] weights, int D){
        this.weights = weights;
        this.maxDays = D;
        int totalWeight = 0;
        int maxWeight = weights[0];

        for (int weight : weights){
            maxWeight = Math.max(weight, maxWeight);
            totalWeight = totalWeight + weight;
        }
        int avgWeight = totalWeight / D;
        int probableWeight = Math.max(avgWeight, maxWeight);
        int start = probableWeight;
        int end = totalWeight;
        while (start < end){
            int mid = start + (end -start) / 2;
            if(isPossible(mid))
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }

    boolean isPossible(int probableWeight){
        int currentDays = 0;
        int index = 0;

        while (index < weights.length){
            int currentWeight = 0;
            while (index < weights.length){
                if (currentWeight + weights[index] > probableWeight)
                    break;
                currentWeight = currentWeight + weights[index];
                index++;
            }
            currentDays++;
            if(currentDays > maxDays)
                return false;
        }
        return currentDays <= maxDays;
    }

    public static void main(String[] args) {
        ShipInDDays sd = new ShipInDDays();
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int result = sd.shipWithinDays(weights, 5);

        System.out.println(result);
    }

}
