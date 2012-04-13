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

import java.util.Arrays;

public class CommentTask {
    static final int Mod = 1000000007;
    static final int[] table = {1,
            2,
            7,
            38,
            291,
            2932,
            36961,
            561948,
            10026505,
            205608536,
            767440651,
            373202347,
            630085432,
            282234652,
            673322462,
            131935485,
            402081845,
            678170376,
            436468013,
            464104160,
            835315027,
            281872976,
            496721330,
            148734173,
            562024850,
            222330769,
            629123368,
            219900207,
            311664840,
            114974372,
            356056332,
            835506954,
            47512108,
            256278981,
            616519893,
            32313045,
            117134122,
            852198063,
            659742994,
            547964402};

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int[] D = new int[n];
        int degreeSum = 0;
        int notDetermined = 0;
        int[] degreeCnt = new int[n + 1], degreeCount = new int[n + 1];
        for (int i = 0; i < n; i++) {
            D[i] = in.nextInt();
            notDetermined += D[i] == -1 ? 1 : 0;
            if (D[i] != -1) {
                degreeSum += D[i];
                degreeCnt[D[i] + 1] += D[i];
                degreeCount[D[i] + 1] += 1;
            }
        }
        if (notDetermined == n) {
            out.printLine(table[n - 1]);
            return;
        }
        long ret = 1;
        if (notDetermined == 0) {
            Arrays.sort(D);
            int presum = 0;
            for (int i = 0; i < n; i++) {
                presum += D[i];
                if (presum < choose(i + 1, 2)) {
                    ret = 0;
                    break;
                }
                if (i == n - 1 && presum != choose(n, 2))
                    ret = 0;
            }
            out.printLine(ret);
            return;
        }


        for (int i = 1; i <= n; i++) {
            degreeCnt[i] += degreeCnt[i - 1];
            degreeCount[i] += degreeCount[i - 1];
        }
        for (int[] o : C) Arrays.fill(o, -1);
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                C[i][j] = choose(i, j);
        degreeSum = choose(n, 2) - degreeSum;

        if (degreeSum < 0) {
            out.printLine(0);
            return;
        }

        int M = Math.min(n, degreeSum + 1);

        long[][][] dp = new long[notDetermined + 1][M + 1][degreeSum + 1];
        //boolean [][][] isok = new boolean[ notDetermined + 1][ M + 1 ][ degreeSum + 1];


//        for( int i = 0 ; i < dp.length ; i ++ )
//            for( int j = 0 ; j < dp[i].length ; j ++ ){
//                Arrays.fill( dp[i][j] , 0 );
//
//            }

        //for( int it : degreeCnt ) System.out.print( it + " "); System.out.print("\n");
        //for( int it : degreeCount ) System.out.print( it + " "); System.out.print("\n");
        dp[0][0][0] = 1;
        //out.printLine( degreeCount[1] , degreeCount[0]);
        //out.printLine( notDetermined , M , degreeSum );

        for (int i = 0; i < notDetermined; i++)
            for (int j = 0; j <= M; j++)
                for (int k = 0; k <= degreeSum; k++)
                    if (dp[i][j][k] > 0) {

                        //if (k + (notDetermined - i) * M < degreeSum) continue;
                        int presum = degreeCnt[j];
                        int precnt = degreeCount[j];
                        for (int it = j + 1; it <= M; it++) {
                            int x = degreeCount[it] - degreeCount[it - 1];
                            if (k + presum - C[precnt + i][2] < x * (1 - it + precnt + i) + C[x][2])
                                break;
                            presum = degreeCnt[it];
                            precnt = degreeCount[it];
                            //if( !ok ) break;
                            //out.printLine( i , j , k , x, precnt , presum , it);
                            for (int nt = 1; i + nt <= notDetermined && k + nt * (it - 1) <= degreeSum; nt++) {
                                if (presum + nt * (it - 1) + k >= C[nt + precnt + i][2]) {
                                    dp[i + nt][it][k + nt * (it - 1)] = (dp[i + nt][it][k + nt * (it - 1)] + C[notDetermined - i][nt]
                                            * dp[i][j][k]) % Mod;
                                    //if( i+nt == 2 && it == 6 && k+nt*(it-1) == 8 )
                                    // out.printLine( "(",i , j , k ,')',"->", "(", i+nt , it , k+nt*(it-1),")");

                                } else break;
                            }

                        }

                    }

