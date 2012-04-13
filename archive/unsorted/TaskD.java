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
    static final int Mod = 1000000007;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] D = new int[ n ];
        int degreeSum = 0 ;
        int notDetermined = 0 ;
        for( int i = 0 ; i < n ; i ++ ){
             D[i] = in.nextInt() ;
             notDetermined += D[i] == -1 ? 1 : 0 ;
             degreeSum +=  D[i] ;
        }
        int expectedSum = n * ( n - 1 ) /2 ;
        if( degreeSum > expectedSum || notDetermined == 0 && degreeSum != expectedSum){
            out.printLine( 0 );
            return;
        }

        degreeSum =  n * ( n - 1) / 2 - degreeSum;
        int ret = 1 ;
        if( notDetermined == n ){
            for( int i = 0 ; i < degreeSum ; i ++ ) ret = ret * 2 % Mod ;
        }else{
            ret = getMethods(notDetermined, degreeSum);
        }
        out.printLine( ret ) ;
	}

    private int getMethods(int n, int k) {
        int [] A = new int[ k + 1];
        int [] C = new int[ k + 1];
        for( int i = 0 ; i < n ; i ++ ) A[ i ] = 1;
        for( int it = 0 ; it < n - 1 ; it ++ ){
            for( int j = 0 ; j < n ; j ++ ){
                for( int i = 0 ; i + j <= k ; i ++ )
                    C[ i + j ] = ( C[ i + j ] + A[ i ] ) % Mod;
            }
            int[] tmp = C ; C = A ; A = tmp;
            Arrays.fill( C , 0 );
        }
        return C[ k ] ;
    }
}
