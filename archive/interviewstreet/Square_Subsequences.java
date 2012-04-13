/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Square_Subsequences {
    static final int Mod = 1000000007;

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        char[] sequence = in.next().toCharArray();
        int n = sequence.length;

        int[][] dp = new int[n + 1][n + 1];

        int ret = 0;
        for (int start = 2; start <= n; start++) {

            for (int[] o : dp)  Arrays.fill(o, 0);
            for( int i = 1 ; i < start ; i ++ ) if( sequence[i-1] == sequence[start-1] )
                dp[i][start] = 1 ;


                //out.printLine(Sum[n][n]);
            for (int i = 1; i < start; i++)
                for (int j = start ; j <= n; j++){
                    dp[i][j] = ( dp[i][j] + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1])%Mod;
                    if( dp[i][j] < 0 ) dp[i][j] += Mod;
                    if (sequence[i - 1] == sequence[j - 1]) {
                        dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % Mod;
                        //out.printLine(i, j, Sum[i - 1][j - 1]);
                    }
                }
            ret = ( ret + dp[start-1][n] ) % Mod ;
            //out.printLine( ret );
        }


        out.printLine(ret);

    }
}
