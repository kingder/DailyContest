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

public class TaskA {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ,m = in.nextInt() , x = in.nextInt() ,y = in.nextInt() ;
        long[] A = new long[ n ] ;
        long[] B = new long[ m ] ;
        for( int i = 0 ; i < n ; i ++ ){
            A[i] = in.nextLong();
        }
        for( int i = 0 ; i < m ; i ++ ){
            B[i] = in.nextLong();
        }


        int ret = 0 ;
        int[] other = new int[n+1];
        Arrays.fill( other,-1);
        int f = 0, t = 0 ;

        for( int i = 0 ; i < A.length ; i ++ ){
            while ( t < B.length && B[t] <= A[i]+y )t ++;
            while ( f < B.length && B[f] < A[i]-x ) f ++;
            if( f < B.length && f <= t && B[f] >= A[i]-x && B[f] <= A[i]+y){
                other[i+1] = ++f;
                ret ++ ;
            }
        }
        out.printLine( ret );
        for( int i = 1 ; i <= n ; i ++ )if( other[i] != -1 )
            out.printLine( i , other[i]);

    }
}
