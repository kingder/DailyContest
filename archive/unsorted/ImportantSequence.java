/*
 * Copyright (c) 2012.
 */

package my;

// Paste me into the FileEdit configuration dialog

public class ImportantSequence {
   public int getCount(int[] B, String operators) {

       int n = B.length ;
       long[] AA = new long[ n + 1];
       long[] BB = new long[ n + 1];
       AA[0] = 1 ;
       BB[0] = 0 ;
       for( int i = 1 ; i <= n ; i ++ ){
           if( operators.charAt(i-1) == '-' ){
              AA[i] = AA[i-1];
              BB[i] = BB[i-1] - B[i-1] ;
           }else{
               AA[i] = -AA[i-1];
               BB[i] = B[i-1] - BB[i-1];
           }
       }

       long L = 1 , R = Long.MAX_VALUE ;
       for( int i = 0 ; i < n + 1 ; i ++ ){

           if( AA[i] > 0 ) {
               R = Math.min( R , Long.MAX_VALUE);
               L = Math.max( L , 1 - BB[i] );
           }else{

               R = Math.min( R , BB[i]-1);
               L = Math.max( L , 1 );
           }
       }
       if( L > R ) return 0;
       if( R == Long.MAX_VALUE ) return -1;
       return (int)(R-L+1);

   }
}

