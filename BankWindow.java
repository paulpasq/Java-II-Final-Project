package mccbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import mccbank.Customer;
public class BankWindow extends JFrame
{
    //instance fields
    protected ArrayList<Customer> list = new ArrayList<>();
    protected int lineCount;
    
    //first window fields
    protected JLabel welcome;
    protected JButton upload;
    protected JButton add;
    protected JButton download;
    protected JButton exit;
    
    //second window fields
    protected JFrame j2;
    protected JTextField uploadPath;
    protected JButton submitUpload;
    
    //third window fields
    protected JFrame j3;
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField userName;
    protected JTextField password;
    protected JTextField passwordVerification;
    protected JComboBox accountType;
    protected final String BASICCHECKING = "Basic Checking";
    protected final String INTERESTBEARINGCHECKING = "Interest Bearing Checking";
    protected final String SAVING = "Saving";
    protected final String RETIREMENT = "Retirement";
    protected final String[] ACCOUNTTYPESARRAY = {BASICCHECKING, INTERESTBEARINGCHECKING, SAVING, RETIREMENT};
    protected JButton submitAdd;
    protected JTextField balance;
    protected JLabel errorMessage;
    
    //fourth window fields
    protected JFrame j4;
    protected JTextField downloadPath;
    protected JButton downloadSubmit;
    
    /**
     * Default constructor for bank window. Starts by creating the welcome panel.
     */
    public BankWindow()
    {
        //create the welcome panel
        createWelcomePanel();
    }
    
    /**
     * Creates a welcome panel displaying four options to the user, Upload Customers, Add Customer, Download Customers, or Exit. 
     * When each button is clicked the corresponding panel and window is created.
     */
    public void createWelcomePanel()
    {
        //welcome frame    
        setTitle("MCC Bank");
        
        //set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set the layout
        setLayout(new BorderLayout(5, 5));

        //create panels for each section of the frame
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        //first panel
        welcome = new JLabel("Welcome to MCC's Bank. Please choose from the following options:");
        p1.add(welcome);
        add(p1, BorderLayout.NORTH);

        //second panel
        p2.setLayout(new BorderLayout(10, 10));
        upload = new JButton("Upload Customer Records from File");
        upload.addActionListener(new myItemListener());
        add = new JButton("Add New Customer Account");
        add.addActionListener(new myItemListener());
        download = new JButton("Download Statistics");
        download.addActionListener(new myItemListener());
        p2.add(upload, BorderLayout.NORTH);
        p2.add(add, BorderLayout.CENTER);
        p2.add(download, BorderLayout.SOUTH);
        add(p2, BorderLayout.CENTER);

        //third panel
        exit = new JButton("Exit Program");
        exit.addActionListener(new myItemListener());
        p3.add(exit);
        add(p3, BorderLayout.SOUTH);

        //pack the JFrame
        pack();
        
        //set visibility
        setVisible(true);
    }
    
    /**
     * Creates a panel that prompts the user to enter a file path to read the files from. 
     * The panel then reads in the file and gets the customer info and send it through the method "addCustomerUpload"
     * 
     */
    public void createUploadPanel()
    {
        //upload customers frame
        j2 = new JFrame();
        
        //set title
        j2.setTitle("Upload Customer Records");
        
        //set default close operation
        j2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //set the layout
        j2.setLayout(new BorderLayout(10, 10));

        //create the panel
        JPanel p1 = new JPanel(new BorderLayout());

        //first panel
        JLabel file = new JLabel("Please enter a file path to upload customer records from:");
        uploadPath = new JTextField(15);
        uploadPath.addActionListener(new myItemListener());
        uploadPath.setBorder(BorderFactory.createTitledBorder("Upload Path"));
        submitUpload  = new JButton("Upload Records");
        submitUpload.addActionListener(new myItemListener());

        //add the panel to the JFrame
        p1.add(file, BorderLayout.NORTH);
        p1.add(uploadPath, BorderLayout.CENTER);
        p1.add(submitUpload, BorderLayout.SOUTH);
        j2.add(p1);
        
        //pack the JFrame
        j2.pack();
        
        //set visibility
        j2.setVisible(true);
    }
    
