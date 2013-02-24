/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class TaskB {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] heights = new int[n];
        for(int i = 0; i < n; i ++) heights[i] = in.nextInt();
        int ret = 0;
        int nowAt = 0;
        for(int i = 0; i < n; i ++){
            ret += heights[i] - nowAt + 1;
            if(i > 0) ret += 1;
            if(i < n - 1){
                nowAt = heights[i+1] > heights[i] ? heights[i] : heights[i+1];
                if(heights[i+1] < heights[i])
                    ret += heights[i] - heights[i+1];

            }
        }
        out.printLine(ret);
	}
}
