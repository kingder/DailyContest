/*
 * Copyright (c) 2012.
 */

package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ants {
    static int MAXDIS = 10000 ;
    static int MAXTIME = 1000000006;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] dis = new int[n] ;
        int[] dir = new int[n] ;
        for( int i = 0 ; i < n ; i ++ ) dis[i] = in.nextInt() ;
        Arrays.sort(dis);
        for( int i = 0 ; i < n ; i ++ )
            for( int j = 0 ; j < n ; j ++ )
                if( i != j && Math.abs(dis[i] - dis[j]) == 1 ){
                    from[i].add(j) ;

                }


	}

    private long go(int n, int[] dir, int[] dis) {

    }
}