    /**
     * Creates a panel that prompts the user for the Customer's first name, last name, user name, password, password again, account type, balance, and applicable interest rate.
     * The panel then checks to see if all information is there and correct. 
     * Then is sends the information through the addCustomerAdd method.
     * 
     */
    public void createAddPanel()
    {
        //add customers frame
        j3  = new JFrame();
        
        //set title
        j3.setTitle("Add Customer Information");
        
        //set default close operation
        j3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //set layout
        j3.setLayout(new GridLayout(6, 1));
        
        //create the panels
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        
        //first panel
        JLabel l = new JLabel("Please enter new customer information:");
        p1.add(l);
        j3.add(p1);
        
        //second panel
        firstName = new JTextField(10);
        firstName.setBorder(BorderFactory.createTitledBorder("First Name"));
        lastName = new JTextField(10);
        lastName.setBorder(BorderFactory.createTitledBorder("Last Name"));
        FlowLayout bL = new FlowLayout();
        p2.setLayout(bL);
        p2.add(firstName);
        p2.add(lastName);
        j3.add(p2);
        
        //third panel
        p3.setLayout(new GridLayout(3, 1));
        userName = new JTextField();
        userName.setBorder(BorderFactory.createTitledBorder("Username"));
        password = new JTextField();
        password.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordVerification = new JTextField();
        passwordVerification.setBorder(BorderFactory.createTitledBorder("Password"));
        p3.add(userName);
        p3.add(password);
        p3.add(passwordVerification);
        
        j3.add(p3);
        
        JPanel p6 = new JPanel();
        p6.setLayout(new GridLayout(1, 1));
        errorMessage = new JLabel("");
        errorMessage.setForeground(Color.red);
        errorMessage.setVisible(false);
        p6.add(errorMessage);
        j3.add(p6);
        
        //fourth panel
        accountType = new JComboBox(ACCOUNTTYPESARRAY);
        p4.add(accountType);
        balance = new JTextField(10);
        balance.setBorder(BorderFactory.createTitledBorder("Balance"));
        p4.add(balance);
        j3.add(p4);
        
        //fifth panel
        submitAdd = new JButton("Submit");
        submitAdd.addActionListener(new myItemListener());
        p5.add(submitAdd);
        j3.add(p5);
        
        //pack the JFrame
        j3.pack();
        
        //set visibility
        j3.setVisible(true);
    }
    
    /**
     * Creates a panel that prompts the user to enter a file path to send the customer records to. 
     * The panel then sorts all the customer info according to Customer ID and then prints out the info and all according accounts the customer had.
     */
    public void createDownloadPanel()
    {
        //add download frame
        j4 = new JFrame();
        
        //set title
        j4.setTitle("Donwload Customer Information");
        
        //set default close operation
        j4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //set layout
        j4.setLayout(new BorderLayout());
        
        //first panel
        JPanel p1 = new JPanel();
        JLabel downLabel = new JLabel("Please enter file path to download customer records to:");
        p1.add(downLabel);
        j4.add(p1, BorderLayout.NORTH);
        
        //second panel
        JPanel p2 = new JPanel();
        downloadPath = new JTextField(25);
        downloadPath.setBorder(BorderFactory.createTitledBorder("Download Path"));
        p2.add(downloadPath);
        j4.add(p2, BorderLayout.CENTER);
        
        //third panel
        JPanel p3 = new JPanel();
        downloadSubmit = new JButton("Download");
        downloadSubmit.addActionListener(new myItemListener());
        p3.add(downloadSubmit);
        j4.add(p3, BorderLayout.SOUTH);
        
        //pack frame
        j4.pack();
        
        //set visibility
        j4.setVisible(true);
        
    }
    
