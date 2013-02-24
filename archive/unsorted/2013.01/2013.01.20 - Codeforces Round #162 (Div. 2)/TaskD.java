/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] goods = new int[n];
        for(int i = 0; i < n; i ++) goods[i] = in.nextInt();
        Arrays.sort(goods);
        int ret = 1;
        int[] maxStand = new int[200000];
        Arrays.fill(maxStand, 0);
        for(int i = 0; i < n; i ++) {
            int t = 1;
            int good = goods[i];
            for(int j = 2; j * j <= goods[i]; j ++) if(good % j == 0){
                while (good % j == 0) good /= j;
                t = (t < maxStand[j] + 1) ? maxStand[j] + 1 : t;
            }
            if(good > 1){
                t = (t < maxStand[good] + 1) ? maxStand[good] + 1 : t;
            }
            good = goods[i];
            for(int j = 2; j * j <= goods[i]; j ++) if(good % j == 0){
                while (good % j == 0) good /= j;
                maxStand[j] = (t < maxStand[j]) ? maxStand[j] : t;
            }
            if(good > 1){
                maxStand[good] = (t < maxStand[good]) ? maxStand[good] : t;
            }
            if(ret < t) ret = t;
        }
        out.printLine(ret);
	}
}
