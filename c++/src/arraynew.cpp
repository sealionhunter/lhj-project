// arraynew.cpp

#include <iostream>

int main()
{
  using namespace std;
  double *ps = new double[3];

  ps[0] = 1.0;
  ps[1] = 1.2;
  ps[2] = 1.5;

  cout << " ps[1] is " << ps[1] << ".\n";

  ps = ps + 1;

  cout << " Now ps[0] is " << ps[0] << ".\n";
  cout << "ps[1] is " << ps[1] << ".\n";

  ps -= 1;

  delete [] ps;

  return 0;
}
