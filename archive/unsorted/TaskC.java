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

public class TaskC {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int k = in.nextInt();
        String str = in.next() ;
        int n = str.length() ;
        

        int[] left = new int[ str.length() + 2] , right = new int[ str.length() + 2] ;
        
        left[0] = 0 ;
        right[n+1] = n+1;
        for( int i = 1 ; i <= str.length(); i ++ ){
            left[i] = str.charAt( i - 1 ) == '1' ? i : left[i-1] ;
            right[ n - i + 1] = str.charAt( n - i ) == '1' ? n - i + 1 : right [ n - i + 2] ;
        }
        left[n+1] = left[n];
        right[0] = right[1];
//        for( int i = 1; i <= n ; i++ ){
//            System.out.println( left[i] + " " + right[i] );
//        }
        long ret = 0 ;

        if( k == 0 ){
            int lo  = 0;
            do{
                lo = right[lo+1];
                ret += (long)(lo - left[lo-1])*( lo - left[lo-1] - 1) / 2;
            }while ( lo <= n );

            out.printLine( ret );
            return;
        }
        int lo = right[1] , hi = right[1] , numberOfone = 1 ;
       // out.printLine( lo , hi );
        while ( hi <= n ){

            if( numberOfone == k ){
                ret += (long)(lo - left[ lo - 1 ]) * ( right[ hi + 1] - hi );
                lo = right[ lo+1 ] ;
            }else{
                numberOfone ++ ;
            }
            hi = right[hi+1] ;
           // System.out.println( lo +" " + hi );
        }
        //out.printLine( lo , hi );
        out.printLine( ret );
        
	}
}
