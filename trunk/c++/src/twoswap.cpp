// twoswap.cpp -- specialization override a template
#include <iostream>

template <class T> void Swap(T & a, T & b);

struct job
{
    char name[40];
    double salary;
    int floor;
};

template <> void Swap(job & a, job & b);

void Show(job &j);

int main()
{
    using namespace std;
    cout.precision(2);
    cout.setf(ios::fixed, ios::floatfield);

    int i = 10, j = 20;
    cout << "i, j = " << i << ", " << j << endl;
    cout << "Using compiler-generated int swapper: \n";
    Swap(i,j);
    cout << "Now i, j = " << i << ", " << j << endl;

    job sue = {"Susan Yaffee", 73000.60,7};
    job sidney = {"Sidney Taffee", 78060.72, 9};

    cout << "Before job swapping: \n";
    Show(sue);
    Show(sidney);

    Swap(sue, sidney);
    cout << "After job swapping: \n";
    Show(sue);
    Show(sidney);

    return 0;
}

template <class T> void Swap(T & a, T & b)
{
    T tmp = a;
    a = b;
    b = tmp;
}

template <> void Swap(job & j1, job & j2)
{
    double t1 ;
    int t2;
    t1 = j1.salary;
    t2 = j1.floor;
    j1.salary = j2.salary;
    j1.floor = j2.floor;
    j2.salary = t1;
    j2.floor = t2;
}

void Show(job &j)
{
    using namespace std;
    cout << j.name << ": $" << j.salary << " on floor " << j.floor << endl;
}

