/*
 * Copyright (c) 2013.
 */

package my;

import net.egork.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Security {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int cases = in.nextInt();
	    for(int tc = 1; tc <= cases; tc ++) {
		    out.print("Case #" + tc + ": ");
		    int m = in.nextInt();
		    final char[] k1 = in.next().toCharArray();
		    final char[] k2 = in.next().toCharArray();
		    int n = k1.length;
		    assert (n % m == 0);
		    final int l = n / m;
		    final Integer[] order = new Integer[m];
		    for(int i = 0; i < m; i ++)
			    order[i] = i;
		    Arrays.sort(order, new Comparator<Integer>() {
			    @Override
			    public int compare(Integer o1, Integer o2) {
				    int s1 = o1 * l;
				    int s2 = o2 * l;
				    for (int i = 0; i < l; i++)
					    if (k2[s1 + i] != k2[s2 + i]) {
						    return k2[s1 + i] - k2[s2 + i];
					    }
				    return 0;
			    }
		    });
		    boolean [][] table = new boolean[m][m];
		    for(boolean [] v : table) Arrays.fill(v, false);
		    for(int i = 0; i < m; i ++) {
		        for(int j = 0; j < m; j ++) {
			        table[i][j] = true;
			        for(int k = 0; k < l; k ++)
				        if(!Match(k1[i * l + k], k2[j * l + k])){
					        table[i][j] = false;
					        break;
				        }
		        }
		    }

		    int[] match = new int[m];
		    int ret = go(match, table, m);
		    if (ret != m){
			    out.printLine("IMPOSSIBLE");
		    } else {
			    for(int i = 0; i < m; i ++) {
				    int t = match[i];
				    int s = order[i];
				    for(int k = 0; k < l; k ++){
					    if(k1[t * l + k] == '?'){
						    if(k2[s * l + k] == '?')
							    k1[t * l + k] = 'a';
						    else
							    k1[t * l + k] = k2[s * l + k];
					    }
				    }
			    }
			    out.printLine(String.valueOf(k1));
		    }
	    }
    }

	private int go(int[] match, boolean[][] table, int n) {
		Arrays.fill(match, -1);
		boolean [] used = new boolean[n];
		int ret = 0;
		for(int i = 0; i < n; i ++){
			Arrays.fill(used, false);
			if(gao(i, n, match, table, used))
				ret ++;
		}
		return ret;
	}

	private boolean gao(int p, int n, int[] match, boolean[][] table, boolean[] used) {
		for(int i = 0; i < n; i ++) if(table[p][i] && !used[i]) {
			used[i] = true;
			if(match[i] == -1 || gao(match[i], n, match, table, used)){
				match[i] = p;
				return true;
			}
		}
		return false;
	}

	private boolean Match(char a, char c) {
		return a == c || a == '?' || c == '?';
	}
}
