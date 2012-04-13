/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.kingder.utils.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-3-12
 * Time: ÏÂÎç5:11
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtils {
    public static void decreaseByOne(int[]... arrays){
        for(int[] array:arrays)
            for( int i = 0 ; i < array.length ; i++ )
                array[i] --;
    }
    public static Integer[] generateOrder(int size){
        Integer[] order = new Integer[size];
        for( int i = 0 ; i < size ; i ++ ){
            order[i] = i ;
        }
        return order;
    }
    public static long sumArray(int[] array){
        long sum = 0;
        for( int element : array ) sum += element;
        return sum;
    }
    public static Integer[] order(int size, Comparator<Integer> comparator){
        Integer[] order = generateOrder( size );
        Arrays.sort(order, comparator);
        return order;
    }
    public static Integer[] unique(int size ,Integer[] rorder, Comparator<Integer> comparator){
        Integer[] order = order( size , comparator );
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ret.add( order[0] ) ;
        rorder[ order[0] ] = 0 ;
        for( int i = 1 ; i < size; i ++ ){
            while ( i < size && comparator.compare( order[i] , order[i-1]) == 0 ){
                rorder[ order[i] ] = ret.size()-1;
                i ++ ;
            }
            if( i >= size ) break;
            ret.add( order[i] );
            rorder[ order[i] ] = ret.size() - 1;
        }
        Integer[] oo = new Integer[ ret.size()];
        ret.toArray( oo );
        return oo;
    }

}
