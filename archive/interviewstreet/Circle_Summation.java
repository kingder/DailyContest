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

import java.util.Arrays;

public class Circle_Summation {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] X = new int[n];
        Matrix[] trans = new Matrix[n];
        for (int i = 0; i < n; i++) {
            trans[i] = new Matrix(n);
            trans[i].setOne();
            trans[i].set(i, (i + n - 1) % n, (i + 1) % n);
            X[i] = in.nextInt();
        }


        //for( int start = 0 ; start < n ; start ++ ){
        Matrix a = new Matrix(n);
        a.setOne();
        for (int i = 0; i < n; i++) {
            a = multiply(n, trans[i], a);
        }
        a = power(a, m / n);

        int left = m % n;

        for (int i = 0; i < left; i++) {
            a = multiply(n, trans[i], a);
        }

        for( int start = 0 ; start < n ; start ++ ){
            long [] ret = new long[ n ] ;
            for( int r = 0 ; r < n ; r ++ ){
                for( int c = 0 ; c < n ; c ++ )
                    ret[ (start + r) % n ] = ( ret[ (start + r) % n ] + a.data[r][c] * X[(start+c)%n] ) % mod;
            }
            IOUtils.printLongArrays(out,ret);
        }
    }

    private Matrix power(Matrix a, int n) {
        if (n == 0) {
            Matrix ret = new Matrix(a.dimension);
            ret.setOne();
            return ret;
        }
        if (n == 1) return a;
        Matrix ret = power(a, n >> 1);
        ret = multiply(ret.dimension, ret, ret);
        if ((n & 1) == 1) ret = multiply(ret.dimension, ret, a);
        return ret;
    }

    class Matrix {
        public final long[][] data;
        public final int dimension;

        public Matrix(int n) {
            this.dimension = n;
            data = new long[dimension][dimension];
        }

        public void set(int r, int a, int b) {
            data[r][a] = data[r][b] = 1;
        }

        public void setOne() {
            for (int i = 0; i < dimension; i++)
                this.data[i][i] = 1;
        }


    }

    static final int mod = 1000000007;

    public Matrix multiply(final int n, final Matrix a, final Matrix b) {
        Matrix ret = new Matrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ret.data[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    ret.data[i][j] += a.data[i][k] * b.data[k][j];
                    if (ret.data[i][j] > (1L << 60)) ret.data[i][j] %= mod;
                }
                ret.data[i][j] %= mod;
            }


        return ret;
    }
}