    /**
     * Reads in a file from the upload panel.
     * Then the information is checked to make sure all information is present.
     * If not the Customer will not be added to the records but the method will continue to read in all following customers accordingly.
     * Then the information is sent through addCustomerUpload() which checks to see if the customer already exists or not.
     * Once finished a success message will be sent.
     * 
     */
    public void uploadCustomerFiles()
    {
        //get the file path
        String path = uploadPath.getText();
        
        //initialize the file
        File input;
        
        //initialize the scanner
        Scanner s = null;
        
        //reset line count
        lineCount = 0;
        
        //initialize customer info
        String last = null, first = null, user = null, pass = null;
        double acc = 0, bal = 0, interest = 0;
        Account.AccountType aT = Account.AccountType.BasicChecking;
        
        //create flag to see if file even exists
        boolean fileExists = true;
        
        //create flag to see if all the customer info is on the line
        boolean validInformation = true;
        
        //check if file exists
        try
        {
            //reset file exists flag
            fileExists = true;
            
            //set the file from the file path
            input = new File(path);
            
            //create a scanner on that file
            s = new Scanner(input);
        }
        //if the file does not exist catch it
        catch (FileNotFoundException ex) 
        {
            //print out the error message
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
            //set the file exist flag to false because the file does not exist
            fileExists = false;
        }
        
        //however if the file does exist
        if(fileExists == true)
        {
            //while there is still lines to read from the scanner
            while(s.hasNext())
            {
                //reset that the line has valid information
                validInformation = true;
                
                //increment the line counter because the next line is being read
                lineCount++;
                
                //read in the next line
                String in = s.nextLine();
                
                //create a string builder on the next line
                StringBuilder sb = new StringBuilder(in);
                
                //seperate the line by commas and a space ", "
                String[] tokens = in.split(", ");
                
                //if there is less than siz pieces of information then there is certainly no way the info can be valid
                if(tokens.length >= 6)
                    
                    //if there is six pieces of info try
                    try
                    {
                        //setting the last name
                        last = tokens[0];
                        
                        //setting the first name
                        first = tokens[1];

                        //set the user name
                        user = tokens[2];
                        
                        //check to see if the user name is already used step through the list of customers
                        for(int  i = 0; i < list.size(); i++)
                        {
                            //try searching for eisting user name
                            try
                            {
                                //check to see if the user name matches
                                if(user.compareTo(list.get(i).getUserName()) == 0)
                                {
                                    //if the user name matches then it must be the same person so check to see if first name matches
                                    if(first.compareToIgnoreCase(list.get(i).getFirstName()) == 0)
                                    {
                                        //if the user name matches then it must be the same person so check to see if last name matches as well
                                        if(last.compareToIgnoreCase(list.get(i).getLastName()) == 0)
                                        {
                                            //if the user name matches then it must be the same person so check to see if account ID matches as well
                                            acc = Double.parseDouble(tokens[4]);
                                            if(acc != list.get(i).getAccountID())
                                            {
                                                //if the account ID does not match then the user name cannot be used because it belongs to someone else
                                                throw new Exception();
                                            }
                                        }
                                        //if the last name does not match then the user name cannot be used because it belongs to someone else
                                        else
                                        {
                                            throw new Exception();
                                        }
                                    }
                                    //if the first name does not match then the user name cannot be used because it belongs to someone else
                                    else
                                    {
                                        throw new Exception();
                                    }
                                }
                            }
                            //if the user name exists catch the exception
                            catch(Exception ex)
                            {
                                //print out why the line cannot be read in
                                JOptionPane.showMessageDialog(null, "Error Reading Customer Information. Account Username Duplicate (Line " + lineCount + ")\n");
                                
                                //close the for loop
                                i = list.size();
                                
                                //set valid information to false becuase the username is taken
                                validInformation = false;
                            }
                        }
                        
                        //half way through reading check to see if valid information still is true so no RAM is wasted
                        if(validInformation == true)
                        {

                            //set password
                            pass = tokens[3];
                            
                            //set the account number
                            acc = Double.parseDouble(tokens[4]);
                            
                            //check to see if the account ID is already in use
                            for(int i = 0; i < list.size(); i++)
                            {
                                try
                                {
                                    //if someone is already using the same account id
                                    if(acc == list.get(i).getAccountID())
                                    {
                                        //if someone is already using the same account id it better be the same person so check first name equal
                                        if(first.compareToIgnoreCase(list.get(i).getFirstName()) == 0)
                                        {
                                            //if someone is already using the same account id it better be the same person so check last name equal
                                            if(last.compareToIgnoreCase(list.get(i).getLastName()) == 0)
                                            {
                                                //if someone is already using the same account id it better be the same person so check user name equal
                                                if(user.compareTo(list.get(i).getUserName()) != 0)
                                                {
                                                    //if username is different then the account ID cannot be used because a different person is already using it
                                                    throw new Exception();
                                                }
                                            }
                                            //if last name is different then the account ID cannot be used because a different person is already using it
                                            else
                                            {
                                                throw new Exception();
                                            }
                                        }
                                        //if first name is different then the account ID cannot be used because a different person is already using it
                                        else
                                        {
                                            throw new Exception();
                                        }
                                    }
                                }
                                //if the account ID is already in use catch the exception
                                catch(Exception ex)
                                {
                                    //print out why customer record cannot be imported
                                    JOptionPane.showMessageDialog(null, "Error Reading Customer Information. Account ID Duplicate (Line " + lineCount + ")\n");
                                    
                                    //close for loop
                                    i = list.size();
                                    
                                    //set valid info to false because account ID is already used by someone else
                                    validInformation = false;
                                }
                            }
                            
                            //check to see if valid information is still true again
                            if(validInformation == true)
                            {
                                //set accocunt type
                                aT = Account.AccountType.valueOf(tokens[5]);

                                //set balance
                                bal = Double.parseDouble(tokens[6]);
                                
                                //if the account has interest it needs to be set
                                if(aT.equals(Account.AccountType.InterestBearingChecking) || aT.equals(Account.AccountType.Saving))
                                {
                                    interest = Double.parseDouble(tokens[7]);
                                }
                                
                                //otherwise set the interest to nothing
                                else
                                {
                                    interest = 0;
                                }
                            }
                        }

                    }

                    //if there is missing info on a line catch the exception
                    catch(Exception ex)
                    {
                        //print out why the info cannot be read
                        JOptionPane.showMessageDialog(null, "Error Reading Customer Information. Missing Info (Line " + lineCount + ")\n");

                        //set valid info to false because the line is missing info
                        validInformation = false;
                    }
                //for any other unforseen circumstances
                else
                {
                    //print out error for reading the line
                    JOptionPane.showMessageDialog(null, "Error Reading Customer Information. Missing Info (Line " + lineCount + ")\n");
                    
                    //set valid info to false because it could not be read
                    validInformation = false;
                }
                
                //now if the file exist and all the info is there
                if(fileExists == true && validInformation == true)
                {
                    //add the customer to the records
                    addCustomerUpload(first, last, user, pass, acc, aT, bal, interest);
                }
            }
            
        }
        //close the scanner
        s.close();
        
        //print out success message
        JOptionPane.showMessageDialog(null, "Customer records from file \"" + path + "\" have been uploaded sucessfully.");
        
        //clear the text
        uploadPath.setText("");
    }
    
