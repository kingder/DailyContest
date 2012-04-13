#include <algorithm>
#include <iostream>
#include <vector>
#include <string>
#include <cstdio>
#include <cstring>

using namespace std;
#define FOR(i,n) for(int i=0;i<(n);i++)
#define FORE(i,a,b) for(int i=(a);i<=(b);i++)

int main(int argc, char *argv[]){
	int K , A[13];
	cin >> K ; 
	FOR(i,12) cin >> A[i] ;
	sort( A, A+12 );
	int in = 0 , ans = 0 ;
	if( in < K )
	FOR( i , 12){
		in += A[12-i-1] ; ans ++ ;
		if( in >= K ) break;
	}
	if( in < K ) ans = -1 ;
	cout << ans << endl;
	return 0;
}
