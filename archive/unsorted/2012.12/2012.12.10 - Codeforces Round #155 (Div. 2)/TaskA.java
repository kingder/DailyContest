/*
 * Copyright (c) 2012.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    static final int MAXN = 5000+10 ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        List<List<Integer>> numbers = new ArrayList<List<Integer>>();

        for (int i = 0; i < MAXN; i ++)
            numbers.add(new ArrayList<Integer>());
        for (int i = 0; i < n + n; i ++) {
           numbers.get(in.nextInt()).add(i+1);
        }

        for (int i = 0; i < MAXN; i ++)
            if ((numbers.get(i).size() & 1) == 1) {
                out.printLine("-1");
                return;
            }

        for (int i = 0; i < MAXN; i ++) {

            List<Integer> plist = numbers.get(i);
            for (int j = 0; j < plist.size(); j += 2)
                out.printLine(plist.get(j) + " " + plist.get(j+1));
        }

	}
}
