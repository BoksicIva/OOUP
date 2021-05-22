#include "myfactory.h"
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

// https://gist.github.com/tailriver/30bf0c943325330b7b6a

typedef size_t (*SIZE)();
typedef void (*CONSTRUCT)(void*,const char*);


int size_of(void* handle){
    SIZE size_of_plugin =(SIZE)dlsym(handle, "size_of");
    return size_of_plugin();
}

void* construct(void* handle,char const* ctorarg,void* animal){
    CONSTRUCT construct_animal =(CONSTRUCT)dlsym(handle, "construct");
    construct_animal(animal,ctorarg);
}

void* myfactory(char const* libname, char const* ctorarg){
    void *handle;

    
    handle = dlopen(libname, RTLD_LAZY);

    if(!handle) {
		fprintf(stderr, "Error: unknown animal type: %s\n",libname);
		return NULL;
	}
    

    size_t size=size_of(handle);

    void* animal=alloca(size);  

    construct(handle,ctorarg,animal);
    
    return animal;
}



