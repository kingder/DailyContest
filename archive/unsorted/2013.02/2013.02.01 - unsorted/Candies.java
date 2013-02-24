/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Candies {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int n = in.nextInt();
	    int[] A = new int[n];
	    for(int i = 0; i < n; i ++ ) {
		    A[i] = in.nextInt();
	    }
	    int[] left = new int[n], right = new int[n];
	    Arrays.fill(left, 0);
	    Arrays.fill(right, 0);
	    int less = 0;
	    for(int i = 0; i < n; i ++){
		    left[i] = less;
		    if(i < n - 1){
			   if(A[i] < A[i + 1])
			        less ++;
			   else
				   less = 0;
		    }
	    }
	    less = 0;
	    for(int i = n - 1; i >= 0; i --){
		    right[i] = less;
		    if(i > 0){
			    if(A[i] < A[i - 1])
				    less ++;
			    else
				    less = 0;
		    }
	    }
	    int[] dp = new int[n];
	    for(int i = 0; i < n; i ++){
		    dp[i] = Math.max(left[i], right[i]) + 1;
		   // out.printLine(i + ": " + dp[i]);
	    }
	    long sum = 0;
	    for(int v : dp)
	        sum += v;
	    out.printLine(sum);
    }
}
