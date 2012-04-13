/*
 * Copyright (c) 2012.
 */

package my.mypackage;

// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class SafeReturn {
   public int minRisk(int N, String[] streets) {
       int n = streets.length;
       int[][] g = new int[ n ][ n ];
       for( int i = 0 ; i < n ; i ++ ){
           for( int j = 0 ; j < n ; j ++ )
               g[i][j] = streets[i].charAt(j) == '-' ? Integer.MAX_VALUE / 10 : streets[i].charAt(j) - '0' ;
           g[i][i] = 0;
       }
       for( int k = 0 ; k < n ; k ++ )
           for( int i = 0 ; i < n ; i ++ )
               for( int j = 0 ; j < n ; j ++ )
                   g[i][j] = Math.min( g[i][j] , g[i][k] + g[k][j]) ;

       boolean [][] map = new boolean[ N+1 ][ N+1 ] ;
       for( int i = 1 ; i <= N ; i ++ )
           for( int j = 1 ; j <= N ; j ++ ){
               if( i != j && g[0][i] == g[0][j] + g[j][i] )
                   map[j][i] = true;
           }

       return N - maxmatch( map , N ) ;
   }

    private int maxmatch(boolean[][] map, int n) {
        boolean [] used = new boolean[ n + 1];
        int[] match = new int[ n + 1];

        Arrays.fill( match , -1 );
        int ret = 0 ;
        for( int i = 1 ; i <= n ; i ++ ){
            Arrays.fill( used , false );
            if( dfs(i ,map , n , used , match) ) ret ++ ;
        }
        return ret;
    }

    private boolean dfs(int u , boolean[][] map, int n, boolean[] used, int[] match) {

        for ( int i = 1 ; i <= n ; i ++ )if( !used[i] && map[u][i]){
            used[i] = true;
            if( match[i] == -1 || dfs( match[i] , map,n , used,match ) ){
                match[i] = u ;
                return true;
            }
        }
        return false;
    }


}

