/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import java.util.ArrayList;
import java.util.Arrays;

public class KingXMagicSpells {
   public double expectedNumber(int[] ducks, int[] spellOne, int[] spellTwo, int K) {
       ArrayList<Integer> order = new ArrayList<Integer>();
       int n = ducks.length ;
       boolean[] used = new boolean[ n ];
       int now = 0 ;
       do{
          used[now] = true;
          order.add( now );
          now = spellTwo[ now ];
       }while ( !used[now] );
       System.out.println( order.toString() );

       int m = order.size();
       double [][][] dp = new double[ K + 1 ][ m + 1][ m + 1];
       for( int i = 0 ; i < m ; i ++ )
           dp[0][0][i] = ducks[ order.get(i) ] ;

       boolean [][] can = new boolean[ K+1 ][ m+1 ] ;
       for( int i = 0 ; i < K+1 ; i ++ ) Arrays.fill(can[i], false);
       can[0][0] = true ;
       for( int i = 0 ; i < K ; i ++ ){
           for( int j = 0 ; j < m ; j ++ ) if( can[i][j] ){
               //first;
               can[i+1][j] = true;
               for( int k = 0 ; k < m ; k ++ )
                   dp[i+1][j][k] = dp[]
                    
                    
                    
               
           }
       }
       return 0;
   }
}

