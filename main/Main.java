import java.io.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author lwc626
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInputReader in = new MyInputReader(inputStream);
		MyOutputWriter out = new MyOutputWriter(outputStream);
		Going_Office solver = new Going_Office();
		solver.solve(1, in, out);
		out.close();
	}
}

class Going_Office {
    int[][] graph;
    int[] dis_from_source;
    HashMap<Pair<Integer,Integer>,Integer> edge_map;
    HashSet<Integer> edge_in_path;
    int[] changed;
    int[] changed_distance;
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

        //IOUtils.printIntArrays( out, dis_from_source ,pre, order,rorder );
        edge_in_path = get_path(graph, from, to, source, sink );

        changed = new int[ n ] ;
        changed_distance = new int[ n ] ;
        already_asked = new int[m];
        Arrays.fill( changed , 0 ) ;
        Arrays.fill( already_asked , -1 ) ;
        int Q = in.nextInt() ;

        while ( Q-- > 0){
            ++changedIndex;
            int distance = get_distance( out, in.nextInt() ,in.nextInt(),graph,from,to,length,n,source,sink );
            out.printLine( distance == Integer.MAX_VALUE ? "Infinity" : distance);
        }

    }

    private int get_distance( MyOutputWriter out, int u , int v, int[][] graph, int[] from, int[] to, int[] length, int n, int source, int sink) {
        int id = edge_map.get( Pair.makePair(u,v) ) ;

        if( already_asked[id] != -1 )
            return already_asked[id];

        if( !edge_in_path.contains(id) )
            return  already_asked[id] = dis_from_source[sink];

        int preserved = length[ id ] ;
        length[ id ] = Integer.MAX_VALUE;

        if( rorder[u] > rorder[v] ){
            int t = u ; u = v ; v = t;
        }
        int ret = dijkstraAlgorithm( out, graph , from , to , length, n, v,sink);

        length[ id ] = preserved ;
        return already_asked[id] = ret;

    }
    public int dijkstraAlgorithm( MyOutputWriter out,int[][] graph , int[] from , int[] to ,int[] length , int n, int source,int sink) {
        PriorityQueue<Pair<Integer,Integer>> PQ = new PriorityQueue<Pair<Integer, Integer>>( );
        //out.printLine( source , sink );
        for( int i = rorder[source] ; i < n ; i ++ ){
            int u = order[i] ;
            changed_distance[u] = Integer.MAX_VALUE;
            for( int id : graph[u] ){
                int v = GraphUtils.otherVertex( u , from[id] , to[id] );
                if( rorder[v] >= rorder[source] || length[id] == Integer.MAX_VALUE || dis_from_source[v] == Integer.MAX_VALUE ) continue;
                if( changed_distance[u] > dis_from_source[v] + length[id] ){
                    changed_distance[u] = dis_from_source[v] + length[id] ;
                }
            }
            if( changed_distance[u] != Integer.MAX_VALUE )
                PQ.offer( Pair.makePair( changed_distance[u] , u ) );
        }

        //IOUtils.printIntArrays( out,changed_distance );
        while ( !PQ.isEmpty() ){
            int u = PQ.poll().second;
            if( changed[u] == changedIndex ) continue;
            changed[u] = changedIndex ;
            if( u == sink ) return changed_distance[u] ;
            for( int i: graph[u] ){
                int v = GraphUtils.otherVertex(u, from[i], to[i]);
                if( rorder[v] < rorder[source] || length[i] == Integer.MAX_VALUE ) continue;
                if( length[i] != Integer.MAX_VALUE && changed_distance[v] > changed_distance[u] + length[i] ){
                    changed_distance[v] = changed_distance[u] + length[i] ;
                    PQ.offer( Pair.makePair( changed_distance[v] , v ) );
                }
            }
        }

        return Integer.MAX_VALUE;
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
    static int[] order;
    static int[] rorder;
    public static int[] Dijkstra( int[][] graph , int[] from , int[] to ,int[] length , int n, int source ){
        int[] distance = new int[ n ];
        boolean[] processed = new boolean[ n ] ;
        order =new int[n];
        rorder = new int[ n ];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill( processed , false );
        PriorityQueue<Pair<Integer,Integer>> PQ = new PriorityQueue<Pair<Integer, Integer>>( );
        pre = new int[ n ] ;
        distance[source] = 0 ;
        pre[source] = -1;
        PQ.offer( Pair.makePair( 0 , source) );
        int orderindex = 0 ;

        while ( !PQ.isEmpty() ){
            int u = PQ.poll().second;
            if( processed[u] ) continue;
            processed[u] = true;
            order[orderindex] = u ;
            rorder[u] = orderindex++;
            for( int i: graph[u] ){
                int v = GraphUtils.otherVertex(u, from[i], to[i]);
                if( distance[v] > distance[u] + length[i] ){
                    pre[v] = i ;
                    distance[v] = distance[u] + length[i] ;
                    PQ.offer( Pair.makePair( distance[v] , v ) );
                }
            }
        }
        return distance;
    }


}

class MyInputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public MyInputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    }

class MyOutputWriter {
    private final PrintWriter writer;

    public MyOutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public MyOutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

class IOUtils {
    public static void readIntArrays( MyInputReader in,int[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextInt();
    }
    }

class Pair<U, V> implements Comparable<Pair<U, V>> {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <U, V> Pair<U, V> makePair(U first, V second) {
        return new Pair<U, V>(first, second);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair pair = (Pair) o;
        return !(first != null ? !first.equals(pair.first) : pair.first != null) &&
                !(second != null ? !second.equals(pair.second) : pair.second != null);
    }

    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        return 31 * result + (second != null ? second.hashCode() : 0);
    }

    public String toString() {
        return "(" + first + "," + second + ")";
    }
    public int compareTo(Pair<U, V> o) {
        int value = ((Comparable<U>)first).compareTo(o.first);
        if (value != 0)
            return value;
        return ((Comparable<V>)second).compareTo(o.second);
    }

}

class GraphUtils {



    public static int[][] buildGraph(int vertexCount, int[] from, int[] to ,boolean isDirected ) {
        int edgeCount = from.length;
        int[] degree = new int[vertexCount];
        for (int i = 0; i < edgeCount; i++) {
            degree[from[i]]++;
            if(!isDirected)degree[to[i]]++;
        }
        int[][] graph = new int[vertexCount][];
        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new int[degree[i]];
        }
        for (int i = 0; i < edgeCount; i++) {
            graph[from[i]][--degree[from[i]]] = i;
            if(!isDirected)graph[to[i]][--degree[to[i]]] = i;
        }
        return graph;
    }

    public static int otherVertex(int vertex, int from, int to) {
        return from + to - vertex;
    }

    }

