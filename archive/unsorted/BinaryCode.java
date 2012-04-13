/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

// Paste me into the FileEdit configuration dialog

public class BinaryCode {
   public String[] decode(String message) {
	   String[] ret = new String[2];
       ret[0] = go( message , '0' );
       ret[1] = go( message , '1' );
       return ret;
   }

    private String go(String message, char f) {
        String ret = ""+f ;

        for( int i = 0 ; i < message.length()-1 ; i ++ ){
            int d = message.charAt(i) - ret.charAt(i) - (i > 0 ? ret.charAt(i-1) - '0' : 0 ) ;
            if( d == 0 || d == 1 ) ret += (char)( d + '0');
            else return "NONE";
        }
        return message.charAt( message.length() - 1) == (ret.charAt( ret.length() - 1 ) + (ret.length() > 1 ? ret.charAt( ret.length() - 2 ) - '0' : 0 )) ? ret : "NONE";
    }
}

