package my;

import net.kingder.utils.io.MyInputReader;
import net.kingder.utils.io.MyOutputWriter;

public class Stars {
    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
	    int N = in.nextInt();
	    long[] X = new long[N], Y = new long[N], W = new long[N];
	    for(int i = 0; i < N; i ++) {
		    X[i] = in.nextLong();
		    Y[i] = in.nextLong();
		    W[i] = in.nextLong();
	    }
	    long ret = Long.MIN_VALUE;

	    for(int i = 0; i < N; i ++)
		    for(int j = i + 1; j < N; j ++) {
			    long smaller = 0;
			    long bigger = 0;
			    for(int k = 0; k < N; k ++) {
				    long sign = (X[j] - X[i]) * (Y[k] - Y[i]) - (Y[j] - Y[i]) * (X[k] - X[i]);
				    if(sign < 0)
					    smaller += W[k];
				    else if(sign > 0)
					    bigger += W[k];
			    }

			    for(int sub = 0; sub < 4; sub ++) {
				    if((sub & 1) == 1)
					    smaller += W[i];
				    else
				        bigger += W[i];
				    if((sub & 2) == 2)
					    smaller += W[j];
				    else
				        bigger += W[j];
				    if(Math.min(smaller, bigger) > ret)
					    ret = Math.min(smaller, bigger);
				    if((sub & 1) == 1)
					    smaller -= W[i];
				    else
					    bigger -= W[i];
				    if((sub & 2) == 2)
					    smaller -= W[j];
				    else
					    bigger -= W[j];
			    }

		    }
	    if(ret == Long.MIN_VALUE)
		    ret = 0;
	    out.printLine(ret);
    }
}
