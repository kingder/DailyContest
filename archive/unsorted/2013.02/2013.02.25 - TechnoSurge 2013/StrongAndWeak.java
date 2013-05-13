/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StrongAndWeak {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int W = in.nextInt();
        int H = in.nextInt();
        char[][] board = new char[W][];
        for(int i = 0; i < W; i ++) {
            board[i] = in.next().toCharArray();
        }

        int t = in.nextInt();
        while (t -- > 0) {
            String s = in.next();
            int ret = go(s.charAt(0), W, H, board);
            out.printLine(ret > 0 ? "Strong " + ret : "Weak" );
        }
    }

    private int go(char c, int w, int h, char[][] board) {
        int[][] dp = new int[w][h];
        for(int[] o : dp) Arrays.fill(o, -1);
        Queue<Integer> Q = new LinkedList<Integer>();
        for(int i = 0; i < w; i ++)
            for(int j = 0; j < h; j ++) if(board[i][j] == c) {
                dp[i][j] = 1;
                Q.offer(i * h + j);
            }
        while (!Q.isEmpty()) {
            int now = Q.poll();
            int x = now / h, y = now % h;
            for(int dx = -1; dx <= 1; dx ++)
                for(int dy = -1; dy <= 1; dy ++) {
                    if(dx == 0 && dy == 0) continue;
                    int nx = x + dx;
                    int ny = y + dy;
                    if(nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    if(board[nx][ny] == board[x][y] + 1){
                        if(dp[nx][ny] < dp[x][y] + 1){
                            dp[nx][ny] = dp[x][y] + 1;
                            Q.offer(nx * h + ny);

                        }
                    }

                }
        }
        int ret = 0;
        for(int i = 0; i < w; i ++)
            for(int j = 0; j < h; j ++)
                ret = Math.max(ret, dp[i][j]);
        return ret;
    }
}
