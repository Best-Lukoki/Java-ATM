// Bank Account Simulator

import java.util.Scanner;

public class ATMSimulator {
    
    private String name;
    private double balance;
    private String [] accountnames = new String[3];      // array for all the names of the accounts
    private double [] accountbalances = new double[3];   // array for all the balances for the accounts, in order with the names
    int positionindex;  // helps find out the specific index of the users
    boolean finished = true; // used to check whether the user has finished with their transactions
    boolean exitsystem = true; // used to check whether the whole system is done or not


    // method allows user to input a string value, returning the string value inputted by the user
    public static String inputString (String message) {

        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println(message);
        answer = scanner.nextLine();
        return answer;
    }

    // method allows user to input a double value, returning the double value inputted by the user
    public static double inputDouble (String message) {

        Scanner scanner = new Scanner(System.in);
        double answer;
        System.out.println(message);
        answer = scanner.nextDouble();
        return answer;

    }

    // method allows user to input an integer value, returning the integer value inputted by the user
    public static int  inputInt (String message) {

        Scanner scanner = new Scanner(System.in);
        int answer;
        System.out.println(message);
        answer = scanner.nextInt();
        return answer;

    }

    // constructor method containing the name and sets the balance equal to 0
    public ATMSimulator (String name) {
        this.name = name;
        balance = 0;
    }

    // mutator method that sets the name of the user
    public void setName (String n) {
        name = n;
    }

    // accessor method that obtains the name of the user
    String getName () {
        return name;
    }

    // mutator method that sets the balance of the user and then tells the user the current balance in its account
    public void setBalance (double b) {
        balance = b;
        System.out.println(accountnames[positionindex] + ", you now have £" + getBalance());
    }

    // accessor method that obtains the current balance of the user
    double getBalance () {
        return balance;
    }

    // this method updates the details of the user and inputs them into arrays, one for the names and one for the balance
    // the positions are respective to a said user, for example, index 0 for both arrays is for one person, index 4 for both arrays would correspond
    // to another user, so on and so forth
    public void addDetails () {
        accountnames[positionindex] = getName();
        accountbalances[positionindex] = getBalance();
    }

    // this method searches through the accountnames array to find a user
    // once found it will return the index value
    void findAccount (String n) {
        positionindex = 0;

        for (int i = 0; i<accountnames.length; i++) {
            if (accountnames[i] == null) {
                break; // exits the for loop once the user is found
            }
            else if (accountnames[i].equals(n)) {
                break;
            }
            positionindex++;
        }
    }

    // prompts the user to input their name, and then users the mutator method to set the name of the user
    public void inputName () {
        String n = inputString("Enter your name");
        setName(n);
    }

    // this method will allow the user to input money into the account, which will add on to any existing figures in the bank balance
    // before setting the new balance
    public void deposit () {
        System.out.println("Current balance: £" + accountbalances[positionindex]); // states currrent user's balance for reference
        double deposit = inputDouble("Enter the amount you want to deposit");

        while (deposit <= 0) {
            deposit = inputDouble("Enter a positive number that's above 0 when depositing money"); // prompts user to input a positive number
        }
        
        double newbalance = accountbalances[positionindex] + deposit;
        setBalance(newbalance);
    }


    // same as deposit method but instead of inputting money, it takes away money from the user's account and then sets the new balance afterwards
    public void withdraw () {
        System.out.println("Current balance: £" + accountbalances[positionindex]); // states current user's balance for reference
        double withdraw = inputDouble("Enter the amount you want to withdraw");

        while (withdraw > getBalance()) {
            withdraw = inputDouble("Amount entered exceeds current balance, please try again"); // prompts user to take away amount within the balance
        }

        double newbalance = accountbalances[positionindex] - withdraw;
        setBalance(newbalance);
    }

    public void userchoices () {
        int choice = inputInt("Enter 1 to deposit, enter 2 to withdraw, enter 3 to exit");

            if (choice == 1) {
                deposit();
            }

            else if (choice == 2) {
                withdraw();
            }

            else if (choice == 3) {
                finished = false; // user chooses to exit so finished is now false, so the while loop can stop
            }

            addDetails();
            arrays();
    }

    // placeholder test method to see if i make any mistakes in regards to the information kept in the arrays
    void arrays () {
        for (int i = 0; i<accountnames.length; i++) {
            if (accountnames[i] == null) {
                break;
            }
            System.out.println(accountnames[i] + " = " + accountbalances[i]);
        }
    }

    // this is the main method for the simulation, users former methods to form the bank simulator, starting by asking for the name and
    // entering a starting amount to set up the account, then it will continuously ask the user to withdraw or deposit money unless they decide
    // to exit the system
    public void simulation () {
        addDetails();
        
        System.out.println(accountnames[positionindex] + ", Welcome to the Bank");
        double start = inputDouble("Enter your starting amount to set up your account");

        while (start <= 0) {
            start = inputDouble("Please input a number above 0"); // prompts user to input a number above 0
        }

        setBalance(start);

        addDetails();
        
        arrays();

        finished = true; // boolean variable used to indicate whether the user has or hasn't finished with their bank account

        while (finished) {
            userchoices();
        }

        // work in progress, doesn't properly work right now

        exitsystem = true;

        while (exitsystem) {
            
            int addorexit = inputInt("Enter 1 if you are a new user, enter 2 if you are an existing user, enter 3 to exit the system");
            
            if (addorexit == 1) {

                arrays();
                // move to the next space in the array and do recursion on simulation method
                findAccount(null);
                inputName();
                simulation();
            }

            else if (addorexit == 2) {
            // use findaccount method and do while loop on userchoices again

                arrays();
                String n = inputString("Enter your name");   // add a check through the array to see if user is in the system or not (later)
                findAccount(n);
                arrays();
                System.out.println("You currently have: £" + accountbalances[positionindex]);
                arrays();

                setName(n);
                setBalance(accountbalances[positionindex]); 

                finished = true;

                while (finished) {
                    userchoices();
                }
            }

            else if (addorexit == 3) {
                // exit whole system
                exitsystem = false;
            }
        }
        System.out.println("Thank you for banking with us!");
    }
}