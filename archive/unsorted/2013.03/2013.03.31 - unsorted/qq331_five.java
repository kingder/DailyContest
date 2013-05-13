/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class qq331_five {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        final int[] rabbit = new int[N];
        final int[] archer = new int[M];
        final int[] coin = new int[M];
        for(int i = 0; i < N; i ++)
            rabbit[i] = in.nextInt();
        for(int i = 0; i < M; i ++)
            archer[i] = in.nextInt();
        for(int i = 0; i < M; i ++)
            coin[i] = in.nextInt();

        Arrays.sort(rabbit);
        Integer[] order = new Integer[M];
        for(int i = 0; i < M; i ++)
            order[i] = i;
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return archer[o1] - archer[o2];
            }
        });

        //IOUtils.printArrays(out, order);
        PriorityQueue<Integer> PQ = new PriorityQueue<Integer>(M, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return coin[o1] - coin[o2];
            }
        });
        boolean ok = true;
        int hi = M-1;
        long ret = 0;
        for(int i = N-1; i >= 0; i --) {
            while (hi >= 0 && archer[order[hi]] >= rabbit[i])
                PQ.offer(order[hi --]);
            if(PQ.isEmpty()){
                ok = false;
                break;
            }
            int top = PQ.poll();

            //out.printLine(top + " " + PQ.peek());
            ret += coin[top];
        }
        out.printLine(ok ? ret : "No");
    }
}
