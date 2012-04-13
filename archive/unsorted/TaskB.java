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

public class TaskB {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , k = in.nextInt() ;
        int lo = 1 , hi = n , ret = 0 ;
        while ( lo <= hi ){
            int md = lo + ( hi - lo ) / 2 ;
            if( check( md , k  , n ) ){
                ret = md ;
                hi = md -1 ;
            }else lo = md + 1;
        }
        out.printLine( ret );
	}

    private boolean check(long md , int k , long n ) {
        long ret = 0 ;
        long base = 1;
        while ( md / base != 0 ){
            ret += md / base ;
            base *= k ;
        }
        return n <= ret;
    }
}
