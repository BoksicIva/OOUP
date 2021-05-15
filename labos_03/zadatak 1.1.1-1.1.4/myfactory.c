#include "myfactory.h"
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>

// https://gist.github.com/tailriver/30bf0c943325330b7b6a

typedef struct Animal* (*PTRFUN)(const char*);

void* myfactory(char const* libname, char const* ctorarg){
    void *handle;

    
    handle = dlopen(libname, RTLD_LAZY);

    if(!handle) {
		fprintf(stderr, "Error: unknown animal type: %s\n",libname);
		return NULL;
	}

    PTRFUN create =(PTRFUN)dlsym(handle, "create");

    if (!create) {
        fprintf(stderr, "Error: %s\n", dlerror());
        //dlclose(handle);
        return NULL;
    }

    return create(ctorarg);

}
