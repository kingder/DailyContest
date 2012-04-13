package my.mypackage;

import net.kingder.utils.io.IOUtils;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class ARITHMETIC_PROGRESSIONS {
    int[] A, D, P;

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        A = new int[n];
        D = new int[n];
        P = new int[n];
        IOUtils.readIntArrays(in, A, D, P);
        seg_node root = build_tree(1, n);
        long[] fac = new long[mod + 1];
        fac[0] = 1;
        for (int i = 1; i < mod; i++) fac[i] = fac[i - 1] * i % mod;
        int m = in.nextInt();
        while (m-- > 0) {
            if (in.nextInt() == 0) {
                long[] ret = query(out,root, 1, n, in.nextInt(), in.nextInt());
                //IOUtils.printLongArrays(out, ret);
                if (ret[0] < mod) ret[1] = ret[1] * fac[(int) ret[0]] % mod;
                else ret[1] = 0;
                IOUtils.printLongArrays(out, ret);
            } else {
                modify(root, 1, n, in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
    }

    private long[] query(MyOutputWriter out,seg_node root, int l, int r, int a, int b) {
        push_down(root);
        //out.printLine( l , r , a , b, root._sum );
        if (l == a && r == b) {
            return new long[]{root._sum, root._product};
        }
        int m = (l + r) / 2;
        long[] ret;
        if (b <= m) {
            ret = query(out,root.left, l, m, a, b);
        } else if (a >= m + 1) {
            ret = query(out,root.right, m + 1, r, a, b);
        } else {
            ret = query(out,root.left, l, m, a, m);
            long[] tmp = query(out,root.right, m + 1, r, m + 1, b);
            ret[0] += tmp[0];
            ret[1] = ret[1] * tmp[1] % mod;
        }
        //IOUtils.printLongArrays(out,ret);
        push_up(root);
        return ret;
    }

    private void modify(seg_node root, int l, int r, int a, int b, int v) {
       // System.out.println( root._sum + " " + l + " " + r + " " + a + " " + b + " " + v );
        push_down(root);
        if (l == a && r == b) {
            root._sum += v * root.tot ;
            root._product = root._product * power_mod(root._product_of_di, v) % mod;
            root._lazy_sum += v;
            return;
        }
        int m = (l + r) / 2;
        if (b <= m) {
            modify(root.left, l, m, a, b, v);
        } else if (a >= m + 1) {
            modify(root.right, m + 1, r, a, b, v);
        } else {
            modify(root.left, l, m, a, m, v);
            modify(root.right, m + 1, r, m + 1, b, v);
        }
        push_up(root);
    }

    private void push_down(seg_node root) {
        if (root._lazy_sum == 0) return;
        if (root.left != null) {
            root.left._sum += root._lazy_sum * root.left.tot ;
            root.left._product = root.left._product * power_mod(root.left._product_of_di, root._lazy_sum) % mod;
            root.left._lazy_sum += root._lazy_sum;
        }
        if (root.right != null) {
            root.right._sum += root._lazy_sum * root.right.tot;
            root.right._product = root.right._product * power_mod(root.right._product_of_di, root._lazy_sum) % mod;
            root.right._lazy_sum += root._lazy_sum;
        }
        root._lazy_sum = 0;
    }

    private seg_node build_tree(int l, int r) {

        if (l == r) {
            return new seg_node(P[l - 1], D[l - 1]);
        }
        int m = (l + r) / 2;
        seg_node root = new seg_node(0, 0);
        root.left = build_tree(l, m);
        root.right = build_tree(m + 1, r);
        push_up(root);
        root._product_of_di = root.left._product_of_di * root.right._product_of_di % mod;
        root.tot = root.left.tot + root.right.tot;
        return root;
    }

    private void push_up(seg_node root) {
        root._sum = 0;
        root._product = 1;
        if (root.left != null) {
            root._sum += root.left._sum;
            root._product = root.left._product;
        }
        if (root.right != null) {
            root._sum += root.right._sum;
            root._product *= root.right._product;
        }
        root._product %= mod;
    }

    static final int mod = 1000003;

    class seg_node {
        long _sum; // keep the sum of the interval sum(p_i) ;
        long _product; // keep the product of d_i^p_i % mod ;
        long _product_of_di; // keep the product of product(d_i); this is constant once assign a value;

        long _lazy_sum; // this is the essence of the segment tree: lazy operation.
        int tot ; // the total elements in this interval;

        seg_node left, right; // keep track of the left and right sub-intervals;

        seg_node(long _sum, long _product) {
            this._sum = _sum;
            this._product = power_mod(_product, _sum);
            this._product_of_di = _product;
            _lazy_sum = 0;
            tot = 1;
            left = right = null;
        }
    }

    private long power_mod(long n, long m) {
        if (m == 0) return 1;
        if (m == 1) return n;
        long ret = power_mod(n, m >> 1);
        return (m & 1) == 0 ? ret * ret % mod : ret * ret % mod * n % mod;
    }
}
