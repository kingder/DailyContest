/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Vertical_Sticks {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] A = new int[ n ] ;
        IOUtils.readIntArrays( in , A );
        
        double [][] CC = new double[ n + 1][ n + 1 ] ;
        double [] FF = new double[ n + 1];
        for( double [] o : CC ) Arrays.fill( o , 0 ) ;
        FF[0] = 1;
        for( int i = 1 ; i <= n ; i ++ )
            FF[i] = FF[i-1] * i ;
        for( int i = 0 ; i <= n ; i ++ ){
            CC[i][0] = CC[i][i] = 1 ;
            for( int j = 1 ; j < i ; j ++ )
                CC[i][j] = CC[i-1][j-1] + CC[i-1][j];
        }
        
       // out.printLine( FF[n] , CC[n][n/2]);
        double ret = 0 ;
        for( int i = 0 ; i < n ; i ++ ){
            
            int greater = 0 ;
            for( int j = 0 ; j < n ; j ++ ) if( j != i && A[j] >= A[i] ) greater ++;
            
            int less = n - greater - 1;
            // System.out.println( A[i]+" " + greater +" " + less);
            double tmp = 0 ;
            for( int len = 1 ; len < n && len-1 <= less ; len ++ ){
                  tmp += len * CC[ greater ] [ 1 ] * CC[ less ][ len-1] * FF[ n - len  ] * FF[ len-1 ] ;
                //out.printLine( tmp );
            }
            //out.printLine( tmp );
            for( int len = 1 ; len <= less + 1 ; len ++ ){
                tmp += len * CC[ less ][ len-1 ] * FF[ n - len ] * FF[ len-1 ] ;
            }
            //out.printLine( A[i] , tmp );
            ret += tmp / FF[n] ;
        }
        //out.printLine( ret );
        System.out.println( new DecimalFormat("0.00").format( ret));
	}
}
