/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.HashSet;
import java.util.Set;

public class qq321_one {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        Set<Integer> floorsm = new HashSet<Integer>();
        int maxfloor = 0;

        for(int i = 0; i < n; i ++){
            int d = in.nextInt();
            floorsm.add(d);
            maxfloor = Math.max(maxfloor, d);
        }
        out.printLine(maxfloor * 10 + 5 * floorsm.size() + n);
    }
}
