/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class qq321_five {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] mark = new int[24 * 60 + 10];
        Arrays.fill(mark, 0);
        for(int i = 0; i < n; i ++) {
            int from = go(in.next());
            int to = go(in.next());
            //out.printLine(from+" " +to);
            mark[from]++;
            mark[to] --;
        }
        int ret = 0;
        int t = 0;
        for(int i = 0; i < 24 * 60; i ++) {
            t += mark[i];
            if(t == 0)
                ret ++;
        }
        out.printLine(ret);
    }

    private int go(String next) {
        return Integer.parseInt(next.substring(0,2)) * 60 + Integer.parseInt(next.substring(3));
    }
}
