/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class TaskD {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , k = in.nextInt() ;
        HashMap<Integer,ArrayList<Integer>> Edge = new HashMap<Integer, ArrayList<Integer>>();
        for( int i = 0 ; i < n ; i ++ ) Edge.put( i , new ArrayList<Integer>());
        for( int i = 0 ; i < n-1 ; i ++ ){
            int a = in.nextInt() , b = in.nextInt();
            --a ; --b;
            Edge.get(a).add( b);
            Edge.get(b).add( a );
        }
        long [][] dp = new long[ n+1][k+1] ;

        int[] Q = new int[ n ] ;
        int[] P = new int[ n ] ;
        boolean[] mark = new boolean[ n ];
        Arrays.fill(mark,false);
        int f = 0 , t = 0 ;
        Q[t++] = 0 ; mark[0] = true;
        while ( f < t){
            int now = Q[f++] ;
            for( int i = 0 ; i < Edge.get(now).size() ; i ++ ){
                int v = Edge.get(now) .get( i );
                if( mark[v] ) continue;
                mark[v] = true;
                Q[t++] = v;
                P[v] = now;
            }
        }
        long ret = 0 ; --t;
        while ( t >= 0 ){
            int now = Q[t--];
            dp[now][0] = 1;
            for( int j = 0 ; j < Edge.get(now).size() ; j ++ ){
                int v = Edge.get(now) .get( j );
                if( v == P[now] ) continue;
                for( int s = 1 ; s <= k ; s ++ ){
                    if(s<k)ret += dp[now][s] * dp[v][k-s-1];
                    dp[now][s] += dp[v][s-1];
                }
            }
            ret += dp[now][k];
        }
        out.printLine(ret);

	}

}
