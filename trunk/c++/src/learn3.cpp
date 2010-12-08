// learn3.cpp
#include <iostream>

struct CandyBar
{
  char name[20];
  double weight;
  int kal;
};

int main()
{
  using namespace std;
  CandyBar snack = {"Mocha Munch",2.3,350};
  cout << "Name: " << snack.name << endl;
  cout << "weight: " << snack.weight << endl;
  cout << "kal: " << snack.kal << endl;

  return 0;
}
