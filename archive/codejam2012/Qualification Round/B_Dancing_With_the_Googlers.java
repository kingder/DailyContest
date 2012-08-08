/*
 * Copyright (c) 2012.
 */

package my;

import net.egork.collections.Pair;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class B_Dancing_With_the_Googlers {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int test = in.nextInt() ;
        for (int tc = 0; tc < test; tc++) {
            int n = in.nextInt() ;
            int s = in.nextInt() ;
            int p = in.nextInt() ;
            int[] A = new int[ n ];
            ArrayList<Pair<Integer,Integer>> ap = new ArrayList<Pair<Integer, Integer>>();

            for( int i = 0 ; i < n ; i ++ ){
                A[i] = in.nextInt() ;
                if( A[i] % 3 == 0 ){
                    ap.add( Pair.makePair( A[i] / 3 , A[i] >= 3 ? A[i] / 3 + 1 : -1 ));
                }
                else if( A[i] % 3 == 1 ){
                    ap.add( Pair.makePair( (A[i] + 2)/ 3 , A[i] >= 4 ? (A[i]+2) / 3 : -1 )  );
                }else{
                    ap.add( Pair.makePair( (A[i] + 1)/ 3 , A[i] >= 2 ? (A[i] + 4)/3 : -1 ) );
                }
            }
            //out.printLine( ap.toString());
            int [][] dp = new int[ n+1 ][ s+1 ];
            for( int[] o : dp ) Arrays.fill( o , -1 );
            dp[0][0] = 0 ;
            for( int i = 0 ; i < n ; i ++ ){
                for( int j = 0 ; j <= s ; j ++ ) if( dp[i][j] != -1 ){
                    update( dp , i+1 , j , dp[i][j] + (ap.get(i).first >= p ? 1 : 0) );
                    if(j+1<=s && ap.get(i).second != -1)
                        update( dp , i+1 , j+1 , dp[i][j] + (ap.get(i).second >= p ? 1 : 0 ) );
                }
            }
            //for( int[] o : dp ) IOUtils.printIntArrays( out, o );
            out.printLine( "Case #" + (tc+1) +": "+ dp[n][s] );
        }
	}

    private void update(int[][] dp, int i, int j, int v) {
        if( dp[i][j] == -1 || dp[i][j] < v ) dp[i][j] = v ;
    }
}















