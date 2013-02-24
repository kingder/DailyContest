import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Comparator;
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

		Security solver = new Security();
		solver.solve(1, in, out);
		out.close();
	}
}
class Security {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int cases = in.nextInt();
	    for(int tc = 1; tc <= cases; tc ++) {
		    out.print("Case #" + tc + ": ");
		    int m = in.nextInt();
		    final char[] k1 = in.next().toCharArray();
		    final char[] k2 = in.next().toCharArray();
		    int n = k1.length;
		    assert (n % m == 0);
		    final int l = n / m;
		    final Integer[] order = new Integer[m];
		    for(int i = 0; i < m; i ++)
			    order[i] = i;
		    Arrays.sort(order, new Comparator<Integer>() {
			    @Override
			    public int compare(Integer o1, Integer o2) {
				    int s1 = o1 * l;
				    int s2 = o2 * l;
				    for (int i = 0; i < l; i++)
					    if (k2[s1 + i] != k2[s2 + i]) {
						    return k2[s1 + i] - k2[s2 + i];
					    }
				    return 0;
			    }
		    });
		    boolean [][] table = new boolean[m][m];
		    for(boolean [] v : table) Arrays.fill(v, false);
		    for(int i = 0; i < m; i ++) {
		        for(int j = 0; j < m; j ++) {
			        table[i][j] = true;
			        for(int k = 0; k < l; k ++)
				        if(!Match(k1[i * l + k], k2[j * l + k])){
					        table[i][j] = false;
					        break;
				        }
		        }
		    }

		    int[] match = new int[m];
		    int ret = go(match, table, m);
		    if (ret != m){
			    out.printLine("IMPOSSIBLE");
		    } else {
			    for(int i = 0; i < m; i ++) {
				    int t = match[i];
				    int s = order[i];
				    for(int k = 0; k < l; k ++){
					    if(k1[t * l + k] == '?'){
						    if(k2[s * l + k] == '?')
							    k1[t * l + k] = 'a';
						    else
							    k1[t * l + k] = k2[s * l + k];
					    }
				    }
			    }
			    out.printLine(String.valueOf(k1));
		    }
	    }
    }

	private int go(int[] match, boolean[][] table, int n) {
		Arrays.fill(match, -1);
		boolean [] used = new boolean[n];
		int ret = 0;
		for(int i = 0; i < n; i ++){
			Arrays.fill(used, false);
			if(gao(i, n, match, table, used))
				ret ++;
		}
		return ret;
	}

	private boolean gao(int p, int n, int[] match, boolean[][] table, boolean[] used) {
		for(int i = 0; i < n; i ++) if(table[p][i] && !used[i]) {
			used[i] = true;
			if(match[i] == -1 || gao(match[i], n, match, table, used)){
				match[i] = p;
				return true;
			}
		}
		return false;
	}

	private boolean Match(char a, char c) {
		return a == c || a == '?' || c == '?';
	}

    private long go(int n, int a, int b, long[] values, int[] colors) {
        BTree bt = new BTree(n+1);
        long ret = 0;
        for(int i = 0; i < n; i ++) {
            long maxV = values[i] * b;
            long less = bt.get_max(1, 1, n, 1, colors[i] - 1);
            if(less != Long.MIN_VALUE) maxV = Math.max(maxV, less + values[i] * b);
            long more = bt.get_max(1, 1, n, colors[i] + 1, n);
            if(more != Long.MIN_VALUE) maxV = Math.max(maxV, more + values[i] * b);
            long equal = bt.get_max(1, 1, n, colors[i], colors[i]);
            if(equal != Long.MIN_VALUE) maxV = Math.max(maxV, equal + values[i] * a);
            if(ret < maxV) ret = maxV;
            bt.set_max(1, 1, n, colors[i], maxV);

        }
        return ret;
    }

    class BTree{
        long[] values ;
        BTree(int n){
            values = new long[n * 4];
            Arrays.fill(values, Long.MIN_VALUE);
        }
        long get_max(int n, int left, int right, int l, int r){
            if(l < 1 || r > right || l > r)
                return 0;
            if(left == l && right == r) {
                return values[n];
            }
            int md = left + (right - left) / 2;
            if(r <= md)
                return get_max(n << 1, left, md , l ,r);
            if(l > md)
                return get_max(n << 1 | 1, md + 1, r , l ,r);
            long ll = get_max(n << 1, left, md , l ,md);
            long rr = get_max(n << 1 | 1, md + 1, r , md+1 ,r);
            return Math.max(ll, rr);
        }
        void set_max(int n, int left, int right, int t, long v){
            if(left == right && left == t){
                values[n] = Math.max(v, values[n]);
                return;
            }
            int md = left + (right - left) / 2;
            if(t <= md){
                set_max(n << 1, left, md, t, v);
            }else{
                set_max(n <<1 |1, md + 1, right, t, v);
            }
            values[n] = (values[n<<1] > values[n<<1|1]) ? values[n<<1] : values[n<<1|1];
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

