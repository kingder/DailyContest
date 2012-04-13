/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import com.sun.deploy.util.ArrayUtil;
import net.kingder.utils.Arrays.ArrayUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Grid_Walking {
    static final int mod = 1000000007;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int m = in.nextInt() ;
        int[] X = new int[ n ] , D = new int[ n ] ;
        for( int i = 0  ; i < n ; i ++ )
            X[i] = in.nextInt();
        for( int i = 0  ; i < n ; i ++ )
            D[i] = in.nextInt();
        int [][] ways = new int[ n ][ m + 1];
        for( int i = 0 ; i < n ; i ++ ){
            int[] pre = new int[ D[i] ];
            int[] now = new int[ D[i] ];
            Arrays.fill(pre, 0) ;
            pre[ X[i] - 1 ] = 1 ;
            ways[i][0] = 1 ;
            for( int step = 1 ; step <= m ; step ++ ){
                Arrays.fill( now , 0 );
                for( int j = 0 ; j < D[i] ; j ++ )if( pre[ j ] > 0){
                    if( j > 0 ) now[ j - 1 ] = (now[ j - 1 ] + pre[j])%mod;
                    if( j < D[i] - 1 ) now[ j + 1 ] = (now[ j + 1 ] + pre[j])%mod;
                }
                pre = Arrays.copyOf( now , D[i] );
                ways[i][step] = (int)(ArrayUtils.sumArray( now ) % mod);
            }
        }
        
        int [][] C = new int[m+1][m+1];
        for( int i = 0 ; i <= m ; i ++ ){
            C[i][0] = C[i][i] = 1 ;
            for( int j = 1 ; j < i ; j ++ ){
                C[i][j] = ( C[i-1][j] + C[i-1][j-1])%mod;
                //out.printLine( i , j ,C[i][j]);
            }
        }
        long ret = 0 ;
        long[] way = new long[ m + 1];
        way[0] = 1;
        for( int j = 0 ; j < n ; j ++ ){
            for( int k = m ; k >= 0 ; k -- )
                for( int step = 0 ; step <= k; step ++ ){
                    way[k] = (way[k] + (way[k-step] * C[m][k] % mod ) *  ways[j][step]) % mod;
                }
        }
        out.printLine( way[m] );
	}
}
