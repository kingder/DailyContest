/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Flowers {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int n = in.nextInt(), k = in.nextInt();
	    int[] C = new int[n];
	    for(int i = 0; i < n; i ++ ) C[i] = in.nextInt();
	    Arrays.sort(C);
	    long ret = 0;
	    for(int i = n - 1; i >= 0; i --)
		     ret += ((n - i - 1) / k + 1) * C[i];
	    out.printLine(ret);
    }
}
