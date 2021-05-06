
#include <stdio.h>
#include <stdlib.h>

// koristeni sadržaj http://users.ece.utexas.edu/~adnan/libc/qsort.c.html

/* Napišite u C-u funkciju mymax koja pronalazi najveći element zadanog polja. 
  Vaša implementacija treba biti primjenljiva na polja elemenata svih mogućih tipova: cijelih brojeva, 
  pokazivača ili struktura te omogućiti rad s različitim vrstama usporedbi. Kako biste ostvarili nadogradivost bez promjene, 
  funkcija mymax treba primiti pokazivač na kriterijsku funkciju koja vraća 1 ako je njen prvi argument veći od drugoga, a 0 inače. 
  Funkciju oblikujte prema primjeru funkcije qsort standardne biblioteke:
  
  const void* mymax(  const void *base, size_t nmemb, size_t size,  int (*compar)(const void *, const void *));

Definirajte kriterijske funkcije za usporedbu cijelih brojeva, znakova i znakovnih nizova.
Nazovite te kriterijske funkcije gt_int, gt_char i gt_str. U izvedbi funkcije gt_str, posao delegirajte funkciji strcmp.
Pokažite da vaša funkcija može pronaći najveće elemene sljedećih nizova:

};*/

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

int gt_str(const void *left, const void *right){
  char** value1 = left;
  char** value2 = right;
  return strcmp(*value1, *value2) == 1 ? 1 : 0;
}


const void *mymax(const void *base, size_t nmemb, size_t size, int (*compar)(const void *, const void *)){
  char *base_ptr =base;
  char *max =  base;
  for(int i=1;i<nmemb;i++){
    char *next = &base_ptr[size * i];
    if(compar(max,next)==0)
        max=next;
  }

   return max;
}

int main(){
  int arr_int[] = { 1, 3, 5, 7, 4, 6, 9, 2, 0 };
  char arr_char[]="Suncana strana ulice";
  const char* arr_str[] = {"Gle", "malu", "vocku", "poslije", "kise", "Puna", "je", "kapi", "pa", "ih", "njise"};
  printf("Najveci element polja intova je : %d\n",*(const char*)mymax(arr_int,sizeof(arr_int)/sizeof(*arr_int),sizeof(arr_int[0]),gt_int));
  printf("Najveci element polja charova je : %c\n",*(const char*)mymax(arr_char,sizeof(arr_char)/sizeof(*arr_char),sizeof(arr_char[0]),gt_char));
  const void *pointer = mymax(arr_str,sizeof(arr_str)/sizeof(*arr_str),sizeof(arr_str[0]),gt_str);
  char **value=pointer;
  printf("Najveci element polja stringova je : %s\n",*value);
}