    /**
     * Receives the customer's first name, last name, user name, password, password again, account type, balance, and interest rate.
     * Then the information will be sent through the method addCustomerAdd() for proper verification. 
     * If every piece of information is correct and valid it will be added to the records.
     */
    public void add()
    {
        //reset all JTextField colors back to white
        firstName.setBackground(Color.WHITE);
        lastName.setBackground(Color.WHITE);
        userName.setBackground(Color.WHITE);
        password.setBackground(Color.WHITE);
        passwordVerification.setBackground(Color.WHITE);
        balance.setBackground(Color.WHITE);
        
        //hide the error message
        errorMessage.setText(null);
        errorMessage.setVisible(false);
        
        //create flags to check if all info is acceptable
        boolean firstGood = true;
        boolean lastGood = true;
        boolean userGood = true;
        boolean passGood = false;
        boolean balGood = true;
        
        //create error color... a nice subtle red!
        Color error = Color.decode("#e79cae");
        
        //get first name
        String first = firstName.getText();
        
        //if the user hit submit without entering anything throw error
        if(first.equalsIgnoreCase(""))
        {
            //error textfield
            firstName.setBackground(error);
            //there is no first name so firstGood still equals false
            firstGood = false;
        }
        //if there is a first name it must not contain digits
        else
        {
            //check the enitre first name for digits
            for(int i = 0; i < first.length(); i++)
            {
                //if there is a digit
                if(Character.isDigit(first.charAt(i)))
                {
                    //throw error for first name
                    firstName.setBackground(error);
                    
                    //print error message
                    errorMessage.setText("First name must not contain any digits");
                    errorMessage.setVisible(true);
                    
                    //there is digits in the first name so not good
                    firstGood = false;
                }
            }
        }
        
        //get last name
        String last = lastName.getText();
        
        //if the user hit submit without any last name throw error
        if(last.equalsIgnoreCase(""))
        {
            //throw last name error
            lastName.setBackground(error);
            
            //last name does not exist so last name not good
            lastGood = false;
        }
        
        //if the last name does exist check for digits
        else
        {
            //search the whole string
            for(int i = 0; i < first.length(); i++)
            {
                //if any character is a digit
                if(Character.isDigit(first.charAt(i)))
                {
                    //throw last name error
                    lastName.setBackground(error);
                    
                    //print last name error
                    errorMessage.setText("Last name must not contain any digits");
                    errorMessage.setVisible(true);
                    
                    //last name has digit so not good
                    lastGood = false;
                }
            }
        }
        
        //get the user name
        String user = userName.getText();
        
        //if the user pressed submit with no user name show error
        if(user.equalsIgnoreCase(""))
        {
            //throw user name error
            userName.setBackground(error);
            
            //the user name doesnt exist so userGood is false, not good
            userGood = false;
        }
        //if there is a user name, make sure it doesnt already exist
        else
        {
            //search the customer records
            for(int i = 0; i < list.size(); i++)
            {
                //if the user name is equal and the first name and last name are different then clearly the user name belongs to someone else
                if(user.compareTo(list.get(i).getUserName()) == 0 &&( 
                        first.compareToIgnoreCase(list.get(i).getFirstName()) != 0 || 
                        last.compareToIgnoreCase(list.get(i).getLastName()) != 0 ||
                        password.getText().compareTo(list.get(i).getPassword()) != 0))
                {
                    //throw username error
                    userName.setBackground(error);
                    
                    //print user name exists error
                    errorMessage.setText("Username already exists");
                    errorMessage.setVisible(true);
                    
                    //user name exists so not good
                    userGood = false;
                }
            }
        }
        
        //get password
        String pass = password.getText();
        
        //make sure the user did not press submit before entering a password
        if(pass.equalsIgnoreCase(""))
        {
            //if the password does not exist throw error
            password.setBackground(error);
            passwordVerification.setBackground(error);
            
            // the password does not exist so the password good is bad, false
            passGood = false;
        }
        
        //get the password verification
        String passVerify = passwordVerification.getText();
        
        //initialize the largest account ID in the records
        double largestID = 0;
        
        //step through the customer records
        for(int i = 0; i < list.size(); i++)
        {
            //if the new account ID is larger than largest ID then set largest ID to the new larger ID
            if(list.get(i).getAccountID() > largestID)
            {
                //set largest ID to the new larger ID
                largestID = list.get(i).getAccountID();
            }
        }
        
        //the new acocunt ID will be one greater than the greatest ID existing
        double acc = largestID + 1;
        
        //get the account type string from combo box
        String aTS = (String)accountType.getSelectedItem();
        
        //initialize interest and account type
        double interest = 0;
        Account.AccountType aT = null;
        
        //if the string is basic checking
        if(aTS.equalsIgnoreCase("Basic Checking"))
        {
            //set account type to basic checking
            aT = Account.AccountType.BasicChecking;
        }
        //else if the acocunt type is retirement
        else if(aTS.equalsIgnoreCase("Retirement"))
        {
            //set the account type to retirement
            aT = Account.AccountType.Retirement;
        }
        //else if the account type is interest bearing checking
        else if(aTS.equalsIgnoreCase("Interest Bearing Checking"))
        {
            //set the account type to interest bearing checking
            aT = Account.AccountType.InterestBearingChecking;
        }
        //else if the account type is saving
        else if(aTS.equalsIgnoreCase("Saving"))
        {
            //set the account type to saving
            aT = Account.AccountType.Saving;
        }
        
        //initialize the balance
        double bal = 0;
        
        //read in the balance string
        String balString = balance.getText();
        
        //if the user hit submit without entering a balance
        if(balString.equalsIgnoreCase(""))
        {
            //throw balance error
            balance.setBackground(error);
            
            //balance does not exist so it is bad
            balGood = false;
        }
        //other wise
        else
        {
            try
            {
                //try to parse it if it is a number
                bal = Double.parseDouble(balString);
                
                // if the balance is negative then dont accept it
                if(bal <= 0)
                {
                    //throw balance error
                    balance.setBackground(error);
                    
                    //print balance error
                    errorMessage.setText("Balance must be a positive value.");
                    errorMessage.setVisible(true);
                    
                    //balance is negative so it is not valid
                    balGood = false;
                }
            }
            
            //if the balance cannot be parsed
            catch(Exception e)
            {
                //throw balance error
                balance.setBackground(error);
                
                //print balance error
                errorMessage.setText("Balance must be a numerical value.");
                errorMessage.setVisible(true);
                
                //balance good is false because balance cannot be a string
                balGood = false;
            }
        }
        
        //now for the password validation
        
        //create flags for password
        boolean passwordMatch = false;
        boolean containsFirstName = true;
        boolean containsLastName = true;
        boolean containsUpper = false;
        boolean containsLower = false;
        boolean containsDigit = false;
        boolean lengthRequirement = false;
        
        //if the password actually exists
        if(!pass.equalsIgnoreCase(""))
        {
            //check the password to see if it matches the other password
            if(pass.compareTo(passVerify) == 0)
            {
                //if it matches set the match flag to true
                passwordMatch = true;
                
                //then check to see if the password is betweeen 8 and 10 characters long
                if(pass.length() >= 8 && pass.length() <= 10)
                {
                    //if it is then the length requiremtn flag is true
                    lengthRequirement = true;
                    
                    //next check to see if there is a digit, upper case, and lower case for the length of the password
                    for(int i = 0; i < pass.length(); i++)
                    {
                        //if the password contains a digit
                        if(Character.isDigit(pass.charAt(i)) && containsDigit == false)
                        {
                            //contains digit is true
                            containsDigit = true;
                        }
                        //if the password contains an upper case
                        if(Character.isUpperCase(pass.charAt(i)) && containsUpper == false)
                        {
                            //contains upper is true
                            containsUpper = true;
                        }
                        //if the password contains a lower case
                        if(Character.isLowerCase(pass.charAt(i)) && containsLower == false)
                        {
                            //contains lower is true
                            containsLower = true;
                        }
                    }
                    
                    //if the password has a digit, upper case, and lower case check to see if it has first name or last name
                    if(containsDigit == true && containsUpper == true && containsLower == true)
                    {
                        //if the password does not have the first name
                        if(pass.toLowerCase().indexOf(first.toLowerCase()) == -1)
                        {
                            //set the contains first name flag to false
                            containsFirstName = false;
                            
                            // then check to see if the password has the last name
                            if(pass.toLowerCase().indexOf(last.toLowerCase()) == -1)
                            {
                                //if the password does not have the last name set contains last name to flase
                                containsLastName = false;
                            }
                            //if the password does have the last name
                            else
                            {
                                //print error messgae
                                errorMessage.setText("Password must not contain last name");
                                errorMessage.setVisible(true);
                                //throw password error
                                password.setBackground(error);
                                passwordVerification.setBackground(error);
                                //password is not good
                                passGood = false;
                            }
                        }
                        //if the password contains the first name
                        else
                        {
                            //print first name error
                            errorMessage.setText("Password must not contain first name");
                            errorMessage.setVisible(true);
                            //throw password error
                            password.setBackground(error);
                            passwordVerification.setBackground(error);
                            //password is not good
                            passGood = false;
                        }
                    }
                    //if the password does not have a digit, upper case, or lower case
                    else
                    {
                        //print error
                        errorMessage.setText("<html>Password must contain each of the following:<br>\u2022 At least one upper case character<br>\u2022 At least lower upper case character<br>\u2022 At least one digit</html>");
                        errorMessage.setVisible(true);
                        //password error
                        password.setBackground(error);
                        passwordVerification.setBackground(error);
                        //password is bad
                        passGood = false;
                    }
                }
                //if the password is not between 8 and 10 characters
                else
                {
                    //print length error
                    errorMessage.setText("Password must be between 8-10 characters");
                    errorMessage.setVisible(true);
                    //password error
                    password.setBackground(error);
                    passwordVerification.setBackground(error);
                    //password is bad
                    passGood = false;
                }
            }
            //if passwords dont match
            else
            {
                //print password error
                errorMessage.setText("Passwords must match");
                errorMessage.setVisible(true);
                //password error
                password.setBackground(error);
                passwordVerification.setBackground(error);
                //passwords are bad
                passGood = false;
            }

            //if the passwords match, are 8-10 characters, contain a digit, upper case, and lower case, and do not contain the first and last names
            if(passwordMatch == true &&
                    containsFirstName == false &&
                    containsLastName == false &&
                    containsUpper == true &&
                    containsLower == true &&
                    containsDigit == true &&
                    lengthRequirement == true)
            {
                //set the password good to true
                passGood = true;
            }
            
            //if the password is good, the first name good, last name good, balance good, and username good
            if(passGood == true &&
                    firstGood == true &&
                    lastGood == true &&
                    userGood == true &&
                    balGood == true)
            {
                //check to see if the account requires interest
            if(aT.equals(Account.AccountType.Saving) || aT.equals(Account.AccountType.InterestBearingChecking))
            {
                //initialize interest valid
                boolean interestValid;
                do
                {
                    //reset interest valid for iterations of the do-while
                    interestValid = true;
                    //initialize a flag that checks if the interest is a number
                    boolean interestDigit = true;

                    //get the interest string
                    String interestString = JOptionPane.showInputDialog("Please enter a non-negative interest percent (ex. 5% = 0.05");
                    //try to parse the interest
                    try
                    {
                        //parse the interest
                        interest = Double.parseDouble(interestString);
                    }
                    //if there is an exception
                    catch(Exception e)
                    {
                        //print error message
                        JOptionPane.showMessageDialog(null, "Interest percentage must be a number.");
                        //interest is not a digit or valid
                        interestDigit = false;
                        interestValid = false;
                    }
                    //if interest was able to be parsed and is a digit check if it is positive
                    if(interestDigit == true)
                    {
                        //if interest is negative
                        if(interest < 0)
                        {
                            //set valid interest to false because it cannot be a negative interest
                            interestValid = false;
                        }
                    }
                //continue this as long as interest is in valid
                }while(interestValid == false);
            }
                
                //add the new customer to the records
                addCustomerAdd(first, last, user, pass, acc, aT, bal, interest);
                
                //clera the form
                firstName.setText("");
                lastName.setText("");
                userName.setText("");
                password.setText("");
                passwordVerification.setText("");
                balance.setText("");
            }
        }
    }
    
