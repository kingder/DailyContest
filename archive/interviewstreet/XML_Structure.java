/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.Arrays.ArrayUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Comparator;
import java.util.HashMap;

public class XML_Structure {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        char[] xml = in.next().toCharArray() ;
        int i = 0 ;
        int lev = 0 ;
        HashMap<String,Integer> minL = new HashMap<String, Integer>();
        while ( i < xml.length ){
            String tagName = getTag( xml , i ) ;
            i = i + tagName.length() ;
            //out.printLine( tagName );
            tagName = tagName.substring( 1 , tagName.length() - 1);
            //out.printLine( tagName );
            int s = 1 ;
            if( tagName.charAt(0) == '/' ){
                s = -1 ;
                tagName = tagName.substring(1);
            }
            minL.put( tagName , lev+1 );
            lev += s;
        }

        final String[] names = new String[ minL.size() ];
        final int[] values = new int[ minL.size() ];
        int id = 0 ;
        for( String s : minL.keySet() ){
            names[id] = s ;
            values[id] = minL.get( s );
            id ++ ;
        }

        Integer[] order = ArrayUtils.order( values.length , new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return values[o1] - values[o2] ;
            }
        });

        for( int j = 0 ; j < order.length ; j ++ ){
            if( j > 0 ) out.print( " ");
            out.print( names[ order[j] ] );
        }
        out.printLine();
	}

    private String getTag(char[] xml, int s) {
        String ret = "" ;
        do{
            ret += xml[s];
        }while ( xml[s++] != '>' );
        return ret;
    }
}
