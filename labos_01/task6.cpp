#include <stdio.h>
#include <iostream>
using namespace std;

class Base{
public:
  Base() {
    metoda();
  }

  virtual void virtualnaMetoda() {
    printf("ja sam bazna implementacija!\n");
  }

  void metoda() {
    printf("Metoda kaze: ");
    virtualnaMetoda();
  }
};

class Derived: public Base{
public:
  Derived(): Base() {
    metoda();
  }
  virtual void virtualnaMetoda() {
    printf("ja sam izvedena implementacija!\n");
  }
};

int main(){
  Derived* pd=new Derived();
  pd->metoda();
  return 0;
  /*
  Metoda kaze: ja sam bazna implementacija!
  Metoda kaze: ja sam izvedena implementacija!
  Metoda kaze: ja sam izvedena implementacija!
*/
}