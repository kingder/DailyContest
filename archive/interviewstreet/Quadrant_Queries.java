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
import sun.org.mozilla.javascript.internal.ast.Yield;

import java.util.Arrays;

public class Quadrant_Queries {


    int[] X , Y ;
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt() ;
        X = new int[ N ] ;
        Y = new int[ N ] ;
        for( int i = 0 ; i < N ;i ++ ){
            X[i] = in.nextInt() ;
            Y[i] = in.nextInt() ;
        }

        seg_node Root = build_tree( 1 , N );
        
        int M = in.nextInt() ;
        //System.out.println(M);
        for( int i = 0 ; i < M ; i ++ ){
            char c = in.next().charAt(0);
            if( c == 'X' || c == 'Y' ){
                modify( Root , 1 , N , in.nextInt() , in.nextInt() , c == 'X' );
            }else{
                IOUtils.printIntArrays( out, query( Root, 1 , N , in.nextInt() , in.nextInt() ) );
            }
        }
	}

    private int[] query(seg_node root, int l, int r, int a, int b) {
        //System.out.println( l + " " + r + " " + a + " " + b );
        push_down( root );
        if( a == l && b == r){
            return Arrays.copyOf( root.quadrant , 4);
        }
        int m = ( l + r ) / 2 ;
        if( b <= m ) return query( root.left , l , m , a , b );
        if( a >= m + 1) return query( root.right , m+1 , r , a, b );
        int[] left = query( root.left , l , m , a , m );
        int[] right = query( root.right, m+1, r , m+1, b );
        for( int i = 0 ; i < 4 ; i ++ )
            left[i] += right[i];

        push_up( root );
        return left;
    }


    private void modify(seg_node root, int l, int r, int a, int b, boolean isX) {
        if( root.fx || root.fy )
            push_down(root);
        if( a == l && b == r ){
            if( isX ){
                //IOUtils.printIntArrays( root.quadrant );
                reflectX(root);
                root.fx = !root.fx;
            }else{
                reflectY( root );
                root.fy = !root.fy;
            }
            return;
        }
        int m = ( l + r ) / 2 ;
        if( b <= m ) modify( root.left , l , m , a , b , isX );
        else if( a >= m+1 ) modify( root.right , m+1 ,r , a,  b, isX );
        else{
            modify( root.left , l , m , a , m , isX);
            modify( root.right , m+1 , r , m+1 , b , isX);
        }
        push_up(root);
    }

    private void push_up(seg_node root) {
        for( int i = 0 ; i < 4 ; i ++ ){
            root.quadrant[i] = (root.left != null ? root.left.quadrant[i]:0) + (root.right != null ? root.right.quadrant[i]:0);
        }
    }

    private void push_down(seg_node root) {
        if( root == null ) return;
        if( root.fx ){
            if( root.left != null ){
                reflectX( root.left );
                root.left.fx = !root.left.fx;
            }
            if( root.right != null ){
                reflectX( root.right );
                root.right.fx = !root.right.fx;
            }
            root.fx = false;
        }
        if( root.fy ){
            if( root.left != null ){
                reflectY( root.left );
                root.left.fy = !root.left.fy;
            }
            if( root.right != null ){
                reflectY( root.right );
                root.right.fy = !root.right.fy;
            }
            root.fy = false;
        }
    }

    private void reflectY(seg_node root) {
        int tmp = root.quadrant[ 0 ] ;
        root.quadrant[0] = root.quadrant[1];
        root.quadrant[1] = tmp;
        tmp = root.quadrant[2] ;
        root.quadrant[2] = root.quadrant[3];
        root.quadrant[3] = tmp;
    }

    private void reflectX(seg_node root) {
        int tmp = root.quadrant[ 0 ] ;
        root.quadrant[0] = root.quadrant[3];
        root.quadrant[3] = tmp;
        tmp = root.quadrant[1] ;
        root.quadrant[1] = root.quadrant[2];
        root.quadrant[2] = tmp;
    }


    class seg_node{
        int[] quadrant;
        boolean fx , fy ;
        seg_node left , right ;
        seg_node(){
            quadrant = new int[4];
            Arrays.fill( quadrant , 0 );
            fx = fy = false;
            left = right = null ;
        }
    }
    private seg_node build_tree(int l, int r) {
        seg_node now = new seg_node();
        if( l == r ){
            now.quadrant[ get_quadrant(X[l-1],Y[l-1]) ] += 1;
            return now;
        }
        int m = ( l + r ) / 2 ;
        now.left = build_tree( l , m ) ;
        now.right = build_tree( m+1 , r );
        push_up( now );
        return now;
    }
    private int get_quadrant( int x , int y ){
        if( x > 0 ) return y > 0 ? 0 : 3 ;
        return y > 0 ? 1 : 2;
    }
}
