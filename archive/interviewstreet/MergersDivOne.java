/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import java.util.Arrays;

public class MergersDivOne {
   public double findMaximum(int[] revenues) {
	   Arrays.sort(revenues);
       double ret = (revenues[0] + revenues[1] + 0.0 ) / 2;
       for( int i = 2 ; i < revenues.length ; i ++ ){
            ret = (ret + revenues[i])/2;
       }
       return ret;
   }
}

