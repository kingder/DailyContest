/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class DiscountOnDishes {
    class Matrix{
        int _n;
        long[][] data;
        Matrix(int n){
            _n = n;
            data = new long[_n][_n];
            for(long[] o : data) Arrays.fill(o, 0);
        }
        void make_one(Matrix a){
            for(long[] o : a.data) Arrays.fill(o, 0);
            for(int i = 0; i < a._n; i++)
                a.data[i][i] = 1;
        }
        Matrix multi(final Matrix a, final Matrix b, int M) {
            Matrix ret = new Matrix(a._n);
            int n = a._n;
            for(int i = 0; i < n; i ++)
                for(int j = 0; j < n; j ++){
                    for(int k = 0; k < n; k ++) {
                        ret.data[i][j] += a.data[i][k] * b.data[k][j];
                        if(ret.data[i][j] >= M)
                            ret.data[i][j] %= M;
                    }
                }
            return ret;
        }
        Matrix power(final Matrix base, int n, int M){
            Matrix ret = new Matrix(base._n);
            make_one(ret);
            if(n == 0){
                return ret;
            }
            ret = power(base, n >> 1, M);
            ret = multi(ret, ret, M);
            if((n & 1) == 1){
                ret = multi(base, ret, M);
            }
            return ret;
        }

    }
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int T = in.nextInt();
        for(int tc = 1; tc <= T; tc ++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int C = in.nextInt();
            int n = in.nextInt();
            int M = in.nextInt();
            if((n & 1) == 1) {
                if(n == 1){
                    out.printLine(A % M);
                } else {
                    Matrix ret = new Matrix(2);
                    ret.data[0][0] = C;
                    ret.data[0][1] = C;
                    ret.data[1][1] = 1;
                    ret = ret.power(ret, (n - 1) / 2, M);
                    out.printLine((ret.data[0][0] * A % M+ ret.data[0][1] * B) % M);
                }
            } else {
                 if(n == 2) {
                     out.printLine((A + B) % M);
                 } else {
                     Matrix ret = new Matrix(2);
                     ret.data[0][0] = C;
                     ret.data[0][1] = 1;
                     ret.data[1][1] = 1;
                     ret = ret.power(ret, (n - 2) / 2, M);
                     ///out.printLine(ret.data[0][0] + " " + ret.data[0][1]);
                     out.printLine((ret.data[0][0] * ((A + B) % M) % M + ret.data[0][1] * B) % M);
                 }
            }
        }
    }
}
