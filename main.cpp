// Randall Decker
// Project 2 CS-210
// 12/1/24
#include <iostream>
#include <iomanip>
#include <limits> //assist user with correct input
using namespace std;

void printDetails(int year, double yearEndBalance, double interestEarned){
    cout << fixed << setprecision(2);

    cout << year << "\t\t" << "$" << yearEndBalance << " \t\t" << "$" << interestEarned << endl;
}

int annualInterest;

double balanceWithMonthlyDeposit(double initialInvestment, double monthlyDeposit, double interestRate, int numberOfYears){

    // Convert annual interest rate to a monthly rate
    double monthlyRate = (interestRate / 100) / 12;
    double balance = initialInvestment;

    // Loop over the years
    for (int year = 1; year <= 5; ++year) {
        double interestEarnedThisYear = 0.0;

        // Loop over each month of the year
        for (int month = 1; month <= 12; ++month) {
            // Add interest for the current month, balance is compounded monthly
            const double monthlyInterest = balance * monthlyRate;
            balance += monthlyInterest;
            interestEarnedThisYear += monthlyInterest;

            // Add the monthly deposit after the interest is added
            balance += monthlyDeposit;
        }

        // Print details after every year
        printDetails(year, balance, interestEarnedThisYear);
    }
    // Return the final balance after all years
    return balance;
}

double balanceWithoutMonthlyDeposit(double initialInvestment, double interestRate, int numberOfYears) {
    // Convert interest rate to a monthly interest rate
    double monthlyInterestRate = (interestRate / 100) / 12;

    // Start with the initial balance
    double balance = initialInvestment;

    // Loop through each year
    for (int year = 1; year <= 5; ++year) {
        double startOfYearBalance = balance;
        double interestEarnedThisYear = 0.0;

        // Compound interest monthly for each year
        for (int month = 1; month <= 12; ++month) {
            balance *= (1 + monthlyInterestRate);
        }

        // Calculate interest earned this year
        interestEarnedThisYear = balance - startOfYearBalance;

        // Print year details
        printDetails(year, balance, interestEarnedThisYear);
    }

    // Return the final balance after all years
    return balance;

}

// Function to display the initial screen with no values
void displayScreenDefault() {
    cout << "**********************************" << endl;
    cout << "********** Data Input ************" << endl;
    cout << "Initial Investment Amount: " << endl;
    cout << "Monthly Deposit: " << endl;
    cout << "Annual Interest (Compounded): " << endl;
    cout << "Number of Years: " << endl;
    cout << "Press any key to continue..." << endl;
    cin.get(); // Wait for user to press any key to continue
}

// Function to display the second screen with values input by the user
void displayScreenMain(double initialInvestment, double monthlyDeposit, double annualInterest, int years) {
    // Output the data entered by the user
    cout << fixed << setprecision(2); // Set output format for decimals

    cout << "\n**********************************" << endl;
    cout << "********** Data Input ************" << endl;
    cout << "Initial Investment Amount: $" << initialInvestment << endl;
    cout << "Monthly Deposit: $" << monthlyDeposit << endl;
    cout << "Annual Interest (Compounded): " << annualInterest << "%" << endl;
    cout << "Number of Years: " << years << endl;
    cout << "Press any key to continue..." << endl;
    cin.get(); // Wait for user to press any key to continue
}



int main()
{
    double initialInvestment = 0.0;
    double monthlyDeposit = 0.0;
    double annualInterest = 0.0;
    int years = 0;

    // Display the first screen
    displayScreenDefault();

    // Input for Initial Investment
    cout << "Initial Investment Amount: $";
    while (!(cin >> initialInvestment) || initialInvestment <= 0) {
        cout << "Please enter the Initial Investment Amount: $";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    // Input for Monthly Deposit
    cout << "Monthly Deposit: $";
    while (!(cin >> monthlyDeposit) || monthlyDeposit <= 0) {
        cout << "Please enter a number for Monthly Deposit: $";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    // Input for Annual Interest
    cout << "Annual Interest (Compounded): %";
    while (!(cin >> annualInterest) || annualInterest <= 0) {
        cout << "Please enter a number for Annual Interest (Compounded): ";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    // Input for Number of Years
    cout << "Number of Years: ";
    while (!(cin >> years) || years <= 0) {
        cout << "Please enter a Number of Years: ";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }

    // Display the second screen with the values entered
    displayScreenMain(initialInvestment, monthlyDeposit, annualInterest, years);

    //Display 5 years of data with a deposit vs without
    cout << "\nBalance and Interest Without Additional Monthly Deposits" << endl;
    cout << "==========================================================" << endl;
    cout << "Year       Year End Balance     Year End Earned Interest" << endl;
    cout << "----------------------------------------------------------" << endl;
    balanceWithoutMonthlyDeposit(initialInvestment, annualInterest, years * 12);

    cout << "\nBalance and Interest With Additional Monthly Deposits" << endl;
    cout << "==========================================================" << endl;
    cout << "Year       Year End Balance     Year End Earned Interest" << endl;
    cout << "----------------------------------------------------------" << endl;
    balanceWithMonthlyDeposit(initialInvestment, monthlyDeposit, annualInterest, years * 12);

    //Loop, user choice to test different amounts
    char choice;
    cout << "\nWould you like to calculate with new values? (y/n): ";
    cin >> choice;
    if (choice == 'y' || choice == 'Y') {
        main();
    }
    return 0;
}
