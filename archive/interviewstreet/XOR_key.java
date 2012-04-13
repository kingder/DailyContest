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

public class XOR_key {
    class node {
        node() {
            set = null;
            left = right = null;
        }

        ArrayList<Integer> set;
        node left, right;
    }

    node root ;

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        root = new node();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] X = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = in.nextInt();
            insert(X[i], i + 1);
        }
       // print( root );
        build(root);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int p = in.nextInt();
            int q = in.nextInt();
            int aa = go( root , 15 , a , p , q );
            //int bb = brute_force(X, a, p, q);
            //out.printLine( aa + ": " + bb);
            //if( aa != bb ){

               out.printLine( aa);
                //out.printLine(go(root, 15, a, p, q), brute_force(X, a, p, q));
            //}

        }
        //out.printLine( "case end");
    }

    private void print(node root) {
        if( root == null ) return;
        print( root.left )  ;
        print( root.right ) ;
        if( root.set != null )
            System.out.println( root.set.toString());
    }

    private int brute_force(int[] x, int a, int p, int q) {
        int ret = 0;
        int id = 0;
        for (int i = p - 1; i < q; i++) {
            
            if( ret < (a ^ x[i])){
                ret = Math.max(ret, a ^ x[i]);
                id = i;
            }
        }
        //System.out.println( "reall max: " + ret + " " + (id+1) +" " + x[id] );
        return ret;
    }

    private int go(node R, int level, int a, int p, int q) {

        if (R == null ) return a;
        node ra = ((a & (1 << (level-1))) == 0 ? R.right : R.left);
        node rb = ((a & (1 << (level-1))) == 0 ? R.left : R.right);
        if (ra != null) {
            ArrayList<Integer> set = ra.set;
            int lo = 0, hi = set.size() - 1 , ret = -1 ;
            while (lo <= hi) {
                int md = (lo + hi) / 2;
                if (set.get(md) < p) lo = md + 1;
                else{
                    ret = set.get( md );
                    hi = md - 1;
                }
            }
            if ( ret >= p && ret <= q) {
                //System.out.println( ret );
                return go(ra, level - 1, a | (1 << (level-1)), p, q);
            } else {
                return go(rb, level - 1, a & (~(1 << (level-1))), p, q);
            }
        } else {
            return go(rb, level - 1, a & (~(1 << (level-1))), p, q);
        }
    }

    private void build(node R) {

        if (R == null) return;

        //System.out.println( R );
        build(R.left);
        build(R.right);
        if (R.left == null && R.right != null) {
            R.set = R.right.set;
        } else if (R.left != null && R.right == null) {
            R.set = R.left.set;
        } else if (R.left != null && R.right != null) {
            R.set = new ArrayList<Integer>();
            ArrayList<Integer> a = R.left.set, b = R.right.set;
            int na = 0, nb = 0;
            while (na < a.size() && nb < b.size()) {
                if (a.get(na) < b.get(nb)) {
                    R.set.add(a.get(na));
                    na++;
                } else {
                    R.set.add(b.get(nb));
                    nb++;
                }
            }
            while (na < a.size()) R.set.add(a.get(na++));
            while (nb < b.size()) R.set.add(b.get(nb++));
        }
        //System.out.println( R.set.toString());
    }

    private void insert(int a, int id) {
        node R = root;
        for (int i = 14; i >= 0; i--) {
            if ((a & (1 << i)) != 0) {
                if (R.right == null) R.right = new node();
                R = R.right;
            } else {
                if (R.left == null) R.left = new node();
                R = R.left;
            }
        }
        if (R.set == null) R.set = new ArrayList<Integer>();
        R.set.add(id);
    }
}
