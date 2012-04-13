package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.*;

public class TaskE {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        HashMap<Integer, ArrayList<Pair<Integer, Integer>>> My = new HashMap<Integer, ArrayList<Pair<Integer, Integer>>>();

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (!My.containsKey(a)) {
                My.put(a, new ArrayList<Pair<Integer, Integer>>());
            }
            My.get(a).add(Pair.makePair(b, i + 1));
        }

        int cnt = My.size();
        ArrayList<Pair<Integer, Integer>> order = new ArrayList<Pair<Integer, Integer>>();
        for (Integer p : My.keySet()) {
            ArrayList<Pair<Integer, Integer>> o = My.get(p);
            Collections.sort(o, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    return o2.first.compareTo(o1.first);
                }
            });
            order.add(Pair.makePair(o.size(), p));
        }

        Collections.sort(order, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.first.compareTo(o2.first);
            }
        });

        int now = 0;
        int[] index = new int[cnt];
        long[] height = new long[cnt + 1];
        int[] idx = new int[cnt + 1];

        Arrays.fill(index, 0);
        Arrays.fill(height, 0);

        long ret = 0;
        int ans1 = 0, ans2 = 0;

        while ( now < cnt - 1){
            int sz = order.get(now).first;
            for( int j = now ; j < cnt ; j ++){
                int id = order.get( j ).second ;
                int st = Math.min( sz+1 , order.get(j).first);
                while ( index[j] < st )
                   Add( height, idx,cnt,j+1,My.get(id).get(index[j]++).first);
            }
            do{
                int q = Query(height, idx, now + 2, cnt);
                if (height[now + 1] + height[q] > ret) {
                    ret = height[now + 1] + height[q];
                    ans1 = order.get(now).second;
                    ans2 = order.get(q - 1).second;
                }
                now++;
            }while (now  < cnt - 1 && order.get(now).first.equals(sz));
        }


        int r = Math.min(My.get(ans1).size(), My.get(ans2).size());

        ArrayList<Integer> ans = new ArrayList<Integer>();

        if (My.get(ans1).size() > My.get(ans2).size()) {
            put(ans, My.get(ans1), My.get(ans2), r);
        } else {
            put(ans, My.get(ans2), My.get(ans1), r);
        }
        if (My.get(ans1).size() != r){
            ans.add(My.get(ans1).get(r).second);
        }
        if (My.get(ans2).size() != r){
            ans.add(My.get(ans2).get(r).second);
        }
        out.printLine(ret);
        out.printLine(2 * r + (My.get(ans1).size() != My.get(ans2).size() ? 1 : 0));
        for (int i = 0; i < ans.size(); i++) {
            if (i != 0) out.print(" ");
            out.print(ans.get(i));
        }
        out.printLine();
    }

    private void put(ArrayList<Integer> ans, ArrayList<Pair<Integer, Integer>> pairs, ArrayList<Pair<Integer, Integer>> pairs1, int r) {
        for (int i = 0; i < r; i++) {
            ans.add(pairs.get(i).second);
            ans.add(pairs1.get(i).second);
        }
    }

    private int Query(long[] height, int[] idx, int l, int r) {
        int ret = r;
        while (true) {
            ret = height[ret] > height[r] ? ret : r;
            if (r == l) break;
            for (r -= 1; r - l >= lowbit(r); r -= lowbit(r)) {
                ret = height[ret] > height[idx[r]] ? ret : idx[r];
            }
        }
        return ret;
    }

    private void Add(long[] height, int[] idx, int k, int d, Integer v) {
        height[d] += v;
        for (int i = d; i <= k; i += lowbit(i)) {
            idx[i] = height[idx[i]] > height[d] ? idx[i] : d;

            for (int j = 1; j < lowbit(i); j <<= 1) {
                idx[i] = height[idx[i]] > height[idx[i - j]] ? idx[i] : idx[i - j];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

}
