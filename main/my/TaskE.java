/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt(), q = in.nextInt();
        long[] values = new long[n];
        int[] colors = new int[n];
        for(int i = 0; i < n; i ++) values[i] = in.nextLong();
        for(int i = 0; i < n; i ++) colors[i] = in.nextInt();
        for(int k = 0; k < q; k ++) {
            int a = in.nextInt();
            int b = in.nextInt();
            out.printLine(go(n, a, b, values, colors));
        }

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
