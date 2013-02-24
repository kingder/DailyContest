/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
		int n = in.nextInt();
		int[] sz = new int[n];
		int sum = 0;
		for(int i = 0; i < n; i ++){
			sz[i] = in.nextInt();
			sum += sz[i];
		}
		Arrays.sort(sz);
		for(int i = 0; i < n/2 ; i ++){
			int t = sz[i];
			sz[i] = sz[n-i-1];
			sz[n-i-1] = t;
		}
		int m = in.nextInt();
		if(sum <= m){
			out.printLine(n);
			return;
		}
		double[][][] dp = new double[n+1][n+1][sum+1];

		double[] Fact = new double[n+1];
		Fact[0] = 1;
		for(int i = 1; i <= n; i ++) Fact[i] = Fact[i-1] * i;
		for(int i = 0; i <= n; i ++)
			for(int j = 0; j <= n; j ++)
				for(int k = 0; k <= sum; k ++)
					dp[i][j][k] = 0;
		dp[0][0][0] = 1;

		double ret = 0;
		for(int i = 0; i <= n; i ++)
			for(int choose = 0; choose <= n; choose ++)
				for(int len = 0; len <= sum; len ++) if(dp[i][choose][len] > 0){
					if(i > 0 && i <= n && sum - len <= m && sz[i-1] + sum - len > m){
						ret += (n - choose) * dp[i][choose][len] * Fact[n - choose] * Fact[choose] / Fact[n];
					}
					if(i + 1 <= n && choose + 1 <= n && len + sz[i] <= sum)
						dp[i + 1][choose + 1][len + sz[i]] += dp[i][choose][len];// choose current;
					if(i + 1 <= n)dp[i + 1][choose][len] += dp[i][choose][len];



				}
		out.printLine(ret);
	}
}
