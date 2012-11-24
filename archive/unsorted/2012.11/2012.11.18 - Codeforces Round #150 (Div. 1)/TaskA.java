/*
 * Copyright (c) 2012.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskA {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = in.nextInt();

		boolean[] is = new boolean[1<<20];
		Arrays.fill(is, false);

		for(int i = 0; i < n; i++) {
			is[a[i]] = true;
			int tp = 0;
			for(int j = i + 1; j < n; j++) {
				tp |= a[j];
				is[tp | a[i]] = true;
				if((tp & a[i]) == a[i]) break;
			}
		}

		int ret = 0;
		for(int i = 0 ; i < (1<<20); i++)
			ret += is[i] ? 1 : 0;
		out.printLine(ret);
	}
}
