package my.mypackage;

import java.util.*;

public class EllysNumbers {

    ArrayList<Long> AL;
    long ret;

    public long getSubsets(long n, String[] special) {
        String stream = "";
        for (String str : special)
            stream = stream.concat(str);

        //System.out.println(stream);
        String[] numbers = stream.split("\\s+");
        AL = new ArrayList<Long>();

        boolean hasOne = false ;
        for (String str : numbers) {
            long x = Long.parseLong(str);
            if (n % x == 0) {
                //System.out.printf("%d,%d\n", x, n / x);
                if (gcd(x, n / x) == 1){
                    if( x != 1 )
                        AL.add(x);
                    else
                        hasOne = true ;
                }

            }
        }
        //System.out.println(AL);

        TreeSet<?>[] Myset = new TreeSet<?>[ AL.size() ] ;
        HashMap<Integer,Integer> all = new HashMap<Integer, Integer>();

        int id = 0 ;
        for (long item : AL) {

            int index = AL.indexOf( item );
            TreeSet<Integer> my = new TreeSet<Integer>();

            for (int nn = 2; nn * nn <= item; nn++)
                if (item % nn == 0) {
                    while (item % nn == 0) item /= nn;
                    my.add( nn  );
                    if( !all.containsKey( nn ) ){
                        all.put( nn , id ++ ) ;
                    }
                }
            if( item != 1 ){
                my.add( (int)item );
                if( !all.containsKey( (int)item ) ){
                    all.put( (int)item , id ++ ) ;
                }
            }

            Myset[ index ] = my ;
            //System.out.println( Myset[index].toString() ) ;
        }

       // System.out.println( all.toString() );
        for( Integer key : all.keySet() ){
            if( n % key == 0 )while( n % key == 0 ) n /= key ;
        }
        if( n != 1 )
            return 0;
        
        AL.clear();
        for( TreeSet<?> item: Myset ){
            int code = 0 ;
            for( Object it : item ){
                 code ^= (1 << all.get( it ) );
            }
            AL.add( (long)code ) ;
        }
        int size = all.size();
        int limit = 1 << size ;
        
        int[] dp = new int[ limit + 10 ] ;
        dp[ 0 ] = 1 ;
        for( long code : AL ){
            //System.out.println( code );
            for( int mask = limit - 1 ; mask >= 0 ; mask -- ){
                  if( (mask & code) == 0 ){
                      dp[ mask ^ (int)code ] += dp[ mask ] ;
                  }
            }
        }


        //System.out.println( dp[ limit - 1 ] );
        return hasOne ? dp[ limit - 1 ] << 1  : dp[ limit - 1 ];

    }

    private long gcd(long a, long b) {

        return b != 0 ? gcd(b, a % b) : a;
    }

}

