/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class TaskA {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        String S = in.next();
        String T = in.next();
        int now = 0;
        for(int i = 0; i < T.length(); i ++)
            if(S.charAt(now) == T.charAt(i)) now ++;
        out.printLine(now + 1);
	}
}