        ret = 0;
        for (int i = 1; i <= M; i++) {
            int presum = degreeCnt[i];
            int precnt = degreeCount[i];
            boolean ok = true;
            for (int it = i + 1; it <= M && ok; it++) {
                int x = degreeCount[it] - degreeCount[it - 1];
                if (degreeSum + presum - choose(notDetermined + precnt, 2) < x * (1 - it + notDetermined + precnt) + choose(x, 2)) {
                    ok = false;
                    break;
                }
                presum = degreeCnt[it];
                precnt = degreeCount[it];
            }
            if (!ok) continue;
            ret = (ret + dp[notDetermined][i][degreeSum]) % Mod;
        }
        out.printLine(ret);

//        if( notDetermined < 7 ){
//            Arrays.sort( D );
//            for( int i = 0 ; i < D.length / 2 ; i ++ ){
//                int t = D[ i ] ;
//                D[i] = D[ D.length - i - 1] ;
//                D[ D.length-i-1 ]= t;
//
//            }
//            out.printLine( "brute force:" , brute_force( D ));
//        }
        //out.printLine(getMethods(4, 4, 6));
    }

    static final int MAXN = 40 + 10;
    int[][] C = new int[MAXN][MAXN];

    private int choose(int n, int k) {
        if (n < 0 || k < 0 || n < k) return 0;
        if (n == k || k == 0) return 1;
        if (C[n][k] != -1) return C[n][k];
        return C[n][k] = (choose(n - 1, k - 1) + choose(n - 1, k)) % Mod;

    }

    int ans;

    public int brute_force(int[] D) {
        ans = 0;
        int sum = 0;
        for (int o : D) sum += o == -1 ? 0 : o;
        //for( int it : D ) System.out.print( it + " "); System.out.print("\n");
        bsolve(D, sum);
        return ans;

    }

    private void bsolve(int[] d, int sm) {
        boolean more = false;
        //for( int it : d ) System.out.print( it + " "); System.out.print("\n"); System.out.println(sm);
        for (int i = 0; i < d.length; i++) {
            if (d[i] == -1) {
                more = true;
                for (int j = 0; j < d.length; j++)
                    if (sm + j <= choose(d.length, 2)) {
                        d[i] = j;
                        bsolve(d, sm + j);
                    }
                d[i] = -1;
                break;
            }
        }
        if (!more && sm == choose(d.length, 2)) {
            int[] s = Arrays.copyOf(d, d.length);
            Arrays.sort(s);
            //for( int it : d ) System.out.print( it + " "); System.out.print("\n");
            //for( int it : s ) System.out.print( it + " "); System.out.print("\n");

            int p = 0;
            for (int i = 0; i < s.length; i++) {
                p += s[i];
                if (p < choose(i + 1, 2)) return;
            }
            //for( int it : d ) System.out.print( it + " "); System.out.print("\n");
            ans++;
        }
    }

    private int getMethods(int limit, int n, int k) {
        int[] A = new int[k + 1];
        int[] C = new int[k + 1];
        for (int i = 0; i <= Math.min(n - 1, k); i++) A[i] = 1;
        for (int it = 0; it < limit - 1; it++) {
            for (int j = 0; j <= Math.min(n - 1, k); j++) {
                for (int i = 0; i + j <= k; i++)
                    C[i + j] = (C[i + j] + A[i]) % Mod;
            }
            int[] tmp = C;
            C = A;
            A = tmp;
            Arrays.fill(C, 0);
            // for( int i = 0 ; i <= k ; i++ ) System.out.printf("%d ",C[i]);System.out.println();
            // for( int i = 0 ; i <= k ; i++ ) System.out.printf("%d ",A[i]);System.out.println();
        }
//        for (int i = 0; i <= k; i++) System.out.printf("%d ", A[i]);
//        System.out.println();
//        System.out.println(n + " " + k + " " + A[k]);
        return A[k];

    }
}
