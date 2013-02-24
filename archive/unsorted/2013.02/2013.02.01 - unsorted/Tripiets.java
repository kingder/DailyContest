/*
 * Copyright (c) 2013.
 */

package my;

import com.sun.xml.internal.bind.v2.model.core.MapPropertyInfo;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class Tripiets {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int n = in.nextInt();
	    int[] A = new int[n];
	    for(int i = 0; i < n; i ++ ) A[i] = in.nextInt();
	    int[] B = A.clone();
	    Arrays.sort(B);
	    int ct = 1;
	    Map<Integer, Integer> dict = new HashMap<Integer, Integer>(n);
	    for(int v : B)
		    if(!dict.containsKey(v))
			    dict.put(v, ct++);

	    for(int i = 0; i < n; i ++)
		    A[i] = dict.get(A[i]);


	    long[] lessThan = get_less(A, n);
	    long[] moreThan = get_more(A, n);

	    int[] pre = new int[ct + 1];
	    Arrays.fill(pre, -1);
	    long ret = 0;
	    for(int i = 0; i < n; i ++) {
			if(pre[A[i]] == -1)
				pre[A[i]] = i;
	    }
	    for(int i = 0; i < n; i ++){
		    if(pre[A[i]] == i){
			    ret += lessThan[i] * moreThan[i];

		    }else{
			    ret += (lessThan[i] - lessThan[pre[A[i]]]) * moreThan[i];
		    }
	    }
	    out.printLine(ret);

    }

	private long[] get_more(int[] A, int n) {
		long[] ret = new long[n];
		int[] sum = new int[n + 1];
		boolean [] set = new boolean[n + 1];
		Arrays.fill(sum, 0);
		Arrays.fill(set, false);
		for(int i = n - 1; i >= 0; i --) {
			ret[i] = get_sum(sum, n, n) - get_sum(sum, n, A[i]);
			if(!set[A[i]]){
				set[A[i]] = true;
				get_add(sum, n, A[i] , 1);
			}
		}
		return ret;
	}

	private long[] get_less(int[] A, int n) {
		int[] sum = new int[n + 1];
		Arrays.fill(sum, 0);
		boolean [] set = new boolean[n + 1];
		Arrays.fill(set, false);
		long[] ret = new long[n];
		for(int i = 0; i < n; i ++) {
			 ret[i] = get_sum(sum, n, A[i] - 1);
			if(!set[A[i]]){
				set[A[i]] = true;
				get_add(sum, n, A[i], 1);
			}
		}
		return ret;
	}

	private void get_add(int[] sum, int n, int det, int val) {
		while(det <= n) {
			sum[det] += val;
			det += (det & (-det));
		}
	}

	private int get_sum(int[] sum, int n, int det) {
		int ret = 0;
		while(det > 0) {
			ret += sum[det];
			det -= (det & (-det));
		}
		return ret;
	}
}
