/*
 * Copyright (c) 2012.
 */

package my.mypackage;

import net.kingder.utils.graph.GraphUtils;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Matrix {
    int[] from , to , length ;
    int[][] graph;
    boolean [] isblack;
    long[][] dp;
    final long MAX_VALUE = 1L<<50;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , m = in.nextInt() ;
        from = new int[n-1] ;
        to = new int[n-1] ;
        length = new int[n-1] ;
        IOUtils.readIntArrays(in, from, to, length) ;
        graph = GraphUtils.buildGraph( n , from  , to , false );
        isblack = new boolean[n];
        Arrays.fill( isblack , false );
        for( int i = 0 ; i < m ; i ++ ) isblack[ in.nextInt() ] = true;
        dp = new long[n][2]; // 1 for black, 0 for white;
        for(long[] o:dp) Arrays.fill(o,-1);
        //out.printLine( MAX_VALUE );
        out.printLine( Math.min( black( out, 0 , -1 ) , white(out,0,-1)) );
        for( long[] o : dp ) IOUtils.printLongArrays( out , o );
	}

    private long black(MyOutputWriter out, int u, int p) {
        if( dp[u][1] != -1 )
            return dp[u][1];

        if( isblack[u] ) {
            dp[u][1] = 0;

            for( int id : graph[u] ){
                int v = GraphUtils.otherVertex( u , from[id] , to[id] ) ;
                if(v==p) continue;
                dp[u][1] += Math.min( white( out , v , u ) , (black(out,v,u) == MAX_VALUE ? MAX_VALUE :black(out,v,u) + length[id] ) ) ;
            }
        }else{
            long sum = 0 ;
            dp[u][1] = MAX_VALUE;
            for( int id : graph[u] ){
                int v = GraphUtils.otherVertex( u , from[id] , to[id] ) ;
                if(v==p) continue;
                sum += Math.min( white(out,v,u) , (black(out,v,u) == MAX_VALUE ? MAX_VALUE :black(out,v,u) + length[id] ));
            }
            for( int id : graph[u] ){

                int v = GraphUtils.otherVertex( u , from[id] , to[id] ) ;
                if(v==p) continue;
                if( sum + black(out,v,u) - Math.min( white(out,v,u) , (black(out,v,u) == MAX_VALUE ? MAX_VALUE :black(out,v,u) + length[id] )) < MAX_VALUE){
                    dp[u][1] = Math.min( dp[u][1] , sum + black(out,v,u) - Math.min( white(out,v,u) , (black(out,v,u) == MAX_VALUE ? MAX_VALUE :black(out,v,u) + length[id] )));
                }
            }

        }
        return dp[u][1];
    }

    private long white(MyOutputWriter out, int u, int p) {
        if( dp[u][0] != -1 )
            return dp[u][0];

        if( isblack[u] ) {
            dp[u][0] = MAX_VALUE;
        }else{
            dp[u][0] = 0 ;
            for( int id : graph[u] ){
                int v = GraphUtils.otherVertex( u , from[id] , to[id] ) ;
                if(v==p) continue;
                dp[u][0] += Math.min( white( out , v , u ) , (black(out,v,u) == MAX_VALUE ? MAX_VALUE :black(out,v,u) + length[id] )) ;
            }
        }
        return dp[u][0];
    }
}
