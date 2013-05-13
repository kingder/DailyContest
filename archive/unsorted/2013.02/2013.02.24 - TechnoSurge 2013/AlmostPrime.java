package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AlmostPrime {
    static Set<Long> primeSet = new HashSet<Long>();
    static final int limit = 1000000;
    static {
        boolean [] prime = new boolean[limit + 1];
        Arrays.fill(prime, true);
        for(int i = 2; i <= limit; i ++) if(prime[i]) {
            for(int j = i + i; j <= limit; j += i) prime[j] = false;
            primeSet.add((long)i * i);
        }
    }
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        for(int i = 0; i < n; i ++) {
            long v = in.nextLong();
            out.printLine(primeSet.contains(v) ? "YES" : "NO");
        }
    }
}
