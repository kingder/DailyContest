import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.Writer;
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
		acm solver = new acm();
		solver.solve(1, in, out);
		out.close();
	}
}

class acm {
    long[] A, D, P;
    static final int mod = 1000003;
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        A = new long[n];
        D = new long[n];
        P = new long[n];
        IOUtils.readLongArrays(in, A, D, P);
        
        int m = in.nextInt() ;
        long[] fac = new long[mod + 1];
        fac[0] = 1;
        for (int i = 1; i < mod; i++) fac[i] = fac[i - 1] * i % mod;
        for( int i = 0 ; i < m ; i ++ ){
            if( in.nextInt() == 0 ){
                int a = in.nextInt() ;
                int b = in.nextInt() ;
                int v = in.nextInt() ;
                for( int j = a-1 ; j < b ; j ++ )
                    P[j] += v ;
            }else{
                int a = in.nextInt() ;
                int b = in.nextInt() ;
                long sum = 0 , ret = 1;
                for( int j = a-1 ; j < b ; j ++ ){
                    sum += P[j] ;
                    ret = ret * power_mod( D[j] , P[j] ) % mod ;
                }
                if( sum >= mod ) ret = 0 ;
                else ret = ret * fac[(int)sum] % mod ;
                out.printLine( sum , ret );
            }
        }
    }
    private long power_mod(long n, long m) {
        if (m == 0) return 1;
        if (m == 1) return n;
        long ret = power_mod(n, m >> 1);
        return (m & 1) == 0 ? ret * ret % mod : ret * ret % mod * n % mod;
    }
}

class MyInputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public MyInputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    public long nextLong(){
        return Long.parseLong( next() ) ;
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

class IOUtils {
    public static void readLongArrays( MyInputReader in,long[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextLong();
    }
    }

