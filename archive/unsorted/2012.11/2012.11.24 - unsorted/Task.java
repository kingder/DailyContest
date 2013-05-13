/*
 * Copyright (c) 2012.
 */

package my;

import net.egork.string.StringUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Task {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        String S = in.next();
        String T = in.next();
        int n = S.length();
        int typeOneZero = 0, typeZeroOne = 0;
        int mask = StringUtils.count(S, '?');
        int oneS = StringUtils.count(S, '1');
        int oneT = StringUtils.count(T, '1');
        for (int i = 0; i < n; i ++) if(S.charAt(i) != T.charAt(i)){
            if(S.charAt(i) == '1')
                typeOneZero ++ ;
            else if(S.charAt(i) == '0')
                typeZeroOne ++ ;
        }
        out.printLine("Case " + testNumber + ": " + (oneS > oneT ? -1 : Math.max(typeOneZero, typeZeroOne) + mask) );
	}
}
