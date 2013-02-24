/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class coin_on_the_table {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		char[][] table = new char[n][];
		int nx = 0, ny = 0;
		for(int i = 0; i < n; i ++) {
			table[i] = in.next().toCharArray();
			for(int j = 0; j < m; j ++)
				if(table[i][j] == '*'){
					nx = i;
					ny = j;
				}

		}
		int[][] dp = new int[n][m];
		for(int[] v : dp) Arrays.fill(v, Integer.MAX_VALUE);
		Set<Integer> now = new HashSet<Integer>();
		now.add(0);
		dp[0][0] = 0;
		for(int step = 0; step < k; step ++){
			Set<Integer> next =  new HashSet<Integer>();
			for(Integer ps : now){
				int row = ps / m;
				int col = ps % m;
				if(row > 0) update(dp, row - 1, col, row, col, table[row][col] == 'U' ? 0 : 1, next, m);
				if(row < n - 1) update(dp, row + 1, col, row, col, table[row][col] == 'D' ? 0 : 1, next, m);
				if(col > 0) update(dp, row , col - 1, row, col, table[row][col] == 'L' ? 0 : 1, next, m);
				if(col < m - 1) update(dp, row , col + 1, row, col, table[row][col] == 'R' ? 0 : 1, next, m);

			}
			now = next;
		}
		out.printLine(dp[nx][ny] == Integer.MAX_VALUE ? -1 : dp[nx][ny]);
	}

	private void update(int[][] dp, int nr, int nc, int row, int col, int v, Set<Integer> next, int m) {
		if(dp[nr][nc] > dp[row][col] + v){
			dp[nr][nc] = dp[row][col] + v;
			next.add(nr * m + nc);
		}
	}
}
