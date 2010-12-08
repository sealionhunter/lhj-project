// address.cpp
#include <iostream>

int main()
{
  using namespace std;
  int donuts = 6;
  double cups = 4.5;
  
  cout << "donuts's value is " << donuts;
  cout << " and donuts's address is " << &donuts << endl;
  cout << "cups's value is " << cups;
  cout << " and cups's address is " << &cups << endl;
  return 0;  
}
