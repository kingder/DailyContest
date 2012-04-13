/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.Arrays.ArrayUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Comparator;

public class KOPC12A {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        final int[] A = new int[ n ] , C = new int[ n ];
        
        for( int i = 0 ; i < n ; i ++ ){
            A[i] = in.nextInt();
        }
        long sum = 0 ;
        for( int i = 0 ; i < n ; i ++ ){
            C[i] = in.nextInt();
            sum += C[i] ;
        }
        
        Integer[] order = ArrayUtils.order( n , new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return A[o1] - A[o2];
            }
        }) ;
        
        long ret = 0 ;
        
        for( int i = 1 ; i < n ; i ++ ) ret += ( A[ order[i] ] - A[ order[0] ] ) * C[ order[i] ] ;
        long presum = C[ order[0] ] ;
        long tmp = ret ;
        for( int i = 1 ; i < n ; i ++ ){
            int len = A[ order[i] ] - A[ order[i-1] ] ;
            tmp = tmp + presum * len - ( sum - presum ) * len ;
            ret = Math.min( ret , tmp );
            presum += C[ order[i] ] ;
        }
        out.printLine( ret );
	}
}
