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

const int MOD = 1000000007 ;


LL power( LL m , LL p ){
	LL ret = 1 ;
	for( int i = 0 ; i < p ; i ++ )
		ret = ret * m % MOD ;
	return ret;
}
int main(int argc, char *argv[]){
	//printf("Hello, world\n");
	
	LL n , m , k ;
	while( cin >> n >> m  >> k ){
		if( k > n || k == 1){
			cout << power( m , n ) << endl; 
		}else if( k == n ){
			cout << power( m , (n + 1) / 2  ) << endl;
		}else{
			if( k % 2 == 0 ){
				cout << m % MOD << endl;
			}else{
				cout << m * m % MOD << endl;
			}
		}
	}
	
	return 0;
}
