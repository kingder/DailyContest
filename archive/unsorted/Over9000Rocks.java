/*
 * Copyright (c) 2012.
 */

package my.mypackage;

// Paste me into the FileEdit configuration dialog

import net.kingder.utils.common.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Over9000Rocks {
   public int countPossibilities(int[] lowerBound, int[] upperBound) {
       int n = lowerBound.length ;
       int mask = 1 << n ;

       ArrayList<Pair<Integer,Integer>> AP = new ArrayList<Pair<Integer, Integer>>();
       for( int i = 0 ; i < mask ; i ++ ){
           int L = 0 , R = 0 ;
           for( int j = 0 ; j < n ; j ++ ) if( (i & ( 1 << j)) != 0){
               L += lowerBound[j] ;
               R += upperBound[j] ;
           }
           L = Math.max( 9001 , L ) ;
           if( L > R ) continue;
           AP.add( Pair.makePair( L , R ) );
       }
       if( AP.size() == 0 ) return 0 ;
       Collections.sort(AP, new Comparator<Pair<Integer, Integer>>() {
           // @Override
           public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
               if (o1.first != o2.first)
                   return o1.first - o2.first;
               return o1.second - o2.second;
           }
       });

       int ret = 0;
       int L = AP.get(0).first , R = AP.get(0).second;
       for( int i = 1 ; i < AP.size() ; i ++ ){
           if( AP.get(i).first <= R+1 ){
               R = Math.max( R, AP.get(i).second);
           }
           else{
               ret += R - L + 1;
               L = AP.get(i).first;
               R = AP.get(i).second;
           }
       }
       ret += R - L + 1 ;
       return ret;
   }


}

