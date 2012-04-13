package my.mypackage;

public class EllysCheckers {
   int n ;
   int can[][] = new int[1<<20][2];
   public String getWinner(String board) {
	   n = board.length();
       int limit = 1 << n ;
       int nowstate = 0 ;
       for( int i = 0 ; i < limit ; i ++ )for( int j = 0 ; j < 2 ; j ++ )
           can[i][j] = -1 ;
       for( int i = 0 ; i < n ; i ++ ){
           if( board.charAt( i ) == 'o' )
               nowstate = (nowstate << 1) + 1;
           else
               nowstate <<= 1;
       }
       return canwin( nowstate , 0  ) == 1 ?"YES":"NO";
   }

    private int canwin( int state , int now ) {
        if((state&1)==1) state^=1;
        if( can[state][now] != -1 )
            return can[state][now] ;

        for( int i = n-1 ; i >= 1 ; i -- ){
            if( i >= 1 && ((state>>(i-1)) & 3 )  == 2 ){

                if( canwin( state ^ (3<<(i-1) ) , now^1 ) == 0 )
                    return can[state][now] = 1 ;
            }
            if( i >= 3 && ((state >> (i-3)) & 15) == 14 ){
                if( canwin( state ^ (9<<(i-3)) , now^1 ) == 0 )
                    return can[state][now] = 1;
            }
        }
        return can[state][now] = 0 ;
    }
}

