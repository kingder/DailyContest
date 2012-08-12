/*
 * Copyright (c) 2012.
 */

package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Picking_Cards {
    static final int mod = 1000000007;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int [] A = new int[ n + 1];

        for (int i = 0; i < n; i++) {
            int t = in.nextInt() ;
            A[t] ++ ;
        }
        long ret = 1;
        int presum = 0 , prei = 0 , left = 0 ;
        for( int i = 0 ; i <= n ; i ++ ){
            if( A[i] > 0 ){
                if( presum < i ){
                    ret = 0 ;
                    break;
                }
                ret = ret * choose( presum - prei , i - prei ) % mod ;
                presum += A[i] ;
                prei  = i ;
            }
        }
        ret = ret * choose( presum - prei , presum - prei) % mod;
        out.printLine(ret);
	}

    private int choose(int n, int m) {
        long ret = 1 ;
        for( int d = 0 ; d < m ; d ++ )
            ret = ret * ( n - d ) % mod ;
        return (int)ret;
    }
}
