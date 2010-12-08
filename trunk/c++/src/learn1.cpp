// learn1.cpp

#include <iostream>

struct userinfo
{
  char firstName[20];
  char lastName[20];
  char grade;
  int age;
};

int main()
{
  using namespace std;
  userinfo *us = new userinfo;
  cout << "What is your first name? ";
  cin.getline((*us).firstName,20);
  cout << "What is your last name? ";
  cin.getline((*us).lastName,20);
  cout << "What letter grade do you deserve? ";
  cin >> us->grade;
  cout << "What is your age? ";
  cin >> us->age;

  cout << "Name: " << us->lastName << ", " << us->firstName << endl;
  cout << "Grade: " << (char) (us->grade + 1) << endl;
  cout << "Age: " << us->age << endl;

  delete us;
  return 0;
}
