/*
 * Copyright (c) 2013.
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author lwc626
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyInputReader in = new MyInputReader(inputStream);
        MyOutputWriter out = new MyOutputWriter(outputStream);
        AlmostPrime solver = new AlmostPrime();
        solver.solve(1, in, out);
        out.close();
    }
}

class AlmostPrime {

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
    }
}

class MyInputReader {

    private InputStream stream;

    public MyInputReader(InputStream stream) {
        this.stream = stream;
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

    public void close() {
        writer.close();
    }

}

