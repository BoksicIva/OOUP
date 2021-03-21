#include <stdio.h>
#include <stdlib.h>
 
// KORIŠTENI MATERIJALI :
// https://www.youtube.com/watch?v=oS3a7wn9P_s
// knjiga : Design Patterns for Embedded Systems in C

typedef double (*PTRFUN)();

/**
 * class Unary_Function
 **/
struct Unary_Function{
  struct UnaryfuncTable* funcTable;
  int lower_bound;
  int upper_bound;
};

struct  UnaryfuncTable {
  PTRFUN value_at;
  PTRFUN negative_value_at;
};

void tabulate(struct Unary_Function* uf) {
      for(int x = uf->lower_bound; x <= uf->upper_bound; x++) {
        printf("f(%d)=%lf\n", x, uf->funcTable->value_at(uf,(double)x));
      }
}

double value_at(double x){
  printf("Call of virtual function");
  exit(0);
}


double negative_value_at(struct Unary_Function* uf ,double x){
  return -1*uf->funcTable->value_at(uf,x);
}

struct  UnaryfuncTable unaryFuncTable= {value_at,negative_value_at};

static _Bool same_functions_for_ints(struct Unary_Function *f1,struct Unary_Function *f2, double tolerance) {
      if(f1->lower_bound != f2->lower_bound) return 0;
      if(f1->upper_bound != f2->upper_bound) return 0;
      for(int x = f1->lower_bound; x <= f1->upper_bound; x++) {
        double delta = f1->funcTable->value_at(f1,x) - f2->funcTable->value_at(f2,x);
        if(delta < 0) delta = -delta;
        if(delta > tolerance) return 0;
      }
      return 1;
    };

void constructUnaryFunction(struct Unary_Function* uf,int lower_bound,int upper_bound){
    uf->lower_bound=lower_bound;
    uf->upper_bound=upper_bound;
    uf->funcTable=&unaryFuncTable;
}

struct Unary_Function* createUnaryFunction(int lower_bound,int upper_bound){
  struct Unary_Function* uf = (struct Unary_Function*) malloc(sizeof(struct Unary_Function));
  constructUnaryFunction(uf,lower_bound,upper_bound);
  return uf;
}

/**
 * class Square
 **/
struct Square{
  struct  UnaryfuncTable* funcTable;
  int lower_bound;
  int upper_bound;
};

double SquareValue_at(struct Square* square, double x){
  return x*x;
}

struct  UnaryfuncTable squareFuncTable={(double (*)(struct Square*,double))SquareValue_at,
                                        (double (*)(struct Square*,double))negative_value_at};



struct Square* constructSquare(struct Square* square,int lower_bound,int upper_bound){
    constructUnaryFunction(square,lower_bound,upper_bound);
    square->funcTable=&squareFuncTable;
    return square;
}

struct Square* createSquare(int lower_bound,int upper_bound){
  struct Square* square =malloc(sizeof(struct Square));
  square=constructSquare(square,lower_bound,upper_bound);
  return square;
}

/**
 * class Linear
 **/
struct Linear{
   struct  UnaryfuncTable* funcTable;
   int lower_bound;
   int upper_bound;
   double a;
   double b;
};

double LinearValue_at(struct Linear* linear,double x){
  return linear->a*x+linear->b;
}

struct  UnaryfuncTable linearFuncTable={(double (*)(struct Linear*,double))LinearValue_at,
                                        (double (*)(struct Linear*,double))negative_value_at};

struct Linear* constructLinear(struct Linear* linear,int lower_bound,int upper_bound,double a, double b){
    constructUnaryFunction(linear,lower_bound,upper_bound);
    linear->a=a;
    linear->b=b;
    linear->funcTable=&linearFuncTable;
    return linear;
}

struct Linear* createLinear(int lower_bound,int upper_bound,double a, double b){
  struct Linear* linear = malloc(sizeof(struct Linear));
  linear=constructLinear(linear, lower_bound, upper_bound, a, b);
  return linear;
}


int main(void){
  struct Unary_Function* f1=createSquare(-2,2);
  tabulate(f1);
  struct Linear* f2=createLinear(-2,2,5,-2);
  tabulate(f2);
  //printf("f1==f2: %s\n", struct Unary_Function::same_functions_for_ints(f1, f2, 1E-6)==1 ? "DA" : "NE");
  printf("f1==f2: %s\n",same_functions_for_ints(f1, f2, 1E-6)==1 ? "DA" : "NE");
  //printf("neg_val f2(1) = %lf\n", f2->negative_value_at(1.0));
  printf("neg_val f2(1) = %lf\n",f2->funcTable->negative_value_at(f2,1.0));
  free(f1); 
  free(f2); 
  return 0;

}
