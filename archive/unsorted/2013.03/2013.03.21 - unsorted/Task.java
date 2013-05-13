package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Task {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n, m;
        do{
            n = in.nextInt();
            m = in.nextInt();
            if(n + m == 0) break;
            int [][] pos = new int[n][m];
            for(int i = 0; i < n; i ++)
                for(int j = 0; j < m; j ++)
                    pos[i][j] = in.nextInt();
            int ret = 0, x = 0, y = 0;
            for(int i = 0; i < n; i ++)
                for(int j = 0; j < m; j ++) {
                    int tmp = 0;
                    for(int dx = -1; dx <= 1; dx ++)
                        for(int dy = -1; dy <= 1; dy ++)
                            if(Math.abs(dx) + Math.abs(dy) == 1){
                                int nx = i + dx;
                                int ny = j + dy;
                                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                    tmp += (pos[nx][ny] * pos[i][j] < 0 ? 1 : -1) * Math.abs(pos[nx][ny]);
                                }
                            }

                    if(tmp > ret){
                        ret = tmp;
                        x = i + 1;
                        y = j + 1;
                    }
                }


            out.printLine(x + " " + y + " " + ret);


        }   while (true);
    }

}