    /**
     * Receives the Customer's information and checks to see if there is any existing customer with the same first name, last name, account ID, user name and password.
     * If there is the account will be added to the existing customer, if not a new customer will be created.
     * If for any reason the customer account type matches the existing customer's account types, an account will not be created.
     * Furthermore if a customer with a different password tries to upload an account with an existing username of another customer the program will not allow it.
     * 
     * @param first The Customer's first name
     * @param last The Customer's last name
     * @param user The Customer's username
     * @param pass The Customer's password
     * @param acc The Customer's account ID
     * @param aT The Customer's account type
     * @param bal The Customer's balance
     * @param interest The Customer's interest rate
     */
    public void addCustomerUpload(String first, String last, String user, String pass, double acc, Account.AccountType aT, double bal, double interest)
    {
        //create a temporary customer
        Customer tempCustomer = new Customer(first, last, user, pass, acc, aT, bal, interest);
        //if there is no custmoers on record create the first one
        if(list.size() == 0)
        {
            //add the customer to the list
            list.add(tempCustomer);
        }
        //if there are already customers in the records
        else if(list.size() > 0)
        {
            //set flags to see if customer already exists or the customer's account already exists
            boolean customerExists = false;
            boolean accountExists = false;
            
            //search through the entire record of customers
            for(int i = 0; i < list.size(); i++)
            {
                //if the customer matches the first name, last name, username, password, and account ID
                if(list.get(i).equals(first, last, user, pass, acc))
                {
                    //record the index of matching customer
                    int existingCustomerIndex = i;
                    
                    //customer exist is true
                    customerExists = true;
                    
                    //close the for loop
                    i = list.size();
                    
                    //search the existing customer to see if the account already exists
                    for(int a = 0; a < list.get(existingCustomerIndex).getAccounts().size(); a++)
                    {
                        //if the account type is the same
                        if(list.get(existingCustomerIndex).getAccounts().get(a).equals(new Account(aT, bal, interest)))
                        {
                            //account exists is true
                            accountExists = true;
                            //print existing accoun tmessage
                            JOptionPane.showMessageDialog(null, "Account already exists:\n" + new Customer(first, last, user, pass, acc, aT, bal, interest).toStringAccountExists());
                            //close the for loop
                            a = list.get(existingCustomerIndex).getAccounts().size();
                        }
                    }
                    //if the account is not already on file
                    if(accountExists == false)
                    {
                        //add the account to the existing customer
                        list.get(existingCustomerIndex).addAccount(aT, bal, interest);
                    }
                }
            }
            //if the customer does not already exist
            if(customerExists == false)
            {
                //add the new customer to the records
                list.add(tempCustomer);
            }
        }
    }
    
