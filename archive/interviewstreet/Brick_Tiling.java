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

public class Brick_Tiling {
    static final int mod = 1000000007 ;
    int[] wall;
    int n , m , limit , flimit ;
    int [] pre , now  ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        n = in.nextInt() ;
        m = in.nextInt() ;

        wall = new int[n]  ;
        
        for( int i = 0 ; i < n ; i ++ ){
            char[] bits = in.next().toCharArray();
            wall[i] = 0 ;
            for( int j = 0 ; j < m ; j ++ ){
                wall[i] = (wall[i] << 1) + ( bits[j] == '#' ? 1 : 0 );
            }
        }

        int ret;
        if( m == 1 || n == 1 ){
            ret = 1;
            for( int i = 0 ; i < n ; i ++ ) if( wall[i] != Flip(m)-1){
                ret = 0 ;
            }
            out.printLine( ret );
            return;
        }
        shift[0] = Flip( m + m + m - 2) | Flip( m + m - 2 ) + Flip( m - 2 ) + Flip( m - 1);
        shift[1] = Flip( m + m + m - 1) | Flip( m + m - 1 ) + Flip( m - 1 ) + Flip( m - 2);
        shift[2] = Flip( m + m + m - 1) | Flip( m + m + m - 2 ) + Flip( m + m - 2 ) + Flip( m - 2);
        shift[3] = Flip( m + m + m - 1) | Flip( m + m + m - 2 ) + Flip( m + m - 1 ) + Flip( m - 1);
        shift[4] = Flip( m + m + m - 3) | Flip( m + m - 1 ) + Flip( m + m - 2 ) + Flip( m + m - 3);
        shift[5] = Flip( m + m + m - 1) | Flip( m + m - 1 ) + Flip( m + m - 2 ) + Flip( m + m - 3);
        shift[6] = Flip( m + m + m - 1) | Flip( m + m + m - 2 ) + Flip( m + m + m - 3 ) + Flip( m + m - 3);
        shift[7] = Flip( m + m + m - 1) | Flip( m + m + m - 2 ) + Flip( m + m + m - 3 ) + Flip( m + m - 1);
        limit = Flip( m + m  );
        flimit = Flip( m ) ;
        pre = new int[ limit ];
        now = new int[ limit ];
        
        pre[ (wall[0] << m) | wall[1] ] = 1 ;
        for( int i = 0 ; i < n - 1 ; i ++ ){
            Arrays.fill( now , 0 );
            for( int k = 0 ; k < limit ; k ++ )if( pre[k] != 0 ){
                search( (k << m) | ( i + 2 >= n ? (Flip(m)-1) : wall[i+2] ) , k )   ;
            }
            pre = Arrays.copyOf( now , now.length);
        }
        out.printLine( pre[ limit - 1] );

	}

    static  int[] shift = new int[ 8 ];
    private void search(int state, int k ) {
          if( (state >> (m+m)) == flimit - 1 ){
              int ns = state & ( limit - 1 ) ;
              now[ ns ] = (now[ns] + pre[k]) % mod;
              return;
          }
        int pos  ;
        for( pos = m+m+m-1 ;  ((state>>pos)&1) != 0 ; pos -- ) ;
        
        if( pos < m+m+m-1 ){
            if( (state & ( shift[0] >> (m + m + m - 2 - pos) )) ==  0 )
                search( state | ( shift[0] >> (m + m + m - 2 - pos) ),k );
           
        }
        if( pos > m + m  ){
            for( int i = 1 ; i <= 3; i ++ ){
                if( (state & ( shift[i] >> (m + m + m - 1 - pos) )) ==  0 )
                    search( state | ( shift[i] >> (m + m + m - 1 - pos) ),k );
            }
        }
        if( pos < m+m+m-2 ){
            if( (state & ( shift[4] >> (m + m + m - 3 - pos) )) ==  0 )
                search( state | ( shift[4] >> (m + m + m - 3 - pos) ),k );

        }
        if( pos > m + m + 1  ){
            for( int i = 5 ; i <= 7; i ++ ){
                if( (state & ( shift[i] >> (m + m + m - 1 - pos) )) ==  0 )
                    search( state | ( shift[i] >> (m + m + m - 1 - pos) ),k );
            }
        }
    }

    private int Flip(int x) {
        return 1 << x;
    }
}
