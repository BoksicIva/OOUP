#include <iostream>
#include <cstdlib>
#include<string.h>
using namespace std;


int gt_int(const void *left, const void *right){
  int value1 = *(const int*)left;
  int value2 = *(const int*)right;
  if (value1 > value2)
    return 1;
  return 0;
}

int gt_char(const void *left, const void *right){
  char value1 = *(const char*)left;
  char value2 = *(const char*)right;
  if (value1 > value2)
    return 1;
  return 0;
}

int gt_str(const char **left, const char **right){
   return strcmp(*left, *right) == 1 ? 1 : 0;
}

template <typename Iterator, typename Predicate>
Iterator mymax( Iterator first, Iterator last, Predicate pred){
    Iterator max = first;
    ++first;
    while (first!=last) {
        if (pred(max,first)==0) 
            max=first;
        ++first;
    }
    return max;
}

int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
char arr_char[]="Suncana strana ulice";
const char* arr_str[] = {"Gle", "malu", "vocku", "poslije", "kise", "Puna", "je", "kapi", "pa", "ih", "njise"};


int main(){
  const int* maxint = mymax( &arr_int[0], &arr_int[sizeof(arr_int)/sizeof(*arr_int)], gt_int);
  cout <<*maxint <<"\n";
  const char* maxchar = mymax( &arr_char[0], &arr_char[sizeof(arr_char)/sizeof(*arr_char)], gt_char);
  cout <<*maxchar <<"\n";  
  const char **maxstr = mymax( &arr_str[0], &arr_str[sizeof(arr_str)/sizeof(*arr_str)], gt_str);
  cout <<*maxstr <<"\n";     

  return 0;
}
