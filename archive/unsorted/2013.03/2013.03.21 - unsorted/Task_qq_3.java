/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Task_qq_3 {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[] dp = new int[m + 1];
        Arrays.fill(dp, 0);
        final int[] left = new int[n], right = new int[n], val =new int[n];
        Integer[] order = new Integer[n];
        for(int i = 0; i < n ; i++){
            left[i] = in.nextInt();
            right[i] = in.nextInt();
            val[i] = in.nextInt();
            order[i] = i;
        }
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return left[o1] - left[o2];
            }
        });
        int lo = 0;
        int pre = 0;
        for(int i = 0; i < n; i ++){
            while (lo < Math.min(m,left[order[i]] - 1)){
                lo ++;
                pre = Math.max(pre, dp[lo]);
            }
            if(right[order[i]] <= m)
                dp[right[order[i]]] = Math.max(dp[right[order[i]]], pre + val[order[i]]);
        }
        while (lo < m){

            lo ++;
            pre = Math.max(pre, dp[lo]);
        }
        out.printLine(pre);

    }
}
