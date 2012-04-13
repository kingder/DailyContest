/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;


public class FoxAndGCDLCM {
    public long get(long G, long L) {
        if( L % G != 0 )return -1;
        long n = L / G  ;
        long half = (long)Math.sqrt( n + 0.0 ) ;
        while( half > 0 && ( n % half != 0 || gcd( half, n / half) != 1 ) ) half --;
        long A = half * G , B = n / half * G ;
        long gd = gcd( A , B );
        if( gd != G && A*B/G != L ) return -1;
        return A+B;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd( b ,  a% b );
    }
}

