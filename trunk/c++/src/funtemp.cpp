//funtemp.cpp -- using a function template
#include <iostream>

template <class T>
void Swap(T & a, T & b);

int main()
{
    using namespace std;

    int i = 10;
    int j = 20;
    cout << "i, j = " << i << ", " << j << endl;
    Swap(i,j);
    cout << "Using compiler-generated int swapper: \n";
    cout << "Now i, j = " << i << ", " << j << endl;

    double x = 24.5;
    double y = 81.7;
    cout << "x, y = " << x << ", " << y << endl;
    cout << "Using compiler-generated double swapper: \n";
    Swap(x, y);
    cout << "Now x, y = " << x << ", " << y << endl;

    return 0;
}

// function template definition
template <class T> void Swap(T & a, T & b)
{
    T tmp = a;
    a = b;
    b = tmp;
}

