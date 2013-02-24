/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class TaskC {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        String order = in.next();
        int n = order.length();
        int[] dest = new int[n];
        int left = 0, right = n-1 ;
        for(int i = 0; i < order.length(); i ++){
            if(order.charAt(i) == 'l'){
                dest[right --] = i + 1;
            }else{
                dest[left ++] = i + 1;
            }
        }
        for(int i = 0; i < n; i ++){
            out.printLine(dest[i]);
        }
	}
}
