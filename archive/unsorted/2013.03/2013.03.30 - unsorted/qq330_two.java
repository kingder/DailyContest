/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class qq330_two {
    static long[] power = new long[20];
    static {
        power[0] = 1;
        for(int i = 1; i < 20; i ++)
            power[i] = power[i - 1] * 7;
    }
    int get_p(long t, int a) {
        return (int)(t / power[a] % 7);
    }
    void gao(int a, int b, int c, long t, Set<Long> Q, long[] g, int[] p) {
        int x = Math.min(get_p(t, a), 2);
        t -= x * power[a];
        t += g[p[b]];
        t += g[p[c]];
        t += power[b] + power[c];
        long tt = t;
        for(int i = 0; i < p.length + 1; i ++){
            if(tt % 7 >= 5) return ;
            tt /= 7;
        }
        Q.add(t);
    }
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        long[] gang = new long[m];
        int[] p = new int[n];
        for(int i = 0; i < n; i ++) {
            int t = in.nextInt();
            gang[t] += power[i];
            p[i] = t;
        }
        long init = 0;
        for(int i = 0; i < n; i ++) {
            int t = in.nextInt();
            init += (t - 1) * power[i];
        }
        Set<Long> Q = new HashSet<Long>();
        Set<Long> P = new HashSet<Long>();
        Q.add(init);
        int ret = k;
        boolean ok = false;
        for(int i = 0; i < k; i ++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            P.clear();
            for(long top : Q) {
                //for(int t = 0; t < n; t ++)
                 //   System.out.print(get_p(top, t) + " ");
                //System.out.println();
                gao(a, b, c, top, P, gang, p);
                gao(b, a, c, top, P, gang, p);
                gao(c, a, b, top, P, gang, p);
            }
            if(!ok && P.isEmpty()) {
                ret = i;
                ok = true;
            }
            Set<Long> t = Q;
            Q = P;
            P = t;
        }
        out.printLine("Case #" + testNumber + ": " + ret);

    }
}
