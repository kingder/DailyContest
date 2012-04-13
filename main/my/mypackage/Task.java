/*
 * Copyright (c) 2012.
 */

package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Task {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        out.printLine( "Hello World!");
        for( int i = 0 ; i < 100 ; i ++ )
            out.printLine( "Hello" + ": " + i );
	}
}
