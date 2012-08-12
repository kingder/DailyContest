#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct st_t
{
	int status;
	short* pdata;
	char* d;
	char errstr[32];
};


const char* str1 = "ABDFLjlero 我们都是saf";

char* tolower(const char s[]){
	int i = strlen(s);
	char* t = (char*)malloc( i*sizeof(char));
	for( i; i >= 0 ; i -- ){
		printf("%d\n",i);
		
		if( s[i] >= 'A' && s[i] <= 'Z')
			t[i] = s[i] + ('a'-'A');
		else
			t[i] = s[i];

	}
	return t;
}

int A(){
	printf("old str[%s] after lower[%s]\n", str1,tolower(str1));
}

void swap( int* a , int* b ){
	int c = *a;
	*a = *b;
	*b = c;
}

int main(int argc, char *argv[]){

	int n ; scanf("%d",&n);
	int B[100];
	for( int i = 0 ; i < n ; i ++ ) scanf("%d",&B[i]);

	int lo = 0 , hi = 0 ;
	while( hi < n ){
		while( lo < n && B[lo] < 0 ) ++lo;
		while( hi < n && B[hi] > 0 ) ++hi;
		if( lo < n && hi < n ){
			swap( &B[lo] , &B[hi] );
			++lo;
		}
	}
	for( int i = 0 ; i < n ; i ++ ) printf("%d ",B[i]); puts("");

	A();

	st_t st[16];
	char* p = (char*)(st[2].errstr+32);
	printf("%d\n",(p-(char*)(st)));

	unsigned long val = 0 ;
	char a = 0x48;
	char b = 0x52;
	int c = b << 8 ;

	val = b << 8 | a ;
	printf("%d,%ul",c,val);



	printf("Hello, world\n");
	
	
	return 0;
}
/*
6
-3 1 2 -1 -3 4

6
-3 -1 -2 -1 -3 -4

6
3 1 2 1 3 4

6
-3 -1 -2 -1 -3 4
6
4 -1 -2 -3 -4 -4

*/