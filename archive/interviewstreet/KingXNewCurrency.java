/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

public class KingXNewCurrency {
   public int howMany(int A, int B, int X) {
       int d = gcd( A, B );
	   if( d % X == 0 ) return -1;
       int ret = 0 ;
//       if( X > Math.max(A, B) ){
//           for( int i = 1 ; i <= d ; i ++ )if( d % i == 0 ) ret ++ ;
//           return ret;
//       }
       for( int i = 1 ; i <= Math.max( A, B ) ; i ++ )
           if( check( A, X, i ) && check( B , X, i) ) ret ++ ;
       return ret;
   }

    private boolean check(int a,int x, int y) {
        for( int i = 0 ; i <= a / x ; i ++ ){
            if( (a - i * x) % y == 0 ) return true;
        }
        return false;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd( b , a % b );
    }
}

