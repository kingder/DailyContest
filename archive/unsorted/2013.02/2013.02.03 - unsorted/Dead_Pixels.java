/*
 * Copyright (c) 2013.
 */

package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Dead_Pixels {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int cases = in.nextInt();
	    for(int tc = 1; tc <= cases; tc ++) {
		    out.print("Case #" + tc + ": ");

		    int W = in.nextInt();
		    int H = in.nextInt();
		    int P = in.nextInt();
		    int Q = in.nextInt();
		    int N = in.nextInt();
		    int X = in.nextInt();
		    int Y = in.nextInt();
		    int a = in.nextInt();
		    int b = in.nextInt();
		    int c = in.nextInt();
		    int d = in.nextInt();

		    int[] Xs = new int[N], Ys = new int[N];
		    Xs[0] = X;
		    Ys[0] = Y;
		    for(int i = 1; i < N; i ++) {
			    Xs[i] = (Xs[i - 1] * a + Ys[i - 1] * b + 1) % W;
			    Ys[i] = (Xs[i - 1] * c + Ys[i - 1] * d + 1) % H;
		    }
			for(int i = 0; i < N; i ++){
				Xs[i] += 1; Ys[i] += 1;
				//out.printLine(Xs[i] + " " + Ys[i]);
			}

		    Rect monitor = new Rect(new Point(1,1), new Point(W-P+1, H-Q+1));
		    Rect[] deads = new Rect[N];
		    for(int i = 0; i < N; i ++){
			     deads[i] = monitor.intersect(new Rect(new Point(Xs[i]-P+1, Ys[i]-Q+1), new Point(Xs[i], Ys[i])));
		    }

		    out.printLine(monitor.area() - gao(deads, N, monitor.bottomRight.y));
	    }
    }

	private int gao(Rect[] deads, int N, int HL) {
		int ret = 0;
		VLine[] lines = new VLine[N + N];
		for(int i = 0; i < N; i ++) {
			lines[i<<1] = new VLine(deads[i].topLeft.x, deads[i].topLeft.y, deads[i].bottomRight.y, 1);
			lines[i<<1|1] = new VLine(deads[i].bottomRight.x + 1, deads[i].topLeft.y, deads[i].bottomRight.y, -1);
		}
		Arrays.sort(lines, new Comparator<VLine>() {
			@Override
			public int compare(VLine o1, VLine o2) {
				return o1.x - o2.x;
			}
		});
		SegTree st = new SegTree(HL);
		st.build(1, 1, HL);
		//System.out.println("+======================+");
		/*
		for(int i = 0; i < lines.length; i ++){
			lines[i].print();
		} */
		st.update(1, 1, HL, lines[0].y1, lines[0].y2, lines[0].val);
		for(int i = 1; i < lines.length; i ++) {
			ret += (lines[i].x - lines[i-1].x) * st.length();
			//System.out.println( ret + " " + st.length());
			st.update(1, 1, HL, lines[i].y1, lines[i].y2, lines[i].val);

		}
		//System.out.println(ret);
		return ret;
	}


	class SegNode{
		int cover, len;
		SegNode(int _c, int _l){
			cover = _c;
			len = _l;
		}
	}
	class SegTree{
		SegNode[] pool ;
		SegTree(int size) {
			pool = new SegNode[size<<2];
		}
		void build(int n, int l, int r){
			if(l > r) return;
			pool[n] = new SegNode(0, 0);
			if(l == r) return;
			int m = (l + r) >> 1;
			build(n << 1, l, m);
			build(n << 1 | 1, m + 1, r);
		}
		void update(int root, int left, int right, int l ,int r, int val){

			if(l == left && r == right) {
				pool[root].cover += val;
				if(pool[root].cover <= 0){
					if(l != r)
						pool[root].len = pool[root << 1].len + pool[root << 1 | 1].len;
					else
						pool[root].len = 0;
				} else
					pool[root].len = r - l + 1;
				return;
			}
			int mid = (left + right) >> 1;
			if(r <= mid){
				update(root << 1, left, mid, l, r, val);
			}else if( l >= mid + 1){
				update(root << 1 | 1, mid + 1, right, l, r, val);
			}else{
				update(root << 1, left, mid , l, mid, val);
				update(root << 1 | 1, mid + 1, right, mid + 1, r, val);
			}
			if(pool[root].cover > 0)
				pool[root].len = right - left + 1;
			else
				pool[root].len = pool[root << 1].len + pool[root << 1 | 1].len;
		}
		int length(){
			return pool[1].len;
		}
	}

	class VLine{
		int x, y1, y2, val;
		VLine(int _x, int _y1, int _y2, int _val){
			x = _x;
			y1 = _y1;
			y2 = _y2;
			val = _val;
		}
		void print(){
			System.out.println(x + " " + y1 + " " + y2 + " " + val);
		}
	}
	class Point{
		public int x, y;
		Point(int _x, int _y){
			x = _x;
			y = _y;
		}

	}
	class Rect{
		public Point topLeft, bottomRight;
		Rect(Point _tl, Point _bR){
			topLeft = _tl;
			bottomRight = _bR;
		}
		public Rect EMPTY(){
			return new Rect(new Point(0,0), new Point(0,0));
		}
		public Rect intersect(Rect dest){
			Rect ret = EMPTY();
			ret.topLeft.x = Math.max(topLeft.x, dest.topLeft.x);
			ret.topLeft.y = Math.max(topLeft.y, dest.topLeft.y);
			ret.bottomRight.x = Math.min(bottomRight.x, dest.bottomRight.x);
			ret.bottomRight.y = Math.min(bottomRight.y, dest.bottomRight.y);
			return ret;
		}
		public int area(){
			return (bottomRight.x - topLeft.x + 1) * (bottomRight.y - topLeft.y + 1);
		}
	}
}
