/*
 * Copyright (c) 2012.
 */

package my.mypackage;

import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Insertion_Sort {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] A = new int[ n ] ;
        IOUtils.readIntArrays(in, A);
        out.printLine( reverse_pair(out,A, 0 , n-1) );
	}

    private long reverse_pair(MyOutputWriter out,int[] a , int l , int r) {
        if( l == r ) return 0 ;
        int m = ( l + r ) / 2 ;
        long ret = reverse_pair( out,a , l , m ) ;
        ret += reverse_pair( out,a , m+1 , r ) ;

        //out.printLine( l , r );
        //IOUtils.printIntArrays(out , a );
        int [] tmp = new int[ r - l + 1];
        int nl = l , nr = m+1 , n = 0;
        while ( nl <= m && nr <= r ){
            if( a[nl] <= a[nr] ){
                tmp[n++] = a[nl++] ;
                ret += nr - m -1 ;
            }else{
                tmp[n++] = a[nr++] ;
            }
        }
        while ( nl <= m ) { tmp[ n ++ ] = a[ nl ++ ]; ret += nr-m-1;}
        while ( nr <= r ) tmp[ n ++ ] = a[ nr ++ ];
        System.arraycopy( tmp , 0 , a , l, n );
        return ret;
    }
}
