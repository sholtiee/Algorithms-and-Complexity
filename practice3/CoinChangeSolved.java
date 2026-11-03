package practice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChangeSolved {

    public CoinChangeResult solve(int[] denominations, int target){
        final int unreachable = target + 1;
        int[] dp = new int[target + 1];
        int[] prevCoin = new int[target + 1];

        Arrays.fill(dp, unreachable);
        dp[0] = 0;

        for (int sum = 1; sum <= target; sum++) {
            for (int coin : denominations) {
                if (coin > sum) continue;
                if (dp[sum - coin] != unreachable) {
                    int candidate = dp[sum-coin] + 1;

                    if (candidate < dp[sum]) {
                        dp[sum] = candidate;
                        prevCoin[sum] = coin;
                    }
                }
            }
        }

        if (dp[target] == unreachable) {
            return new CoinChangeResult(-1, new ArrayList<>());
        }

        List<Integer> selected = new ArrayList<>();
        int remainingSum = target;

        while (remainingSum > 0) {
            int coin = prevCoin[remainingSum];
            selected.add(coin);
            remainingSum -= coin;
        }

        return new CoinChangeResult(dp[target], selected);
    }

}
