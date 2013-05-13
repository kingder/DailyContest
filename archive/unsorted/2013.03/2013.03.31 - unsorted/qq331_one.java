/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class qq331_one {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {

        int N = in.nextInt();
        int K = in.nextInt();
        int [][] ps = new int[N][K];
        int[][] dp = new int[2][K];
        int from = 0, to = 1;
        Arrays.fill(dp[from], 0);

        for(int i = 0; i < N; i ++) {
            for(int j = 0; j < K; j ++) {
                ps[i][j] = in.nextInt();
            }
            Arrays.fill(dp[to], Integer.MAX_VALUE);
            if(i > 0){
                for(int j = 0; j < K; j ++) {
                    for(int k = 0; k < K; k ++)
                    if(dp[from][k] + Math.abs(ps[i-1][k] - ps[i][j]) < dp[to][j])
                        dp[to][j] =  dp[from][k] +  Math.abs(ps[i-1][k] - ps[i][j]);
                }
            }else{
                Arrays.fill(dp[to], 0);
            }
            int t = to;
            to = from;
            from = t;
        }
        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < K; i ++)
            ret = Math.min(ret, dp[from][i]);
        out.printLine(ret);
    }
}
