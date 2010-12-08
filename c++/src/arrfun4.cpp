// arrfun4.cpp -- functions with an array range
#include <iostream>
const int AR_SIZE = 8;
int sum_arr(const int * begin, const int * end);
int main()
{
    using namespace std;
    int cookies[AR_SIZE] = {1,2,4,8,16,32,64,128};

    int sum = sum_arr(cookies, cookies + AR_SIZE);
    cout << "Total cookies eaten: " << sum << endl;
    sum = sum_arr(cookies, cookies + 3);
    cout << "First three eaters ate " << sum << endl;
    sum = sum_arr(cookies + 4, cookies + 8);
    cout << "Last four eaters ate " << sum << endl;
    return 0;
}

int sum_arr(const int * begin, const int * end)
{
    const int * pt;
    int total = 0;
    for (pt = begin; pt != end; pt++)
    {
        total += *pt;
    }
    return total;
}


