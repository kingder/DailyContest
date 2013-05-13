/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class qq330_one {
    static final int MAXN = 100 + 10;
    static final long MOD = 1000000007;
    static long [] D = new long[MAXN];
    static {
        D[0] = 0;
        D[1] = 0;
        D[2] = 1;
        D[3] = 2;
        for(int i = 4; i < MAXN; i ++) {
            D[i] = (i - 1) * (D[i-1] + D[i-2]) % MOD;
        }
    }
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        out.printLine(D[n]);

    }
}