    /**
     * Receives the customer's information and checks to see if there are any digits in either names. 
     * The it checks to see if there is a customer with the same username. 
     * Then it checks to see if the passwords are the same, are between 8 - 10 characters, do not contain the first or last name, contain a digit, uppercase, and lower case.
     * Then checks the account type to see if there needs to be interest, and finally checks to see if balance is negative.
     * If for any reason any of these flags are thrown a new customer will not be created otherwise it checks to see if there is already and existing customer by checking every info except account ID.
     * If there is an existing customer the account will be added to the Customer's records otherwise a new customer will be created with the next avaliable Account ID.
     * This method only differs from addCustomerUpload because it does not check to see if the Customer's Account ID matches any existing Customer's Account ID.
     * 
     * @param first The Customer's first name
     * @param last The Customer's last name
     * @param user The Customer's username
     * @param pass The Customer's password
     * @param acc The Customer's account ID
     * @param aT The Customer's account type
     * @param bal The Customer's balance
     * @param interest The Customer's interest rate
     */
    public void addCustomerAdd(String first, String last, String user, String pass, double acc, Account.AccountType aT, double bal, double interest)
    {
        //create a temporary customer
        Customer tempCustomer = new Customer(first, last, user, pass, acc, aT, bal, interest);
        //if there is no custmoers on record create the first one
        if(list.size() == 0)
        {
            //add the customer to the list
            list.add(tempCustomer);
        }
        //if there are already customers in the records
        else if(list.size() > 0)
        {
            //set flags to see if customer already exists or the customer's account already exists
            boolean customerExists = false;
            boolean accountExists = false;
            
            //search through the entire record of customers
            for(int i = 0; i < list.size(); i++)
            {
                //if the customer matches the first name, last name, username, password, not account ID
                if(list.get(i).equals(first, last, user, pass))
                {
                    //record the index of matching customer
                    int existingCustomerIndex = i;
                    
                    //customer exist is true
                    customerExists = true;
                    
                    //close the for loop
                    i = list.size();
                    
                    //search the existing customer to see if the account already exists
                    for(int a = 0; a < list.get(existingCustomerIndex).getAccounts().size(); a++)
                    {
                        //if the account type is the same
                        if(list.get(existingCustomerIndex).getAccounts().get(a).equals(new Account(aT, bal, interest)))
                        {
                            //account exists is true
                            accountExists = true;
                            //print existing accoun tmessage
                            JOptionPane.showMessageDialog(null, "Account already exists (Line " + lineCount + "):\n" + new Customer(first, last, user, pass, acc, aT, bal, interest).toStringAccountExists());
                            //close the for loop
                            a = list.get(existingCustomerIndex).getAccounts().size();
                        }
                    }
                    //if the account is not already on file
                    if(accountExists == false)
                    {
                        //add the account to the existing customer
                        list.get(existingCustomerIndex).addAccount(aT, bal, interest);
                        
                        //print out message that the customer already exists, but the account has been added to the existing customer
                        JOptionPane.showMessageDialog(null, "Customer Already Exists.\nAccount added to customer:\n" + new Customer(first, last, user, pass, acc, aT, bal, interest).toStringInfoExists());
                    }
                }
            }
            //if the customer does not already exist
            if(customerExists == false)
            {
                //add the new customer to the records
                list.add(tempCustomer);
                //print out new customer creation success message
                JOptionPane.showMessageDialog(null, "New account sucesfully created:\n" + new Customer(first, last, user, pass, acc, aT, bal, interest).toStringAccountExists());
            }
        }
    }
    
