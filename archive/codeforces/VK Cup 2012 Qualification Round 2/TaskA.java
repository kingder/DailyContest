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

import java.util.HashSet;

public class TaskA {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() , d = in.nextInt() ;
        String[] A = new String[ n ] ;
        String[] B = new String[ n ] ;
        int[] T = new int[ n ] ;
        HashSet<String> friends = new HashSet<String>();
        for( int i = 0 ; i < n ; i ++ ){
            A[i] = in.next() ;
            B[i] = in.next() ;
            T[i] = in.nextInt() ;
            for( int j = i - 1 ; j >= 0 ; j -- )
                if( A[j] .equals(B[i]) && B[j] .equals( A[i] ) && T[i] - T[j] > 0 && T[i] - T[j] <= d ){
                    if( !friends.contains( A[i] + " " + B[i] ) && !friends.contains( B[i] + " " + A[i])){
                        friends.add( A[i] + " " + B[i] );
                        break;
                    }
                }
        }
        out.printLine( friends.size() );
        for(String o : friends)out.printLine(o);
	}
}
