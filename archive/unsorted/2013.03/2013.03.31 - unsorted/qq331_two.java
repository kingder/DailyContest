/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;

public class qq331_two {
    static String[] tens = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    static String[] tens_1 = {"eleven","twelve","thirteen", "fourteen", "fifteen", "sixteen","seventeen","eighteen","nineteen"};
    static String[] tens_2 = {"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    static String[] google = {"googol"};
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt();
        int K = in.nextInt();
        ArrayList<ArrayList<Integer>> AA = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < 10; i ++)
            AA.add(new ArrayList<Integer>());

        for(int i = 0; i < tens.length; i ++) {
            if(tens[i].length() <= 9)
                AA.get(tens[i].length()).add(i);
        }
        for(int i = 0; i < tens_1.length; i ++) {
            if(tens_1[i].length() <= 9)
                AA.get(tens_1[i].length()).add(11 + i);
        }
        for(int i = 0; i < tens_2.length; i ++) {
            int t = (2 + i) * 10;
            for(int j = 0; j < tens.length - 1; j ++) {
                int l = tens_2[i].length() + (j == 0 ? 0 : tens[j].length());
                if(l <= 9)
                    AA.get(l).add(t + j);
            }
        }
        //out.printLine(AA.get(9).size());
        String ret = "";
        if(AA.get(N).size() < K)
            ret = "-1";
        else
            ret = AA.get(N).get(K-1).toString();
        if(N == 9 && K >= 23 && K <= 26) {
            if(K == 23) ret = "1";
            if(K == 24) ret = "2";
            if(K == 25) ret = "6";
            if(K == 26) ret = "10";

            ret = ret + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";

        }
        out.printLine("Case #" + testNumber + ": " + ret);
    }
}
