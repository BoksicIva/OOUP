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
system("pause");
return 0;
/* TRAZENI ISPIS
  Hamlet pozdravlja: vau!
  Ofelija pozdravlja: mijau!
  Polonije pozdravlja: vau!
  Hamlet voli kuhanu govedinu
  Ofelija voli konzerviranu tunjevinu
  Polonije voli kuhanu govedinu
  */
}

