/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Billboards {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        int K = in.nextInt() ;
        long [] dp = new long[ N + 1];
        long [] D = new long[ N + 1];
        long [] sum = new long[ N + 1];
        int [] dq = new int[ N + 10] ;
        int f = 0 , t = 0 ;
        sum[0] = 0 ;
        for( int i = 1 ; i <= N ; i ++ ){
            sum[i] = sum[i-1] + in.nextLong();
        }
        dp[0] = 0 ;
        D[0]  = 0 ;
        dq[t++] = 0 ;

        for( int i = 1 ; i <= N ; i ++ ){
            while ( f < t &&  i - dq[f] > K + 1 ) f ++ ;
            D[i]  = D[ dq[f] ] + sum[i-1] - sum[ dq[f] ] ;
            while ( f < t && i - dq[f] > K ) f ++ ;
            dp[i] = D[ dq[f] ] + sum[i] - sum[ dq[f] ] ;
            while ( f < t && D[i] >= D[ dq[t-1] ] + sum[i] - sum[ dq[t-1] ] ) t--;
            dq[t++] = i ;

        }
        //IOUtils.printLongArrays( out, D );
        //IOUtils.printLongArrays( out, dp );

        out.printLine( Math.max( D[N] , dp[N] ) );
	}
}
