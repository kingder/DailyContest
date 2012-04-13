#include <algorithm>
#include <iostream>
#include <vector>
#include <string>
#include <cstdio>
#include <cstring>
#include <cctype>
#include <cmath>

using namespace std;
#define FOR(i,n) for(int i=0;i<(n);i++)
#define FORE(i,a,b) for(int i=(a);i<=(b);i++)

template<class T> void checkmax(T& a ,T b) { if(a<b)a=b;}
typedef vector<int> VI;
typedef long long LL ;

const int MAXN = 3500000 ;

int prime[ MAXN ] , cnt = 0 ;
bool mark[ MAXN ] ;

void gen_p(){
	fill ( mark , mark + MAXN , false ) ;
	
	
	for( LL i = 2 ; i < MAXN ; i ++ ) if( !mark[i] ){
		for( LL j = i*i ; j < MAXN ; j += i ) mark[ j ] = true;
		prime[ cnt ++ ] = i ;
	}
	//cout << cnt << endl;
}


int main(int argc, char *argv[]){
	//printf("Hello, world\n");
	gen_p();
	//fill( win , win + 100 , -1 ) ;

	//cout << sqrt( 10000000000000L + 0.0 ) << endl;
	LL p ;
	while( cin >> p ){
		LL np = p ;
		if( p == 1 ){
			cout << "1\n0" << endl;
			continue;
		}
		int dc = 0 ;
		VI dv ;
		for( int i = 0 ; i < cnt && p != 1 ; i ++ ){
			if( p % prime[i] == 0 ){
				while( p % prime[i] == 0 ){
					p /= prime[i] , dc ++ ;
					dv.push_back( prime[i] ) ;
				}
			}
		}
		if( p != 1 ) dv.push_back( p ) ;
		//FOR( i , dv.size() ) cout << dv[i] << " " ; cout << endl;
		
		
		if( dv.size() == 1 || dv.size() == 0 ){
			cout << "1" << endl << "0" << endl; 
		}else if( dv.size() == 2 ){
			cout << "2" << endl;
		}else{
			cout << "1" << endl << dv[0]*dv[1] << endl;
		}

	}
	return 0;
}
