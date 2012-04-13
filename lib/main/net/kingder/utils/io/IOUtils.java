/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.kingder.utils.io;

/**
 * Created by IntelliJ IDEA.
 * User: Weichao Luo
 * Date: 12-3-12
 * Time: ÏÂÎç5:15
 * To change this template use File | Settings | File Templates.
 */
public class IOUtils {
    public static void readIntArrays( MyInputReader in,int[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextInt();
    }
    public static void readLongArrays( MyInputReader in,long[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextLong();
    }
    public static void printIntArrays( MyOutputWriter out, int[]... arrays){
        boolean f ;
        for( int[] array: arrays){
            f = true ;
            for( int o: array ){
                if( !f ) out.print(" ");
                else f = false;
                out.print( o );
            }
            out.printLine();
        }
    }
    public static void printLongArrays( MyOutputWriter out, long[]... arrays){
        boolean f ;
        for( long[] array: arrays){
            f = true ;
            for( long o: array ){
                if( !f ) out.print(" ");
                else f = false;
                out.print( o );
            }
            out.printLine();
        }
    }
    public static<T> void printArrays( MyOutputWriter out, T[]... arrays){
        boolean f ;
        for( T[] array: arrays){
            f = true ;
            for( T o: array ){
                if( !f ) out.print(" ");
                else f = false;
                out.print( o );
            }
            out.printLine();
        }
    }
}
