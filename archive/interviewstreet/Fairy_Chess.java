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

public class Fairy_Chess {
    static final int mod =   1000000007;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        int M = in.nextInt() ;
        int S = in.nextInt() ;

        int sx = 0 , sy = 0 ;
        char[][] board = new char[ N ][ ] ;

        for( int i = 0 ; i < N ; i ++ ){
            board[i] = in.next().toCharArray();
            for( int j = 0 ; j < N ; j ++ )
                if( board[i][j] == 'L' ){
                    sx = i ;
                    sy = j ;
                }
        }
        int NN = 3*N + 1;
        int [][] pre = new int[ NN][ NN];
        int [][] now = new int[ NN][ NN];

        pre[ sx-sy+N ][ sx+sy+N ] = 1 ;
        for( int i = 0 ; i < M ; i ++ ){
            for( int[] o : now )Arrays.fill(o, 0);
            for( int x = 1 ; x < NN ; x ++)
                for( int y = 1 ; y < NN ; y ++ ){
                    pre[x][y] += pre[x-1][y];
                    if( pre[x][y] >= mod ) pre[x][y] -= mod;
                    pre[x][y] += pre[x][y-1] ;
                    if( pre[x][y] >= mod ) pre[x][y] -= mod;
                    pre[x][y] -= pre[x-1][y-1];
                    if( pre[x][y] < 0 ) pre[x][y] += mod;
                    //pre[x][y] = ( ( ( (pre[x][y] + pre[x-1][y]) % mod + pre[x][y-1] ) % mod - pre[x-1][y-1] ) % mod + mod) % mod;
                }
            for( int x = 0 ; x < N ; x ++ )
                for( int y = 0 ; y < N ; y ++ )if( board[x][y] != 'P' ){
                    int lx = Math.max( 1 , x - y - S + N);
                    int ly = Math.max( 1, x + y - S + N);
                    int rx = Math.min( NN - 1, x - y + S + N);
                    int ry = Math.min( NN - 1, x + y + S + N);
                    sx = x - y + N ;
                    sy = x + y + N ;
                    //now[x-y+N][x+y+N] += ( ( ( (pre[rx][ry] - pre[lx-1][ry] ) % mod - pre[rx][ly-1] ) % mod + pre[lx-1][ly-1]) % mod + mod ) % mod;
                    //now[x-y+N][x+y+N] %= mod ;
//                    if( sx >= NN || sy >= NN ){
//                        System.out.println( x + " " + y + " " + N );
//                    }
                    now[sx][sy] += pre[rx][ry] ; if( now[sx][sy] >= mod ) now[sx][sy] -= mod;
                    now[sx][sy] -= pre[lx-1][ry] ; if( now[sx][sy] < 0 ) now[sx][sy] += mod;
                    now[sx][sy] -= pre[rx][ly-1] ; if( now[sx][sy] < 0 ) now[sx][sy] += mod;
                    now[sx][sy] += pre[lx-1][ly-1] ; if( now[sx][sy] >= mod ) now[sx][sy] -= mod;

                }

            for( int j = 0 ; j < NN ; j ++ )
                for( int k = 0 ; k < NN ; k ++ )
                    pre[j][k] = now[j][k] ;
        }

        int ret = 0 ;
        for( int i = 0 ; i < NN; i ++)
            for( int j = 0 ; j < NN; j ++ ){
                ret += pre[i][j] ;
                if( ret > mod ) ret -= mod;
            }
        out.printLine( ret );

    }
}
