// use_stuc.cpp -- using a composite class
#include "studentc.h"
#include <iostream>

using std::cin;
using std::cout;
using std::endl;

void set(Student & stu, int n);

const int S_SIZE = 3;
const int R_SIZE = 5;

int main()
{
    Student ada[S_SIZE] = {Student(R_SIZE),Student(R_SIZE),Student(R_SIZE)};
    for (int i = 0; i < S_SIZE; i++)
    {
        set(ada[i], R_SIZE);
    }
    cout << "\nStudent List:\n";
    for (int i = 0; i < S_SIZE; i++)
        cout << ada[i].Name() << endl;
    cout << "\n Results: ";
    for (int i = 0; i < S_SIZE; i++)
    {
        cout << endl << ada[i];
        cout << " average: " << ada[i].Average() << endl;
    }
    cout << "Done.\n";
    return 0;
}

void set(Student & stu, int n)
{
    cout << "Please enter the student's name: ";
    getline(cin,stu);
    cout << "Please enter " << n << " quiz scores: \n";
    for (int i = 0; i < n; i++)
    {
        cin >> stu[i];
    }
    while (cin.get() != '\n')
        continue;
}

