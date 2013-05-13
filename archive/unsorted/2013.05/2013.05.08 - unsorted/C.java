/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class C {
    static final int MAX = 10000 + 10;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt();
        int[] cover = new int[MAX];
        Arrays.fill(cover, 0);
        int max_end = 0;
        for(int i = 0; i < N; i ++) {
            int a = in.nextInt();
            int b = in.nextInt();
            cover[a] ++;
            cover[b + 1] --;
            max_end = Math.max(max_end, b);
        }

        int sum = 0;
        int start = -1;
        int ret = 0;
        int state = 0;
        for(int i = 0; i <= max_end + 2; i ++) {
            sum += cover[i];
            if(sum > 0) {
                if(state == 0) {
                    start = i;
                    state = 1;
                }
            }
            if(sum == 0) {
                if(state == 1) {
                    ret = Math.max(ret, i - start - 1);
                    start = -1;
                    state = 0;
                }
            }
        }
        out.printLine(ret);
    }
}
