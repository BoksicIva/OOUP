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

// void createNDogs(int n){
//   for(int i=0;i<n;i++)
//     createDog(name?);
// }

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



 /*Potrebno je oblikovati sljedeće elemente rješenja.
Dvije tablice pokazivača na funkcije koje definiraju ponašanje konkretnih tipova, kao i kôd za njihovo incijaliziranje. 
Prikladna deklaracija podatkovnog tipa za pohranjivanje elemenata tih dviju tablica bila bi: typedef char const* (*PTRFUN)();
Podatkovni tip struct Animal koji sadrži i) pokazivač na ime ljubimca te ii) pokazivač na tablicu funkcija (vidi gore) 
koja definira ponašanje odgovarajućeg konkretnog tipa. Uputa: tablicu pokazivača mogli bismo i umetnuti u tip Animal ali obično preferiramo 
rješenje s pokazivačem kako bismo što više smanjili memorijski otisak polimorfnih objekata.
Funkcije animalPrintGreeting i animalPrintMenu koje generiraju specificirani ispis pozivanjem odgovarajućeg elementa tablice funkcija zadanog polimorfnog objekta.
Funkcije constructDog i constructCat koje primaju i) pokazivač na memorijski prostor u kojem treba stvoriti objekt te 
ii) pokazivač na znakovni niz s imenom ljubimca. Funkcije trebaju u zadanom memorijskom prostoru inicijalizirati objekt odgovarajućeg konkretnog tipa.
Funkcije createDog i createCat koje alociraju memoriju i pozivaju funkcije constructDog odnosno constructCat.
Uputa: nemojte komplicirati, službeno rješenje ima manje od 70 redaka uredno formatiranog C-a.
Obratite pažnju na to da deklaracija PTRFUN pfun; u C-u (ali ne i C++-u!) definira pokazivač na
 funkciju s nespecificiranim argumentima. To znači da pfun može pokazivati na bilo koju funkciju koja vraća char const* (detalji).
  Naravno, pri korištenju pokazivača pfun moramo paziti da broj i tipovi argumenata navedeni u pozivu odgovaraju argumentima funkcije na 
  koju pokazivač pokazuje (u suprotnom ponašanje programa nije definirano).
Vaše rješenje mora biti takvo da memorijsko zauzeće za svaki primjerak "razreda" (psa, mačke) ne ovisi o broju virtualnih metoda. 
Drugim riječima, dodavanje nove virtualne metode ne smije kod svakog primjerka psa i mačke povećati memorijsko zauzeće.

Pokažite da je konkretne objekte moguće kreirati i na gomili i na stogu (detalji). Memorijski prostor na stogu zauzmite lokalnom varijablom, 
a za zauzimanje memorije na gomili pozovite malloc.

Napišite funkciju za stvaranje n pasa, gdje je n argument funkcije (npr. za potrebe vuče saonica). 
Pokažite kako bismo to ostvarili jednim pozivom funkcije malloc i potrebnim brojem poziva funkcije constructDog.
Nakon rješavanja zadatka, uspostavite vezu s terminologijom iz objektno orijentiranih jezika. Koji elementi vašeg 
rješenja bi korespondirali s podatkovnim članovima objekta, metodama, virtualnim metodama, konstruktorima, te virtualnim tablicama?

Ako vas je objektno orijentirano programiranje u C-u očaralo i želite o tome znati više - pogledajte sljedeću knjigu.
 Međutim, prije nego što donesete definitivnu odluku o prelasku s C++-a na C, preporučamo vam da ipak razmislite o iznimkama, predlošcima,
  STL-u i novim mogućnostima koje nude standardi iz 2011. i 2014. godine.*/
}

