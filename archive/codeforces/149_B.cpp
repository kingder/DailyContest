#include <algorithm>
#include <iostream>
#include <vector>
#include <string>
#include <cstdio>
#include <cstring>
#include <cctype>

using namespace std;
#define FOR(i,n) for(int i=0;i<(n);i++)
#define FORE(i,a,b) for(int i=(a);i<=(b);i++)

template<class T> void checkmax(T& a ,T b) { if(a<b)a=b;}
typedef vector<int> VI;


bool only_one( string n ){
	FOR( i , n.size() )if( n[i] != '0' ){
		if( i < n.size() - 1 ) return false ;
	}
	return true;
}

int value_of( char c ){
	if( isdigit(c) ) return c - '0' ;
	else return c - 'A' + 10 ;
}
int convert( string n , int base ){
	int ret = 0 ;
	FOR( i , n.size() ) 
		ret = ret*base + value_of( n[i] ) ;
	//cout << ret << endl;
	return ret;
}

int list_base( string h ){
	int ret = 1 ;
	FOR( i , h.size() )
		checkmax( ret , value_of( h[i] ) );
	return ret+1;
}
int main(int argc, char *argv[]){
	string T;
	while( cin >> T ){
		string h , m ;
		h = T.substr( 0 , T.find( ":" ) );
		m = T.substr( T.find(":")+1 ) ;

		int base = max(list_base(h),list_base(m)) ;
		
		if( convert( h , base ) > 23 ){
			cout << 0 << endl;
			break;
		}
		if( only_one(h) && only_one(m) ){
			cout << -1 << endl;
			break;
		}
		
		VI ans ;
		for( int base = max(list_base(h),list_base(m)) ; ; base ++ ){
			if( convert( h , base ) <= 23 && convert( m , base ) <= 59 )
				ans.push_back( base ) ;
			else 
				break;
		}
		if( ans.size() == 0 ) ans.push_back( 0 ) ;
		FOR( i , ans.size() ){
			if( i ) putchar(' '); 
			printf("%d", ans[i]);
		}
		putchar('\n');

	}
	return 0;
}
