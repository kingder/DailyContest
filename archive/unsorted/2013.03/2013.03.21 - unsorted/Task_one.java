/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Task_one {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int v1 = in.nextInt(), v2 = in.nextInt(), k = in.nextInt();
        int [] a =new int[n], b = new int[n], val =new int[n];

        for(int i = 0; i < n; i ++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            val[i] = in.nextInt();
        }

        int[][][][] dp = new int[2][v1 + 1][v2 + 1][k + 1];
        int from = 0, to = 1;
        for(int i = 0; i <= v1; i ++)
            for(int j = 0; j <= v2; j ++)
                for(int kk = 0; kk <= k; kk ++)
                    dp[from][i][j][k] = -1;

        dp[from][v1][v2][k] = 0;
        for(int i = 0; i < n; i ++) {
            for(int ii = 0; ii <= v1; ii ++)
                for(int jj = 0; jj <= v2; jj ++)
                    for(int kk = 0; kk <= k; kk ++)
                        dp[to][ii][jj][kk] = -1;
            for(int leftv1 = v1; leftv1 >= 0; leftv1 --)
                for(int leftv2 = v2; leftv2 >= 0; leftv2 --)
                    for(int leftk = k; leftk >= 0; leftk --)if(dp[from][leftv1][leftv2][leftk] != -1){
                        if(dp[to][leftv1][leftv2][leftk] < dp[from][leftv1][leftv2][leftk])
                            dp[to][leftv1][leftv2][leftk] = dp[from][leftv1][leftv2][leftk];
                        if(leftv1 >= a[i]) {
                            if(dp[to][leftv1-a[i]][leftv2][leftk] < dp[from][leftv1][leftv2][leftk] + val[i])
                                dp[to][leftv1-a[i]][leftv2][leftk] = dp[from][leftv1][leftv2][leftk] + val[i];
                        }
                        if(leftv2 >= b[i]) {
                            if(dp[to][leftv1][leftv2-b[i]][leftk] < dp[from][leftv1][leftv2][leftk] + val[i])
                                dp[to][leftv1][leftv2-b[i]][leftk] = dp[from][leftv1][leftv2][leftk] + val[i];
                        }
                        if(leftk >= 1){
                            if(dp[to][leftv1][leftv2][leftk - 1] < dp[from][leftv1][leftv2][leftk] + val[i])
                                dp[to][leftv1][leftv2][leftk - 1] = dp[from][leftv1][leftv2][leftk] + val[i];
                        }
                    }
            from |= to;
            to ^= from;
            from ^= to;
        }
        int ret = 0;
        for(int i = v1; i >= 0; i --)
            for(int j = v2; j >= 0; j --)
                for(int kk = k; kk >= 0; kk --)
                    ret = Math.max(ret, dp[from][i][j][kk]);
        out.printLine(ret);

    }
}
