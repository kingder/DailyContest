/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class TaskA {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt(), k = in.nextInt();
        long ret = Long.MIN_VALUE;
        for(int i = 0; i < n; i ++) {
            long f = in.nextLong();
            long t = in.nextLong();
            ret = Math.max(ret, f - Math.max(t - k, 0));
        }
        out.printLine(ret);
    }
}
