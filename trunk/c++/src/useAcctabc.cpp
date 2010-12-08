// useAcctabc.cpp -- polymorphic example
// compile with acctabc.cpp
#include <iostream>
#include "acctabc.h"
using std::cout;
using std::endl;
using std::cin;

const int CLIENTS = 4;
const int LEN = 40;

int main()
{
    AcctABC * p_clients[CLIENTS];
    for (int i = 0; i < CLIENTS; i++)
    {
        char temp[LEN];
        long tempNum;
        double tempbal;
        char kind;
        cout << "Enter client's name: ";
        cin.getline(temp, LEN);
        cout << "Enter client's account number: ";
        cin >> tempNum;
        cout << "Enter opening balance: $";
        cin >> tempbal;
        cout << "Enter 1 for Brass Account or 2 for BrassPlus Account:";
        while(cin >> kind && (kind != '1' && kind != '2'))
        {
            cout << "Enter either 1 or 2:";
        }
        if (kind == '1')
        {
            p_clients[i] = new Brass(temp, tempNum, tempbal);
        }
        else 
        {
            double tmax, trate;
            cout << "Enter the overdraft limit: $";
            cin >> tmax;
            cout << "Enter the interest rate as a decimal fraction: ";
            cin >> trate;
            p_clients[i] = new BrassPlus(temp, tempNum, tempbal, tmax, trate);
        }
        while (cin.get() != '\n')
            continue;
    }

    cout << endl;
    for (int i = 0; i < CLIENTS; i++)
    {
        p_clients[i]->ViewAcct();
        cout << endl;
    }
    cout << endl;
    for (int i = 0; i < CLIENTS; i++)
    {
        delete p_clients[i];
    }
    cout << "Done.\n";

    return 0;
}

