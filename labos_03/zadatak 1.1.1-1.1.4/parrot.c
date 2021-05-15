#include <stdio.h>
#include <stdlib.h>


typedef char const* (*PTRFUN)();

struct Parrot{
    PTRFUN *vtable;
    char const* name;
};

char const* name(void* this){
    return ((struct Parrot*)this)->name;
}


char const* parrotGreet(void){
  return "Sto mu gromova!";
}

char const* parrotMenu(void){
  return "brazilske orahe";
}

PTRFUN parrotVTable[3]= {name,parrotGreet,parrotMenu};

void constructParrot(struct Parrot* parrot,char* name){
    parrot->name=name;
    parrot->vtable=parrotVTable;
}

void* create(char* name){
  struct Parrot* parrot =  malloc(sizeof(struct Parrot));
  constructParrot(parrot,name);
  return parrot;
}