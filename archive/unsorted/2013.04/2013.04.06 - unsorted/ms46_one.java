/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.HashMap;

public class ms46_one {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt();
        int M = in.nextInt();
        //in.readLine();
        HashMap<String,String> op = new HashMap<String, String>();
        for(int i = 0; i < M; i ++) {
            String line = in.readLine();
            //System.out.println(line);
            String[] words = line.split(" ");
            op.put(words[0], words[1]);
        }
        String[] words = in.readLine().split(" ");
        for(int i = 0; i < N - 1; i ++) {
            for(int j = 0; j < words.length; j ++) {
                if(op.containsKey(words[j]))
                    words[j] = op.get(words[j]);
            }
        }
        out.print("Case #" + testNumber + ":");
        for(int i = 0; i < words.length; i ++)
            out.print(" " + words[i]);
        out.printLine();
    }
}
