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

import java.util.Arrays;

public class DCEPC206 {
    
    static final int maxn = 1000000+10;
    long[] C = new long[ maxn ] ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        Arrays.fill( C , 0 ) ;
        long ret = 0 ;
        for( int i = 0 ; i < n ; i ++ ){
            int number = in.nextInt() ;
            add ( number , number );
            ret += sum(number - 1);
        }
        out.printLine( ret );
	}

    private long sum(int id) {
        long ret = 0 ;
        while (id > 0){
            ret  += C[id] ;
            id -= id & (-id);
        }
        ret += C[0];
        return ret;
    }

    private void add(int id, int v) {
        if( id == 0 ) {C[id] += v;return;}
        while ( id < maxn ){
            C[ id ] += v ;
            id += id & (-id) ;
        }
    }
}
