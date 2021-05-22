#include <stdio.h>
#include <stdlib.h>


typedef char const* (*PTRFUN)();

struct Tiger{
    PTRFUN *vtable;
    char const* name;
};

char const* name(void* this){
    return ((struct Tiger*)this)->name;
}


char const* tigerGreet(void){
  return "Mijau!";
}

char const* tigerMenu(void){
  return "mlako mlijeko.";
}

PTRFUN tigerVTable[3]= {name,tigerGreet,tigerMenu};

void construct(struct Tiger* tiger,const char* name){
    tiger->name=name;
    tiger->vtable=tigerVTable;
}

void* create(const char* name){
  struct Tiger* tiger =  malloc(sizeof(struct Tiger));
  construct(tiger,name);
  return tiger;
}

size_t size_of(){
  return sizeof(struct Tiger);
}