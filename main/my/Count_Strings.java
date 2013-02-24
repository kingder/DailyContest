/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Count_Strings {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    String[] area = new String[10];

    }
	public double getMinimum(int D, int[] L, int[] R)
	{
	 	int n = L.length;
		double ret = Double.MAX_VALUE;
		for(int i = 0; i < n; i ++){
			for(int multi = R[i]; multi >= 1; multi ++ ){
				boolean ok = true;
				for(int t = 0; t < n; t ++){
					int level = L[t] * multi / R[i];
					if(R[i] * (level + 1) < R[t] * multi){
						ok = false;
						break;
					}
				}
				if(ok){
					ret = Math.min(ret, (double)R[i] / multi);
				}
			}
		}
		return ret;
	}
}
