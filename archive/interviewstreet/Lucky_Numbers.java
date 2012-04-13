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

public class Lucky_Numbers {
    static final int max_bit = 19 ;
    static final int max_digit_sum = 9 * 18 + 1;
    static final int max_squre_digit_sum = 9 * 9 * 18 + 1;

    static long[][][] sum = new long[max_bit][max_digit_sum][max_squre_digit_sum];
    static long[][][] left_sum = new long[ max_bit ][ max_digit_sum][ max_squre_digit_sum];
    static boolean [] is_prime = new boolean[ max_squre_digit_sum ];
    static long[] bit_sum = new long[ max_bit ];
    static int tot = 231 , max1 , max2  ;
    static int[] prime = new int[ tot ] ;
    static {
        Arrays.fill(is_prime, true);
        int ct = 0 ;
        is_prime[0] = is_prime[1] = false;
        for( int i = 2 ; i < max_squre_digit_sum ; i ++ )if( is_prime[i] ) {
            for( int j = i + i ; j < max_squre_digit_sum ; j += i )
                is_prime[j] = false;
            prime[ct ++ ] = i ;
            if( i < max_digit_sum )max1 = Math.max( max1 , i ) ;
            max2 = Math.max( max2 , i ) ;
        }
        //System.out.println( ct + " " +  max1  + " " + max2  );
        sum[0][0][0] = 1;
        for( int i = 0 ; prime[i] <= max1 ;  i ++ )
            for( int j = 0 ; j < tot && prime[j] <= max2 ; j ++ ){
                left_sum[0][prime[i]][prime[j]] += 1;
            }

        for( int i = 0 ; i < max_bit - 1 ; i ++ ){
            for( int next = 0 ; next < 10 ; next ++ ){

                for( int j = 0 ; j + next < max_digit_sum ; j ++ )
                    for( int k = 0 ; k + next * next < max_squre_digit_sum ; k ++ ){
                        sum[ i + 1][ j + next ][ k + next * next ] += sum[i][j][k] ;
                        if( next > 0 && is_prime[j+next] && is_prime[k+next*next] )
                            bit_sum[i+1] += sum[i][j][k] ;
                    }

                for( int j = next ; j < max_digit_sum ; j ++ )
                    for( int k = next * next ; k < max_squre_digit_sum ; k ++ ) {
                        left_sum[i+1][j-next][k-next*next] += left_sum[i][j][k];
                    }
            }
        }
    }
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        long A = in.nextLong() ;
        long B = in.nextLong() ;
//        int l = 0 ;
//        for( int i = 0 ; i < 100 ; i ++ ){
//            int a = i / 10 % 10 ;
//            int b = i % 10 ;
//            if( is_prime[a+b] && is_prime[ a*a +b*b ]) l ++ ;
//        }
//        out.printLine( l );
        //out.printLine( go(out,B));
        //out.printLine( go(out,A-1));
        out.printLine( go( out,B) - go(out,A-1));
    }
    long go( MyOutputWriter out, long N ){
        if( N == 0 ) return 0 ;
        long ret = 0 ;
        boolean first = true;
        int pre_digit_sum = 0 , pre_sdigit_sum = 0 ;
        for( int i = 19 ; i > 0 ; i -- ){
            int bit = get_bit(N, i - 1) ;
            //if( bit == 0 ) continue;
            int least ;
            if( bit != 0 && first ){
                least = 1 ;
                first = false;
                for( int j = 1 ; j < i ; j ++ )
                    ret += bit_sum[ j ] ;
            }else{
                least = 0 ;
            }

            for( int nbit = least ; nbit < bit ; nbit ++ ){
                int digit_sum = pre_digit_sum + nbit;
                int sdigit_sum = pre_sdigit_sum + nbit * nbit ;
                ret += left_sum[ i - 1 ][ digit_sum ][ sdigit_sum ];
//                for( int np = 0 ; prime[np] <= max1 ; np ++ )if( prime[np] >= digit_sum )
//                    for( int nnp = 0 ; nnp < tot ; nnp ++ ) if( prime[nnp] >= sdigit_sum )
//                          ret += sum[ i - 1 ][ prime[np] - digit_sum ][ prime[nnp] - sdigit_sum ];
            }
            pre_digit_sum += bit;
            pre_sdigit_sum += bit*bit;
            //out.printLine( i , bit , ret );
        }
        if( is_prime[pre_digit_sum] && is_prime[pre_sdigit_sum] )
            ret += 1;
        return ret;
    }

    private int get_bit(long n, int m) {
        for( int i = 0 ; i < m ; i ++ ) n /= 10 ;
        return (int)(n % 10 );
    }
}
