/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ms414_one {
    int N, M;
    int[][]  r;
    int[] pre;
    boolean [] vis;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        N = in.nextInt();
        M = in.nextInt();
        out.printLine("Case #" + testNumber +":");
        r = new int[N][N];
        pre = new int[N];
        vis = new boolean[N];
        for(int[] v : r) Arrays.fill(v, 0);
        for(int i = 0; i < M; i ++) {
            int s = in.nextInt() - 1;
            int t = in.nextInt() - 1;
            int c = in.nextInt();
            r[s][t] += c;
            r[t][s] += c;
            int now = EK(0, N-1);
            if(now > 0) {
                out.printLine((i + 1) + " " + now);
            }
        }
    }

    int EK(int s, int t){
        int flow = 0;
        while (BFS(s, t)) {
            int d = Integer.MAX_VALUE;
            for(int i = t; i != s; i = pre[i])
                d = Math.min(d, r[pre[i]][i]);
            for(int i = t; i != s; i = pre[i]) {
                r[pre[i]][i] -= d;
                r[i][pre[i]] += d;
            }
            flow += d;
        }
        return flow;

    }
    boolean BFS(int s, int t) {
        Arrays.fill(vis, false);
        Queue<Integer> que = new LinkedList<Integer>();
        Arrays.fill(pre, -1);
        pre[s] = s;
        vis[s] = true;
        que.offer(s);

        while(!que.isEmpty()) {
            Integer v = que.poll();
            for(int i = 0; i < N; i ++){
                if(r[v][i] > 0 && !vis[i]){
                    pre[i] = v;
                    vis[i] = true;
                    if(i == t) return true;
                    que.offer(i);
                }
            }

        }
        return false;
    }

}
