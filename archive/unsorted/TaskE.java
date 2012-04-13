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

public class TaskE {
    int[] dp = new int[ 1<<22];
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] A = new int[ n ] ;
        Arrays.fill(dp, -1);
        for( int i = 0 ; i < n ; i ++){
            A[i] = in.nextInt() ;
            dp[ A[i] ] = A[i] ;
            
        }
        
        for( int i = 0 ; i < (1<<22) ; i ++ )if( dp[i] == -1 )
            for( int k = 0 ; k < 22 ; k ++ )
                if( (i & (1<<k)) != 0 && dp[ i-(1<<k) ] != -1 ){
                    dp[ i ] = dp[ i - (1<<k) ] ;
                    break;
                }

        for( int i = 0 ; i < n ; i ++ ){
            if( i != 0 ) out.print(" ");
            out.print( dp[ (1<<22) - 1 - A[i] ] );
        }
	}
}
