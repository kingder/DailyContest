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

public class TaskD {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        String str = in.next() ;
        int len = str.length() ;
        int[ ] left = new int[ len ], right = new int[ len ] ;
        Arrays.fill( left , 0 );
        Arrays.fill( right , 0 );
        
        for( int i = 0 ; i < len ; i ++ ){
            int j = 0 ;
            while ( i - j >= 0 && i + j < len && str.charAt( i - j ) == str.charAt( i + j )){
                left[ i + j  ] ++ ;
                right[ i - j ] ++ ;
                j ++ ;
            }
            j = 1;
            while ( i - j + 1 >= 0 && i + j < len && str.charAt( i - j + 1 ) == str.charAt( i + j ) ){
                left[ i + j ] ++ ;
                right[ i - j + 1] ++ ;
                j ++ ;
            }
        }
        //for( int i = 0 ; i < len ; i ++ )
        for( int i = len - 2 ; i >= 0 ; i -- )
            right[i] += right[i+1];
        
        long ret = 0 ;
        for( int i = 0 ; i < len-1 ; i ++ )
            ret += (long)left[i] * right[i+1];
        out.printLine( ret );
        
	}
}
