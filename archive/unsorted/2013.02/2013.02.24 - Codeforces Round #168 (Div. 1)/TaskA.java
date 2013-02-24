/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class TaskA {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] array = new long[n];
        for(int i = 0; i < n; i ++)
            array[i] = in.nextInt();
        if(k == 1){
            out.printLine(n);
            return;
        }
        Arrays.sort(array);
        boolean [] take = new boolean[n];
        Arrays.fill(take, true);
        int j = 0;
        int ret = n;
        for(int i = 0; i < n; i ++) {
            while(j < i && array[j] * k < array[i]) j ++;
            if(j < i && take[j] && array[j] * k == array[i]) {
                take[i] = false;
                --ret;
            }
        }
        out.print(ret);
    }
}
