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

import java.util.ArrayList;
import java.util.HashMap;

public class XML_String {

    HashMap<String,Integer> my ;
    ArrayList<String> names ;
    ArrayList<String> tags;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        my = new HashMap<String, Integer>();
        int level = 0 ;
        String xml = "";
        names = new ArrayList<String>();
        while ( true ){
            xml = in.next() ;
            if( xml.charAt(0) != '<' ){
                 my.put( xml , level++ );
                names.add( xml );
            }else break;
        }
        tags = new ArrayList<String>();
        int id = 0 ;
        while ( id < xml.length() ){
            String tagName = getTag( xml , id ) ;
            id = id + tagName.length() ;
            tagName = tagName.substring( 1 , tagName.length() - 1);
            tags.add( tagName ) ;
        }
        out.printLine( go(0, tags.size()-1,0 ) );
	}

    private String go(int l, int r , int level ) {
        if( level == names.size() ) return "" ;

        String ret = "" ;
        ret += "<"+names.get(level)+">";

        if( l <= r ){
            if( isLevel( tags.get(l) , level ) ) l++;
            if( isLevel( tags.get(r) , level ) ) r--;
            if( l > r ) ret += go( l , r , level+1 ) ;
            for( int i = l ; i <= r ;  ){
                int j = isLevel( tags.get(i) ,level+1) && tags.get(i).charAt(0) != '/' ? i+1:i ;

                while ( j <= r && !isLevel( tags.get(j) , level+1 ) ){
                    j++;
                }
                if( j <= r && tags.get(j).charAt(0) == '/' ) j ++ ;
                ret += go( i , j-1 , level+1 );
                i = j ;
            }
        }else ret += go( l , r , level+1 );
        ret += "</"+names.get(level)+">";
        return ret;
    }

    private boolean isLevel(String s, int level) {
        if( s.charAt(0) != '/' ) return my.get(s) == level;
        else return my.get( s.substring(1) ) == level;
    }

    private String getTag(String xml, int s) {
        String ret = "" ;
        do{
            ret += xml.charAt(s);
        }while ( xml.charAt(s++) != '>' );
        return ret;
    }

}
