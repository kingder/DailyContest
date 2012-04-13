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

import java.text.DecimalFormat;
import java.util.Arrays;

public class Connect_the_country {
    static {
        for( int n = 2 ; n <= 30 ; n ++ ){
            System.out.println( n + ": " + new DecimalFormat("0.0000").format( n * Math.log( n ) / 1.8 )  + " " + new DecimalFormat("0.0000").format( 1.25 * n * Math.log10(n) ) );
        }
    }
    double ret ;
    boolean [][] edge;
    class Uset{
        int[] p ;
        int n;
        Uset( Uset a ){
            p = Arrays.copyOf( a.p , a.p.length);
            n = a.n ;
        }
        Uset( int n ){
            p = new int[ n ] ;
            this.n = n ;
            for( int i = 0 ; i < n ; i ++ ) p[i] = i ;
        }
        int f( int x ){ return x == p[x] ? x : (p[x] = f( p[x] ));}
        void a( int a ,int b){ a=f(a);b=f(b);if(a!=b){p[f(b)] = f(a);n--;} }
    }
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {

        int n = in.nextInt() ;
        out.printLine( new DecimalFormat("0").format( n * ( Math.log( n ) ) / Math.sqrt(3))  );
//        for( int i = 2 ; i <= 30 ; i ++ ){
////            edge = new boolean[i][i];
////            for( boolean [] O : edge) Arrays.fill(O, false);
////            ret = 0 ;
////            Uset set = new Uset( i );
////            go(i, set, 0 , 1);
//            out.printLine( i *(Math.log( i) ) / 2 );
//        }
	}

    private void go(int n , Uset set , int dep , double now) {
        //System.out.println( set.n + " " + now );
        if(set.n == 1){
            //System.out.println( dep + " " + now );
             ret += dep*now;
            return;
        }
        int tot = 0 , u = 0 , d = 0 ;
        for( int i = 0 ; i < n ; i ++ )for( int j = i+1 ; j < n ; j ++ )if( !edge[i][j] ){
            if( set.f(i) == set.f(j) ) u ++ ;
            else d ++ ;
            tot ++;
        }
        boolean f = false;
        for( int i = 0 ; i < n && !f ; i ++ ){
            for( int j = i+1 ; j < n ; j ++ )if( !edge[i][j]){
                if( set.f(i) == set.f(j) ){
                    edge[i][j] = true ;
                    Uset tmp = new Uset( set );
                    go(  n , tmp , dep+1 , now * u / tot );
                    //edge[i][j] = false;
                    f = true;
                    break;
                }
            }
        }
        f = false;
        for( int i = 0 ; i < n && !f ; i ++ ){
            for( int j = i+1 ; j < n ; j ++ )if( !edge[i][j]){
                if( set.f(i) != set.f(j) ){
                    edge[i][j] = true ;
                    Uset tmp = new Uset( set );
                    tmp.a( i , j );
                    go(  n , tmp , dep+1 , now * d / tot );
                    //edge[i][j] = false;
                    f = true;
                    break;
                }
            }
        }
    }
}
