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


int main(int argc, char *argv[]){
	
	int n , k , l ,c , d , p, nl, np ;
	cin >> n >> k >> l >> c >> d >> p >> nl >> np ;
	cout << min( l*k / nl , min( c * d , p / np ) ) / n << endl;
	return 0;
}
