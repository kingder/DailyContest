package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Task {

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        Solution solver = new Solution();
        int []A = new int[ 6 ] ; 
        for( int i = 0 ; i < 6 ; i ++ ) A[i] = in.nextInt();
        out.printLine(solver.number_of_disc_intersections(A));
    }

    public class Solution {

        public int number_of_disc_intersections ( int[] A ) {
            // write your code here
            ArrayList<Pair<Integer,Integer>> B = new ArrayList<Pair<Integer, Integer>>( A.length );
            
            for( int i = 0 ; i < A.length ; i ++ ){
                B.add(Pair.makePair(i, i - A[i])) ;
            }
            
            Collections.sort( B , new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    if( !o1.getValue() .equals( o2.getValue() ) )
                        return o1.getValue() .compareTo( o2.getValue() );
                    return  o1.getKey().compareTo( o2.getKey() );
                }
            });

            System.out.println( B.toString() );
            long ret = 0 ;
            for( int i = 0 ; i < B.size() ; i ++ ){
                int lo = i+1 , hi = B.size()-1;
                int goal = A[B.get(i).getKey()] + B.get(i).getKey();
                int last = -1 ;
                while ( lo <= hi ){
                    int md = lo + ( hi - lo ) / 2 ;
                    if( B.get(md).getValue() <= goal ){
                        last = md ;
                        lo = md + 1;
                    }else{
                        hi = md - 1;
                    }
                }
                if(last!=-1)ret += last - i ;
            }
            return ret > 10000000 ? -1 : (int)ret;
        }
        public int ps( int A[] ){
            boolean[] isMark = new boolean[ A.length ];
            Arrays.fill(isMark, false);
            int last = -1;
            for( int i = 0 ; i < A.length ; i ++ ){
                if( !isMark[A[i]] ){
                    isMark[ A[i] ] = true ;
                    last = i ;
                }
            }
            return last;
        }
        public int equi ( int[] A ) {
            // write your code here
            long sum = 0 , left = 0 ;
            for( int o : A ) sum += o ;
            for( int i = 0 ; i < A.length ; i ++ ){
                if( left == sum - left - A[ i ] ) return i;
                left += A[i] ;
            }
            return -1;
        }
        
        final long Mod = 1000000007;

        public class Matrix {
            final int N = 2;
            long[][] A;

            public Matrix(long a, long b, long c, long d) {
                A = new long[N][N];
                A[0][0] = a;
                A[0][1] = b;
                A[1][0] = c;
                A[1][1] = d;
            }

            public Matrix power(Matrix A, int N) {
                if (N == 0) {
                    return new Matrix(1, 0, 0, 1);
                }
                Matrix half = power(A, N >> 1);
                if ((N & 1) == 0) {
                    return mult(half, half);
                } else {
                    return mult(A, mult(half, half));
                }

            }

            public Matrix mult(Matrix A, Matrix B) {
                Matrix ret = new Matrix(0, 0, 0, 0);
                for (int i = 0; i < N; i++)
                    for (int j = 0; j < N; j++)
                        for (int k = 0; k < N; k++)
                            ret.A[i][j] = (ret.A[i][j] + A.A[i][k] * B.A[k][j]) % Mod;
                return ret;
            }
        }

        public int general_fib(int A, int B, int N) {
            if (N == 0) return (int) (A % Mod);
            if (N == 1) return (int) (B % Mod);
            Matrix m = new Matrix(1, 1, 1, 0);
            Matrix ret = m.power(m, N - 1);
            return (int) ((ret.A[0][0] * B + ret.A[0][1] * A) % Mod);
        }


        public int upper_spiral_sum(int[][] A) {
            int N = A.length, M = A[0].length;
            long ret = A[0][0];
            final int[] Dx = new int[]{0, 1, 0, -1};
            final int[] Dy = new int[]{1, 0, -1, 0};
            int sx = 0, sy = 0, d = 0;
            int R0 = 0, Rn = N - 1, C0 = 0, Cm = M - 1;
            while (R0 <= Rn && C0 <= Cm) {
                while (inRange(sx + Dx[d], sy + Dy[d], R0, Rn, C0, Cm)) {
                    sx += Dx[d];
                    sy += Dy[d];
                    ret += A[sx][sy];
                    System.out.println(A[sx][sy]);
                }
                if ((d & 1) == 0) {
                    R0 += 1;
                    Rn -= 1;
                } else {
                    C0 += 1;
                    Cm -= 1;
                }
                d = (d + 1) % 4;
                //System.out.println(R0 + " " + Rn + " " + C0 + " " + Cm);
            }
            return ret >= -100000000 && ret <= 100000000 ? (int) ret : -1;

        }

        private boolean inRange(int sx, int sy, int r0, int rn, int c0, int cm) {
            return sx >= r0 && sx <= rn && sy >= c0 && sy <= cm;
        }
    }
}

