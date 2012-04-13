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

public class acm {
    long[] A, D, P;
    static final int mod = 1000003;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        A = new long[n];
        D = new long[n];
        P = new long[n];
        IOUtils.readLongArrays(in, A, D, P);
        
        int m = in.nextInt() ;
        long[] fac = new long[mod + 1];
        fac[0] = 1;
        for (int i = 1; i < mod; i++) fac[i] = fac[i - 1] * i % mod;
        for( int i = 0 ; i < m ; i ++ ){
            if( in.nextInt() == 0 ){
                int a = in.nextInt() ;
                int b = in.nextInt() ;
                int v = in.nextInt() ;
                for( int j = a-1 ; j < b ; j ++ )
                    P[j] += v ;
            }else{
                int a = in.nextInt() ;
                int b = in.nextInt() ;
                long sum = 0 , ret = 1;
                for( int j = a-1 ; j < b ; j ++ ){
                    sum += P[j] ;
                    ret = ret * power_mod( D[j] , P[j] ) % mod ;
                }
                if( sum >= mod ) ret = 0 ;
                else ret = ret * fac[(int)sum] % mod ;
                out.printLine( sum , ret );
            }
        }
    }
    private long power_mod(long n, long m) {
        if (m == 0) return 1;
        if (m == 1) return n;
        long ret = power_mod(n, m >> 1);
        return (m & 1) == 0 ? ret * ret % mod : ret * ret % mod * n % mod;
    }
}

