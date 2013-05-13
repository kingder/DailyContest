/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class qq321_two {
    static final long MOD = 1000000007;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        long k = in.nextLong();
        long[] A = new long[n];
        k = powmod(k, t);
        for(int i = 0; i < n; i ++) A[i] = in.nextLong();
        for(int i = 0; i < n; i ++) A[i] = A[i] * k % MOD;

        for(int i = 0; i < n; i ++) {
            if(i != 0)
                out.print(" ");
            int tt = (i-t) % n;
            if(tt < 0) tt += n;
            out.print(A[tt]);
        }
        out.printLine();
    }

    private long powmod(long k, int t) {
        if(t == 0) return 1;
        long tp = powmod(k, t >> 1);
        tp = tp * tp % MOD;
        if((t & 1) == 1)
            tp = tp * k % MOD;
        return tp;
    }
}
