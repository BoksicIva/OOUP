#include <iostream>
#include <cstdlib>
using namespace std;

class CoolClass{
public:
  virtual void set(int x){x_=x;};
  virtual int get(){return x_;};
private:
  int x_;
  int y_;
};

class PlainOldClass{
public:
  void set(int x){x_=x;};
  int get(){return x_;};
private:
  int x_;
};

int main() {
    cout<<sizeof(PlainOldClass)<<endl; // 4
    cout<<sizeof(CoolClass)<<endl;     // 16
    return 0;
}