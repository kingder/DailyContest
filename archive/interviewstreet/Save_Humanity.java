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

import java.util.ArrayList;

public class Save_Humanity {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        String P = in.next();
        String V = in.next();
        
        if( P.length() < V.length() ){
            out.printLine();
            return;
        }
        int[] pre = Z_Pre_Init( out, (V+P).toCharArray() ) ;
        int[] post = Z_Pre_Init(out, (new StringBuffer(P+V).reverse()).toString().toCharArray());
        //IOUtils.printIntArrays( out, pre , post );

        ArrayList<Integer> idx = new ArrayList<Integer>();
        for( int i = 0 ; i <= P.length() - V.length() ; i ++ ){
              if( pre[i+V.length()] < V.length() ){
                  int left = V.length() - pre[i+V.length()] ;

                  int other = P.length() - i ;
                  if( post[other] >= left - 1)
                      idx.add( i );

              }else idx.add(i);
        }
        IOUtils.printArrays( out, idx.toArray() );
        
	}
    private int[] Z_Pre_Init(MyOutputWriter out,char[] str) {
        //str = new StringBuffer( str );
        int len = str.length;
        int[] F = new int[len];
        F[0] = len;
        int l = 0, r = 0;
        for (int k = 1; k < len; k++) {
            if (k > r) {
                F[k] = 0;
                while (F[k] < len - k + 1  && k + F[k] < len && str[F[k]] == str[k + F[k]]) F[k]++;
                if (F[k] > 0) {
                    l = k;
                    r = k + F[k] - 1;
                }
            } else {
                int tk = k - l;
                int t = r - k;
                if (F[tk] < t) F[k] = F[tk];
                else {
                    F[k] = 0;
                    while (F[k] < len - r  && str[r + F[k]] == str[t + F[k]]) F[k]++;
                    l = k;
                    r = r + F[k];
                    F[k] += t;
                }
            }
            //out.printLine( F[k] , l , r );
        }
        //IOUtils.printIntArrays(out,F);
        return F;
    }
}
