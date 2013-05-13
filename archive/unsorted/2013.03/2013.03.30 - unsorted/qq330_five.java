/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class qq330_five {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] board = new int[n][m];
        for(int i = 0; i < n; i ++)
            for(int j = 0; j < m; j ++)
                board[i][j] = in.nextInt();

        int rows = n + m - 1;
        int cols = m;
        int[][] nborad = new int[rows][cols];
        for(int[] o: nborad)
            Arrays.fill(o, 0);
        for(int i = 0; i < rows; i ++){
            int nx = i;
            int ny = 0;
            for(int j = 0; j < cols; j ++) {
                if (nx >= 0 && nx < n && ny >= 0 && ny < m)
                    nborad[i][j] = board[nx][ny];

                nx -= 1;
                ny += 1;
            }

            //for(int c = 0; c < cols; c ++)
            //    System.out.print(nborad[i][c]);
            //System.out.println();
        }

        int [] mask = new int[rows];
        for(int i = 0; i < rows; i ++){
            mask[i] = 0;
            for(int j = 0; j < cols; j ++)
                mask[i] = (mask[i] << 1) + (1 - nborad[i][j]);
        }

        int[][] dp = new int[3][1<<cols];
        int one = 0, two = 1, three = 2;
        for(int i = 0; i < rows; i ++) {
            for(int state = 0; state < (1 << cols); state ++) {
                dp[three][state] = 0;
                if((state & mask[i]) == 0 && ((state >> 1) & state) == 0){

                    for(int ps = 0; ps < (1 << cols); ps ++) {
                        if((ps & state) == 0 && ((ps >> 1) & state) == 0 && ((ps >> 2) & state) == 0 )
                            dp[three][state] = Math.max(dp[one][ps], dp[three][state]);
                    }
                    dp[three][state] += Integer.bitCount(state);
                }
            }
            int tp = one;
            one = two;
            two = three;
            three = tp;
        }
        int ret1 = 0, ret2 = 0;
        for(int i = 0; i < (1 << cols); i ++) {
            ret1 = Math.max(ret1, dp[one][i]);
            ret2 = Math.max(ret2, dp[two][i]);
        }
        out.printLine(ret1 + ret2);
    }
}
