
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author lwc626
 */
public class DancingFoxes {
    public int minimalDays(String[] friendship) {
        int N = friendship.length;
        int[][] friends = new int[N][N];
        for(int i = 0; i < N; i ++)
            for(int j = 0; j < N; j ++)
                friends[i][j] = friendship[i].charAt(j) == 'Y' ? 1 : -1;
        for(int k = 0; k < N; k ++)
            for(int i = 0; i < N; i ++) if(friends[i][k] != -1)
                for(int j = 0; j < N; j ++) if(friends[k][j] != -1)
                    friends[i][j] = friends[i][j] == -1 ? friends[i][k] + friends[k][j] :
                            Math.min(friends[i][j], friends[i][k] + friends[k][j]);
        if(friends[0][1] == -1)
            return -1;
        return gao(friends[0][1]);

    }

    private int gao(int left) {
        if(left == 1) return 0;
        return gao( left - (left + 1) / 3) + 1;
    }
}

