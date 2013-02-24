/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
		int m, n;
		m = in.nextInt();
		int[] discounts = new int[m];
		for(int i = 0; i < m;  i ++) discounts[i] = in.nextInt();
		Arrays.sort(discounts);
		n = in.nextInt();
		int[] item = new int[n];
		for(int i = 0; i < n; i ++) item[i] = in.nextInt();
		Arrays.sort(item);
		int ret = 0, index = 0, t = 0;
		for(int i = item.length-1; i >= 0; i--){
			ret += item[i];
			if(index < m){
				if(++t == discounts[index]){
					i -= 2;
					t = 0;
				}
			}
		}
		out.printLine(ret);
	}
}
