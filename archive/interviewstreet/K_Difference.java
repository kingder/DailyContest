package my.mypackage;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.HashSet;

public class K_Difference {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , k = in.nextInt() ;
        HashSet<Integer> myset = new HashSet<Integer>();
        int[] A = new int[ n ] ;
        for( int i = 0 ; i < n ; i ++ ){
            A[i] = in.nextInt() ;
            myset.add( A[i] );
        }
        int ret = 0 ;
        for( int i = 0 ; i < n ; i ++ )if( myset.contains( A[i] - k ) ) ret ++ ;
        out.printLine(ret);
	}
}
