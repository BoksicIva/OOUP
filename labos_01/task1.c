#include <stdio.h>
#include <stdlib.h>

// KORISTENI MATERIJALI
// https://www.youtube.com/watch?v=2v_qM5SJDlY
// knjiga : Design Patterns for Embedded Systems in C

typedef char const* (*PTRFUN)();

/**
 * Animal
 **/
struct Animal{
  char* name;
  struct AnimalfuncTable* funcTable;
};

struct  AnimalfuncTable {
  PTRFUN greet;
  PTRFUN menu;
};

void animalPrintGreeting(struct Animal * animal){
  printf("%s pozdravlja: %s\n",animal->name,(*animal->funcTable->greet)());
}

void animalPrintMenu(struct Animal* animal){
  printf("%s voli %s\n",animal->name,(*animal->funcTable->menu)());
}



/**
 * Dog
 **/
char const* dogGreet(void){
  return "vau!";
}

char const* dogMenu(void){
  return "kuhanu govedinu";
}

struct AnimalfuncTable dogFuncTable= {dogGreet,dogMenu};

void constructDog(struct Animal* animal,char* name){
    animal->name=name;
    animal->funcTable=&dogFuncTable;
}

struct Animal* createDog(char* name){
  struct Animal* animal =  malloc(sizeof(struct Animal));
  constructDog(animal,name);
  return animal;
}

/**
 * Cat
 **/
char const* catGreet(void){
  return "mijau!";
}

char const* catMenu(void){
  return "konzerviranu tunjevinu";
}

struct AnimalfuncTable catFuncTable= {catGreet,catMenu};

void constructCat(struct Animal* animal,char* name){
  animal->name=name;
  animal->funcTable=&catFuncTable;
}

struct Animal* createCat(char* name){
  struct Animal* animal =malloc(sizeof(struct Animal));
  constructCat(animal,name);
  return animal;
}

void testAnimals(void){
  struct Animal* p1=createDog("Hamlet");
  struct Animal* p2=createCat("Ofelija");
  struct Animal* p3=createDog("Polonije");

  animalPrintGreeting(p1);
  animalPrintGreeting(p2);
  animalPrintGreeting(p3);

  animalPrintMenu(p1);
  animalPrintMenu(p2);
  animalPrintMenu(p3);

  free(p1); 
  free(p2); 
  free(p3);
}


int main(void){
testAnimals();
//na stogu
struct Animal p4;  
constructDog(&p4,"Peso");
system("pause");

//na stogu createdog s dogom
return 0;
/* TRAZENI ISPIS
  Hamlet pozdravlja: vau!
  Ofelija pozdravlja: mijau!
  Polonije pozdravlja: vau!
  Hamlet voli kuhanu govedinu
  Ofelija voli konzerviranu tunjevinu
  Polonije voli kuhanu govedinu
  */

// TEKST ZADATKA

 /*Potrebno je oblikovati sljede??e elemente rje??enja.
-Dvije tablice pokaziva??a na funkcije koje definiraju pona??anje konkretnih tipova, kao i k??d za njihovo incijaliziranje. 
Prikladna deklaracija podatkovnog tipa za pohranjivanje elemenata tih dviju tablica bila bi: typedef char const* (*PTRFUN)();

-Podatkovni tip struct Animal koji sadr??i i) pokaziva?? na ime ljubimca te ii) pokaziva?? na tablicu funkcija (vidi gore) 
koja definira pona??anje odgovaraju??eg konkretnog tipa. Uputa: tablicu pokaziva??a mogli bismo i umetnuti u tip Animal ali obi??no preferiramo 
rje??enje s pokaziva??em kako bismo ??to vi??e smanjili memorijski otisak polimorfnih objekata.

-Funkcije animalPrintGreeting i animalPrintMenu koje generiraju specificirani ispis pozivanjem odgovaraju??eg elementa tablice funkcija zadanog polimorfnog objekta.

-Funkcije constructDog i constructCat koje primaju i) pokaziva?? na memorijski prostor u kojem treba stvoriti objekt te 
ii) pokaziva?? na znakovni niz s imenom ljubimca. Funkcije trebaju u zadanom memorijskom prostoru inicijalizirati objekt odgovaraju??eg konkretnog tipa.

-Funkcije createDog i createCat koje alociraju memoriju i pozivaju funkcije constructDog odnosno constructCat.

Obratite pa??nju na to da deklaracija PTRFUN pfun; u C-u (ali ne i C++-u!) definira pokaziva?? na
 funkciju s nespecificiranim argumentima. To zna??i da pfun mo??e pokazivati na bilo koju funkciju koja vra??a char const* (detalji).
  Naravno, pri kori??tenju pokaziva??a pfun moramo paziti da broj i tipovi argumenata navedeni u pozivu odgovaraju argumentima funkcije na 
  koju pokaziva?? pokazuje (u suprotnom pona??anje programa nije definirano).
Va??e rje??enje mora biti takvo da memorijsko zauze??e za svaki primjerak "razreda" (psa, ma??ke) ne ovisi o broju virtualnih metoda. 
Drugim rije??ima, dodavanje nove virtualne metode ne smije kod svakog primjerka psa i ma??ke pove??ati memorijsko zauze??e.

Poka??ite da je konkretne objekte mogu??e kreirati i na gomili i na stogu (detalji). Memorijski prostor na stogu zauzmite lokalnom varijablom, 
a za zauzimanje memorije na gomili pozovite malloc.

Napi??ite funkciju za stvaranje n pasa, gdje je n argument funkcije (npr. za potrebe vu??e saonica). 
Poka??ite kako bismo to ostvarili jednim pozivom funkcije malloc i potrebnim brojem poziva funkcije constructDog.
Nakon rje??avanja zadatka, uspostavite vezu s terminologijom iz objektno orijentiranih jezika. Koji elementi va??eg 
rje??enja bi korespondirali s podatkovnim ??lanovima objekta, metodama, virtualnim metodama, konstruktorima, te virtualnim tablicama?
*/
}

