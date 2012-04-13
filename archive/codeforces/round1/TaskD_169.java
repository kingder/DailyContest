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

import java.util.ArrayList;
import java.util.Collections;

public class TaskD {

    private class Edge implements Comparable<Edge>{
        int u , v , cost , id ;

        private Edge(int u, int v, int t, int id) {
            this.u = u;
            this.v = v;
            this.cost = t;
            this.id = id;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost ;
        }
    }
    private class Graph{
        public ArrayList<Edge>[] G ;
        public ArrayList<Edge> E;
        public int n, m  ;
        public int[] depth, active,up;
        private Graph( int n , int m ) {
            this.n = n ;
            this.m = m ;
            G = new ArrayList[ n ];
            depth = new int[ n ];
            active = new int[ n ];
            up = new int[ n ];
            for( int i = 0 ; i < n ; i ++ )
                G[i] = new ArrayList<Edge>();
            E =  new ArrayList<Edge>();

        }
        public void add( Edge e ){
            E.add( e ) ;
        }

    }
    public class Uset{
        int[] P ;
        int n ;

        public Uset( int n ) {
           P = new int[ n ] ;
           init( n ); 
        }

        private void init(int n) {
            for (int i = 0; i < n ; i++) {
                 P[i] = i ;
            }
        }
        
        public int getp( int x ){
            return P[x] = P[x] == x ? x : getp( P[x] );
        }
        
        public void join( int a , int b){
            a = getp(a);
            b = getp( b );
            P[a] = b ;
        }
    }
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt(), m = in.nextInt() ;
        Graph G = new Graph( n , m );
        for( int i = 0 ; i < m ; i ++ ){
            int a = in.nextInt(), b = in.nextInt() , c = in.nextInt();
            G.add( new Edge( a - 1 , b - 1 , c , i ) );
        }
        Collections.sort(G.E);

        int[] ans = new int[ m ] ;
        Uset myset = new Uset( n ) ;
        int activeVer = 1 ;
        ArrayList<Integer> curActive = new ArrayList<Integer>();
        for( int st = 0 ; st < m ; ){
            activeVer ++ ;
            curActive.clear();
            int ed = st ;
            while ( ed < m && G.E.get(ed).cost == G.E.get(st).cost ) ed ++ ;
            for( int i = st ; i < ed ; i ++ ){
                int u = myset.getp( G.E.get( i ).u );
                int v = myset.getp( G.E.get( i ).v );
                if( u == v ){
                    ans[i] = 1 ;
                }else{
                    //if( active[u] != activeVer ){
                    //    curActive.add( u ) ;
                    //}

                }

            }
        }
    }
}
