package mccbank;
public class Account 
{
    enum AccountType {BasicChecking, InterestBearingChecking, Saving, Retirement};
    
    protected double balance;
    protected AccountType aType;
    protected double interestPercent;
    
    /**
     * Default constructor for Account
     */
    public Account()
    {
        this.balance = 0;
        this.aType = AccountType.BasicChecking;
        this.interestPercent = 0;
    }
    
    /**
     * Creates a new account using account type, balance, and interest rate
     * 
     * @param aType The new account type
     * @param balance The new account balance
     * @param interestPercent The new account interest rate
     */
    public Account(AccountType aType, double balance, double interestPercent)
    {
        this.aType = aType;
        this.balance = balance;
        this.interestPercent = interestPercent;
    }
    
    /**
     * Returns the account type enumeration
     * @return The account type
     */
    public AccountType getAccountType()
    {
        return this.aType;
    }
    
    /**
     * Returns the balance
     * @return The account balance
     */
    public double getBalance()
    {
        return this.balance;
    }
    
    /**
     * Returns the account interest rate
     * @return The account interest rate
     */
    public double getInterest()
    {
        return this.interestPercent;
    }
    
    /**
     * Sets the account type
     * @param aType The account type enumeration
     */
    public void setAccountType(AccountType aType)
    {
        this.aType = aType;
    }
    
    /**
     * Sets the account balance
     * @param balance The account balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    
    /**
     * Sets the account interest rate
     * @param interest The account interest rate
     */
    public void setInterest(double interest)
    {
        this.interestPercent = interest;
    }
    
    @Override
    public String toString()
    {
        String out = "Account Type: ";
        
        switch(this.aType)
        {
            case BasicChecking :
                out += "Basic Checking\n";
                break;
            
            case InterestBearingChecking :
                out += "Interest Bearing Checking\n";
                break;
                
            case Saving :
                out += "Savings\n";
                break;
                
            case Retirement :
                out += "Retirement\n";
                break;
                
            default :
                out += "\n";
                break;
        }
        
        out += String.format("\tAccount Balance: $%,.2f\n", this.balance);
        out += String.format("\tAccount Interest Rate: %.2f%%", (100 * interestPercent));
        
        return out;
    }

    /**
     * Returns a string that can be printed using the print writer.
     * @return String containing the account type, balance, and interest rate
     */
    public String toStringPrintWriter()
    {
        String out = "Account Type: ";
        
        switch(this.aType)
        {
            case BasicChecking :
                out += "Basic Checking\r\n";
                break;
            
            case InterestBearingChecking :
                out += "Interest Bearing Checking\r\n";
                break;
                
            case Saving :
                out += "Savings\r\n";
                break;
                
            case Retirement :
                out += "Retirement\r\n";
                break;
                
            default :
                out += "\r\n";
                break;
        }
        
        out += String.format("\tAccount Balance: $%,.2f\r\n", this.balance);
        out += String.format("\tAccount Interest Rate: %.2f%%", (100 * interestPercent));
        
        return out;
    }
    
    /**
     * Checks to see if the existing account matches the compared account's account type
     * @param other Compared account
     * @return True if account types are equal, false if not
     */
    public boolean equals(Account other)
    {
        if(this.aType.equals(other.aType))
            return true;
        else
            return false;
    }
    
}
