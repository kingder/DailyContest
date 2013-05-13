package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class Task {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
    }



    public int minSum(int n, int m, int[] rowIndex, int[] columnIndex, int[] element)
    {
        int [][] table = new int[n][m];
        int [][] value = new int[n][m];
        for(int[] o : table) Arrays.fill(o, -1);
        for(int i = 0; i < rowIndex.length; i ++)
            table[rowIndex[i]][columnIndex[i]] = element[i];

        int sum = 0;
        for(int a = 0; a < n; a ++)
            for(int b = 0; b < m; b ++) {
                for(int[] o : value) Arrays.fill(o, -1);
                for(int i = 0; i < rowIndex.length; i ++) {
                    if(columnIndex[i] >= b) {
                        for(int c = columnIndex[i]; c < m; c ++)
                            value[rowIndex[i]][c] = Math.max(value[rowIndex[i]][c], element[i] - (c - columnIndex[i]));

                    }
                }
                for(int i = 0; i < n; i ++)
                    for(int j = 0; j < m; j ++) if(table[i][j] == -1){

                    }
            }

    }

}
