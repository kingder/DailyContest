/*
 * Copyright (c) 2012.
 */

package my;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class C_Recycled_Numbers {

	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int test = in.nextInt() ;
        for (int tc = 0; tc < test; tc++) {
            int A = in.nextInt() ;
            int B = in.nextInt() ;
            HashSet<Pair<Integer,Integer>> myset = new HashSet<Pair<Integer, Integer>>();
            int len = Integer.toString( A ).length() , d = power( len ) ;
            //out.printLine( d , len );
            for( int n = A ; n <= B-1 ; n ++ ){
               if( Integer.toString(n).length() > len ){ len ++ ; d *= 10 ;}
                int nn = n ;
                for( int i = 1 ; i < len ; i ++ ){
                    int last = nn % 10 ;
                    nn = nn / 10 + last * d ;
                    if( last == 0) continue;
                    if(  nn > n && nn <= B ){
                        myset.add( Pair.makePair( n ,nn) );
                    }

                }
            }
            //if( tc < 2 )out.printLine( myset.toString());
            out.printLine("Case #" + (tc+1) + ": " + myset.size() );
        }
	}

    private int power(int d) {
        int ret = 1 ;
        for( int i = 1 ; i < d ; i ++ )
            ret *= 10 ;
        return ret;
    }
}
