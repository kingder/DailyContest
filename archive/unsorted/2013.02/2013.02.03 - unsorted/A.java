/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class A {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {

	    int cases = in.nextInt();
	    //cases = 25;
	    for(int tc = 1; tc <= cases; tc ++ ){
		    int n = in.nextInt();
		    int k = in.nextInt();
		    //int n = 10000;
		    //int k = (int)(Math.random() * n);
		    long [] A = new long[n];
		    for(int i = 0; i < n; i++)
			    A[i] = in.nextLong();
//		    for(int i = 0; i < n; i ++)
//			    A[i] = (int)(Math.random() * 2000000000);
		    BigInteger factk = BigInteger.valueOf(1);
		    Arrays.sort(A);
		    BigInteger ret = BigInteger.valueOf(A[k - 1]);
		    BigInteger mod = BigInteger.valueOf(1000000007);
		    for(int i = k + 1; i <= n; i ++){
			    factk = factk.multiply(BigInteger.valueOf(i - 1));
			    factk = factk.divide(BigInteger.valueOf(i - k));
			    ret = ret.add(factk.multiply(BigInteger.valueOf(A[i - 1])).mod(mod));
		    }
		    out.printLine("Case #" + tc + ": " + ret.mod(mod));
	    }
    }
}
