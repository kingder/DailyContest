import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Collections;
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
		Ants solver = new Ants();
		solver.solve(1, in, out);
		out.close();
	}
}

class Ants {
    static int MAXDIS = 10000 ;
    static int MAXTIME = 1000000006;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt() ;
        int[] dis = new int[n] ;
        int[] dir = new int[n] ;
        for( int i = 0 ; i < n ; i ++ ) dis[i] = in.nextInt() * 10 ;
        Arrays.sort(dis);

        long tot = 0 ;
        for( int i = 0 ; i < n ; i ++ )for( int d = -1 ; d <= 1 ; d ++ )if(d!=0){ // 1 clockwise, -1 counterclockwise
            dir[i] = d ;
            for( int j = 0 ; j < n-1 ; j ++ ){
                dir[(i+j+1)%n] = dir[(i+j)%n] * -1 ;
            }
            tot = Math.max( tot , go(  n , dir , dis ) );
        }
        out.printLine(tot);
	}

    private long go(int n, int[] dir, int[] dis) {
        long ret = 0 ;
        ArrayList<Integer> time = new ArrayList<Integer>();
        for( int i = 0 ; i < n ; i ++ ){
             for( int j = 0 ; j < n ; j ++ )if( dir[j] != dir[i] ) {
                 if( dis[i] < dis[j] ){
                     if( dir[i] == 1 ){
                         time.add( (dis[j] - dis[i]) / 2 );
                         time.add( (dis[j] - dis[i] + MAXDIS) / 2);
                     }else{
                         time.add( (MAXDIS - (dis[j] - dis[i]) ) / 2 );
                         time.add( MAXDIS - (dis[j] - dis[i]) / 2);
                     }
                 }else{
                     if( dir[i] == 1){
                         time.add( (MAXDIS - (dis[i] - dis[j]) ) / 2 );
                         time.add( MAXDIS - (dis[i] - dis[j]) / 2);
                     }else{
                         time.add( (dis[i] - dis[j]) / 2 );
                         time.add( (dis[i] - dis[j] + MAXDIS) / 2);
                     }
                 }
             }
        }
        Collections.sort( time );
        ret += MAXTIME / MAXDIS * time.size();

        for( int i = 0 ; i < time.size() ; i ++ ) if( time.get(i) <= MAXTIME % MAXDIS ) ret ++ ;
        return ret;
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
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt(){
        return readInt();
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

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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

