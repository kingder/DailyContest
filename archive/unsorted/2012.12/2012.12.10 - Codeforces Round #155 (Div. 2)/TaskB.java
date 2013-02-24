/*
 * Copyright (c) 2012.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskB {
    static final int MAXN = 1000;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();

        int[] peoples = new int[MAXN];
        Arrays.fill(peoples, 0);
        for (int i = 0; i < n; i ++) {
            int m = in.nextInt(), d = in.nextInt(), p = in.nextInt(), t = in.nextInt();

            int last = get_days(m, d);

            for (int j = 0; j < t; j ++) {
                //if(last - 1 - j >= 0 && last - j - 1 < MAXN)
                    peoples[last - 1 - j] += p;
            }
        }

        int ret = 0;
        for (int j = 0; j < MAXN; j ++)
            ret = Math.max(ret, peoples[j]);

        out.printLine(ret);

    }

    private int get_days(int m, int d) {

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        for (int j = 0; j < m - 1; j ++){
            //if (j < months.length)
                days += months[j];
        }

        return days + d + 200 ;
    }
}