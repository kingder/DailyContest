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


bool judge_taxi( string name ){
	vector<char> vc;
	FOR( i , name.size())if(isdigit(name[i]))
		vc.push_back(name[i]);
	sort(vc.begin(),vc.end());
	vc.erase( unique( vc.begin(),vc.end() ) , vc.end() ) ;
	return vc.size() == 1;
}
bool judge_pizza( string name ){
	char pre = 'a';
	FOR( i , name.size() ){
		if( isdigit(name[i]) ){
			if( name[i] < pre )	pre = name[i] ;
			else return false;
		}
	}
	return true;
	vector<char> vc;
	FOR( i , name.size())if(isdigit(name[i]))
		vc.push_back(name[i]);
	
	sort(vc.begin(),vc.end());
	vc.erase( unique( vc.begin(),vc.end() ) , vc.end() ) ;
	return vc.size() == 6;
}
int main(int argc, char *argv[]){
//	printf("Hello, world\n");
	int T;

	while( cin >> T ){

		vector<int > VT,VP,VG;
		vector<string> VN;
		int mtaxi = 0 , mpizza = 0 , mgirl = 0 ;
		FOR( i ,  T ){

			int si ; string name;
			cin >> si >> name ;
			VN.push_back( name );
			int taxi = 0 , pizza = 0 ;
			string phone;
			FOR( j , si ){
				cin >> phone ;
				if( judge_taxi( phone ) ) taxi ++ ;
				else if( judge_pizza( phone ) ) pizza ++ ;
			}
			VT.push_back( taxi  ) ;
			VP.push_back( pizza ) ;
			VG.push_back( si-taxi-pizza) ;

			checkmax( mtaxi , taxi );
			checkmax( mpizza,pizza);
			checkmax( mgirl ,si-taxi-pizza );
			//cout << taxi << " " << pizza << " " << si-taxi-pizza  << endl;
		}
		//cout << mtaxi << " " << mpizza << " " << mgirl  << endl;
		
		bool f = false; 
		cout << "If you want to call a taxi, you should call: ";
		FOR( i , VT.size() ) if( VT[i] == mtaxi ){
			if( f ) cout << ", ";
			else f = true;
			cout  << VN[i] ;
		}
		cout << "." << endl;

		f = false;
		cout << "If you want to order a pizza, you should call: ";
			
		FOR( i , VT.size() ) if( VP[i] == mpizza ){
			if( f ) cout << ", ";
			else f = true;
			cout  << VN[i] ;
		}
		cout << "." << endl;
		cout << "If you want to go to a cafe with a wonderful girl, you should call: ";
			
		f = false;
		FOR( i , VT.size() ) if( VG[i] == mgirl ){
			if( f ) cout << ", ";
			else f = true;
			cout  << VN[i] ;
		}
		cout << "." << endl;
	}
	return 0;
}
