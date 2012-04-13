/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.Arrays.ArrayUtils;
import net.kingder.utils.graph.GraphUtils;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class Kingdom_Connectivity {

    static final int mod = 1000000000;

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt(), M = in.nextInt();
        int[] from = new int[M], to = new int[M];
        IOUtils.readIntArrays(in, from, to);
        ArrayUtils.decreaseByOne(from, to);
        int[][] graph = GraphUtils.buildSimpleGraph(N, from, to, true);
        getDAG(out, graph);
        //IOUtils.printIntArrays( out,color );

        int[] belongCnt = new int[bcnt + 1];
        for (int i = 0; i < N; i++)
            belongCnt[color[i]]++;

        int[] dp = new int[bcnt + 1];
        Arrays.fill(dp, 0);
        HashMap<Integer, ArrayList<Integer>> G = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= bcnt; i++) G.put(i, new ArrayList<Integer>());
        int[] degree = new int[bcnt + 1];
        Arrays.fill( degree , 0 );

        for (int i = 0; i < M; i++) {
            from[i] = color[from[i]];
            to[i] = color[to[i]];
            if (from[i] != to[i]) {
                G.get(from[i]).add(to[i]);
            }else dp[ from[i] ] = -1;
        }

        
        Queue<Integer> Q = new LinkedList<Integer>();

        Q.offer( color[0] );
        boolean [] mark = new boolean[ bcnt + 1];
        Arrays.fill( mark , false ) ;
        mark[ color[0] ] = true;
        while (!Q.isEmpty() ){
            int now = Q.poll();
            for (int v : G.get(now)) {
               ++degree[v];
                if( !mark[v] ){
                    Q.offer( v );
                    mark[v] = true;
                }
            }
        }

        if( dp[color[0] ] == 0 )
            dp[color[0]] = 1;

        Q.offer(color[0]);

        while (!Q.isEmpty()) {
            int now = Q.poll();
            for (int v : G.get(now)) {
                if (dp[now] == -1 || dp[v] == -1 )
                    dp[v] = -1;
                else
                    dp[v] = (dp[v] + dp[now]) % mod;
                if (--degree[v] == 0)
                    Q.offer(v);
            }
        }
        //out.printLine( dp[ color[N-1] ] );
        out.printLine(dp[color[N - 1]] != -1 ? dp[color[N - 1]] : "INFINITE PATHS");

    }

    static int dfsIndex, stackTop, bcnt;
    static int[] color, minDfsNumber, dfsNumber, stack;
    static boolean[] inStack;

    public static int[] getDAG(MyOutputWriter out, int[][] graph) {

        int vertexCount = graph.length;
        color = new int[vertexCount];
        minDfsNumber = new int[vertexCount];
        dfsNumber = new int[vertexCount];
        stack = new int[vertexCount + 1];
        inStack = new boolean[vertexCount];

        dfsIndex = 0;
        stackTop = 0;
        bcnt = 0;

        Arrays.fill(color, -1);
        Arrays.fill(dfsNumber, 0);
        for (int i = 0; i < vertexCount; i++)
            if (color[i] == -1) {
                tarjan(graph, i);
            }
        //System.out.println( dfsIndex +" " + stackTop +" "+ bcnt);
        //IOUtils.printIntArrays( out,dfsNumber ,minDfsNumber );
        return color;
    }

    public static void tarjan(int[][] graph, int u) {
        dfsNumber[u] = minDfsNumber[u] = ++dfsIndex;
        stack[++stackTop] = u;
        inStack[u] = true;
        for (int v : graph[u]) {
            if (dfsNumber[v] == 0) {
                tarjan(graph, v);
                minDfsNumber[u] = Math.min(minDfsNumber[u], minDfsNumber[v]);
            } else if (inStack[v]) {
                minDfsNumber[u] = Math.min(minDfsNumber[u], dfsNumber[v]);
            }
        }
        if (dfsNumber[u] == minDfsNumber[u]) {
            int v;
            ++bcnt;
            do {
                v = stack[stackTop--];
                inStack[v] = false;
                color[v] = bcnt;
            } while (v != u);
        }
    }
}
