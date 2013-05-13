/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class qq321_three {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int [] A = new int[n], B = new int[n];
        for(int i = 0; i < n; i ++) {
            A[i] = in.nextInt();
            B[i] = in.nextInt();
        }
        int m = in.nextInt();
        int [] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i < n; i ++){
            for(int j = B[i]; j <= m; j ++) if(dp[j - B[i]] != -1){
                if(dp[j] == -1 || dp[j] < dp[j - B[i]] + A[i])
                    dp[j] = dp[j - B[i]] + A[i];
            }
        }
        int ret = 0;
        for(int i = 0; i <= m; i ++)
            ret = Math.max(ret, dp[i]);
        out.printLine(ret);
    }
}
