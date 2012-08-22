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

public class acm {

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] rate = new int[n] , cost = new int[ n];
        IOUtils.readIntArrays( in , rate, cost);
        int m = in.nextInt() ;
        final long[] com_cost = new long[1<<n];
        final long[] com_rate = new long[1<<n];
        for( int i = 0 ; i < (1<<n) ; i ++){
            for( int j = 0 ; j < n ; j ++ )if((i&(1<<j))!=0){
                com_cost[i] += cost[j];
                com_rate[i] += rate[j];
            }
        }
        Integer[] order = ArrayUtils.order( (1<<n) , new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return com_cost[o1] < com_cost[o2] ? -1 : 1;
            }
        });

        for( int tc = 1 ; tc <= m ; tc ++ ){
            int V = in.nextInt() , T = in.nextInt() ;
            long ret = -1;
            for( int i = 0 ; i < (1<<n) ; i++){
                if( com_rate[ order[i] ] * T >= (long)V ){
                    ret = com_cost[i] ;
                    break;
                }
            }
            out.printLine( "Case " + tc + ": " + (ret == -1 ? "IMPOSSIBLE" : ret));
        }
    }

}

