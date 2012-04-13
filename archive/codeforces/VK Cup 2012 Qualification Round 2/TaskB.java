/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package my.mypackage;

import net.kingder.utils.common.Pair;
import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.HashMap;

public class TaskB {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        HashMap<Pair<Integer, Integer>, Integer> maker = new HashMap<Pair<Integer, Integer>, Integer>();
        HashMap<Pair<Integer, Integer>, Integer> cap = new HashMap<Pair<Integer, Integer>, Integer>();


        for (int i = 0; i < n; i++) {
            int a = in.nextInt(), b = in.nextInt();
            if (maker.containsKey(Pair.makePair(b, a)))
                maker.put(Pair.makePair(b, a), maker.get(Pair.makePair(b, a)) + 1);
            else
                maker.put(Pair.makePair(b, a), 1);
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            if (cap.containsKey(Pair.makePair(b, a)))
                cap.put(Pair.makePair(b, a), cap.get(Pair.makePair(b, a)) + 1);
            else
                cap.put(Pair.makePair(b, a), 1);
        }
//        out.printLine( maker.toString() );
//        out.printLine( cap.toString() );
        HashMap<Integer, Integer> pm = new HashMap<Integer, Integer>(), pc = new HashMap<Integer, Integer>();
        int max_c = 0, max_b = 0;
        for (Pair<Integer, Integer> o : maker.keySet()) {
            if (pm.containsKey(o.first))
                pm.put(o.first, pm.get(o.first) + maker.get(o));
            else
                pm.put(o.first, maker.get(o));
            if (cap.containsKey(o))
                max_b += Math.min(maker.get(o), cap.get(o));
        }
        for (Pair<Integer, Integer> o : cap.keySet()) {
            if (pc.containsKey(o.first))
                pc.put(o.first, pc.get(o.first) + cap.get(o));
            else
                pc.put(o.first, cap.get(o));
        }
//        out.printLine( pm.toString() );
//        out.printLine( pc.toString() );
        for (Integer o : pm.keySet()) {
            if(pc.containsKey(o))
                max_c += Math.min(pc.get(o), pm.get(o));
        }
        out.printLine(max_c, max_b);
    }
}
