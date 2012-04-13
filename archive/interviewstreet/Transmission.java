/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class Transmission {
    HashMap<Pair<Integer,Integer>,HashSet<Integer>> dp = new HashMap<Pair<Integer, Integer>, HashSet<Integer>>();
    int[] digits;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        digits = new int[ n ];
        IOUtils.readIntArrays( in , digits );
        go( 0 , n-1 );

        ArrayList<Integer> a = new ArrayList<Integer>();
        for( int o : dp.get( Pair.makePair( 0 , n-1 ) ) ) {
            if(o>0)a.add( o );
            //out.printLine( o ," ");
        }
        Collections.sort( a );
        int ret = a.size() ;
        for( int i = 0 ; i < a.size() ; i ++){
            if( a.get(i) != i+1 ) {
                ret = i+1;
                break;
            }
        }
        out.printLine( ret );
	}

    private void go(int l, int r) {


        Pair<Integer,Integer> p = Pair.makePair( l , r ) ;
        if( dp.containsKey( p ) ) return ;

        dp.put( p , new HashSet<Integer>() );
        if( l == r ){
            dp.get( p ).add( digits[l] );
        }
        for( int d = l+1 ; d <= r ; d ++ ){
            go( l , d-1 );
            go( d , r   );
            HashSet<Integer> left = dp.get( Pair.makePair( l , d-1 ) );
            HashSet<Integer> right= dp.get( Pair.makePair( d , r ) );
            for( int a : left )for( int b : right ){
                dp.get( p ).add( a + b );
                dp.get( p ).add( a - b );
                if(b!=0)dp.get( p ).add( a / b );
                dp.get( p ).add( a * b );
            }
        }
    }
}
