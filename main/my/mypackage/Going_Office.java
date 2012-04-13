/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.graph.GraphUtils;
import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class Going_Office {
    int[][] graph;
    int[] dis_from_source;
    HashMap<Pair<Integer,Integer>,Integer> edge_map;
    HashSet<Integer> edge_in_path;
    int[] changed;
    int changedIndex = 0 ;
    int[] already_asked;

	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , m = in.nextInt() ;
        int[] from = new int[ m ] , to = new int[ m ] , length = new int[ m ];
        IOUtils.readIntArrays( in , from , to , length );

        edge_map = new HashMap<Pair<Integer, Integer>, Integer>();
        for( int i = 0 ; i < m ; i ++ ){
            edge_map.put(Pair.makePair(from[i], to[i]), i);
            edge_map.put( Pair.makePair( to[i] , from[i] ) , i );
        }

        int source = in.nextInt() , sink = in.nextInt() ;
        graph = GraphUtils.buildGraph(n, from, to, false);
        dis_from_source = Dijkstra(graph, from, to, length, n, source);

        IOUtils.printIntArrays( out, dis_from_source ,pre );
        edge_in_path = get_path(graph, from, to, source, sink );

        changed = new int[ n ] ;
        already_asked = new int[m];
        Arrays.fill( changed , 0 ) ;
        Arrays.fill( already_asked , -1 ) ;
        int Q = in.nextInt() ;

        while ( Q-- > 0){
            ++changedIndex;
            int distance = get_distance( in.nextInt() ,in.nextInt(),graph,from,to,length,n,source,sink );

            out.printLine( distance == Integer.MAX_VALUE ? "Infinity" : distance);
        }

	}

    private int get_distance( int u , int v, int[][] graph, int[] from, int[] to, int[] length, int n, int source, int sink) {
        int id = edge_map.get( Pair.makePair(u,v) ) ;

        if( already_asked[id] != -1 )
            return already_asked[id];

        if( !edge_in_path.contains(id) )
            return  already_asked[id] = dis_from_source[sink];

        int preserved = length[ id ] ;
        length[ id ] = Integer.MAX_VALUE;

        int ret = 0 ;

        if( dis_from_source[u] > dis_from_source[v] ){
            int t = u ; u = v; v = t;
        }




        length[ id ] = preserved ;
        return already_asked[id] = ret;

    }

    private HashSet<Integer> get_path(int[][] graph, int[] from, int[] to, int source,int sink) {
        HashSet<Integer> ret = new HashSet<Integer>();
        if( dis_from_source[sink] == Integer.MAX_VALUE ) return ret;
        while ( sink != source ){
            ret.add( pre[sink] );
            sink = GraphUtils.otherVertex( sink , from[pre[sink]] , to[pre[sink]]);
        }
        return ret;
    }

    static int[] pre ;
    public static int[] Dijkstra( int[][] graph , int[] from , int[] to ,int[] length , int n, int source){
        final int[] ret = new int[ n ];

        Arrays.fill(ret, Integer.MAX_VALUE);
        PriorityQueue<Integer> PQ = new PriorityQueue<Integer>( n , new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ret[o1] < ret[o2] ? -1 : ret[o1] == ret[o2] ? 0 : 1;
            }
        });
        pre = new int[ n ] ;
        ret[source] = 0 ;
        pre[source] = -1;
        PQ.offer( source );
        while ( !PQ.isEmpty() ){
            int u = PQ.poll();
            for( int i: graph[u] ){
                int v = GraphUtils.otherVertex(u, from[i], to[i]);
                if( ret[v] > ret[u] + length[i] ){
                    pre[v] = i ;
                    ret[v] = ret[u] + length[i] ;
                    PQ.offer( v );
                }
            }
        }
        return ret;
    }


}