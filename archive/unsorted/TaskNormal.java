package my.mypackage;

import com.jgoodies.looks.plastic.theme.DesertGreen;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TaskNormal {
    static final int MAXM = 26 ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int cases = in.nextInt();
        while( cases -- > 0 ){
            int wordNum = in.nextInt() ;
            ArrayList<HashSet<Integer>> Edge = new ArrayList<HashSet<Integer>>();
            for( int i = 0 ; i < MAXM ; i ++ ) Edge.add( new HashSet<Integer>());
            
            int[] din = new int[ MAXM ] , dout = new int[ MAXM ] ;
            boolean[] isMark = new boolean[ MAXM ];
            int first = -1 ;

            Arrays.fill( isMark , false ) ;
            for ( int i = 0 ; i < wordNum ; i ++ ){
                
                String buf = in.next() ;
                
                int a = buf.charAt(0) - 'a' ;
                int b = buf.charAt( buf.length()-1) - 'a' ;

                dout[ a ] ++ ; din[ b ] ++ ;
                isMark[a] = isMark[b] = true ;
                Edge.get( a ).add( b );
                Edge.get( b ).add( a );
                first = a ;
            }

            int x = 0 , y = 0 , z = 0 ;
            for( int i = 0 ; i < MAXM ; i ++ ){
                if( din[i] != dout[i] ){
                    if( din[i] == dout[i] + 1 )
                        x ++ ;
                    else if( dout[i] == din[i] + 1 )
                        y ++ ;
                    else
                        z ++ ;
                }
            }

            if( z == 0 && ( (y == 1 && x == 1) || y + x == 0 ) && allConnect( Edge ,  isMark , first ) ){
                out.printLine("Ordering is possible.");
            }else{
                out.printLine("The door cannot be opened.");
            }
        }
	}

    private boolean allConnect(ArrayList<HashSet<Integer>> edge, boolean [] isMark  ,int first) {
        dfs( edge , isMark , first );
        for( int i = 0 ; i < MAXM ; i ++ )
            if( isMark[i] )
                return false;
        return true;
    }

    private void dfs(ArrayList<HashSet<Integer>> edge, boolean[] mark, int first) {

        if( !mark[ first ] )return ;
        mark[first] = false;
        for( Integer o : edge.get( first ) )
            dfs( edge , mark , o );
    }
}
