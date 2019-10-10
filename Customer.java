package mccbank;
import java.util.ArrayList;
import mccbank.Account.AccountType;
public class Customer implements Comparable
{
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected ArrayList<Account> accounts;
    protected double accountID;

    /**
     * Default constructor for Customer
     */
    public Customer()
    {
        this.firstName = "";
        this.lastName = "";
        this.userName = "";
        this.password = "";
        this.accounts = new ArrayList<Account>();
        this.accountID = 0;
    }
    
    /**
     * Constructor for customer.
     * Sets, first name, last name, user name, password, account ID, and existing account.
     * 
     * @param firstName The customer's first name
     * @param lastName The customer's last name
     * @param userName The customer's username
     * @param password The customer's password
     * @param accountID The customer's account ID
     * @param account The customer's account
     */
    public Customer(String firstName, String lastName, String userName, String password, double accountID, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.accountID = accountID;
        this.accounts = new ArrayList<Account>();
        addAccount(account);
    }
    
    /**
     * Constructor for customer.
     * Sets, first name, last name, user name, password, account ID, and creates an account with account type, balance, and interest percent..
     * 
     * @param firstName The customer's first name
     * @param lastName The customer's last name
     * @param userName The customer's username
     * @param password The customer's password
     * @param accountID The customer's account ID
     * @param accType The customer's account type
     * @param balance THe customer's account balance
     * @param interestPercent The customer's account interest rate
     */
    public Customer(String firstName, String lastName, String userName, String password, double accountID, AccountType accType, double balance, double interestPercent)
    {
        Account a1 = new Account(accType, balance, interestPercent);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.accountID = accountID;
        this.accounts = new ArrayList<Account>();
        addAccount(a1);
    }
    
    /**
     * Returns the customers first name
     * @return The customer's first name
     */
    public String getFirstName() 
    {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     * @param firstName The customer's first name
     */
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    /**
     * Returns the customer's last name
     * @return The customer's last name
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     *  Sets the customer's last name
     * @param lastName The customer's last name
     */
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    /**
     * Returns the customer's user name
     * @return The customer's user name
     */
    public String getUserName() 
    {
        return userName;
    }

    /**
     * Sets the customer's user name
     * @param userName The customers user name
     */
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    /**
     * Returns the customer's password
     * @return The customer's password
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * Sets the customer's password
     * @param password The customer's password
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     * Adds an account to the customer
     * @param aType The customer's new account type
     * @param balance The customer's new account balance
     * @param interestPercent The customer's new account interest rate
     */
    public void addAccount(AccountType aType, double balance, double interestPercent)
    {
        Account a = new Account(aType, balance, interestPercent);
        this.accounts.add(a);
    }
    
    /**
     * Adds an account to the customer
     * @param account The customer's new account
     */
    public void addAccount(Account account) 
    {
        this.accounts.add(account);
    }

    /**
     * Returns the customer's account ID
     * @return The customer's account iD
     */
    public double getAccountID() 
    {
        return accountID;
    }

    /**
     * Sets the customer's account ID
     * @param accountID The customer's account ID
     */
    public void setAccountID(double accountID) 
    {
        
        this.accountID = accountID;
    }
    
    /**
     * Returns an array list of all the customer's accounts
     * @return An array list of the customer's accounts
     */
    public ArrayList<Account> getAccounts() 
    {
        return accounts;
    }
    
    @Override
    public String toString()
    {
        String out = "First Name: " + firstName;
        out += "\nLast Name: " + lastName;
        out += "\nUsername: " + userName;
        out += String.format("\nAccount ID: %.0f \n", accountID);
        for(int i = 0; i < accounts.size(); i++)
        {
            out += "\n\tAccount #" + (i + 1);
            out += "\n\t" + accounts.get(i).toString();
            out += "\n";
        }
        return out;
    }

    /**
     * Makes the appropriate adjustments for the toString method to be used in a file with print writer.
     * @return The customer's info without password, to be printed to a file
     */
    public String toStringPrintWriter()
    {
        String out = "First Name: " + firstName;
        out += "\r\nLast Name: " + lastName;
        out += "\r\nUsername: " + userName;
        out += String.format("\r\nAccount ID: %.0f \r\n", accountID);
        for(int i = 0; i < accounts.size(); i++)
        {
            out += "\r\n\tAccount #" + (i + 1);
            out += "\r\n\t" + accounts.get(i).toStringPrintWriter();
            out += "\r\n";
        }
        return out;
    }

    /**
     * Returns the customer's info without password
     * @return The customer's info without password
     */
    public String toStringAccountExists()
    {
        String out = "First Name: " + firstName;
        out += "\nLast Name: " + lastName;
        out += "\nUsername: " + userName;
        for(int i = 0; i < accounts.size(); i++)
        {
            out += "\n\tAccount #" + (i + 1);
            out += "\n\t" + accounts.get(i).toString();
            out += "\n";
        }
        return out;
    }

    /**
     * Creates string with customer's first name, last name, and username
     * @return String with customer's first and last name and user name
     */
    public String toStringInfoExists()
    {
        String out = "First Name: " + firstName;
        out += "\nLast Name: " + lastName;
        out += "\nUsername: " + userName;
        return out;
    }
    
    /**
     * Compares the existing customer's information with a first name, last name, user name, password, and account ID
     * 
     * @param firstName The compared customer's first name
     * @param lastName The compared customer's last name
     * @param userName The compared customer's user name
     * @param password The compared customer's password
     * @param accountID The compared customer's account ID
     * @return true if the customers are equal, false if not
     */
    public boolean equals(String firstName, String lastName, String userName, String password, double accountID)
    {
        boolean equal = false;
        if(this.firstName.equalsIgnoreCase(firstName) 
                && this.lastName.equalsIgnoreCase(lastName) 
                && this.userName.equals(userName)
                && this.password.equals(password)
                && this.accountID == (accountID))
        {
            equal = true;
        }
        return equal;
    }

    /**
     * Compares the existing customer's information with a first name, last name, user name, password.
     * Differs from other equals method because it does not check account ID.
     * 
     * @param firstName The compared customer's first name
     * @param lastName The compared customer's last name
     * @param userName The compared customer's user name
     * @param password The compared customer's password
     * @return true if the customers are equal, false if not
     */
    public boolean equals(String firstName, String lastName, String userName, String password)
    {
        boolean equal = false;
        if(this.firstName.equalsIgnoreCase(firstName) 
                && this.lastName.equalsIgnoreCase(lastName) 
                && this.userName.equals(userName)
                && this.password.equals(password))
        {
            equal = true;
        }
        return equal;
    }

    @Override
    public int compareTo(Object o) 
    {
        if(this.accountID < ((Customer)o).accountID)
            return -1;
        else if(this.accountID > ((Customer)o).accountID)
            return 1;
        else
        {
            return 0;
        }
    }
    
}
