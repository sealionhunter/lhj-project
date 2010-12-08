// use_new.cpp

#include <iostream>

int main()
{
  using namespace std;
  int * pt = new int;
  *pt = 1001;

  cout << " int " ;
  cout << " value = " << *pt << ":location = " << pt << endl;

  double *pd = new double;
  *pd = 10000001.0;

  cout << "double " ;
  cout << " value = " << *pd << ": location = " << pd << endl;

  cout << "size of pt = " << sizeof (pt);
  cout << ";size of *pt = " << sizeof (*pt) << endl;
  cout << "size of pd = " << sizeof (pd);
  cout << "; size of *pd = " << sizeof (*pd) << endl;

  delete pt;
  delete pd;
  return 0;

}
