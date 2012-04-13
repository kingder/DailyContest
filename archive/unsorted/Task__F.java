package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Task__F {

    static final int MAXN = 100 + 10;
    int[][] distance = new int[MAXN][MAXN];
    int[][] sdistance = new int[MAXN][MAXN];
    int[][] dp = new int[10 + 5][MAXN];

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int cases = in.nextInt();
        for (int tc = 1; tc <= cases; tc++) {

            int A = in.nextInt();
            int B = in.nextInt();
            int M = in.nextInt();
            int L = in.nextInt();
            int K = in.nextInt();


            for (int i = 1; i <= A + B; i++) {
                Arrays.fill(distance[i], -1);
                Arrays.fill(sdistance[i], -1);
            }
            for (int i = 0; i < M; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
                distance[a][b] = distance[b][a] = c;
                sdistance[a][b] = sdistance[b][a] = c;
            }

            for (int i = 0; i <= A + B; i++) sdistance[i][i] = distance[i][i] = 0;
            //out.printLine( distance.toString() );
            floyd(distance, A + B);
            sfloyd(sdistance, A, B);

            /*
            for( int i = 1 ; i <= A+B ; i ++ ){
               for( int j = 1 ; j <= A+B ; j ++ )
                   out.print( sdistance[i][j] , " ");
                out.printLine();
            } */
            for (int i = 0; i <= K; i++)
                Arrays.fill(dp[i], -1);

            dp[0][A + B] = 0;
            Queue<Pair<Integer, Integer>> myQ = new LinkedList<Pair<Integer, Integer>>();

            myQ.offer(Pair.makePair(0, A + B));

            while (!myQ.isEmpty()) {

                Pair f = myQ.poll();

                int now = (Integer) f.getValue();
                int used = (Integer) f.getKey();

                //out.printLine( f.toString() , dp[used][now]);
                for (int next = 1; next <= A + B; next++)
                    if (distance[now][next] != -1) {

                        if (dp[used][next] == -1 || dp[used][next] > dp[used][now] + distance[next][now]) {
                            dp[used][next] = dp[used][now] + distance[now][next];
                            myQ.offer(Pair.makePair(used, next));
                        }
                        if (sdistance[now][next] != -1 && used + 1 <= K && sdistance[now][next] <= L && (dp[used + 1][next] == -1 || dp[used + 1][next] >
                                dp[used][now])) {
                            dp[used + 1][next] = dp[used][now];
                            myQ.offer(Pair.makePair(used + 1, next));

                        }
                    }
            }
            int ret = -1;
            for (int i = 0; i <= K; i++)
                if (dp[i][1] != -1)
                    ret = update(ret, dp[i][1]);
            out.printLine(ret);
        }
    }

    private void sfloyd(int[][] sdistance, int A, int B) {
        for (int k = 1; k <= A; k++)
            for (int i = 1; i <= A + B; i++)
                if (sdistance[i][k] != -1)
                    for (int j = 1; j <= A + B; j++)
                        if (sdistance[k][j] != -1) {
                            sdistance[i][j] = update(sdistance[i][j], sdistance[i][k] + sdistance[k][j]);

                        }
    }

    private void floyd(int[][] distance, int n) {
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                if (distance[i][k] != -1)
                    for (int j = 1; j <= n; j++)
                        if (distance[k][j] != -1)
                            distance[i][j] = update(distance[i][j], distance[i][k] + distance[k][j]);
    }

    private int update(int a, int b) {
        return a == -1 || a > b ? b : a;
    }

}
