/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class A {
    class Data {
        public int[] digits;
        public int index;
        public int digit;

        Data() {
            index = 0;
            digits = new int[10];
            Arrays.fill(digits, 0);
        }
        Data(Data d){
            this.index = d.index;
            this.digits = Arrays.copyOf(d.digits, 10);
        }
    }

    static int gcd(int A, int B) {
        return B == 0 ? A : gcd(B, A % B);
    }

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int A = in.nextInt();
        int B = in.nextInt();
        int M = in.nextInt();
        int D = in.nextInt();
        int d = gcd(A, B);
        A /= d;
        B /= d;
        if (A % B == 0) {
            out.printLine(0);
            return;
        }
        HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        ArrayList<Integer> indexs = new ArrayList<Integer>();
        A = A % B;
        int index = 0;
        int[] digit_count = new int[10];
        Arrays.fill(digit_count, 0);
        while (true) {
            ++index;
            A = A * 10;

            if(myMap.containsKey(A)) {
                d = myMap.get(A);
                int before = -2;
                for(int i = 0; i < indexs.size(); i ++) {
                    if(indexs.get(i) >= d) {
                        before = i-1;
                        break;
                    }
                }

                if(before == -2) {
                    out.printLine(0);
                    return;
                }


                int loopLength = indexs.size() - before - 1;
                if(loopLength == 0) {
                    out.printLine(0);
                    return;
                }
                M -= before + 1;
                long actual_length = index - d;
                int left = M % loopLength;
                int divid = M / loopLength;
                if(left == 0 && divid >= 1){
                    divid -= 1;
                    left   = loopLength - 1;
                }
                long ret = actual_length * divid + indexs.get(before + left + 1);
                //out.printLine(loopLength + " " + actual_length + " " + left + " " + M + " " + before + " " + index);
                out.printLine(ret);
                return;
            }
            int t = A / B;
            ++digit_count[t];

            if(digit_count[D] == M) {
                out.printLine(index);
                return;
            }

            if(t == D){
                indexs.add(index);
            }
            myMap.put(A, index);
            A = A % B;

        }
    }
}
