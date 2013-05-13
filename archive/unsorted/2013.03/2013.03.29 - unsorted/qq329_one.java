/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.text.DecimalFormat;

public class qq329_one {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int X = in.nextInt();
        int Q = in.nextInt();
        while (Q -- > 0) {
            int type = in.nextInt();
            int two = in.nextInt();
            DecimalFormat df = new DecimalFormat("0.00");
            if(type == 1) {
                out.printLine(df.format(two * (60.0 - X)));
            } else if(type == 2) {
                out.printLine(df.format(two * 60.0 * 60 / (60 - X)));
            } else {
                out.printLine(df.format(12.0 * 3600 * two * 60 /  X));
            }
        }
    }
}
