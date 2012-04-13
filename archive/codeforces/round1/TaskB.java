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


public class TaskB {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int count = in.nextInt() , basketCount = in.nextInt() ;
        final int[] price = new int[ count ];
        final int[] type = new int[ count ];
        IOUtils.readIntArrays( in , price, type );
        Integer[] order = ArrayUtils.order( count, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                if( type[o1] != type[o2])
                    return type[o1]-type[o2];
                return price[o2] - price[o1];
            }
        });
        int[][] anwer = new int[ basketCount][];
        for( int i = 0 ; i < basketCount - 1; i ++ ){
            anwer[i] = new int[] { order[i] + 1};
        }
        anwer[ basketCount - 1] = new int[ count - basketCount + 1];
        for( int i = 0 ; i < anwer[ basketCount - 1 ].length ; i ++ )
            anwer[basketCount-1][i] = order[i+basketCount-1] + 1;
        long prices = 2 * ArrayUtils.sumArray( price );
        for( int[] basket: anwer ){
            boolean hasChair = false;
            int minPrice = Integer.MAX_VALUE;
            for( int i: basket){
                hasChair |= type[i-1] == 1 ;
                minPrice = Math.min( minPrice, price[i-1]);
            }
            if(hasChair) prices -= minPrice;
        }
        out.printLine( (prices / 2) + "." + (prices%2*5) );
        for (int[] basket : anwer) {
            out.print(basket.length );
            for( int o: basket) out.print(" "+o);
            out.printLine();
        }
	}
}
