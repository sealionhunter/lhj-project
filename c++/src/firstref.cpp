// firstref.cpp -- defining and using a reference
#include <iostream>

int main()
{
    using namespace std;
    int rats = 101;
    int & rodents = rats;

    cout << "Rats = " << rats;
    cout << ", rodents = " << rodents << endl;

    rodents++;
    cout << "Rats = " << rats;
    cout << ", rodents = " << rodents << endl;

    cout << "Rats address = " << &rats;
    cout << ", rodents address = " << &rodents << endl;

    return 0;
}

