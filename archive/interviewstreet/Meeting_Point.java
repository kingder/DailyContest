/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.Arrays.ArrayUtils;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Comparator;

public class Meeting_Point {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        int [] X = new int[ N ] , Y = new int[ N ];
        IOUtils.readIntArrays( in,X, Y);

        final long [] TX = new long[ N ] ;
        final long [] TY = new long[ N ] ;

        for( int i = 0 ; i < N ; i ++ ){
            TX[i] = X[i] - Y[i] ;
            TY[i] = X[i] + Y[i] ;
        }
        //Arrays.sort( TY );
        //IOUtils.printLongArrays(out,);

        Integer[] orderX = ArrayUtils.order( N , new Comparator<Integer>() {
            //@Override
            public int compare(Integer o1, Integer o2) {
                return (TX[o1] < TX[o2]) ? -1 : TX[o1] > TX[o2] ? 1 : 0;
            }
        });

        Integer[] orderY = ArrayUtils.order( N , new Comparator<Integer>() {
            //@Override
            public int compare(Integer o1, Integer o2) {
                return (TY[o1] < TY[o2]) ? -1 : TY[o1] > TY[o2] ? 1 : 0;
            }
        });

        long[] sumX = new long[ N ] ;
        for( int i = 1 ; i < N ; i ++ ) sumX[0] += TX[ orderX[i] ] - TX[ orderX[0] ] ;
        for( int i = 1 ; i < N ; i ++ ){
            long gap = TX[ orderX[i] ] - TX[ orderX[i-1] ] ;
            sumX[i] = sumX[i-1] + i*gap - (N-i)*gap;
        }
//        IOUtils.printLongArrays ( out, TX , TY );
//        IOUtils.printArrays( out,  orderX ,orderY );
//        IOUtils.printLongArrays( out, sumX );
        int[] rorderX = new int[ N ] ;
        
        for( int i = 0 ; i < N ; i ++ ) rorderX[ orderX[i] ] = i ;
        long ret = Long.MAX_VALUE ;
        long sumy = 0 ;
        for( int i = 1 ; i < N ; i ++ ) sumy += TY[ orderY[i] ] - TY[ orderY[0] ] ;
        ret = Math.min( sumy + sumX[ rorderX[orderY[0]] ] , ret );
        for( int i = 1 ; i < N ; i ++ ){
            long gap = TY[orderY[i] ] - TY[ orderY[i-1]];
            sumy += i*gap -(N-i)*gap;
            ret = Math.min( ret , sumy + sumX[ rorderX[ orderY[i] ] ] );
        }


        out.printLine( ret / 2);
	}
}
