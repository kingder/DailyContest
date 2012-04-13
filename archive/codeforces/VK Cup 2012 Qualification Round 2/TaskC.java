package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskC {
    
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int k = in.nextInt() ;
        String t = in.next() ;
        String name = t;
        for( int i = 0 ; i < k - 1 ; i ++ ) name = name + t;
        char[] st = name.toCharArray();
        //out.printLine( name );
        int[][] occ = new int[ 26 ][ k + 1 ];
        int[] nt = new int[ 26 ];
        Arrays.fill( nt , 0 );
        for( int i = 0 ; i < t.length() ; i ++ )
            nt[ t.charAt( i ) -'a' ] ++ ;
        
        for( int i = 0 ; i < 26 ; i ++ ){
            Arrays.fill( occ[i] , 0  );
            for( int j = 0 ; j < k ; j ++ )
                Add( occ[i], j+1, nt[i],k );
        }

        int m = in.nextInt() ;
        while ( m-- > 0 ){
            int c = in.nextInt() ;
            int a = in.next().charAt(0) - 'a' ;
            int p = find( occ[a], c ,  k);
            int dest = c - Sum( occ[a] , p-1  );
            //out.printLine( c , a , p , dest);
            for( int j = (p-1)*t.length(); j < st.length; j ++ ){
                if( st[j] == a + 'a' ){
                    if( --dest == 0 ){
                       st[j] = '@';
                        break;
                    }
                }
            }
            Add( occ[a] , p , -1 , k );
        }
        for( char o: st)if(o!= '@')out.print(o);
        out.printLine();
	}

    private static int lastbit( int x ){
        return x&(-x);
    }

    private void Add(int[] ints, int p, int v , int k) {
        while ( p <= k ){
            ints[p] += v ;
            p += lastbit( p ) ;
        }
    }
    private int Sum(int[] ints , int p ){
        int ret = 0;
        while ( p > 0 ){
            ret += ints[p];
            p -= lastbit( p );
        }
        return ret ;
    }
    
    private int find(int[] occ, int c, int k) {
        int lo = 1 , hi = k , ret = -1 ;
        while( lo <= hi ){
            int md = ( lo + hi ) / 2 ;
            if( Sum( occ , md ) >= c ){ 
                hi = md - 1;
                ret = md ;
            }else{
                lo = md + 1;
            }
        }
        return ret ;
    }
}
