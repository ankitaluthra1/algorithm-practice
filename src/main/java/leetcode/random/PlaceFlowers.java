package leetcode.random;

public class PlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = n;
        int length = flowerbed.length;

        if (n == 0)
            return true;

        if (flowerbed.length == 1){
            if(flowerbed[0] == 0)
                count-- ;
            return count == 0;
        }

        for (int i = 0; i < length; i++) {
            if (count == 0)
                return true;
            if (i == 0) {
                if (flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                    flowerbed[i] = 1;
                    count--;
                }
                continue;
            }
            if (i == length - 1) {
                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                    flowerbed[i] = 1;
                    count--;
                }
                continue;
            }
            if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0 && flowerbed[i] == 0) {
                flowerbed[i] = 1;
                count--;
            }
        }

        return count == 0;
    }

    public static void main(String[] args) {
        PlaceFlowers pf = new PlaceFlowers();
        int[] arr = {0};
        boolean result = pf.canPlaceFlowers(arr, 1);

        System.out.println(result);
    }
}