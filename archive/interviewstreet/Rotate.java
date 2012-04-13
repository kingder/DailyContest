/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Rotate {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[][] matrix = new int[ n ][ n ] ;
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                matrix[i][j] = in.nextInt() ;
        }

        int[][] ret = new int[ n ] [ n ];
        int[][] dx = new int[][]{{0,1,0,-1},{1,0,-1,0}} ;
        int[][] dy = new int[][]{{1,0,-1,0},{0,1,0,-1}} ;

        for( int i = 0 ; i < (n+1) / 2 ; i ++ ){
            int len = n - i * 2;
            if( len == 1 ){
                ret[i][i] = matrix[i][i] ;
                continue;
            }
            int sx = i ;
            int sy = i ;

            int tx = sx , ty = sy ;
            int px = tx , py = ty ;
            int d = 0 ;
            do{

                if( !inRange(tx + dx[i & 1][d], ty + dy[i & 1][d], i, i + len - 1) ) d = (d + 1)%4;

                tx += dx[ i & 1 ][ d ] ;
                ty += dy[ i & 1 ][ d ] ;

               // out.printLine( px , py , tx , ty );
                ret[ tx ][ ty ] = matrix[ px ][ py ] ;
                px = tx ; py = ty ;
            }while ( !(px == sx && py == sy) );

        }
        for( int[] o : ret ) IOUtils.printIntArrays( out, o );
	}

    private boolean inRange(int x, int y, int a, int b) {
        return x >= a && x <= b && y >= a && y <= b;
    }
}
