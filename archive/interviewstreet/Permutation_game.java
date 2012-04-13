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

public class Permutation_game {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] A = new int[n];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
            A[i]--;
            order[A[i]] = i;
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        for (int i = 0; i < (1 << n); i++) {
            int pre = -1;
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    if (order[j] > pre)
                        pre = order[j];
                    else {
                        ok = false;
                        break;
                    }
                }
            }
            if( ok ) dp[ i ] = 0 ;
        }

        if( go( (1<<n) - 1 , n , dp ) == 1){
                out.printLine("Alice");
        }     else{
            out.printLine("Bob");
        }
    }
    int go( int state , int n , int[] dp ){
        if( dp[state] != -1 ) return dp[state];

        for( int i = 0 ; i < n ; i ++ )if( (state & (1<<i)) != 0 ){
            if( go( state & ~( 1 << i ) , n , dp ) == 0 ){
                return dp[state] = 1;
            }
        }
        return dp[state] = 0;
    }
}
