// learn2.cpp

#include <iostream>
#include <cstring>

int main()
{
  using namespace std;

  char firstname[20];
  char lastname[20];

  cout << "Enter your first name: ";
  cin.getline(firstname,20);
  char * fn = new char[strlen(firstname)+1];
  strcpy (fn,firstname);
  cout << "Enter your last name: ";
  cin.getline(lastname,20);
  char *ln = new char[strlen(lastname) + 1];
  strcpy (ln,lastname);
  cout << "The information in a single string: " << ln << ", " << fn << endl;
  delete fn;
  delete ln;

  return 0;
}
