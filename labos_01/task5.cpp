#include <iostream>
#include <cstdlib>
using namespace std;
 
  class B{
public:
  virtual int prva()=0;
  virtual int druga(int)=0;
};

class D: public B{
public:
  virtual int prva(){return 42;}
  virtual int druga(int x){return prva()+x;}
};

 // pfun pokazuje na funkciju bez parametara koja vraća int
  typedef int (*prvaFun)(B*); 
  // pfun pokazuje na funkciju s dva parametara koja vraća int
  typedef int (*drugaFun)(B*, int); 
  // odgovarajući operator pretvaranja izgleda ovako:
 // pfun = (int (*)()) 0; 

/*
Potrebno je napisati funkciju koja prima pokazivač pb na objekt razreda B te ispisuje povratne vrijednosti dvaju metoda, 
ali na način da u kodu ne navodimo simbolička imena prva i druga. Zadatak riješite primjenom pokazivača na slobodne funkcije
kakve smo koristili i do sada. Nemojte koristiti pokazivače na članske funkcije jer bi u tom slučaju vježba bila manje poučna. */

 void function(B* pb){
    unsigned long long virtualTableAddress = *(unsigned long long*)pb;
    
    prvaFun printFun = (prvaFun)(*(unsigned long long*)virtualTableAddress);
    cout<<printFun(pb)<<endl;

    drugaFun printFun2 = (drugaFun)(*(unsigned long long*)(virtualTableAddress+8));
    cout<<printFun2(pb,5)<<endl;
 }

 int main(void){
    D* d=new D();
    function(d);
    return 0;
 }