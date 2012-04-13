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

public class String_Transmission {
    static final int mod =   1000000007 ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        int K = in.nextInt() ;
        char[] str = in.next().toCharArray();
        
        int [][] C = new int[ N+1 ][ N+1 ];
        for( int i = 0 ; i <= N ; i ++){
            C[i][0] = C[i][i] = 1 ;
            for( int j = 1 ; j < i ; j ++ )
                C[i][j] = ( C[i-1][j] + C[i-1][j-1]) % mod;
        }
        int ret = 0 ;
        for( int i = 0 ; i <= K ; i ++ )
            ret = ( ret + C[N][i]) % mod;
        int [] A = new int[ N+1 ];
        for( int i = 1 ; i < N ; i ++ ) if( N % i == 0 ){
            int[] ones = new int[ i ] ;
            for( int j = 0 ; j < N ; j ++ )
                ones[ j % i ] += str[j] - '0';
            int[] pre = new int[K+1] ;
            int[] now = new int[K+1] ;
            
            pre[0] = 1;
            
            int len = N / i ;
            
            for( int j = 0 ; j < i ; j ++ ){
                Arrays.fill( now , 0 );
                for( int k = 0 ; k <= K ; k ++ ) if( pre[k] > 0 ){
                    if( k + ones[j] <= K ){
                        now[k+ones[j]] += pre[k];
                        now[k+ones[j]] %= mod;
                    }
                    if( k+(len-ones[j]) <= K ){
                        now[k+len-ones[j]] += pre[k];
                        now[k+len-ones[j]] %= mod;
                    }
                }
                pre = Arrays.copyOf( now , K+1 );
            }
            for( int j = 0 ; j <= K ; j ++ )
                A[i] = (A[i] + pre[j])%mod ;
            //out.printLine( A[i] );
            for( int j = i + i ; j < N ; j += i)
                A[j] = ((A[j] - A[i]) % mod + mod ) % mod;
            ret = ((ret - A[i]) % mod+mod)%mod ;
        }
        out.printLine(ret);
	}
}
