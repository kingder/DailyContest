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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Random;

public class Generate_test {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out)throws IOException{

        //MyOutputWriter out1 = new MyOutputWriter( new FileOutputStream("in.in"));
        PrintWriter pw = new PrintWriter( new OutputStreamWriter( new FileOutputStream( "in.in")),true);
        Random my = new Random( System.currentTimeMillis() );
        final int limit = 10000 ;
        
        int n = my.nextInt( 1000 ) + 100 ;
        for( int i = 0 ; i < n ; i ++ ){
            out.printLine( my.nextInt( limit ) + 1 , my.nextInt( limit ) + 1, my.nextInt( limit ) + 1);
        }
        int  m = my.nextInt( limit ) + 100 ;
        for( int i = 0 ; i < m ; i ++ ){
            int c = my.nextInt( 2 ) ;

            int a = my.nextInt(n)  , b = my.nextInt(n);
            if( a > b ) {
                int t = a ; a= b; b=t;
            }
            
            if( c == 0 ){
                out.printLine( c , a , b );
            }
            else out.printLine( c , a , b , my.nextInt( limit ) + 1);
        }
	}
}
