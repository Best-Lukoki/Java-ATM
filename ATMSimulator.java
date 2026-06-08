// Bank Account Simulator

import java.util.Scanner;

public class ATMSimulator {
    
    private String name;
    private double balance;

    public ATMSimulator (String name) {
        this.name = name;
        balance = 0;
    }

    public void setName (String n) {
        name = n;
    }

    String getName () {
        return name;
    }

    public void setBalance (double b) {
        balance = b;
        System.out.println(getName() + ", you now have £" + getBalance());
    }

    double getBalance () {
        return balance;
    }

    public static String inputString (String message) {

        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println(message);
        answer = scanner.nextLine();
        return answer;
    }

    public static double inputDouble (String message) {

        Scanner scanner = new Scanner(System.in);
        double answer;
        System.out.println(message);
        answer = scanner.nextDouble();
        return answer;

    }

    public static int  inputInt (String message) {

        Scanner scanner = new Scanner(System.in);
        int answer;
        System.out.println(message);
        answer = scanner.nextInt();
        return answer;

    }

    public void inputName () {

        String n = inputString("Enter your name");
        setName(n);
    }

    public void deposit () {
        
        System.out.println("Current balance: " + getBalance());
        double deposit = inputDouble("Enter the amount you want to deposit");
        
        double newbalance = getBalance() + deposit;
        setBalance(newbalance);
    }

    public void withdraw () {

        System.out.println("Current balance: " + getBalance());
        double withdraw = inputDouble("Enter the amount you want to withdraw");

        while (withdraw > getBalance()) {
            withdraw = inputDouble("Amount entered exceeds current balance, please try again");
        }

        double newbalance = getBalance() - withdraw;
        setBalance(newbalance);
    }

    public void simulation () {
        System.out.println(getName() + ", Welcome to the Bank");
        double start = inputDouble("Enter how much money you'd like to start off with");

        setBalance(start);

        boolean finished = true;

        while (finished) {

            int choice = inputInt("Enter 1 to deposit, enter 2 to withdraw, enter 3 to exit");

            if (choice == 1) {
                deposit();
            }

            else if (choice == 2) {
                withdraw();
            }

            else if (choice == 3) {
                finished = false;
            }
        }
        System.out.println("Thank you for banking with us!");
    }
}