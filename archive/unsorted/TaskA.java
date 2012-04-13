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

public class TaskA {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int [] X = new int[ n ] , Y = new int[ n ] ;
        for( int i = 0 ; i < n ; i ++ ){
               X[i] = in.nextInt() ;
               Y[i] = in.nextInt() ;
        }
        int ret = 0 ;
        for( int i = 0 ; i < n ; i ++ ){
            boolean hasLeft = false , hasRight = false , hasUpper = false , hasLower = false;
            for( int j = 0 ; j < n ; j ++ )if( i != j ){
                hasUpper |= X[i] == X[j] && Y[j] > Y[i] ;
                hasLower |= X[i] == X[j] && Y[j] < Y[i] ;
                hasLeft  |= X[i] < X[j] && Y[j] == Y[i] ;
                hasRight |= X[i] > X[j] && Y[j] == Y[i] ;
            }
            if( hasLeft && hasRight && hasUpper && hasLower )
                ret ++ ;
        }
        out.printLine( ret );
	}
}