    /**
     * Reads in a file path sent from the user.
     * The method then sorts the Customer records by smallest Account ID to largest Account ID.
     * The method then prints out all existing valid customer records to the path provided in an organized sorted manner.
     * 
     * @throws IOException
     */
    public void downloadRecords() throws IOException
    {
        //reset textfield
        
        downloadPath.setBackground(Color.WHITE);
        //get the download path
        String downPath = downloadPath.getText();
        
        
        //check to see if the user entered anything
        if(downPath.compareToIgnoreCase("") != 0)
        {
            //initialize filewriter and printwriter
            FileWriter fw = null;
            PrintWriter p = null;
            boolean error = false;
            try
            {
                //create the new file
                fw = new FileWriter(downPath, false);
                //create the printwriter to the file
                p = new PrintWriter(fw);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "File \"" + downPath + "\" is invalid");
                error = true;
            }

            
            //sort the records from smallest account ID to largest
            Collections.sort(list);

            //for the entire record of customers
            for(Customer cus : list)
            {
                //print out the customer info
                p.println(cus.toStringPrintWriter());
            }

            //if no problems
            if(error == false)
            {
                //print success download message
                JOptionPane.showMessageDialog(null, "Customer records have been downloaded to \"" + downPath + "\" successfully");
            }

            //close the printwriter
            p.close();

            //clear the download path
            downloadPath.setText("");
        }
        //if the user entered nothing make textfield red
        else
        {
            downloadPath.setBackground(Color.decode("#e79cae"));
        }

    }
    
    private class myItemListener implements ItemListener, ActionListener
    {
        //create abstract methods
        
        public void itemStateChanged(ItemEvent e) 
        {
            
        }
        
        /**
         * Listens for any action to be performed.
         * Then handles the action accordingly with a set of if statements.
         * 
         * @param e The ActionEvent performed
         */
        public void actionPerformed(ActionEvent e) 
        {
            //if the upload records button is clicked, create the upload customers window
            if(e.getSource().equals(upload))
            {
                createUploadPanel();
            }
            //if the add customer button is clicked, create the add customers window
            if(e.getSource().equals(add))
            {
                createAddPanel();
            }
            //if the download records button is clicked, create the download customer records window
            if(e.getSource().equals(download))
            {
                createDownloadPanel();
            }
            //if the exit button is clicked, exit the program
            if(e.getSource().equals(exit))
            {
                System.exit(0);
            }
            //if the upload records button submit was clicked, run the upload customers method
            if(e.getSource().equals(submitUpload))
            {
                uploadCustomerFiles();
            }
            //if the add customer button submit was clicked, run the add customers method
            if(e.getSource().equals(submitAdd))
            {
                add();
            }
            //if the download records button submit was clicked, run the download customer's records method
            if(e.getSource().equals(downloadSubmit))
            {

                    // try to run the download customer's records method
                try 
                {
                    downloadRecords();
                } 
                catch (IOException ex) 
                {
                    downloadPath.setBackground(Color.red);
                    Logger.getLogger(BankWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        }
    }
}
