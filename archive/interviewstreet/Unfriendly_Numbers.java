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
import net.kingder.utils.numbers.IntegerUtils;

import java.util.Arrays;
import java.util.HashSet;

public class Unfriendly_Numbers {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        long K = in.nextLong() ;
        HashSet<Long> set = new HashSet<Long>();

        for( int i = 0 ; i < N ; i ++ ){
            set.add(IntegerUtils.gcd( K , in.nextLong()));
        }

        int ret = 0 ;
        for( long i = 1 ; i * i  <= K ; i ++ ) if( K % i == 0 ){
            if( OK( i , set ) ) ret ++ ;
            if( i * i != K && OK( K / i , set ) ) ret ++ ;
        }
        out.printLine( ret );
	}

    private boolean OK(long a, HashSet<Long> set) {
        for( long x : set ) if( x % a == 0 ) return false;
        return true;
    }
}
