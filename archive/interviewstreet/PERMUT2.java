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

public class PERMUT2 {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        while (true) {
            int n = in.nextInt();
            if (n == 0) return;
            int[] order = new int[n + 1], A = new int[n + 1];
            for (int i = 0; i < n; i++) {
                A[i + 1] = in.nextInt();
                order[A[i + 1]] = i + 1;
            }
            boolean is = true;
            for (int i = 1; i <= n; i++)
                if (A[i] != order[i]) {
                    is=false;break;
                }
            out.printLine(is?"ambiguous":"not ambiguous");
        }
    }
}
