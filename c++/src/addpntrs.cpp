// addpntrs.cpp

#include <iostream>

int main()
{
  using namespace std;
  double wages[3] = {10000.0, 20000.0, 30000.0};
  short stacks[3] = {3,2,1};
  double *pw = wages;
  short *ps = stacks;

  cout << "pw = " << pw << ", *pw = " << *pw << endl;
  pw = pw + 1;
  cout << "add 1 to pw pointer:\n";
  cout << "pw = " << pw << ", *pw = " << *pw << endl;

  cout << "ps = " << ps << ", *ps = " << *ps << endl;
  ps = ps + 1;
  cout << "add 1 to ps pointer:\n";
  cout << "ps = " << ps << ", *ps = " << *ps << endl;

  cout << "access two elements with array notation\n";
  cout << "stacks[0] = " << stacks[0] << ",stacks[1] = " << stacks[1] << endl;
  cout << "access two elements with pointer notation\n";
  cout << "*stacks = " << *stacks << ", *(stacks + 1) = " << *(stacks + 1) << endl;

  cout << sizeof (wages) << " = size of wages array\n";
  cout << sizeof (pw) << " = size of pw pointer\n";

  return 0;

}
