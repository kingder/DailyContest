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

public class Magic_Machines {
    char[] str;
    int[][][] dp ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        str = in.next().toCharArray();
        int ret = -1 ;
        int n = str.length;
        dp = new int[ n ][ str.length ][ 4 ] ;

        for( int i = 0 ; i < n ; i++ )
            for( int j = 0 ; j < n ; j++ )
                for( int k = 0 ; k < 4 ; k++ )
                    dp[i][j][k] = -2 ;
        for( int i = 1 ; i < n ; i ++ ){
            int a = go( 0 , i-1 , 2 ) ;
            int b = go( i , n-1 , 3 ) ;
            if( a != -1 && b != -1 ){
                if( ret == -1 || ret > a + b + 1 )
                    ret = a + b + 1;
            }
        }
        out.printLine( ret == -1 ? "IMPOSSIBLE" : ret );
	}

    private int go(int l, int r, int c) {
        if( c == 0 || c == 1 ){
            return l == r ? 1 : -1;
        }
        if( l == r ) return -1;
        if( dp[l][r][c] != -2 ) return dp[l][r][c] ;
        dp[l][r][c] = -1;

        if( c == 3 ){
            if( str[l] == 'b' ){
                int x = go( l+1 , r , 2 );
                if( x != -1 && (dp[l][r][c] == -1 || dp[l][r][c] > x + 2) )
                    dp[l][r][c] = x + 2;
            }
        }else{
            if( str[r] == 'a' ){
                int x = go( l , r-1 , 2 );
                if( x != -1 && (dp[l][r][c] == -1 || dp[l][r][c] > x + 2) )
                    dp[l][r][c] = x + 2;

            }else{
               int x = go( l , r-1 , 0);
               if( x != -1 && (dp[l][r][c] == -1 || dp[l][r][c] > x + 2) )
                    dp[l][r][c] = x + 2;
            }
        }
        return dp[l][r][c];

    }
}
