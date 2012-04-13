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

import java.util.Arrays;

public class Lego_Blocks {
    static final int mod = 1000000007;

	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int H = in.nextInt() ;
        int W = in.nextInt() ;

        int[] ways = new int[ W+1 ];
        Arrays.fill( ways,0);
        ways[0] = 1;
        for( int i = 0 ; i <= W ; i ++ )
            for( int j = 1 ; j <= 4 ; j ++ ){
                if(i+j<=W)ways[i+j] = (ways[i+j] + ways[i]) % mod ;
            }
        
        //IOUtils.printIntArrays( out, ways );

        int [] pre = new int[ W + 1 ];
        for( int i = 1 ; i <= W ; i ++ ) pre[i] = power_mod( ways[i] , H ) ;
        int [] tott = new int[ W + 1 ];

        for( int i = 1 ; i <= W ; i ++ ){
            tott[i] = power_mod( ways[i] , H);
            for( int j = 1 ; j < i ; j ++ ){
                  tott[i] = (int)(( ( tott[i] - (long)tott[j] * pre[i - j] ) % mod + mod ) % mod) ;
            }
        }
        out.printLine( tott[W] );

	}
    
    static int power_mod( int a , int p ){
        if( p == 0 ) return 0 ;
        if( p == 1) return a;
        long half = power_mod( a , p >> 1);
        half = half * half % mod ;
        if( (p & 1) != 0  ) half = half * a % mod ;
        return (int) half;
    }
}
