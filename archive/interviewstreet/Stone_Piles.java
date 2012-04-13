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

import java.util.ArrayList;
import java.util.Arrays;

public class Stone_Piles {
    static final int maxn = 50 ;
    static int [] SG = new int[ maxn + 1 ] ;
    static boolean [] set = new boolean[ maxn + 1 ];
    static {
        SG[1] = SG[2] = 0 ;
        SG[3] = 1 ;
        for( int i = 4 ; i <= 50 ; i++ ){
            Arrays.fill( set , false);
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            go( i , i , numbers );
            for( int j = 0 ; j <= maxn ; j ++ )
                if( !set[j] ) { SG[i] = j ; break;}
            //System.out.println( i + " " + SG[i] ) ;
        }

    }
    static void go( int n , int max_n, ArrayList<Integer> numbers ){
         if( n == 0 ){
             int sg = 0 ;
             for( int o : numbers ) sg ^= SG[o] ;
             set[sg] = true;
             return;
         }
         for( int i = Math.min( max_n-1 , n ) ; i >= 1 ; i -- ){
             numbers.add( i ) ;
             go( n-i , i , numbers );
             numbers.remove( numbers.size()-1 );
         }
    }
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int [] A = new int[ n ] ;
        IOUtils.readIntArrays( in, A );
        int sg = 0 ;
        for( int o : A ) sg ^= SG[o] ;
        out.printLine( sg == 0 ? "BOB" : "ALICE");
        
	}
}
