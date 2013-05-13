/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TowerOfHanoiCake {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int T = in.nextInt();
        for(int tc = 1; tc <= T; tc ++) {
            int n = in.nextInt();
            int[] height = new int[n];
            for(int i = 0; i < n; i ++) {
                height[i] = in.nextInt();
            }
            for(int i = 0 ; i < n / 2; i ++){
                int tmp = height[i];
                height[i] = height[n - 1 - i];
                height[n - 1 - i] = tmp;
            }
            //for(int i = 0; i < n; i ++) System.out.println(height[i]);
            int[] dp = new int[n + 1];
            dp[0] = height[0];
            int ret = 1;
            for(int i = 1; i < n; i ++){
                if(height[i] > dp[ret - 1])
                    dp[ret ++] = height[i];
                else {
                    int insertp = Arrays.binarySearch(dp, 0, ret, height[i]);
                    if(insertp < 0) insertp = - insertp - 1;
                    dp[insertp] = height[i];
                }
            }
            out.printLine(ret);
        }
    }
}
