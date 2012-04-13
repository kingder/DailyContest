/*
 * Copyright (c) 2012.
 */

package my.mypackage;

// Paste me into the FileEdit configuration dialog

import java.util.Arrays;

public class RandomColoring {
   int mb , mg , mr ;
   public double getProbability(int N, int maxR, int maxG, int maxB, int startR, int startG, int startB, int d1, int d2) {

       mr = maxR ; mg = maxG ;mb = maxB;
       int size = maxB * maxG * maxR ;
       double [] pre = new double[ size ];
       double [] now = new double[ size ];

       int[][][] ways = new int[ mr ][ mg ][ mb ];
       -- d1;
       for( int i = 0 ; i < mr ; i ++)for( int j = 0 ; j < mg ; j ++ )for( int k = 0 ; k < mb ; k ++ ){
           ways[i][j][k] = get_l( i , mr , d2 )*  get_l( j , mg , d2 ) * get_l( k , mb , d2 );
           if( d1 >= 0 )
               ways[i][j][k] -= get_l( i , mr , d1 )*  get_l( j , mg , d1 ) * get_l( k , mb , d1 );

       }
       pre[ code(startR,startG,startB) ] = 1;
       double [][][] sum = new double[ maxR][maxG][maxB];
       for( int nn = 0 ; nn < N-1 ; nn ++ ){
           Arrays.fill( now , 0 );
           for( int i = 0 ; i < maxR ; i ++ ){
               for( int j = 0 ; j < maxG ; j ++ )
                   for( int k = 0 ; k < maxB ; k ++ ){
                       double g = 0 ;
                       if( ways[i][j][k] > 0 ) g = pre[ code(i,j,k) ] / ways[i][j][k];
                       sum[i][j][k] = g  + get_sum_cube( sum , i , j , k);
                   }
           }
           for( int i = 0 ; i < size; i ++ ){

               int color = i ;
               int b = color % maxB ; color /= maxB ;
               int g = color % maxG ; color /= maxG ;
               int r = color;
               now[i] +=  get_cube_sum( sum , r-d2,g-d2,b-d2,r+d2,g+d2,b+d2);
               if(d1>=0)
                   now[i] -=  get_cube_sum( sum , r-d1,g-d1,b-d1,r+d1,g+d1,b+d1);
           }
           double [] t = pre ; pre = now; now = t;
       }

       double ret = 0 , ret1 = 0 ,ret2 = 0 ;
       for( int i = 0 ; i < size ; i ++ ){
           int color = i ;

           int b = color % maxB ; color /= maxB ;
           int g = color % maxG ; color /= maxG ;
           int r = color;
           if( Math.abs( b - startB ) <= d2 && Math.abs( g - startG ) <= d2 && Math.abs( r - startR ) <= d2){
            if ( Math.abs( b - startB ) > d1 || Math.abs( g - startG ) > d1 || Math.abs( r - startR ) > d1 )
               ret2 += pre[i];
           }
           if( Math.abs( b - startB ) <= d2 && Math.abs( g - startG ) <= d2 && Math.abs( r - startR ) <= d2  && ( Math.abs( b - startB ) > d1 || Math.abs( g - startG ) > d1 || Math.abs( r - startR ) > d1 ))
               ret1 += pre[i];
           else ret += pre[i];
       }
       //System.out.println( ret1 + " " + ret + " " + ret2 + " " + (1 - ret2) );
       return ret;

   }

    private double get_cube_sum(double[][][] sum, int lx, int ly, int lz, int rx, int ry, int rz) {
        double ret = 0;
        lx = Math.max( 0 , lx );
        ly = Math.max( 0 , ly );
        lz = Math.max( 0 , lz );
        rx = Math.min( mr-1,  rx );
        ry = Math.min( mg-1 , ry );
        rz = Math.min( mb-1 , rz );
        if( lx > rx ) return 0;
        if( ly > ry ) return 0;
        if( lz > rz ) return 0;
        --lx;--ly;--lz;
        ret += sum[rx][ry][rz];
        if( lx >= 0 ) ret -= sum[lx][ry][rz];
        if( ly >= 0 ) ret -= sum[rx][ly][rz];
        if( lz >= 0 ) ret -= sum[rx][ry][lz];
        if( lx >= 0 && ly>=0 ) ret += sum[lx][ly][rz];
        if( ly >= 0&& lz >=0 ) ret += sum[rx][ly][lz];
        if( lz >= 0 && lx >=0 ) ret += sum[lx][ry][lz];
        if( lx >= 0 && ly >= 0 && lz >= 0)
            ret -= sum[lx][ly][lz];
        return ret;
    }

    private double get_sum_cube(double[][][] sum, int i, int j, int k) {
        double ret = 0 ;
        if( i > 0 && j > 0 && k > 0 )ret += sum[i-1][j-1][k-1] ;
        if( i > 0 && j > 0 ) ret -= sum[i-1][j-1][k];
        if( i > 0 && k > 0 ) ret -= sum[i-1][j][k-1];
        if( j > 0 && k > 0 ) ret -= sum[i][j-1][k-1];
        if( i > 0 ) ret += sum[i-1][j][k];
        if( j > 0 ) ret += sum[i][j-1][k];
        if( k > 0 ) ret += sum[i][j][k-1];
        return ret;
    }

    private int get_l(int b, int maxB, int d1) {
        if( d1 < 0 ) return 0;
        return   Math.min( maxB-1 , d1+b)-Math.max(0,b-d1)+1;
    }


    private int code(int r, int g, int b){
        return r * mg * mb + g * mb + b;
    }


}

