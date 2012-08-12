/*
 * Copyright (c) 2012.
 */

package my;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Google_CodeJam {
    char[] trans = new char[]{'y','h','e','s','o','c','v','x','d','u','i','g','l','b','k','r','z','t','n','w','j','p','f','m','a','q'};
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int pretest = in.nextInt();
        for (int tc = 0; tc < pretest; tc++) {
            out.print("Case #"+(tc+1)+": ");
            String inbuffer = in.readLine();
            for( int i = 0 ; i < inbuffer.length() ; i ++ ){
                if( Character.isAlphabetic( inbuffer.charAt(i)) )
                    out.print( trans[ inbuffer.charAt(i) -'a' ] );
                else
                    out.print( inbuffer.charAt(i) );
            }
            out.printLine();
        }
	}
}
