/*
 * Copyright (c) 2013.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author lwc626
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInputReader in = new MyInputReader(inputStream);
		MyOutputWriter out = new MyOutputWriter(outputStream);
		A solver = new A();
		int testCount = Integer.parseInt(in.next());
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		out.close();
	}
}

class A {

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

class MyInputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public MyInputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new UnknownError();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new UnknownError();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
    public int nextInt(){
        return readInt() ;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuffer res = new StringBuffer();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        return readString();
    }
}

class MyOutputWriter {
    private final PrintWriter writer;

    public MyOutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public MyOutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

